package com.yijie.libraryManagementSystem.tool.internet;

import com.google.gson.Gson;
import com.yijie.libraryManagementSystem.entity.Message;
import com.yijie.libraryManagementSystem.entity.User;
import com.yijie.libraryManagementSystem.tool.UserTool;
import lombok.Setter;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketTimeoutException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @desc    TcpSocketTool.java
 * @author  yijie
 * @date    2021-01-07 08:33
 * @note    2021-01-07 08:33 yijie Created TcpSocketTool.java file
 */
public class TcpSocketTool {
    public static final int PORT = 31535;

    static class Chat implements Runnable {
        private Socket client = null;
        public Chat(Socket client){
            this.client = client;
        }

        interface Receiver {
            void getMessage(Message msg);
        }
        @Setter
        private Receiver receiver;

        @Override
        public void run() {
            try {
                while(true) {
                    String str = new BufferedReader(new InputStreamReader(
                            client.getInputStream()
                    )).readLine();
                    Message msg = new Gson().fromJson(str, Message.class);
                    if (msg.getCode() == 404) break;

                    if (receiver != null) {
                        receiver.getMessage(msg);
                    } else {
                        PrintStream printStream = new PrintStream(client.getOutputStream());
                        printStream.print(Message.structureJson(
                                UserTool.curUser(), "", Message.Type.SUCCESS
                        ));
                        printStream.close();
                    }
                }
                client.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
    public static final List<Chat> chats = new ArrayList<>();

    static class Server implements Runnable {
        private ServerSocket server = null;
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
    }
    private static Server server;

    public static void createServer() {
        try {
            server = new Server();
            new Thread(server).start();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static Message sendMessage(
            String url, String message
    ) {
        return TcpSocketTool.sendMessage(url, message, Message.Type.SUCCESS);
    }

    public static Message close(String url) {
        return TcpSocketTool.sendMessage(url, "", Message.Type.CLOSE);
    }

    public static Message sendMessage(
            String url, String message, Message.Type type
    ){
        Message msg = null;
        try (Socket client = new Socket(url, TcpSocketTool.PORT)) {
            client.setSoTimeout(10000);

            // 向服务器发送数据
            new PrintStream(
                    client.getOutputStream()
            ).println(Message.structureJson(
                    UserTool.curUser(), message, Message.Type.SUCCESS
            ));

            try {
                msg = new Gson().fromJson(
                        new BufferedReader(
                                new InputStreamReader(client.getInputStream())
                        ).readLine(), Message.class
                );
            } catch(SocketTimeoutException e) {
                System.out.println("Time out, No response");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return msg;
    }
}
