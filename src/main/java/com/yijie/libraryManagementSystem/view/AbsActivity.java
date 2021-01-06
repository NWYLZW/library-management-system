package com.yijie.libraryManagementSystem.view;

import com.yijie.libraryManagementSystem.view.itf.ItfActivity;

import javax.swing.*;

/**
 * @desc    absActivity.java
 * @author  yijie
 * @date    2021-01-06 16:17
 * @note    2021-01-06 16:17 yijie Created absActivity.java file
 */
public abstract class AbsActivity implements ItfActivity {
    public enum LifeStage {
        CREATED(),
        MOUNTED()
    }
    LifeStage stage = null;

    public AbsActivity load() {
        stage = LifeStage.CREATED;
        created();
        stage = LifeStage.MOUNTED;
        mounted();
        return this;
    }

    /**
     * 最小化按钮的监听者
     */
    public interface MinListener {
        public void emit();
    }
    private MinListener minListener = null;

    /**
     * listener抛出
     * @param clazz Listener.class
     */
    public void emitListener(Class clazz) {
        if (clazz == MinListener.class) {
            minListener.emit();
        }
    }

    public void setMinListener(MinListener minListener) {
        this.minListener = minListener;
    }

    public LifeStage currentStage() {
        return stage;
    }
}
