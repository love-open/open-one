package com.liu.auth.controller;

import com.alibaba.fastjson.JSON;
import com.github.pagehelper.PageInfo;
import com.liu.common.util.MyTool;
import com.liu.common.util.MyTreeNode;
import com.liu.common.util.ServiceCode;
import com.liu.auth.domain.SysMenu;
import com.liu.auth.domain.SysRole;
import com.liu.auth.domain.SysRoleMenu;
import com.liu.auth.service.SysDictTypeService;
import com.liu.auth.service.SysMenuService;
import com.liu.auth.service.SysRoleMenuService;
import com.liu.auth.service.SysRoleService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.extern.log4j.Log4j2;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 角色管理，后台控制器
 */
@RestController
@RequestMapping("/role")
@Api(tags = {"role"})
@Log4j2
public class SysRoleController {
    @Autowired
    SysRoleService sysRoleService;
    @Autowired
    SysRoleMenuService sysRoleMenuService;
    @Autowired
    SysDictTypeService sysDictTypeService;
    @Autowired
    SysMenuService sysMenuService;

    /**
     * 角色管理页面
     */
    @GetMapping("/manage/page")
    @ApiOperation(value = "角色管理", notes = "跳转到角色管理页")
    public ModelAndView manage(@ModelAttribute("sysRole") SysRole sysRole) {
        ModelAndView mv = new ModelAndView("role/manage");
        return mv;
    }

    /**
     * 分页查询
     *
     * @param draw   请求次数(浏览器cache的编号，递增不可重复)
     * @param start  起始数
     * @param length 每页显示数
     * @return json
     */
    @PostMapping("/listRoles")
    @ApiOperation(value = "分页查询角色列表", notes = "分页查询角色列表的POST方法")
    @ApiImplicitParams({@ApiImplicitParam(name = "Authorization", value = "Authorization token", required = true, dataType = "string", paramType = "header")})
    public Map<String, Object> listRoles(int draw, int start, int length, String name, String uniqueId, String remark) {
        Map<String, Object> result = new HashMap<String, Object>();
        Map<String, Object> parameterMap = new HashMap<String, Object>();

        parameterMap.put("name", name);
        parameterMap.put("uniqueId", uniqueId);
        parameterMap.put("remark", remark);

        PageInfo<SysRole> listSysRolePageInfo = sysRoleService.listSysRoles(start, length, parameterMap);
        long count = listSysRolePageInfo.getTotal();
        result.put("draw", draw);
        result.put("recordsTotal", count);
        result.put("recordsFiltered", count);
        result.put("data", listSysRolePageInfo.getList());
        return result;
    }

    /**
     * 添加
     */
    @Transactional
    @PostMapping("/addRole")
    @ApiOperation(value = "新增或修改角色", notes = "角色新增和修改的POST方法")
    @ApiImplicitParams({@ApiImplicitParam(name = "Authorization", value = "Authorization token", required = true, dataType = "string", paramType = "header")})
    public Map<String, Object> addRole(SysRole sysRole, String[] sysMenuIds) {
        Map<String, Object> map = new HashMap<>();
        String msg = "";
        Map<String, Object> parameterMap = new HashMap<String, Object>();
        String id = sysRole.getId();
        //新增
        if (StringUtils.isEmpty(id)) {
            //查询唯一识别码唯一性
            parameterMap.put("uniqueId", sysRole.getUniqueId());
            parameterMap.put("equalLikeFlag", "equal");
            PageInfo<SysRole> listSysRoles = sysRoleService.listSysRoles(0, Integer.MAX_VALUE, parameterMap);
            if (listSysRoles.getList().size() > 0) {
                msg = "唯一识别码已存在！";
                map.put("msg", msg);
                return map;
            }
            String sysRoleId = MyTool.getUUID();
            sysRole.setId(sysRoleId);
            //1，角色写入
            int n = sysRoleService.insertSelective(sysRole);
            //2,角色菜单关联写入
            int j = 0;
            if (null != sysMenuIds) {
                for (String sysMenuId : sysMenuIds) {
                    SysRoleMenu sysRoleMenu = new SysRoleMenu();
                    sysRoleMenu.setId(MyTool.getUUID());
                    sysRoleMenu.setRoleId(sysRoleId);
                    sysRoleMenu.setMenuId(sysMenuId);

                    j += sysRoleMenuService.insertSelective(sysRoleMenu);
                }
            }
            if (n == 1) {
                msg = "添加成功:" + n + "个角色，" + j + "个关联菜单！";
                map.put("msg", msg);
                map.put("code", ServiceCode.SUCCESS_CODE);
                log.info(msg);
                return map;
            }
            msg = "添加失败！";
            map.put("msg", msg);
            log.info(msg);
            return map;
        }
        //修改
        else {
            parameterMap.put("uniqueId", sysRole.getUniqueId());
            parameterMap.put("equalLikeFlag", "equal");
            PageInfo<SysRole> listSysRoles = sysRoleService.listSysRoles(0, Integer.MAX_VALUE, parameterMap);
            if (listSysRoles.getList().size() > 1) {
                msg = "唯一识别码已存在！";
                map.put("msg", msg);
                return map;
            } else if (listSysRoles.getSize() == 1) {
                if (!id.equals(listSysRoles.getList().get(0).getId())) {
                    msg = "唯一识别码已存在！";
                    map.put("msg", msg);
                    return map;
                }
            }

            //1,角色更新
            //int n =
            sysRoleService.updateByPrimaryKeySelective(sysRole);
            //2,角色菜单关联更新
            //2.1 角色sysRoleId关联菜单，删除
            // roleId 关联菜单删除
            parameterMap.put("roleId", id);
            PageInfo<SysRoleMenu> pageInfos = sysRoleMenuService.listSysRoleMenus(0, Integer.MAX_VALUE, parameterMap);
            if (pageInfos.getSize() > 0) {
                for (SysRoleMenu sysRoleMenu : pageInfos.getList()) {
                    sysRoleMenuService.deleteByPrimaryKey(sysRoleMenu.getId());
                }
            }
            //2.2 角色sysRoleId菜单关联，写入
            //int j = 0;
            if (null != sysMenuIds) {
                for (String sysMenuId : sysMenuIds) {
                    SysRoleMenu sysRoleMenu = new SysRoleMenu();
                    sysRoleMenu.setId(MyTool.getUUID());
                    sysRoleMenu.setRoleId(id);
                    sysRoleMenu.setMenuId(sysMenuId);

                    //j +=
                    sysRoleMenuService.insertSelective(sysRoleMenu);
                }
            }
            msg = "修改成功！";
            map.put("msg", msg);
            map.put("code", ServiceCode.SUCCESS_CODE);

        }
        log.info(msg);
        return map;
    }

    /**
     * 删除
     *
     * @param ids
     */
    @Transactional
    @PostMapping("/deleteRole")
    @ApiOperation(value = "删除角色", notes = "删除角色的POST方法")
    @ApiImplicitParams({@ApiImplicitParam(name = "Authorization", value = "Authorization token", required = true, dataType = "string", paramType = "header")})
    public Map<String, Object> deleteRole(String[] ids) {
        int n = 0;
        for (String roleId : ids) {
            n += sysRoleService.deleteByPrimaryKey(roleId);
            // roleId 关联菜单删除
            Map<String, Object> parameterMap = new HashMap<String, Object>();
            parameterMap.put("roleId", roleId);
            PageInfo<SysRoleMenu> pageInfos = sysRoleMenuService.listSysRoleMenus(0, Integer.MAX_VALUE, parameterMap);
            if (pageInfos.getSize() > 0) {
                for (SysRoleMenu sysRoleMenu : pageInfos.getList()) {
                    sysRoleMenuService.deleteByPrimaryKey(sysRoleMenu.getId());
                }
            }
        }
        Map<String, Object> map = new HashMap<>();
        map.put("msg", "删除成功:" + n + "条记录！");
        if (n == ids.length) {
            map.put("code", ServiceCode.SUCCESS_CODE);
        }
        log.info("删除成功:" + n + "条记录");
        return map;
    }


    /**
     * 查询
     *
     * @return
     */
    @PostMapping("/getRole")
    @ApiOperation(value = "根据角色主键或者角色名查询角色", notes = "根据角色主键或角色名查询角色的POST方法")
    @ApiImplicitParams({@ApiImplicitParam(name = "Authorization", value = "Authorization token", required = true, dataType = "string", paramType = "header")})
    public Map<String, Object> getRole(String id) {
        Map<String, Object> map = new HashMap<>();
        String msg = "";
        SysRole sysRole = sysRoleService.selectByPrimaryKey(id);
        if (null != sysRole) {
            map.put("code", ServiceCode.SUCCESS_CODE);
            map.put("sysRole", sysRole);
            //查询关联sysMenuIds
            List<SysMenu> sysMenus = new ArrayList<SysMenu>();
            Map<String, Object> parameterMap = new HashMap<String, Object>();
            parameterMap.put("roleId", sysRole.getId());
            PageInfo<SysRoleMenu> lists = sysRoleMenuService.listSysRoleMenus(0, Integer.MAX_VALUE, parameterMap);
            for (SysRoleMenu sysRoleMenu : lists.getList()) {
                SysMenu sysMenu = sysMenuService.selectByPrimaryKey(sysRoleMenu.getMenuId());
                if (sysMenu != null) {
                    sysMenus.add(sysMenu);
                }
            }
            if (sysMenus.size() > 0) {
                map.put("sysMenus", sysMenus);
            }

            log.info("查询成功!");
        } else {
            msg = "查询失败！";
            map.put("msg", msg);
            log.info(msg);
        }

        return map;
    }

    /**
     * 获取用户分组tree
     * @param hasChildrenNodeFlag 包含子节点明细标志
     */
    @GetMapping("/getTree")
    @ApiOperation(value = "查询用户分组分组树结构", notes = "查询用户分组分组树结构的GET方法")
    @ApiImplicitParams({@ApiImplicitParam(name = "Authorization", value = "Authorization token", required = true, dataType = "string", paramType = "header")})
    public List<MyTreeNode> getTree(String hasChildrenNodeFlag) {
        Map<String, Object> parameterMap = new HashMap<String, Object>();
        //只获取分组节点
        if (org.thymeleaf.util.StringUtils.isEmpty(hasChildrenNodeFlag)) {
            parameterMap.put("isEmptyFlagKey", "isEmptyFlag");
        }
        PageInfo<SysRole> listSysRolePageInfo = sysRoleService.listSysRoles(0, Integer.MAX_VALUE, parameterMap);
        // 将分组加入节点
        List<MyTreeNode> nodes = new ArrayList<>();
        MyTreeNode root = new MyTreeNode();

        root.setId("root");
        root.setText("全部");
        root.setParent("#");
        nodes.add(root);

        for (SysRole role : listSysRolePageInfo.getList()) {
            MyTreeNode node = new MyTreeNode();
            node.setId(role.getId());
            node.setText(role.getName());
            node.setParent("root");
            nodes.add(node);
        }
        String msg = "获取分组tree！";
        log.info(msg);
        return nodes;
    }

    /**
     * 可拖拽分组排序
     *
     * @param sysMenus 新的tree分组
     */
    @Transactional
    @PostMapping("/dragTree")
    @ApiOperation(value = "菜单分组树结构的可拖拽分组排序", notes = "菜单分组树可拖拽分组排序的POST方法")
    @ApiImplicitParams({@ApiImplicitParam(name = "Authorization", value = "Authorization token", required = true, dataType = "string", paramType = "header")})
    public void dragTree(@RequestParam("sysMenus") String sysMenus) {
        List<SysMenu> sysMenusList = JSON.parseArray(sysMenus, SysMenu.class);
        //遍历list 修改排序
        sysMenusList.forEach(sysMenu -> {
            sysMenuService.updateByPrimaryKeySelective(sysMenu);
        });
    }

}
