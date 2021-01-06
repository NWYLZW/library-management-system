package com.yijie.libraryManagementSystem.view;

import com.yijie.libraryManagementSystem.view.itf.ItfActivity;

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

    /**
     * 最小化按钮的监听者
     */
    interface MinListener {
        public void emit();
    }
    private Login.MinListener minListener = null;

    /**
     * listener抛出
     * @param clazz Listener.class
     */
    public void emitListener(Class clazz) {
        if (clazz == MinListener.class) {
            minListener.emit();
        }
    }

    public void setMinListener(Login.MinListener minListener) {
        this.minListener = minListener;
    }

    public AbsActivity load() {
        stage = LifeStage.CREATED;
        created();
        stage = LifeStage.MOUNTED;
        mounted();
        return this;
    }

    public LifeStage currentStage() {
        return stage;
    }
}
