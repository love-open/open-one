package com.liu.auth.controller;

import com.github.pagehelper.PageInfo;
import com.liu.common.util.MyTool;
import com.liu.common.util.MyTreeNode;
import com.liu.common.util.ServiceCode;
import com.liu.auth.domain.SysMenu;
import com.liu.auth.service.SysMenuService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.thymeleaf.util.StringUtils;

import java.util.*;

/**
 * 用户菜单，后台控制器
 *
 * @author Frank.liu
 * @create 2019-08-22 下午 16:44
 **/
@RestController
@RequestMapping("/menu")
@Api(tags = {"menu"})
@Log4j2
public class SysMenuController {
    @Autowired
    SysMenuService sysMenuService;

    /**
     * 菜单管理页面
     */
    @GetMapping("/manage/page")
    @ApiOperation(value = "菜单管理", notes = "跳转到菜单管理页")
    public ModelAndView manage(@ModelAttribute("sysMenu") SysMenu sysMenu) {
        ModelAndView mv = new ModelAndView("menu/manage");
        return mv;
    }


    /**
     * 分页查询
     *
     * @param draw   请求次数(浏览器cache的编号，递增不可重复)
     * @param start  起始数
     * @param length 每页显示数
     * @return 当前页产品集合json
     */
    @PostMapping("/listSysMenus")
    @ApiOperation(value = "分页查询菜单列表", notes = "分页查询菜单列表的POST方法")
    @ApiImplicitParams({@ApiImplicitParam(name = "Authorization", value = "Authorization token", required = true, dataType = "string", paramType = "header")})
    public Map<String, Object> listSysMenus(int draw, int start, int length, String parentMenuId, String url, String name, String displayOrder, String remark) {
        Map<String, Object> result = new HashMap<String, Object>();
        Map<String, Object> parameterMap = new HashMap<String, Object>();

        if (!StringUtils.isEmpty(parentMenuId)) {
            List<SysMenu> sysMenus = recursiveChildSysMenus(parentMenuId);
            List<String> parentMenuIds = new ArrayList<>();
            parentMenuIds.add(parentMenuId);
            for (SysMenu sysMenu : sysMenus) {
                parentMenuIds.add(sysMenu.getId());
            }
            parameterMap.put("parentMenuIds", parentMenuIds);
        }
        parameterMap.put("url", url);
        parameterMap.put("name", name);
        parameterMap.put("displayOrder", displayOrder);
        parameterMap.put("remark", remark);
        parameterMap.put("notEmptyFlag", "notEmptyFlag");

        PageInfo<SysMenu> listSysMenuPageInfo = sysMenuService.listSysMenus(start, length, parameterMap);
        long count = listSysMenuPageInfo.getTotal();


        result.put("draw", draw);
        result.put("recordsTotal", count);
        result.put("recordsFiltered", count);
        result.put("data", listSysMenuPageInfo.getList());
        return result;
    }


    /**
     * 获取菜单分组tree
     *
     * @param hasChildrenNodeFlag 包含子节点明细标志
     * @return
     * @author Frank.liu
     * @create 2019-08-22 下午 16:44
     */
    @GetMapping("/getTree")
    @ApiOperation(value = "查询菜单分组树结构", notes = "查询菜单分组树结构的GET方法")
    @ApiImplicitParams({@ApiImplicitParam(name = "Authorization", value = "Authorization token", required = true, dataType = "string", paramType = "header")})
    public List<MyTreeNode> getTree(String hasChildrenNodeFlag) {
        Map<String, Object> parameterMap = new HashMap<String, Object>();
        //只获取分组节点
        if (StringUtils.isEmpty(hasChildrenNodeFlag)) {
            parameterMap.put("isEmptyFlagKey", "isEmptyFlag");
        }
        PageInfo<SysMenu> listSysMenusPageInfo = sysMenuService.listSysMenus(0, Integer.MAX_VALUE, parameterMap);
        // 将分组加入节点
        List<MyTreeNode> nodes = new ArrayList<>();
        MyTreeNode root = new MyTreeNode();

        root.setId("root");
        root.setText("全部");
        root.setParent("#");
        nodes.add(root);

        for (SysMenu menus : listSysMenusPageInfo.getList()) {
            MyTreeNode node = new MyTreeNode();

            node.setId(menus.getId());
            node.setText(menus.getName() + "(" + menus.getDisplayOrder() + ")");

            String parentIdTemp = menus.getParentMenuId();
            if (StringUtils.isEmpty(parentIdTemp)) {
                parentIdTemp = "root";
            }
            node.setParent(parentIdTemp);
            nodes.add(node);
        }
        String msg = "获取分组tree！";
        log.info(msg);
        return nodes;
    }

    /**
     * 递归获取当前分组下的所有子项
     * * @author Frank.liu
     * * @date 2019-08-22
     *
     * @return
     */

    Vector<SysMenu> recursiveChildSysMenus(String parentMenuId) {
        Vector<SysMenu> vectors = new Vector<>();
        Map<String, Object> parameterMap = new HashMap<String, Object>();
        parameterMap.put("parentMenuId", parentMenuId);

        PageInfo<SysMenu> listSysMenuPageInfo = sysMenuService.listSysMenus(0, Integer.MAX_VALUE, parameterMap);
        vectors.addAll(listSysMenuPageInfo.getList());
        for (SysMenu sysMenu : listSysMenuPageInfo.getList()) {
            Vector<SysMenu> childs = recursiveChildSysMenus(sysMenu.getId());
            vectors.addAll(childs);
        }
        return vectors;
    }

    /**
     * 添加菜单分组
     *
     * @author Frank.liu
     * @create 2019-08-22 下午 16:44
     */
    @Transactional
    @PostMapping("/addSysMenuGroup")
    @ApiOperation(value = "添加和修改菜单分组", notes = "添加和修改菜单分组的POST方法")
    @ApiImplicitParams({@ApiImplicitParam(name = "Authorization", value = "Authorization token", required = true, dataType = "string", paramType = "header")})
    public Map<String, Object> addSysMenuGroup(SysMenu sysMenu) {
        Map<String, Object> map = new HashMap<>();
        String msg = "";
        Map<String, Object> parameterMap = new HashMap<String, Object>();
        String id = sysMenu.getId();
        //新增
        if (StringUtils.isEmpty(id)) {

            parameterMap.put("name", sysMenu.getName());
            parameterMap.put("equalLikeFlag", "equal");
            PageInfo<SysMenu> sysMenuPageInfo = sysMenuService.listSysMenus(0, Integer.MAX_VALUE, parameterMap);
            long count = sysMenuPageInfo.getTotal();
            if (count > 0) {
                msg = "名称已存在！";
                map.put("msg", msg);
                return map;
            }
            sysMenu.setId(MyTool.getUUID());
            //设置叶子节点 frank 2019.10.17
            sysMenu.setLeafFlag(false);
            int n = sysMenuService.insertSelective(sysMenu);
            if (n == 1) {
                msg = "添加成功！";
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
            //查询分组名称已存在
            parameterMap.put("name", sysMenu.getName());
            parameterMap.put("equalLikeFlag", "equal");
            parameterMap.put("start", 0);
            parameterMap.put("length", Integer.MAX_VALUE);

            PageInfo<SysMenu> sysMenuList = sysMenuService.listSysMenus(0, Integer.MAX_VALUE, parameterMap);

            if (sysMenuList.getSize() > 1) {
                msg = "名称已存在！";
                map.put("msg", msg);
                return map;
            } else if (sysMenuList.getSize() == 1) {
                if (!id.equals(sysMenuList.getList().get(0).getId())) {
                    msg = "名称已存在！";
                    map.put("msg", msg);
                    return map;
                }
            }

            //int n =
            sysMenuService.updateByPrimaryKeySelective(sysMenu);
            msg = "修改成功！";
            map.put("msg", msg);
            map.put("code", ServiceCode.SUCCESS_CODE);
        }
        log.info(msg);
        return map;
    }


    /**
     * 添加菜单
     *
     * @author Frank.liu
     * @create 2019-08-22 下午 16:44
     */
    @Transactional
    @PostMapping("/addSysMenuTree")
    @ApiOperation(value = "添加和修改菜单", notes = "添加和修改菜单的POST方法")
    @ApiImplicitParams({@ApiImplicitParam(name = "Authorization", value = "Authorization token", required = true, dataType = "string", paramType = "header")})
    public Map<String, Object> addSysMenuTree(SysMenu sysMenu) {
        Map<String, Object> map = new HashMap<>();
        String msg = "";
        Map<String, Object> parameterMap = new HashMap<String, Object>();
        String id = sysMenu.getId();
        //新增
        if (StringUtils.isEmpty(id)) {
            //查询已存在
            parameterMap.put("url", sysMenu.getUrl());
            parameterMap.put("equalLikeFlag", "equal");
            PageInfo<SysMenu> sysMenuPageInfo = sysMenuService.listSysMenus(0, Integer.MAX_VALUE, parameterMap);
            long count = sysMenuPageInfo.getTotal();
            if (count > 0) {
                msg = "路径已存在！";
                map.put("msg", msg);
                return map;
            }
            sysMenu.setId(MyTool.getUUID());
            //设置叶子节点 frank 2019.10.17
            sysMenu.setLeafFlag(true);
            int n = sysMenuService.insertSelective(sysMenu);
            if (n == 1) {
                msg = "添加成功！";
                map.put("msg", msg);
                map.put("code", ServiceCode.SUCCESS_CODE);
                log.info(msg);
                return map;
            }
            msg = "添加失败！";
            map.put("msg", msg);
        }
        //修改
        else {
            parameterMap.put("url", sysMenu.getUrl());
            parameterMap.put("equalLikeFlag", "equal");
            parameterMap.put("start", 0);
            parameterMap.put("length", Integer.MAX_VALUE);
            PageInfo<SysMenu> Menus = sysMenuService.listSysMenus(0, Integer.MAX_VALUE, parameterMap);
            if (Menus.getList().size() > 1) {
                msg = "地址已存在！";
                map.put("msg", msg);
                return map;
            } else if (Menus.getSize() == 1) {
                if (!id.equals(Menus.getList().get(0).getId())) {
                    msg = "路径已存在！";
                    map.put("msg", msg);
                    return map;
                }
            }

            //int n =
            sysMenuService.updateByPrimaryKeySelective(sysMenu);
            msg = "修改成功！";
            map.put("msg", msg);
            map.put("code", ServiceCode.SUCCESS_CODE);
        }
        log.info(msg);
        return map;
    }

    /**
     * 删除菜单分组
     *
     * @author Frank.liu
     * @create 2019-08-22 下午 16:44
     */
    @Transactional
    @PostMapping("/deleteMenuGroup")
    @ApiOperation(value = "删除菜单分组", notes = "删除菜单分组的POST方法")
    @ApiImplicitParams({@ApiImplicitParam(name = "Authorization", value = "Authorization token", required = true, dataType = "string", paramType = "header")})
    public Map<String, Object> deleteMenuGroup(String id) {
        Map<String, Object> map = new HashMap<>();
        String msg = "";
        //判定子节点
        List<SysMenu> Menus = recursiveChildSysMenus(id);
        if (null != Menus && Menus.size() > 0) {
            msg = "该分组下有子分组，不允许删除！";
            map.put("msg", msg);
            return map;
        }
        //判定分组下有产品
        Map<String, Object> parameterMap = new HashMap<String, Object>();
        List<String> parentMenuIds = new ArrayList<>();
        parentMenuIds.add(id);
        parameterMap.put("parentMenuIds", parentMenuIds);
        PageInfo<SysMenu> listSysMenuTreePageInfo = sysMenuService.listSysMenus(0, Integer.MAX_VALUE, parameterMap);
        int count = listSysMenuTreePageInfo.getSize();
        if (count > 0) {
            msg = "该分组下有数据，不允许删除！";
            map.put("msg", msg);
            return map;
        }
        int n = sysMenuService.deleteByPrimaryKey(id);
        if (n == 1) {
            msg = "删除分组成功！";
            map.put("msg", msg);
            map.put("code", ServiceCode.SUCCESS_CODE);
            log.info(msg);
            return map;
        }
        msg = "删除分组失败！";
        map.put("msg", msg);
        log.info(msg);
        return map;
    }


    /**
     * 删除菜单
     *
     * @param ids
     * @return
     * @author Frank.liu
     * @create 2019-08-22 下午 16:44
     */
    @Transactional
    @PostMapping("/deleteMenu")
    @ApiOperation(value = "删除菜单", notes = "删除菜单的POST方法")
    @ApiImplicitParams({@ApiImplicitParam(name = "Authorization", value = "Authorization token", required = true, dataType = "string", paramType = "header")})
    public Map<String, Object> deleteMenu(String[] ids) {
        int n = 0;
        for (String id : ids) {
            n += sysMenuService.deleteByPrimaryKey(id);
        }
        Map<String, Object> map = new HashMap<>();
        map.put("msg", "删除成功:" + n + "条记录！");
        if (n == ids.length) {
            map.put("code", ServiceCode.SUCCESS_CODE);
        }
        log.info("删除成功:" + n + "条记录！");
        return map;
    }


    /**
     * 查询菜单
     *
     * @return
     * @author Frank.liu
     * @create 2019-08-22 下午 16:44
     */
    @PostMapping("/getMenu")
    @ApiOperation(value = "查询菜单", notes = "查询菜单的POST方法")
    @ApiImplicitParams({@ApiImplicitParam(name = "Authorization", value = "Authorization token", required = true, dataType = "string", paramType = "header")})
    public Map<String, Object> getMenu(String id) {
        Map<String, Object> map = new HashMap<>();
        String msg = "";
        SysMenu sysMenu = sysMenuService.selectByPrimaryKey(id);
        if (null != sysMenu) {
            map.put("code", ServiceCode.SUCCESS_CODE);
            map.put("sysMenu", sysMenu);
            log.info("查询成功!");
        } else {
            msg = "查询失败！";
            map.put("msg", msg);
            log.info(msg);
        }
        return map;
    }
}
