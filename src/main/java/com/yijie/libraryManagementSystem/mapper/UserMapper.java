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
    /**
     * 通过pp号获取用户名
     * @param ppNum pp号
     * @return 搜索到的用户
     */
    @Select("select * from user where ppNum = #{ppNum}")
    User getUserByPPNum(String ppNum);

    /**
     * 获取当前总用户数
     * @return 总用户数量
     */
    int getUserCount();

    /**
     * 获取所有的用户列表
     * @return 用户列表
     */
    List<User> getAllUsers();

    /**
     * 添加一个用户
     * @param user 用户实体
     * @return 是否添加成功
     */
    int addUser(User user);

    /**
     * 删除一个用户
     * @param ppNum 用户pp号
     * @return 是否删除成功
     */
    int deleteUser(String ppNum);

    /**
     * 编辑用户
     * @param user  用户字典
     * @return 是否编辑成功
     */
    int editUser(User user);
}
