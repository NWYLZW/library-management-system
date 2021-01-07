package com.yijie.libraryManagementSystem;

import com.yijie.libraryManagementSystem.model.UserModel;
import org.junit.Test;

import java.util.Date;

/**
 * @desc    TestUserModel.java
 * @author  yijie
 * @date    2021-01-07 14:39
 * @note    2021-01-07 14:39 yijie Created TestUserModel.java file
 */
public class TestUserModel {
    @Test
    public void testRegister () {
        new UserModel().register(
                "测试用户", "123456"
                , "", true, new Date()
        );
    }
}
