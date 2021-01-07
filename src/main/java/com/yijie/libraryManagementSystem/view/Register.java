package com.yijie.libraryManagementSystem.view;
import com.yijie.libraryManagementSystem.config.AppConfig;
import com.yijie.libraryManagementSystem.model.UserModel;
import com.yijie.libraryManagementSystem.tool.DateTool;
import com.yijie.libraryManagementSystem.tool.FontTool;
import com.yijie.libraryManagementSystem.tool.ListenerTool;
import com.yijie.libraryManagementSystem.tool.WindowTool;

import java.awt.*;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;
import java.util.function.BiConsumer;
import java.util.function.Function;
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
    private JRadioButton choose1;
    private JRadioButton choose2;
    private JRadioButton choose3;
    private JRadioButton choose4;
    private JPanel bottom;
    private JLabel picture1;
    private JLabel picture2;
    private JLabel picture3;
    private JLabel picture4;
    private JPanel pictures;

    ButtonGroup genderGroup = new ButtonGroup();
    ButtonGroup avatarGroup = new ButtonGroup();

    interface registerSuccessListener {
        public void emit();
    }
    public Register.registerSuccessListener registerSuccessListener = null;

    @Override
    public JPanel getMain() {
        return main;
    }

    UserModel userModel = new UserModel();
    public void register() {
        if (nicknameInput.getText().equals("")) {
            JOptionPane.showMessageDialog(
                    null, "请输入昵称！！！", "警告", JOptionPane.WARNING_MESSAGE
            );
            return;
        }
        if (passwordInput.getText().equals("")) {
            JOptionPane.showMessageDialog(
                    null, "请输入密码！！！", "警告", JOptionPane.WARNING_MESSAGE
            );
            return;
        }
        if (!female.isSelected() && !male.isSelected()) {
            JOptionPane.showMessageDialog(
                    null, "请选择性别！！！", "警告", JOptionPane.WARNING_MESSAGE
            );
            return;
        }
        if (avatarGroup.getSelection() == null) {
            JOptionPane.showMessageDialog(
                    null, "请选择头像！！！", "警告", JOptionPane.WARNING_MESSAGE
            );
            return;
        }

        boolean gender = male.isSelected();
        Calendar calendar = Calendar.getInstance();
        calendar.set(
                2021 - yearInput.getSelectedIndex(), monthInput.getSelectedIndex(), dayInput.getSelectedIndex() + 1
        );

        if (userModel.register(
                nicknameInput.getText(), String.valueOf(passwordInput.getPassword())
                , avatarGroup.getSelection().getActionCommand(), gender, calendar.getTime()
                )) {
            if (registerSuccessListener != null) {
                registerSuccessListener.emit();
            }
        } else {
            JOptionPane.showMessageDialog(
                    null, "注册失败！！！", "警告", JOptionPane.WARNING_MESSAGE
            );
        }
    }

    public void created() {
        FontTool.setFont(min)
                .setText("\uE6B7");
        FontTool.setFont(back)
                .setText("\uE636");
        FontTool.setFont(close)
                .setText("\uE65E");
        FontTool.setFont(name)
                .setText("\ue74c");
        FontTool.setFont(pwd)
                .setText("\ue620");
        FontTool.setFont(gender)
                .setText("\ue61e");
        FontTool.setFont(birthday)
                .setText("\ue74d");
        min.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        back.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        close.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

        FontTool.setFont(icon)
                .setText("\ue612");
        for(int i=2021;i>1980;i--){
            yearInput.addItem(i);
        }
        for(int i=1;i<=12;i++){
            monthInput.addItem(i);
        }
        for(int i=1;i<=31;i++){
            dayInput.addItem(i);
        }

        Map<String, JRadioButton> radioButtonMap = new HashMap<>();
        radioButtonMap.put("male", male);
        radioButtonMap.put("female", female);

        Function<ButtonGroup, BiConsumer<String, JRadioButton>> createSetAction = (group) -> (key, btn) -> {
            btn.setActionCommand(key);
            group.add(btn);
        };
        radioButtonMap.forEach(createSetAction.apply(genderGroup));

        radioButtonMap.clear();
        radioButtonMap.put("1", choose1);
        radioButtonMap.put("2", choose2);
        radioButtonMap.put("3", choose3);
        radioButtonMap.put("4", choose4);

        radioButtonMap.forEach(createSetAction.apply(avatarGroup));

        JLabel[] pictures = new JLabel[] {
                picture1, picture2, picture3, picture4
        };
        for (int i = 0; i < pictures.length; i++) {
            JLabel picture = pictures[i];
            picture.setIcon(new ImageIcon(getClass().getResource("/avatar/00" + (i + 1) + ".jpg")));
        }

        title.setText(AppConfig.NAME + "[" + AppConfig.VERSION + "]");
    }

    public void mounted() {
        ListenerTool.setMouseClickWithLeftBtn(close, () -> {
            System.exit(0);
        }).setMouseClickWithLeftBtn(back, WindowTool::closeFrame
        ).setMouseClickWithLeftBtn(min, () -> {
            emitListener(AbsActivity.MinListener.class);
        }).setMouseClickWithLeftBtn(registerButton, this::register);

        yearInput.addActionListener(e -> {
            JComboBox cb=(JComboBox) e.getSource();
            Object newItem=cb.getSelectedItem();
            int days = DateTool.getDayByYearAndMonth(
                    Integer.parseInt(newItem.toString())
                    , Integer.parseInt(monthInput.getSelectedItem().toString())
            );
            dayInput.removeAllItems();
            for(int i=1;i<days;i++)
                dayInput.addItem(i);
        });

        monthInput.addActionListener(e -> {
            JComboBox cb = (JComboBox) e.getSource();
            Object newItem = cb.getSelectedItem();
            int days = DateTool.getDayByYearAndMonth(
                    Integer.parseInt(newItem.toString())
                    , Integer.parseInt(monthInput.getSelectedItem().toString())
            );
            dayInput.removeAllItems();
            for(int i=1;i<days;i++) {
                dayInput.addItem(i);
            }
        });
    }
}
