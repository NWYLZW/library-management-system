package com.yijie.libraryManagementSystem.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

/**
 * @desc    UserMapper.java
 * @author  yijie
 * @date    2021-01-06 10:20
 * @note    2021-01-06 10:20 yijie Created UserMapper.java file
 */
@Mapper
public interface UserMapper {
    @Select("select * where id = #{id} from user")
    UserMapper selectUser(int id);
}
