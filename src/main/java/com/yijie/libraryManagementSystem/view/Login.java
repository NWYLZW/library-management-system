package com.yijie.libraryManagementSystem.view;

import com.yijie.libraryManagementSystem.config.AppConfig;
import com.yijie.libraryManagementSystem.tool.FontTool;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * @desc    Login.java
 * @author  yijie
 * @date    2021-01-05 10:50
 * @note    2021-01-05 10:50 yijie Created Login.java file
 */
public class Login {
    private JPanel main;
    private JPanel top;
    private JPanel bottom;
    private JTextField userNameInput;
    private JPasswordField passwordInput;
    private JButton loginBtn;
    private JPanel titlePanel;
    private JLabel close;
    private JLabel min;
    private JLabel icon;
    private JLabel title;
    private JButton register;
    private JLabel user;
    private JLabel pwd;

    interface MinListener {
        public void emit();
    }
    public MinListener minListener = null;

    interface LoginSuccessListener {
        public void emit();
    }
    public LoginSuccessListener loginSuccessListener = null;

    public void created() {
        FontTool.setFont(icon)
                .setText("\ue612");
        FontTool.setFont(close)
                .setText("\uE65E");
        FontTool.setFont(min)
                .setText("\uE6B7");
        FontTool.setFont(user)
                .setText("\ue6fd");
        FontTool.setFont(pwd)
                .setText("\ue6ac");


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
        loginBtn.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                if (loginSuccessListener != null) {
                    loginSuccessListener.emit();
                }
            }
        });
    }

    public void mounted() {
        title.setText(AppConfig.NAME + "[" + AppConfig.VERSION + "]");
    }

    public Login() {
        this.created();
        this.mounted();
    }

    public static void show() {
        JFrame frame = new JFrame("Login");
        Login loginUi = new Login();

        loginUi.minListener = new MinListener() {
            @Override
            public void emit() {
                frame.setExtendedState(JFrame.ICONIFIED);
            }
        };
        loginUi.loginSuccessListener = new Login.LoginSuccessListener() {
            @Override
            public void emit() {
                frame.setVisible(false);
            }
        };
        frame.setContentPane(loginUi.main);
        frame.setUndecorated(true);

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);

        frame.setLocationRelativeTo(null);
    }
}
