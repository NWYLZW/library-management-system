package com.yijie.libraryManagementSystem;

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
        assertNull(userMapper.getUserByPPNum("2000000"));
    }
}
