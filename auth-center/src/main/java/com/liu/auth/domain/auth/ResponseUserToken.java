package com.liu.auth.domain.auth;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @author Frank.liu
 * createAt: 2020/2/14
 */
@Data
@AllArgsConstructor
public class ResponseUserToken {
    private String token;
    private UserDetail userDetail;
}
