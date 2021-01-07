package com.yijie.libraryManagementSystem.entity;

import lombok.Data;


import java.util.Date;


/**
 * @desc    ConnectionInfo.java
 * @author  iBesokuse
 * @date    2021-01-06 19:32
 * @note    2021-01-06 19:32 iBesokuse Created ConnectionInfo.java file
 */
@Data
public class ConnectionInfo {
    /** id */
    private Integer id;
    /** ip */
    private String ip;
    /** 连接是否存活 */
    private Boolean isSurvive;
    /** 连接的用户Id */
    private String userId;

    /** 创建时间 */
    private Date cDateTime;
    /** 修改时间 */
    private Date mDateTime;
}
