package com.yijie.libraryManagementSystem.view;

import com.yijie.libraryManagementSystem.config.AppConfig;
import com.yijie.libraryManagementSystem.tool.FontTool;
import com.yijie.libraryManagementSystem.tool.ListenerTool;
import com.yijie.libraryManagementSystem.tool.WindowTool;

import javax.swing.*;
import java.awt.*;

/**
 * @desc    Chats.java
 * @author  yijie
 * @date    2021-01-06 22:10
 * @note    2021-01-06 22:10 yijie Created the Chats.java file
 */
public class Chats extends AbsActivity {
    private JPanel main;
    private JPanel top;
    private JPanel titlePanel;
    private JLabel title;
    private JTextField searchText;
    private JLabel headImage;
    private JLabel loginName;
    private JLabel searchIcon;
    private JLabel icon;
    private JLabel min;
    private JLabel close;
    private JTextArea view;

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

        title.setText(AppConfig.NAME + "[" + AppConfig.VERSION + "]");
    }

    @Override
    public void mounted() {
        ListenerTool.setMouseClickWithLeftBtn(close, () -> {
            System.exit(0);
        }).setMouseClickWithLeftBtn(min, () -> {
            emitListener(MinListener.class);
        });
    }
}
