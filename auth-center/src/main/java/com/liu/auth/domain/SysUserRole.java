package com.liu.auth.domain;

import lombok.Data;

@Data
public class SysUserRole {
    /**
     * 主键
     */
    private String id;

    /**
     * 角色主键id
     */
    private String roleId;

    /**
     * 用户主键ID
     */
    private String userId;

}