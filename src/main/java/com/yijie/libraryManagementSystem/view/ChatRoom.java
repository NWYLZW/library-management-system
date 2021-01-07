package com.yijie.libraryManagementSystem.view;

import javax.swing.*;

public class ChatRoom {
    private JPanel panel1;
    private JTextField textField1;
    private JButton button1;

    public static void main(String[] args) {
        JFrame frame = new JFrame("聊天室");
        frame.setContentPane(new ChatRoom().panel1);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }
}
