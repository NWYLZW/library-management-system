package com.yijie.libraryManagementSystem;

import com.yijie.libraryManagementSystem.mapper.UserMapper;
import com.yijie.libraryManagementSystem.view.Login;
import org.apache.ibatis.mapping.Environment;
import org.apache.ibatis.session.Configuration;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.apache.ibatis.transaction.jdbc.JdbcTransactionFactory;

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

    public static void main(String[] args) {
        Configuration myBatisConfig = sessionFactory.getConfiguration();
        myBatisConfig.addMapper(UserMapper.class);

        UserMapper userMapper = sessionFactory.openSession().getMapper(UserMapper.class);

        System.out.println(
                userMapper.selectUser(0)
        );
        Login.show();
    }
}
