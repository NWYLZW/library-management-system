package com.yijie.libraryManagementSystem.view;
import com.yijie.libraryManagementSystem.config.AppConfig;
import com.yijie.libraryManagementSystem.tool.FontTool;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.*;

public class register {

    private JPanel main;
    private JTextField nickname;
    private JPasswordField passwordField1;
    private JRadioButton female;
    private JRadioButton male;
    private JComboBox year;
    private JComboBox day;
    private JComboBox month;
    private JPanel left;
    private JPanel right;
    private JButton registerButton;
    private JLabel registerpassword;
    private JLabel registergender;
    private JLabel registerbirthday;
    private JLabel title;
    private JPanel titlePanel;
    private JLabel close;
    private JLabel min;
    private JLabel icon;
    private JLabel registerusername;

    //
    interface MinListener {
        public void emit();
    }
    public register.MinListener minListener = null;

    interface registerSuccessListener {
        public void emit();
    }
    public register.registerSuccessListener registerSuccessListener = null;

    public void created() {

        FontTool.setFont(close)
                .setText("\uE65E");
        FontTool.setFont(min)
                .setText("\uE6B7");


        min.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        close.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

        close.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                System.exit(0);
            }
        });
        min.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                if (minListener != null) {
                    minListener.emit();
                }
            }
        });
        registerButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                if (registerSuccessListener != null) {
                    registerSuccessListener.emit();
                }
            }
        });
    }

    public void mounted() {
        title.setText(AppConfig.NAME + "[" + AppConfig.VERSION + "]");
    }

    public register() {
        this.created();
        this.mounted();
    }

    public static void show() {
        JFrame frame = new JFrame("Register");
        register registerUi=new register();

        registerUi.minListener = new MinListener() {
            @Override
            public void emit() {
                frame.setExtendedState(JFrame.ICONIFIED);
            }
        };
        registerUi.registerSuccessListener = new register.registerSuccessListener() {
            @Override
            public void emit() {
                frame.setVisible(false);
            }
        };
        frame.setContentPane(registerUi.main);
        frame.setUndecorated(true);

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);

        frame.setLocationRelativeTo(null);
    }

}
