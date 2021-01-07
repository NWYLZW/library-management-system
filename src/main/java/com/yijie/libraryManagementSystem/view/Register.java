package com.yijie.libraryManagementSystem.view;
import com.yijie.libraryManagementSystem.config.AppConfig;
import com.yijie.libraryManagementSystem.tool.FontTool;
import com.yijie.libraryManagementSystem.tool.ListenerTool;
import com.yijie.libraryManagementSystem.tool.WindowTool;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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
    public  int getDayByYearAndMonth(int year,int month) {
        if (month == 2)
            if ((year%4==0 && year%100!=0) || (year%100==0 && year%400==0))
                return 29;
            else return 28;
        else if (month==1 || month==3 || month==5 || month==7 || month==8 || month==10 || month==12)
            return 31;
        else return 30;
    }


        public void created() {
        System.out.println(min);
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

        ButtonGroup group = new ButtonGroup();
        group.add(female);
        group.add(male);

        title.setText(AppConfig.NAME + "[" + AppConfig.VERSION + "]");
    }

    public void mounted() {
        ListenerTool.setMouseClickWithLeftBtn(close, () -> {
            System.exit(0);
        }).setMouseClickWithLeftBtn(back, WindowTool::closeFrame
        ).setMouseClickWithLeftBtn(min, () -> {
            emitListener(AbsActivity.MinListener.class);
        }).setMouseClickWithLeftBtn(registerButton, this::register);

        yearInput.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JComboBox cb=(JComboBox) e.getSource();
                Object newItem=cb.getSelectedItem();
                int days=getDayByYearAndMonth(Integer.parseInt(newItem.toString()),
                        Integer.parseInt(monthInput.getSelectedItem().toString()));
                dayInput.removeAllItems();
                for(int i=1;i<days;i++)
                    dayInput.addItem(i);
            }
        });

        monthInput.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JComboBox cb=(JComboBox) e.getSource();
                Object newItem=cb.getSelectedItem();
                int days=getDayByYearAndMonth(Integer.parseInt(yearInput.getSelectedItem().toString()),
                        Integer.parseInt(newItem.toString()));
                dayInput.removeAllItems();
                for(int i=1;i<days;i++)
                    dayInput.addItem(i);
            }
        });
    }


}
