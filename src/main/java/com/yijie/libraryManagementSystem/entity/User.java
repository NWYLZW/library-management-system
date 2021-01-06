package com.yijie.libraryManagementSystem.entity;

import lombok.Data;

import java.util.Date;

/**
 * @desc    User.java
 * @author  iBesokuse
 * @date    2021-01-06 19:32
 * @note    2021-01-06 19:32 iBesokuse Created User.java file
 */
@Data
public class User {
    /** pp号 */
    private String ppNum;
    /** 昵称 */
    private String nickName;
    /** 密码 */
    private String password;
    /** 默认头像ID */
    private String avatarPath;
    /** 性别 */
    private Boolean gender;
    /** 诞生日 */
    private Date birthday;

    /** 创建时间 */
    private Date cDateTime;
    /** 修改时间 */
    private Date mDateTime;
}
