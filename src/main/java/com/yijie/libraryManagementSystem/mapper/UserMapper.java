package com.yijie.libraryManagementSystem.mapper;

import com.yijie.libraryManagementSystem.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @desc    UserMapper.java
 * @author  yijie
 * @date    2021-01-06 10:20
 * @note    2021-01-06 10:20 yijie Created UserMapper.java file
 */
@Mapper
public interface UserMapper {
    @Select("select * from user where ppNum = #{ppNum}")
    User getUserByPPNum(String ppNum);

    List<User> getAllUsers();
    int addUser(User user);
    int deleteUser(String ppNum);
    int editUser(User user);
}
