package com.yijie.libraryManagementSystem.model;

import com.yijie.libraryManagementSystem.Main;
import com.yijie.libraryManagementSystem.mapper.AbsMapper;
import org.apache.ibatis.session.Configuration;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.util.HashMap;
import java.util.Map;

/**
 * @desc    AbsModel.java
 * @author  yijie
 * @date    2021-01-06 11:36
 * @note    2021-01-06 11:36 yijie Created AbsModel.java file
 */
public abstract class AbsModel {
    // SQL session Factory
    private static final SqlSessionFactory sessionFactory = new SqlSessionFactoryBuilder()
            .build(Main.class.getResourceAsStream(
                    "/mybatis/config.xml"
            ));

    /**
     * 根据Mapper.class获取实例
     * @param type  需要的Mapper.class
     * @param <T>   Mapper.class类
     * @return 返回有sqlSession链接的Mapper示例
     */
    public static <T> T getMapper(Class<T> type) {
        Configuration myBatisConfig = sessionFactory.getConfiguration();
        if (!myBatisConfig.hasMapper(type)) {
            myBatisConfig.addMapper(type);
        }
        return sessionFactory.openSession().getMapper(type);
    }

    private final Map<String, AbsMapper> mappers = new HashMap();

    /**
     * 添加一个mapper实例
     * @param mapperClazz   需要的Mapper.class
     * @param <T>           Mapper.class类
     * @return  mapper实例
     */
    public <T extends AbsMapper> T addMapper(Class<T> mapperClazz) {
        return this.addMapper(mapperClazz.getName(), mapperClazz);
    }

    /**
     * 添加一个mapper实例
     * @param name          mapper名
     * @param mapperClazz   需要的Mapper.class
     * @param <T>           Mapper.class类
     * @return  mapper实例
     */
    public <T extends AbsMapper> T addMapper(String name, Class<T> mapperClazz) {
        T mapper = AbsModel.getMapper(mapperClazz);
        mappers.put(name, AbsModel.getMapper(mapperClazz));
        return mapper;
    }

    /**
     * 根据mapper名字获取Mapper实例
     * @param name  mapper名
     * @return mapper实例
     */
    public AbsMapper getMapper(String name) {
        return mappers.get(name);
    }
}
