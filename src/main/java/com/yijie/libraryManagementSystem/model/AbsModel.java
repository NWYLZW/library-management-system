package com.yijie.libraryManagementSystem.model;

import com.yijie.libraryManagementSystem.Main;
import com.yijie.libraryManagementSystem.mapper.AbsMapper;
import com.yijie.libraryManagementSystem.mapper.UserMapper;
import org.apache.ibatis.session.Configuration;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

/**
 * @desc    AbsModel.java
 * @author  yijie
 * @date    2021-01-06 11:36
 * @note    2021-01-06 11:36 yijie Created AbsModel.java file
 */
public abstract class AbsModel {
    private static final SqlSessionFactory sessionFactory = new SqlSessionFactoryBuilder()
            .build(Main.class.getResourceAsStream(
                    "/mybatis/config.xml"
            ));
    public static <T> T getMapper(Class<T> type) {
        Configuration myBatisConfig = sessionFactory.getConfiguration();
        if (!myBatisConfig.hasMapper(UserMapper.class)) {
            myBatisConfig.addMapper(UserMapper.class);
        }
        return sessionFactory.openSession().getMapper(type);
    }
    public AbsMapper mapper;
}
