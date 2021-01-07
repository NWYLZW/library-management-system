package com.yijie.libraryManagementSystem.model;

import com.yijie.libraryManagementSystem.entity.User;
import com.yijie.libraryManagementSystem.mapper.UserMapper;
import org.apache.ibatis.session.SqlSession;

import java.util.Date;

/**
 * @desc    UserModel.java
 * @author  yijie
 * @date    2021-01-06 11:01
 * @note    2021-01-06 11:01 yijie Created UserModel.java file
 */
public class UserModel extends AbsModel {
    SqlSession[] sessions = new SqlSession[1];

    public UserModel() {
        this.addMapper(UserMapper.class, sessions);
    }

    /**
     * 登陆用户
     * @param ppNum     PP号
     * @param password  密码
     * @return 返回登陆是否成功
     */
    public boolean login(
            String ppNum, String password
    ) {
        try {
            UserMapper userMapper= (UserMapper) getMapper("com.yijie.libraryManagementSystem.mapper.UserMapper");
            User user = userMapper.getUserByPPNum(ppNum);
            if (user == null) {
                return false;
            }
            return user.getPassword().equals(password);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 注册用户
     * @return 返回注册是否成功
     */
    public String register(
            String nickName, String password, String avatarPath
            , Boolean gender, Date birthday
    ) {
        try {
            UserMapper userMapper = (UserMapper) getMapper("com.yijie.libraryManagementSystem.mapper.UserMapper");
            int startPPNum = 200000;
            userMapper.addUser(new User(
                    "" + (startPPNum + userMapper.getUserCount()), nickName, password, avatarPath, gender, birthday
                    , new Date(), new Date()
            ));
            sessions[0].commit();
            return "" + (startPPNum + userMapper.getUserCount()-1);
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
    }

    /**
     * 登出用户
     * @param ppNum     PP号
     */
    public void logout(String ppNum) {
    }
}
