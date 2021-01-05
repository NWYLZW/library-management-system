package com.yijie.libraryManagementSystem.view;

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
    private JTextField passwordInput;
    private JButton loginBtn;
    private JPanel title;
    private JLabel close;
    private JLabel min;

    interface MinListener {
        public void emit();
    }
    public MinListener minListener = null;

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
    }

    public Login() {
        this.created();
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
        frame.setContentPane(loginUi.main);
        frame.setUndecorated(true);

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);

        Dimension screenSize = Toolkit.getDefaultToolkit()
                .getScreenSize();
        frame.setLocation(
                (screenSize.width  - frame.getWidth() )/2,
                (screenSize.height - frame.getHeight())/2
        );
    }
}
