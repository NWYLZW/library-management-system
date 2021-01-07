package com.yijie.libraryManagementSystem;

import com.yijie.libraryManagementSystem.entity.User;
import com.yijie.libraryManagementSystem.mapper.UserMapper;
import com.yijie.libraryManagementSystem.model.AbsModel;
import org.apache.ibatis.session.SqlSession;
import org.junit.Test;

import java.util.Date;

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
        SqlSession[] sessions = new SqlSession[1];
        UserMapper userMapper = AbsModel.getMapper(UserMapper.class, sessions);
        assertNull(userMapper.getUserByPPNum("10000"));
        sessions[0].commit();
    }

    @Test
    public void testUserCount() {
        UserMapper userMapper = AbsModel.getMapper(UserMapper.class, new SqlSession[1]);
        System.out.println(userMapper.getUserCount());
    }

    @Test
    public void testAddUser() {
        SqlSession[] sessions = new SqlSession[1];
        UserMapper userMapper = AbsModel.getMapper(UserMapper.class, sessions);
        User user = new User();
        user.setPpNum("t_100001");
        user.setNickName("t_100001");
        user.setPassword("t_100001");
        user.setCDateTime(new Date());
        user.setMDateTime(new Date());
        userMapper.addUser(user);
        assertNotNull(userMapper.getUserByPPNum("t_100001"));
        sessions[0].commit();
    }

    @Test
    public void testDeleteUser() {
        SqlSession[] sessions = new SqlSession[1];
        UserMapper userMapper = AbsModel.getMapper(UserMapper.class, sessions);
        userMapper.deleteUser(userMapper.getUserByPPNum("100001").getPpNum());
        assertNotNull(userMapper.getUserByPPNum("t_100001"));
        sessions[0].commit();
    }
}
