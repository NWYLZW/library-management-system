package com.yijie.libraryManagementSystem.tool;

import com.yijie.libraryManagementSystem.tool.lambda.Procedure;

import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * @desc    ListenerTool.java
 * @author  yijie
 * @date    2021-01-06 18:56
 * @note    2021-01-06 18:56 yijie Created ListenerTool.java file
 */
public class ListenerTool {
    enum Button {
        LEFT_BUTTON(1),
        RIGHT_BUTTON(2);

        public Integer val = null;

        Button(Integer val) {
            this.val = val;
        }
    }

    /**
     * 给组件设置鼠标左键点击事件
     * @param component 组件
     * @param callBack  点击时的回调
     */
    public static ListenerTool setMouseClickWithLeftBtn(
            JComponent component, Procedure callBack
    ) {
        return ListenerTool.setMouseClick(component, null, callBack);
    }

    /**
     * 给组件设置鼠标点击事件
     * @param component 组件
     * @param btns      会被触发的鼠标按键
     * @param callBack  点击时的回调
     */
    public static ListenerTool setMouseClick(
            JComponent component, Button[] btns, Procedure callBack
    ) {
        if (btns == null) {
            btns = new Button[] {Button.LEFT_BUTTON};
        }
        Button[] finalBtns = btns;
        component.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                for (Button btn : finalBtns) {
                    if (btn.val == e.getButton()) {
                        callBack.run();
                        break;
                    }
                }
            }
        });
        return null;
    }
}
