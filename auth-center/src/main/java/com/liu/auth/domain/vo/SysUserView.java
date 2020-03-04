package com.liu.auth.domain.vo;

import com.liu.auth.domain.SysMenu;
import com.liu.auth.domain.SysRole;
import com.liu.auth.domain.SysUser;
import lombok.Data;

import java.util.List;

@Data
public class SysUserView extends SysUser {

    /**
     * token
     */
    private String token;

    /**
             * 用户角色
     */
    private List<SysRole> sysRoles;
    
    /**
	     *菜单权限
	*/
    private List<SysMenu> sysMenus;


}
