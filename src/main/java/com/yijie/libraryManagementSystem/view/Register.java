package com.yijie.libraryManagementSystem.view;
import com.yijie.libraryManagementSystem.config.AppConfig;
import com.yijie.libraryManagementSystem.tool.FontTool;
import com.yijie.libraryManagementSystem.tool.ListenerTool;
import com.yijie.libraryManagementSystem.tool.WindowTool;

import java.awt.*;
import javax.swing.*;

public class Register extends AbsActivity {

    private JPanel main;
    private JTextField nicknameInput;
    private JPasswordField passwordInput;
    private JRadioButton female;
    private JRadioButton male;
    private JComboBox yearInput;
    private JComboBox dayInput;
    private JComboBox monthInput;
    private JPanel left;
    private JPanel right;
    private JButton registerButton;
    private JLabel pwd;
    private JLabel gender;
    private JLabel birthday;
    private JLabel title;
    private JPanel titlePanel;
    private JLabel close;
    private JLabel min;
    private JLabel icon;
    private JLabel name;
    private JLabel back;

    interface registerSuccessListener {
        public void emit();
    }
    public Register.registerSuccessListener registerSuccessListener = null;

    @Override
    public JPanel getMain() {
        return main;
    }

    public void register() {
        registerSuccessListener.emit();
    }

    public void created() {
        FontTool.setFont(min)
                .setText("\uE6B7");
        FontTool.setFont(back)
                .setText("\uE636");
        FontTool.setFont(close)
                .setText("\uE65E");
        min.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        back.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        close.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

        FontTool.setFont(icon)
                .setText("\ue612");

        title.setText(AppConfig.NAME + "[" + AppConfig.VERSION + "]");
    }

    public void mounted() {
        ListenerTool.setMouseClickWithLeftBtn(close, () -> {
            System.exit(0);
        }).setMouseClickWithLeftBtn(back, WindowTool::closeFrame
        ).setMouseClickWithLeftBtn(min, () -> {
            emitListener(AbsActivity.MinListener.class);
        }).setMouseClickWithLeftBtn(registerButton, this::register);
    }
}
