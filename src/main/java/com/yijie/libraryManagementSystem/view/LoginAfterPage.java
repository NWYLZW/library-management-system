package com.yijie.libraryManagementSystem.view;

import com.yijie.libraryManagementSystem.config.AppConfig;
import com.yijie.libraryManagementSystem.model.UserModel;
import com.yijie.libraryManagementSystem.tool.FontTool;
import com.yijie.libraryManagementSystem.tool.ListenerTool;
import com.yijie.libraryManagementSystem.tool.WindowTool;

import javax.swing.*;
import java.awt.*;

import javax.swing.*;
import java.awt.*;

public class LoginAfterPage {
    private JPanel main;
    private JTextField search;
    private JPanel bottom;
    private JLabel searchIcon;
    private JLabel icon;
    private JLabel min;
    private JLabel close;
    private JLabel headImage;
    private JLabel username;

    private final UserModel userModel = new UserModel();

    /**
     * 登陆按钮的监听者
     */
    interface LoginSuccessListener {
        public void emit();
    }
    public Login.LoginSuccessListener loginSuccessListener = null;

    @Override
    public JPanel getMain() {
        return main;
    }

    @Override
    public void created() {
        FontTool.setFont(min)
                .setText("\uE6B7");
        FontTool.setFont(close)
                .setText("\uE65E");
        min.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        close.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

        FontTool.setFont(icon)
                .setText("\ue612");
        FontTool.setFont(searchIcon)
                .setText("\ue6d3");

        //title.setText(AppConfig.NAME + "[" + AppConfig.VERSION + "]");
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
        ListenerTool.setMouseClickWithLeftBtn(close, () -> {
            System.exit(0);
        }).setMouseClickWithLeftBtn(min, () -> {
            emitListener(AbsActivity.MinListener.class);
        }).setMouseClickWithLeftBtn(loginBtn, this::login
        ).setMouseClickWithLeftBtn(registerBtn, () -> {
            WindowTool.openFrame("Login", new Register(), (frame, activity) -> {
                activity.registerSuccessListener = () -> frame.setVisible(false);
            }, null, newWindow -> {
                newWindow.setLocationRelativeTo(null);
            });
        });
    }

    public static void show() {
        WindowTool.openFrame("Login", new Login(), (frame, activity) -> {
            activity.loginSuccessListener = () -> {
                WindowTool.openFrame("Chats", new Chats(), null, null, null);
            };
        }, null, newWindow -> {
            newWindow.setLocationRelativeTo(null);
        });
    }
}

