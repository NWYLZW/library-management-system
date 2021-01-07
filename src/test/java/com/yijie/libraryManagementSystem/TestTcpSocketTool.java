package com.yijie.libraryManagementSystem;

import com.yijie.libraryManagementSystem.entity.Message;
import com.yijie.libraryManagementSystem.tool.UserTool;
import com.yijie.libraryManagementSystem.tool.internet.TcpSocketTool;
import org.junit.Test;

/**
 * @desc    TestTcpSocketTool.java
 * @author  yijie
 * @date    2021-01-07 10:21
 * @note    2021-01-07 10:21 yijie Created TestTcpSocketTool.java file
 */
public class TestTcpSocketTool {
    @Test
    public void testServer() {
        TcpSocketTool.sendMessage("127.0.0.1", "测试信息1", response -> {
            System.out.println("response:" + response);
            return Message.structure(
                    UserTool.curUser(), "", Message.Type.CLOSE
            );
        });
    }
}
