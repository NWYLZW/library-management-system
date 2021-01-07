package com.yijie.libraryManagementSystem.view;
import com.yijie.libraryManagementSystem.config.AppConfig;
import com.yijie.libraryManagementSystem.model.UserModel;
import com.yijie.libraryManagementSystem.tool.DateTool;
import com.yijie.libraryManagementSystem.tool.FontTool;
import com.yijie.libraryManagementSystem.tool.ListenerTool;
import com.yijie.libraryManagementSystem.tool.WindowTool;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
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
    ButtonGroup groupPicture = new ButtonGroup();

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
        if (!female.isSelected() && !male.isSelected()) {
            JOptionPane.showMessageDialog(
                    null, "请选择性别！！！", "警告", JOptionPane.WARNING_MESSAGE
            );
        }
        boolean gender = male.isSelected();

        Calendar calendar = Calendar.getInstance();
        calendar.set(
                2021 - yearInput.getSelectedIndex(), monthInput.getSelectedIndex(), dayInput.getSelectedIndex() + 1
        );

        if (userModel.register(
                nicknameInput.getText(), String.valueOf(passwordInput.getPassword())
                , "0", gender, calendar.getTime()
                )) {
            registerSuccessListener.emit();
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

        male.setActionCommand("male");
        female.setActionCommand("female");
        genderGroup.add(female);
        genderGroup.add(male);

        groupPicture.add(choose1);
        groupPicture.add(choose2);
        groupPicture.add(choose3);
        groupPicture.add(choose4);

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
