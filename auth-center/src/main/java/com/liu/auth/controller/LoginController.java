package com.liu.auth.controller;

import com.github.pagehelper.PageInfo;
import com.liu.auth.domain.SysMenu;
import com.liu.auth.domain.SysRole;
import com.liu.auth.domain.SysRoleMenu;
import com.liu.auth.domain.SysUser;
import com.liu.auth.domain.auth.UserDetail;
import com.liu.auth.domain.vo.SysUserView;
import com.liu.common.util.MyTool;
import com.liu.auth.service.*;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.*;

/**
 * 用户登录，后台控制器
 */
@RestController
//@Api(description = "非前后端分离系统用到的登录相关接口")
@Api(tags={"login"})
@RequestMapping("/")
@Log4j2
public class LoginController {
    @Autowired
    private SysUserService sysUsersService;
    
//    @Autowired
//    private SysUserRoleService sysRoleUsersService;
    
    @Autowired
    private SysRoleService sysRoleService;
    
    @Autowired
    private SysRoleMenuService sysRoleMenuService;
    
    @Autowired
    private SysMenuService sysMenuService;

    @Autowired
    private AuthService authService;

    @GetMapping(value = "/")
    @ApiOperation(value = "登录页", notes = "跳转登录页")
    public ModelAndView login(Model model){
        log.info("登陆请求/：" + new Date());
        ModelAndView mv = new ModelAndView("login");
        return mv;
    }
    /**
     * 登录成功
     * @return
     */
    @PostMapping("/index")
    @ApiOperation(value = "登录成功后跳转首页", notes = "登录成功后跳转首页")
    //@ApiImplicitParams({@ApiImplicitParam(name = "Authorization", value = "Authorization token", required = true, dataType = "string", paramType = "header")})
    public ModelAndView toIndex(HttpServletRequest request, Model model, String token){
        //System.out.println(token);
        //拼接用户视图
        SysUserView sysUsersView = new SysUserView();
        //设置token
        sysUsersView.setToken(token);
    	//根据用户账号的token获取当前登录用户的信息
        UserDetail userDetail = authService.getUserByToken(token);
        //记录登录日志
        String username = userDetail.getUsername();
        SysUser sysUser = new SysUser();
        sysUser.setUsername(username);
        sysUser.setLastLoginIp(request.getRemoteAddr());
        sysUser.setLastLoginTime(MyTool.getStandardTime());
        sysUser.setOnlineFlag(true);
        sysUsersService.updateByAccountSelective(sysUser);
        log.info("登录成功：" + username);
        try {
        	//将sysUsers对象的属性值赋值给ysUsersView对象
			MyTool.fatherToChild(sysUser, sysUsersView);
		} catch (Exception e) {
			e.printStackTrace();
		}
        //获取用户角色
        List<SysRole> sysRoles = new ArrayList<>();
        Map<String, Object> parameterMap = new HashMap<>();
        parameterMap.put("userId", sysUser.getId());
        parameterMap.put("uniqueId", userDetail.getRole().getUniqueId());
        parameterMap.put("equalLikeFlag", "equal");
        PageInfo<SysRole> sysRolePageInfo = sysRoleService.listSysRoles(0, Integer.MAX_VALUE, parameterMap);
        sysRoles.addAll(sysRolePageInfo.getList());
        //设置用户角色
        sysUsersView.setSysRoles(sysRoles);
        //获取用户菜单
        List<SysMenu> sysMenus = new ArrayList<SysMenu>();
        for(SysRole sysRole : sysRoles) {
        	parameterMap.put("roleId", sysRole.getId());
            PageInfo<SysRoleMenu> SysRoleMenuPageInfo = sysRoleMenuService.listSysRoleMenus(0, Integer.MAX_VALUE, parameterMap);
            List<SysRoleMenu> SysRoleMenus = SysRoleMenuPageInfo.getList();
            for(SysRoleMenu sysRoleMenu : SysRoleMenus) {
            	SysMenu sysMenu = sysMenuService.selectByPrimaryKey(sysRoleMenu.getMenuId());
            	if(sysMenu != null) {
            		sysMenus.add(sysMenu);
            	}
            }
        }
        //排序
        Collections.sort(sysMenus);
        sysUsersView.setSysMenus( sysMenus);
        model.addAttribute("sysUsersView", sysUsersView);
        ModelAndView mv = new ModelAndView("index");
        return mv;
    }
}
