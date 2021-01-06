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
    //pp号
    @Getter @Setter
    private String ppNum;
    //昵称
    @Getter @Setter
    private String nickName;
    //密码
    @Getter @Setter
    private String passWord;
    //默认头像ID
    @Getter @Setter
    private int avatarId;
    //性别
    @Getter @Setter
    private String sex;
    //诞生日
    @Getter @Setter
    private String birthDay;
    //注册时间
    @Getter @Setter
    private String registerTime;



}
