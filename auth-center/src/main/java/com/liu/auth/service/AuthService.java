package com.liu.auth.service;

import com.liu.auth.domain.auth.ResponseUserToken;
import com.liu.auth.domain.auth.UserDetail;

/**
 * @author: Frank.liu
 * createAt: 2020/2/14
 */
public interface AuthService {
    /**
     * 注册用户
     * @param userDetail
     * @return
     */
    UserDetail register(UserDetail userDetail);

    /**
     * 登陆
     * @param username
     * @param password
     * @return
     */
    ResponseUserToken login(String username, String password);

    /**
     * 登出
     * @param token
     */
    void logout(String token);

    /**
     * 刷新Token
     * @param oldToken
     * @return
     */
    ResponseUserToken refresh(String oldToken);

    /**
     * 根据Token获取用户信息
     * @param token
     * @return
     */
    UserDetail getUserByToken(String token);
}
