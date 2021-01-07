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

public class LoginAfterPage extends AbsActivity {
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

    public Login.LoginSuccessListener loginSuccessListener = null;


    public JPanel getMain() {
        return main;
    }


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





    public void mounted() {
        ListenerTool.setMouseClickWithLeftBtn(close, () -> {
            System.exit(0);
        }).setMouseClickWithLeftBtn(min, () -> {
            emitListener(AbsActivity.MinListener.class);
        });
    }


}

