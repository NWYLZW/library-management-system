package com.yijie.libraryManagementSystem;

import com.yijie.libraryManagementSystem.entity.User;
import com.yijie.libraryManagementSystem.mapper.UserMapper;
import com.yijie.libraryManagementSystem.model.AbsModel;
import org.junit.Test;
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
        UserMapper userMapper = AbsModel.getMapper(UserMapper.class);
        assertNull(userMapper.getUserByPPNum("10000"),0);
    }

    @Test
    public void testAddUser() {
        UserMapper userMapper = AbsModel.getMapper(UserMapper.class);
        User user = new User();
        user.setPpNum("t_100001");
        user.setAvatarPath("");
        userMapper.addUser(user);
        assertNotNull(userMapper.getUserByPPNum("t_100001"));
    }

    public void testdeleteUser() {
        UserMapper userMapper = AbsModel.getMapper(UserMapper.class);
        userMapper.deleteUser(userMapper.getUserByPPNum("100001"));
        assertNotNull(userMapper.getUserByPPNum("t_100001"));
    }
}
