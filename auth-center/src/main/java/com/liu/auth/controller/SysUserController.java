package com.liu.auth.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.github.pagehelper.PageInfo;
import com.liu.common.util.MyTool;
import com.liu.common.util.MyTreeNode;
import com.liu.common.util.ServiceCode;
import com.liu.auth.domain.Department;
import com.liu.auth.domain.Employee;
import com.liu.auth.domain.SysUser;
import com.liu.auth.domain.SysUserRole;
import com.liu.auth.service.SysRoleService;
import com.liu.auth.service.SysUserRoleService;
import com.liu.auth.service.SysUserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.extern.log4j.Log4j2;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 系统用户，后台控制器
 */
@RestController
@RequestMapping("/user")
@Api(tags={"user"})
@Log4j2
public class SysUserController {
    @Autowired
    SysUserService sysUsersService;
    @Autowired
    SysRoleService sysRoleService;
    @Autowired
    SysUserRoleService sysRoleUsersService;
    @Value("${ry_url}")
    private String mdmUrl;
    @Autowired
    private RestTemplate restTemplate;

    /**
     * 用户管理页面
     */
    @GetMapping("/manage/page")
    @ApiOperation(value = "用户管理", notes = "跳转到用户管理页")
    public ModelAndView manage(@ModelAttribute("sysUsers") SysUser sysUsers) {
        ModelAndView mv = new ModelAndView("user/manage");
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
    @PostMapping("/listSysUsers")
    @ApiOperation(value = "分页查询用户列表",notes = "分页查询用户列表的POST方法")
    @ApiImplicitParams({@ApiImplicitParam(name = "Authorization", value = "Authorization token", required = true, dataType = "string", paramType = "header")})
    public Map<String, Object> listSysUsers(int draw, int start, int length, String name, String username, String teamName, String onlineFlag, String lockFlag, String
            wechatNickname, String remark) {
        Map<String, Object> result = new HashMap<>();
        Map<String, Object> parameterMap = new HashMap<>();

        parameterMap.put("name", name);
        parameterMap.put("username", username);
        parameterMap.put("teamName", teamName);
        parameterMap.put("onlineFlag", onlineFlag);
        parameterMap.put("lockFlag", lockFlag);
        parameterMap.put("wechatNickname", wechatNickname);
        parameterMap.put("remark", remark);

        PageInfo<SysUser> listSysUsersPageInfo = sysUsersService.listSysUsers(start, length, parameterMap);
        long count = listSysUsersPageInfo.getTotal();
        result.put("draw", draw);
        result.put("recordsTotal", count);
        result.put("recordsFiltered", count);
        result.put("data", listSysUsersPageInfo.getList());
        return result;
    }

    /**
     * 添加
     */
    @Transactional
    @PostMapping("/addSysUsers")
    @ApiOperation(value = "新增或修改用户",notes = "新增或修改用户的POST方法")
    @ApiImplicitParams({@ApiImplicitParam(name = "Authorization", value = "Authorization token", required = true, dataType = "string", paramType = "header")})
    public Map<String, Object> addSysUsers(SysUser sysUsers, String[] sysRoleIds) {
        Map<String, Object> map = new HashMap<>();
        String msg = "";
        Map<String, Object> parameterMap = new HashMap<>();
        String id = sysUsers.getId();
        //新增
        //String sysRoleId = null;
        if (StringUtils.isEmpty(id)) {
            //查询已存在
            parameterMap.put("username", sysUsers.getUsername());
            parameterMap.put("equalLikeFlag", "equal");
            PageInfo<SysUser> listSysUsers = sysUsersService.listSysUsers(0, Integer.MAX_VALUE, parameterMap);

            if (listSysUsers.getSize() > 0) {
                msg = "账号已存在！";
                map.put("msg", msg);
                return map;
            }
            //密码加密
            String password = sysUsers.getPassword();
            BCryptPasswordEncoder encode = new BCryptPasswordEncoder();
            String hashStr = encode.encode(password);
            sysUsers.setPassword(hashStr);

            //sysRoleId = MyTool.getUUID();
            sysUsers.setId(MyTool.getUUID());
            //1，角色写入
            int n = sysUsersService.insertSelective(sysUsers);
            //2,角色用户关联写入
            int j = 0;
            if (null != sysRoleIds) {
                for (String sysRoleId : sysRoleIds) {
                    SysUserRole SysUserRole = new SysUserRole();
                    SysUserRole.setId(MyTool.getUUID());
                    SysUserRole.setRoleId(sysRoleId);
                    SysUserRole.setUserId(sysUsers.getId());

                    j += sysRoleUsersService.insertSelective(SysUserRole);
                }
            }
            if (n == 1) {
                msg = "添加成功:" + n + "个用户，" + j + "个关联角色！";
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
            parameterMap.put("username", sysUsers.getUsername());
            parameterMap.put("equalLikeFlag", "equal");
            PageInfo<SysUser> listSysUsers = sysUsersService.listSysUsers(0, Integer.MAX_VALUE, parameterMap);
            if (listSysUsers.getList().size() > 1) {
                msg = "账号已存在！";
                map.put("msg", msg);
                return map;
            } else if (listSysUsers.getSize() == 1) {
                if (!id.equals(listSysUsers.getList().get(0).getId())) {
                    msg = "账号已存在！";
                    map.put("msg", msg);
                    return map;
                }
            }
            //密码非空，修改密码  Frank.liu 2019.6.29
            String password = sysUsers.getPassword();
            if (StringUtils.isNotEmpty(password)) {
                BCryptPasswordEncoder encode = new BCryptPasswordEncoder();
                String hashStr = encode.encode(password);
                sysUsers.setPassword(hashStr);
            }
            //1,用户更新
            int n = sysUsersService.updateByPrimaryKeySelective(sysUsers);
            //2,角色用户关联更新
            int j = 0;
            if (null != sysRoleIds) {
                //2.1 角色 roleId 关联用户删除
                parameterMap.put("userId", id);
                PageInfo<SysUserRole> pageInfos = sysRoleUsersService.listSysRoleUsers(0, Integer.MAX_VALUE, parameterMap);
                if (pageInfos.getSize() > 0) {
                    for (SysUserRole SysUserRole : pageInfos.getList()) {
                        sysRoleUsersService.deleteByPrimaryKey(SysUserRole.getId());
                    }
                }
                //2.2 新角色 roleId 关联用户写入
                for (String sysRoleId : sysRoleIds) {
                    SysUserRole SysUserRole = new SysUserRole();
                    SysUserRole.setId(MyTool.getUUID());
                    SysUserRole.setRoleId(sysRoleId);
                    SysUserRole.setUserId(id);

                    j += sysRoleUsersService.insertSelective(SysUserRole);
                }
            }
            msg = "修改成功:" + n + "个用户，" + j + "个关联角色！";
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
     * @author Frank.liu
     * @date 2019-8-22
     */
    @Transactional
    @PostMapping("/deleteUsers")
    @ApiOperation(value = "删除用户",notes = "删除用户的POST方法")
    @ApiImplicitParams({@ApiImplicitParam(name = "Authorization", value = "Authorization token", required = true, dataType = "string", paramType = "header")})
    public Map<String, Object> deleteUsers(String[] ids) {
        int n = 0;
        for (String roleId : ids) {
            n += sysUsersService.deleteByPrimaryKey(roleId);
            // roleId 关联用户删除
            Map<String, Object> parameterMap = new HashMap<String, Object>();
            parameterMap.put("roleId", roleId);
            PageInfo<SysUserRole> pageInfos = sysRoleUsersService.listSysRoleUsers(0, Integer.MAX_VALUE, parameterMap);
            if (pageInfos.getSize() > 0) {
                for (SysUserRole SysUserRole : pageInfos.getList()) {
                    sysRoleUsersService.deleteByPrimaryKey(SysUserRole.getId());
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
     * @return
     */
    @PostMapping("/getUsers")
    @ApiOperation(value = "根据用户主键或者用户名查询用户",notes = "根据用户主键或用户名查询用户的POST方法")
    @ApiImplicitParams({@ApiImplicitParam(name = "Authorization", value = "Authorization token", required = true, dataType = "string", paramType = "header")})
    public Map<String, Object> getUsers(String id, String username) {
        Map<String, Object> map = new HashMap<>();
        String msg;
        SysUser sysUsers;
        //1,根据id 查询  Frank.liu 2019.9.28
        if(StringUtils.isNotEmpty(id)){
            sysUsers = sysUsersService.selectByPrimaryKey(id);
            map.put("code", ServiceCode.SUCCESS_CODE);
            map.put("sysUsers", sysUsers);
            //查询关联角色主键
            List<String> checkedRoleIds = new ArrayList<>();
            Map<String, Object> parameterMap = new HashMap<String, Object>();
            parameterMap.put("userId", sysUsers.getId());
            PageInfo<SysUserRole> lists = sysRoleUsersService.listSysRoleUsers(0, Integer.MAX_VALUE, parameterMap);
            for (SysUserRole SysUserRole : lists.getList()) {
                checkedRoleIds.add(SysUserRole.getRoleId());
            }
            if (checkedRoleIds.size() > 0) {
                map.put("checkedRoleIds", checkedRoleIds);
            }
            log.info("查询成功!");
            return map;
        }
        //根据账号查询 Frank.liu 2019.9.28
        else if(StringUtils.isNotEmpty(username)){
            Map<String, Object> parameterMap = new HashMap<String, Object>();
            parameterMap.put("username",username);
            parameterMap.put("equalLikeFlag","equal");

            PageInfo<SysUser> usersList = sysUsersService.listSysUsers(0,Integer.MAX_VALUE,parameterMap);
            if(usersList.getSize()==1){
                sysUsers = usersList.getList().get(0);
                map.put("code", ServiceCode.SUCCESS_CODE);
                map.put("sysUsers", sysUsers);
                log.info("查询成功!");
                return map;
            }
        }
        msg = "查询用户失败！";
        map.put("msg", msg);
        log.info(msg);
        return map;
    }
    /**
     * 修改密码
     * Frank.liu 2019.7.4
     *
     * @return
     */
    @Transactional
    @PostMapping("/updatePassword")
    @ApiOperation(value = "修改用户密码",notes = "修改用户密码的POST方法")
    @ApiImplicitParams({@ApiImplicitParam(name = "Authorization", value = "Authorization token", required = true, dataType = "string", paramType = "header")})
    public Map<String, Object> updatePassword(String oldPassword, String newPassword, String username) {
        Map<String, Object> map = new HashMap<>();
        String msg = "";
        SysUser sysUsers = null;
        if (StringUtils.isNotEmpty(username)) {
            Map<String, Object> parameterMap = new HashMap<String, Object>();
            parameterMap.put("username", username);
            parameterMap.put("equalLikeFlag", "equal");

            PageInfo<SysUser> usersList = sysUsersService.listSysUsers(0, Integer.MAX_VALUE, parameterMap);
            if (usersList.getSize() == 1) {
                sysUsers = usersList.getList().get(0);
            }
            if (usersList.getSize() == 1) {
                sysUsers = usersList.getList().get(0);
                String password = sysUsers.getPassword();
                //校验原密码
                BCryptPasswordEncoder encode = new BCryptPasswordEncoder();
                if (encode.matches(oldPassword, password)) {
                    sysUsers.setPassword(encode.encode(newPassword));
                    sysUsersService.updateByPrimaryKeySelective(sysUsers);
                    msg = "修改密码成功！";
                    map.put("code", ServiceCode.SUCCESS_CODE);
                    map.put("msg", msg);
                    log.info(msg);
                } else {
                    msg = "原密码不正确！";
                    map.put("msg", msg);
                    log.info(msg);
                }
            } else {
                msg = "账号查询异常！";
                map.put("msg", msg);
                log.info(msg);
            }
        } else {
            msg = "账号为空！";
            map.put("msg", msg);
            log.info(msg);
        }
        return map;
    }

    @PostMapping("/loadDepartmentsTree")
    @ApiOperation(value = "查询部门树，目前没有部门管理功能，暂时先放在用户管理下")
    @ApiImplicitParams({@ApiImplicitParam(name = "Authorization", value = "Authorization token", required = true, dataType = "string", paramType = "header")})
    public List<MyTreeNode> LoadDepartmentsTree(Department department){
        @SuppressWarnings("unchecked")
        List<MyTreeNode> myTreeNodes = restTemplate.getForObject(mdmUrl + "department/loadDepartmentsTree", List.class);
        return myTreeNodes;
    }

    @PostMapping("/loadEmployeeByDepart")
    @ApiOperation(value = "查询员工所在的部门，目前没有部门管理功能，暂时先放在用户管理下")
    @ApiImplicitParams({@ApiImplicitParam(name = "Authorization", value = "Authorization token", required = true, dataType = "string", paramType = "header")})
    public JSONArray loadEmployeeByDepartment(Employee employee){
        String employees = restTemplate.postForObject(mdmUrl + "employee/selectSelective", employee, String.class);
        JSONArray jsonArray = JSON.parseArray(employees);
        return jsonArray;
    }

    @PostMapping("/selectEmpNameAndDepartName")
    @ApiOperation(value = "根据员工编号查询员工的名字及部门的名字，目前没有部门管理功能，暂时先放在用户管理下")
    @ApiImplicitParams({@ApiImplicitParam(name = "Authorization", value = "Authorization token", required = true, dataType = "string", paramType = "header")})
    public Map<String,String> selectEmpNameAndDepartName(String userId){
        Map<String,String> res = new HashMap<>();
        ResponseEntity<Employee> employee = restTemplate.getForEntity(mdmUrl + "employee/" + userId, Employee.class);
        res.put("name",employee.getBody().getName());
        res.put("userId",employee.getBody().getUserId());

        String departmentId = employee.getBody().getDepartmentId();
        //一个人可能又多个部门
        String[] departmentIdArr = departmentId.split(",");
        StringBuffer departmentName = new StringBuffer("");
        for(int i = 0; i < departmentIdArr.length; i++) {
            ResponseEntity<Department> department = restTemplate.getForEntity(mdmUrl + "department/" + departmentIdArr[i], Department.class);
            if(i == 0) {
                departmentName.append(department.getBody().getName());
            }else {
                departmentName.append(",").append(department.getBody().getName());
            }
        }
        res.put("departmentName",departmentName.toString());
        res.put("departmentId",departmentId);
        return res;
    }
}
