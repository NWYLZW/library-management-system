package com.yijie.libraryManagementSystem.tool.internet;

import com.google.gson.Gson;
import com.yijie.libraryManagementSystem.entity.Message;
import com.yijie.libraryManagementSystem.tool.UserTool;
import lombok.Getter;
import lombok.Setter;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketTimeoutException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;

/**
 * @desc    TcpSocketTool.java
 * @author  yijie
 * @date    2021-01-07 08:33
 * @note    2021-01-07 08:33 yijie Created TcpSocketTool.java file
 */
public class TcpSocketTool {
    public static final int PORT = 31535;

    static class Chat implements Runnable {
        @Getter @Setter
        private Socket client = null;
        public Chat(Socket client){
            this.client = client;
        }

        interface Receiver {
            Message receive(Message response);
        }
        @Setter
        private Receiver receiver;

        @Override
        public void run() {
            try {
                InputStreamReader serverInputStream = new InputStreamReader(
                        client.getInputStream()
                );
                PrintStream serverPrintStream = new PrintStream(client.getOutputStream());
                while(true) {
                    String str = new BufferedReader(serverInputStream).readLine();
                    if (str == null) break;

                    Message msg = new Gson().fromJson(str, Message.class);
                    if (msg.getCode() == Message.Type.CLOSE.val) break;

                    serverPrintStream.println(
                            receiver == null?Message.structureJson(
                                    UserTool.curUser(), "", Message.Type.SUCCESS
                            ) : new Gson().toJson(receiver.receive(msg))
                    );
                }
                serverPrintStream.close();
                client.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    static class Server implements Runnable {
        private ServerSocket server = null;
        private static final List<Chat> chats = new ArrayList<>();

        public Server() throws IOException {
            this.server = new ServerSocket(TcpSocketTool.PORT);
        }

        @Override
        public void run() {
            try {
                while(true) {
                    Chat chat = new Chat(server.accept());
                    chats.add(chat);
                    new Thread(chat).start();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        public static Server create() {
            Server server = null;
            try {
                server = new Server();
                new Thread(server).start();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return server;
        }
    }
    private static Server server = null;

    private static final Map<String, Chat> chatMap = new HashMap<>();
    public static void connectChat(String url) {
        if (server == null) server = Server.create();
        try {
            Socket client = new Socket(url, TcpSocketTool.PORT);
            Chat chat = new Chat(client);
            client.setSoTimeout(10000);

            chatMap.put(url, chat);
            new Thread(chat).start();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void sendMessage(
            String url, String message, Message.Type type, Function<Message, Message> callBack
    ){
        try {
            if (!chatMap.containsKey(url)) connectChat(url);
            Socket client = chatMap.get(url).getClient();

            // 向服务器发送数据
            String sendMsg = Message.structureJson(UserTool.curUser(), message, type);
            new PrintStream(
                    client.getOutputStream()
            ).println(sendMsg);

            try {
                callBack.apply(new Gson().fromJson(
                        new BufferedReader(
                                new InputStreamReader(client.getInputStream())
                        ).readLine(), Message.class
                ));
            } catch(SocketTimeoutException e) {
                System.out.println("Time out, No response");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void sendMessage(
            String url, String message, Function<Message, Message> callBack
    ) {
        TcpSocketTool.sendMessage(url, message, Message.Type.SUCCESS, callBack);
    }

    public static void sendMessage(
            String url, String message
    ) {
        TcpSocketTool.sendMessage(url, message, Message.Type.SUCCESS, null);
    }

    public static void close(String url) {
        TcpSocketTool.sendMessage(url, "", Message.Type.CLOSE, null);
    }

    public static void hello(String url) {
        TcpSocketTool.sendMessage(url, "", Message.Type.HELLO, null);
    }
}
