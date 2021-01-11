package com.yijie.libraryManagementSystem.mapper;

import com.yijie.libraryManagementSystem.entity.User;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * @desc    UserMapper.java
 * @author  yijie
 * @date    2021-01-06 10:20
 * @note    2021-01-06 10:20 yijie Created UserMapper.java file
 */
@Mapper
public interface UserMapper extends AbsMapper {
    /**
     * 通过pp号获取用户名
     * @param ppNum pp号
     * @return 搜索到的用户
     */
    @Select("select * from user where pp_num = #{ppNum}")
    User getUserByPPNum(String ppNum);

    /**
     * 获取当前总用户数
     * @return 总用户数量
     */
    @Select("select count(*) from user ")
    int getUserCount();

    /**
     * 获取所有的用户列表
     * @return 用户列表
     */
    @Select("select * from user")
    List<User> getAllUsers();

    /**
     * 添加一个用户
     * @param user 用户实体
     * @return 是否添加成功
     */
    @Insert(
            "insert into user(" +
                    "pp_num, nick_name, password, avatar_path, gender, birthday, cdatetime, mdatetime" +
                    ") value (" +
                    "#{ppNum}, #{nickName}, #{password}, #{avatarPath}, #{gender}, #{birthday}, #{cDateTime}, #{mDateTime}" +
                    ")"
    )
    int addUser(User user);

    /**
     * 删除一个用户
     * @param ppNum 用户pp号
     * @return 是否删除成功
     */
    @Delete("delete from user where pp_num=#{ppNum}")
    int deleteUser(String ppNum);

    /**
     * 编辑用户
     * @param user  用户字典
     * @return 是否编辑成功
     */
    @Update(
            "<script>" +
                    "update user set " +
                    "<if test = 'nickName != null'>nick_name = #{nickName},</if> " +
                    "<if test = 'password != null'>password = #{password},</if> " +
                    "<if test = 'avatarPath != null'>avatar_path = #{avatarPath},</if> " +
                    "<if test = 'gender != null'>gender = #{gender},</if> " +
                    "mDatetime = #{mDateTime} " +
                    "where ppNum=#{ppNum}" +
            "</script>"
    )
    int editUser(User user);
}
