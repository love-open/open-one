package com.liu.auth.domain;
import lombok.Data;

@Data
public class SysRoleMenu {
    /**
     * 主键
     */
    private String id;

    /**
     * 角色主键id
     */
    private String roleId;

    /**
     * 菜单主键id
     */
    private String menuId;

}