package com.yijie.libraryManagementSystem;

import com.yijie.libraryManagementSystem.mapper.UserMapper;
import com.yijie.libraryManagementSystem.view.Login;
import org.apache.ibatis.session.Configuration;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

/**
 * @desc    Main.java
 * @author  yijie
 * @date    2021-01-05 08:56
 * @note    2021-01-05 08:56 yijie Created Main.java file
 */
public class Main {
    public static SqlSessionFactory sessionFactory = new SqlSessionFactoryBuilder()
            .build(Main.class.getResourceAsStream(
                    "/mybatis/config.xml"
            ));
    static <T> T getMapper(Class<T> type) {
        Configuration myBatisConfig = sessionFactory.getConfiguration();
        if (!myBatisConfig.hasMapper(UserMapper.class)) {
            myBatisConfig.addMapper(UserMapper.class);
        }
        return sessionFactory.openSession().getMapper(type);
    }

    public static void main(String[] args) {
        Main.getMapper(UserMapper.class).getUserByPPNum("10000");
        Login.show();
    }
}
