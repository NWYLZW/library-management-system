package com.yijie.libraryManagementSystem.view;

import com.yijie.libraryManagementSystem.config.AppConfig;
import com.yijie.libraryManagementSystem.model.UserModel;
import com.yijie.libraryManagementSystem.tool.FontTool;
import com.yijie.libraryManagementSystem.tool.ListenerTool;

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
public class Login extends AbsActivity {
    private JPanel main;
    private JPanel top;
    private JPanel bottom;
    private JTextField ppNumInput;
    private JPasswordField passwordInput;
    private JButton loginBtn;
    private JPanel titlePanel;
    private JLabel close;
    private JLabel min;
    private JLabel icon;
    private JLabel title;
    private JButton registerBtn;
    private JLabel user;
    private JLabel pwd;

    private final UserModel userModel = new UserModel();

    /**
     * 登陆按钮的监听者
     */
    interface LoginSuccessListener {
        public void emit();
    }
    public Login.LoginSuccessListener loginSuccessListener = null;

    @Override
    public void created() {
        FontTool.setFont(icon)
                .setText("\ue612");
        FontTool.setFont(close)
                .setText("\uE65E");
        min.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        close.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

        FontTool.setFont(min)
                .setText("\uE6B7");
        FontTool.setFont(user)
                .setText("\ue6fd");
        FontTool.setFont(pwd)
                .setText("\ue6ac");

        title.setText(AppConfig.NAME + "[" + AppConfig.VERSION + "]");
    }

    public void login() {
        if (userModel.login(
                ppNumInput.getText(), String.valueOf(passwordInput.getPassword())
        )) {
            if (loginSuccessListener != null) {
                loginSuccessListener.emit();
            }
        } else {
            JOptionPane.showMessageDialog(
                    null, "登陆失败！！！", "警告", JOptionPane.WARNING_MESSAGE
            );
        }
    }

    @Override
    public void mounted() {
        ListenerTool
                .setMouseClickWithLeftBtn(loginBtn, () -> System.exit(0))
                .setMouseClickWithLeftBtn(loginBtn, () -> emitListener(MinListener.class))
                .setMouseClickWithLeftBtn(loginBtn, this::login)
                .setMouseClickWithLeftBtn(registerBtn, () -> System.out.println(1));
    }

    public static void show() {
        JFrame frame = new JFrame("Login");
        Login loginUi = (Login) new Login().load();

        loginUi.setMinListener(() -> frame.setExtendedState(JFrame.ICONIFIED));
        loginUi.loginSuccessListener = () -> frame.setVisible(false);

        frame.setContentPane(loginUi.main);
        frame.setUndecorated(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);

        frame.setLocationRelativeTo(null);
    }
}
