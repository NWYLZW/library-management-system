package com.yijie.libraryManagementSystem.entity;

import com.google.gson.Gson;
import lombok.Builder;
import lombok.Data;
import lombok.ToString;

import java.util.Date;

/**
 * @desc    Message.java
 * @author  yijie
 * @date    2021-01-07 09:13
 * @note    2021-01-07 09:13 yijie Created Message.java file
 */
@Data
@Builder
@ToString
public class Message {
    public enum Type {
        SUCCESS(200),
        HELLO(201),
        NULL(404),
        CLOSE(500);
        public int val;

        Type(int val) {
            this.val = val;
        }
    }
    private Integer code;
    private String message;
    private String senderPPNum;
    private Date sendDateTime;

    public static Message structure(
            User user, String message, Message.Type type
    ) {
        return Message.builder()
                .code(type.val).message(message).sendDateTime(new Date())
                .senderPPNum(user.getPpNum()).build();
    }

    public static String structureJson(
            User user, String message, Message.Type type
    ) {
        return new Gson().toJson(
                Message.structure(user, message, type)
        );
    }
}
