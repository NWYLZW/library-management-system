package com.yijie.libraryManagementSystem.model;

import com.yijie.libraryManagementSystem.Main;
import com.yijie.libraryManagementSystem.mapper.AbsMapper;
import org.apache.ibatis.session.Configuration;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.util.HashMap;
import java.util.Map;
import java.util.function.BiConsumer;
import java.util.function.BiFunction;
import java.util.function.Consumer;
import java.util.function.Function;

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

    private static <T> void registerMapper(Class<T> type) {
        Configuration myBatisConfig = sessionFactory.getConfiguration();
        if (!myBatisConfig.hasMapper(type)) {
            myBatisConfig.addMapper(type);
        }
    }

    /**
     * 根据Mapper.class获取实例，并为model注册session
     * @param type  需要的Mapper.class
     * @param model 当前model实例
     * @param <T>   Mapper.class类
     * @return 返回有sqlSession链接的Mapper实例
     */
    public static <T> T getMapper(Class<T> type, AbsModel model) {
        registerMapper(type);
        SqlSession session = model.getSession();
        return session.getMapper(type);
    }

    /**
     * 根据Mapper.class获取实例
     * @param type          需要的Mapper.class
     * @param dealMapper    处理mapper的回调
     * @param <T>           Mapper.class类
     */
    public static <T> void getMapper(Class<T> type, Consumer<T> dealMapper) {
        getMapper(type, (session, mapper) -> {
            dealMapper.accept(mapper);
        });
    }

    /**
     * 根据Mapper.class获取实例
     * @param type          需要的Mapper.class
     * @param dealMapper    处理mapper的回调
     * @param <T>           Mapper.class类
     */
    public static <T> void getMapper(Class<T> type, BiConsumer<SqlSession, T> dealMapper) {
        registerMapper(type);
        SqlSession session = sessionFactory.openSession();
        dealMapper.accept(session, session.getMapper(type));
        session.close();
    }

    /**
     * 根据Mapper.class获取实例
     * @param type          需要的Mapper.class
     * @param dealMapper    处理mapper的回调
     * @param <T>           Mapper.class类
     */
    public static <T, R> R getMapper(Class<T> type, Function<T, R> dealMapper) {
        return getMapper(type, (session, mapper) -> {
            return dealMapper.apply(mapper);
        });
    }

    /**
     * 根据Mapper.class获取实例
     * @param type          需要的Mapper.class
     * @param dealMapper    处理mapper的回调
     * @param <T>           Mapper.class类
     */
    public static <T, R> R getMapper(Class<T> type, BiFunction<SqlSession, T, R> dealMapper) {
        registerMapper(type);
        SqlSession session = sessionFactory.openSession();
        R data = dealMapper.apply(session, session.getMapper(type));
        session.close();
        return data;
    }

    private SqlSession session;

    public SqlSession getSession() {
        if (session == null) {
            session = sessionFactory.openSession();
        }
        return session;
    }

    public void closeSession() {
        if (session != null) {
            session.close();
            session = null;
        }
    }

    private final Map<String, AbsMapper> mappers = new HashMap();

    /**
     * 添加一个mapper实例
     * @param mapperClazz   需要的Mapper.class
     * @param <T>           Mapper.class类
     * @return  mapper实例
     */
    public <T extends AbsMapper> T addMapper(Class<T> mapperClazz, AbsModel model) {
        return this.addMapper(mapperClazz.getName(), mapperClazz, model);
    }

    /**
     * 添加一个mapper实例
     * @param name          mapper名
     * @param mapperClazz   需要的Mapper.class
     * @param <T>           Mapper.class类
     * @return  mapper实例
     */
    public <T extends AbsMapper> T addMapper(String name, Class<T> mapperClazz, AbsModel model) {
        T mapper = AbsModel.getMapper(mapperClazz, model);
        mappers.put(name, AbsModel.getMapper(mapperClazz, model));
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
