package com.yijie.libraryManagementSystem.tool;

import com.yijie.libraryManagementSystem.view.AbsActivity;

import javax.swing.*;
import java.util.Stack;
import java.util.function.BiConsumer;
import java.util.function.Consumer;

/**
 * @desc    WindowTool.java
 * @author  yijie
 * @date    2021-01-06 20:24
 * @note    2021-01-06 20:24 yijie Created WindowTool.java file
 */
public class WindowTool {
    private static final Stack<JFrame> frameStack = new Stack<>();

    private static <T> void runConsumer(Consumer<T> consumer, T arg) {
        if (consumer != null) consumer.accept(arg);
    }

    private static <T, U> void runBiConsumer(BiConsumer<T, U> consumer, T arg0, U arg1) {
        if (consumer != null) consumer.accept(arg0, arg1);
    }

    /**
     * 打开一个新的Frame窗口
     * @param frameTitle    窗口标题
     * @param activity      活动名
     * @param dealActivity  处理活动的提供者
     * @param dealCurWindow 处理当前窗口
     * @param dealNewWindow 处理要新打开的窗口
     * @param <T>           活动范型类
     * @return 返回活动
     */
    public static <T extends AbsActivity> T openFrame(
            String frameTitle
            , T activity
            , BiConsumer<JFrame, T> dealActivity
            , Consumer<JFrame> dealCurWindow, Consumer<JFrame> dealNewWindow
    ) {
        if (!frameStack.isEmpty()) {
            JFrame top = frameStack.peek();
            top.setVisible(false);
            runConsumer(dealCurWindow, top);
        }

        JFrame newFrame = new JFrame(frameTitle);
        activity.load();
        activity.setMinListener(() -> newFrame.setExtendedState(JFrame.ICONIFIED));
        runBiConsumer(dealActivity, newFrame, activity);

        newFrame.setContentPane(activity.getMain());
        newFrame.setUndecorated(true);
        newFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        newFrame.pack();
        newFrame.setVisible(true);

        frameStack.push(newFrame);
        runConsumer(dealNewWindow, newFrame);
        return activity;
    }

    public static void closeFrame() {
        if (!frameStack.isEmpty()) {
            frameStack.pop().setVisible(false);
        }
        if (!frameStack.isEmpty()) {
            frameStack.peek().setVisible(true);
        }
    }
}
