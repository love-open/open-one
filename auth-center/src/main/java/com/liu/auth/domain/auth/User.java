package com.liu.auth.domain.auth;

import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.Size;

/**
 * @author : Frank.liu
 * createAt: 2020/2/14
 */
@Builder
@Data
public class User {
    @ApiModelProperty(value = "用户名", required = true)
    @Size(min=3, max=20)
    private String username;
    @ApiModelProperty(value = "密码", required = true)
    @Size(min=3, max=20)
    private String password;
}
