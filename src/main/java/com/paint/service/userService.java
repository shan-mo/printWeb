package com.paint.service;

import com.paint.pojo.Result;
import com.paint.pojo.po.User;

/**
 * 与用户相关的方法
 */
public interface userService {

    /**
     * 用户登录
     *
     * @param user
     * @return
     */
    Result login(User user);

    /**
     * 用户注册
     *
     * @param user
     * @return
     */
    boolean regist(User user);

    /**
     * 用户登出
     *
     * @param user
     * @return
     */
    boolean loginout(User user);

    /**
     * 用户销号
     *
     * @param user
     * @return
     */
    boolean userCancle(User user);

    /**
     * 修改用户信息
     *
     * @param user
     * @return
     */
    boolean userMessageUpdate(User user);

    /**
     * 修改用户的状态
     */
    void updateUserState();
}
