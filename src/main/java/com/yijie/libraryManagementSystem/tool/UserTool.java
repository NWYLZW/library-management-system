package com.yijie.libraryManagementSystem.tool;

import com.yijie.libraryManagementSystem.entity.User;
import lombok.Getter;
import lombok.Setter;

/**
 * @desc    UserTool.java
 * @author  yijie
 * @date    2021-01-07 10:54
 * @note    2021-01-07 10:54 yijie Created UserTool.java file
 */
public class UserTool {
    @Setter
    private static User curUser = null;

    public static User curUser() {
        User curUser = new User();
        curUser.setPpNum("200001");
        return curUser;
    }
}
