package com.yijie.libraryManagementSystem.model;

/**
 * @desc    UserModel.java
 * @author  yijie
 * @date    2021-01-06 11:01
 * @note    2021-01-06 11:01 yijie Created UserModel.java file
 */
public class UserModel extends AbsModel {
    /**
     * 登陆用户
     * @param ppNum     PP号
     * @param password  密码
     * @return 返回登陆是否成功
     */
    public static boolean login(
            String ppNum, String password
    ) {
        return true;
    }

    /**
     * 登出用户
     * @param ppNum     PP号
     */
    public static void logout(String ppNum) {
    }
}
