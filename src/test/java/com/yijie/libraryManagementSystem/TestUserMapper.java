package com.yijie.libraryManagementSystem;

import com.yijie.libraryManagementSystem.entity.User;
import com.yijie.libraryManagementSystem.mapper.UserMapper;
import com.yijie.libraryManagementSystem.model.AbsModel;
import org.apache.ibatis.session.SqlSession;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.Assert.*;

/**
 * @desc    TestUserMapper.java
 * @author  yijie
 * @date    2021-01-06 11:10
 * @note    2021-01-06 11:10 yijie Created TestUserMapper.java file
 */
public class TestUserMapper {
    @Test
    public void testFindUser() {
        AbsModel.getMapper(UserMapper.class, mapper -> {
            assertNull(mapper.getUserByPPNum("10000"));
        });
    }

    @Test
    public void testUserCount() {
        AbsModel.getMapper(UserMapper.class, mapper -> {
            List<User> users = mapper.getAllUsers();
            assertEquals(
                    mapper.getUserCount(), users.size()
            );
        });
    }

    @Test
    public void testAddAndDeleteUser() {
        String ppNum = "t_100001";
        AbsModel.getMapper(UserMapper.class, (session, mapper) -> {
            mapper.addUser(User.builder()
                    .ppNum(ppNum).nickName(ppNum).password(ppNum)
                    .cDateTime(new Date())
                    .mDateTime(new Date())
                    .build());
            session.commit();
        });
        AbsModel.getMapper(UserMapper.class, (session, mapper) -> {
            assertNotNull(mapper.getUserByPPNum(ppNum));
            mapper.deleteUser(ppNum);
            session.commit();
        });
    }
}
