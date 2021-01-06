package com.yijie.libraryManagementSystem.entity;

import lombok.Getter;
import lombok.Setter;

/**
 * @desc    User.java
 * @author  yijie
 * @date    2021-01-06 11:01
 * @note    2021-01-06 11:01 yijie Created User.java file
 */
public class User {
    @Getter @Setter
    private String ppNum;
    @Getter @Setter
    private String nickName;
    @Getter @Setter
    private String password;
}
