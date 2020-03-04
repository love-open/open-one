package com.liu.auth.controller;

import com.github.pagehelper.PageInfo;
import com.liu.auth.domain.SysDictType;
import com.liu.auth.service.SysDictTypeService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashMap;
import java.util.Map;

/**
 * 用户字典，后台控制器
 *
 * @author Frank.liu
 * @create 2019-07-05 下午 16:44
 **/
@Controller
@RequestMapping("/dictType")
@Api(tags = {"dict"})
@Log4j2
public class SysDictTypeController {
    @Autowired
    SysDictTypeService sysDictTypeService;

    /**
     * 字典管理页面
     */
    @GetMapping("/manage/page")
    @ApiOperation(value = "字典管理", notes = "跳转到字典管理页")
    public ModelAndView manage(Model model) {
        SysDictType sysDictType = new SysDictType();
        model.addAttribute("sysDictType", sysDictType);
        ModelAndView mv = new ModelAndView("dictType/manage");
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
    @PostMapping("/listSysDictTypes")
    @ResponseBody
    @ApiOperation(value = "分页查询字典管理列表", notes = "分页查询字典管理列表的POST方法")
    @ApiImplicitParams({@ApiImplicitParam(name = "Authorization", value = "Authorization token", required = true, dataType = "string", paramType = "header")})
    public Map<String, Object> listSysDictTypes(int draw, int start, int length, String code, String value, String name) {
        Map<String, Object> result = new HashMap<String, Object>();
        Map<String, Object> parameterMap = new HashMap<String, Object>();

        parameterMap.put("code", code);
        parameterMap.put("value", value);
        parameterMap.put("name", name);

        PageInfo<SysDictType> listSysDictTypes = sysDictTypeService.listSysDictTypes(start, length, parameterMap);
        long count = listSysDictTypes.getTotal();
        result.put("draw", draw);
        result.put("recordsTotal", count);
        result.put("recordsFiltered", count);
        result.put("data", listSysDictTypes.getList());
        return result;
    }


    /**
     * 添加
     */
    @Transactional
    @PostMapping("/addSysDictType")
    @ResponseBody
    @ApiOperation(value = "添加和修改字典管理",notes = "添加和修改字典管理的POST方法")
    @ApiImplicitParams({@ApiImplicitParam(name = "Authorization", value = "Authorization token", required = true, dataType = "string", paramType = "header")})
    public Map<String, Object> addSysDictType(SysDictType sysDictType) {
        Map<String, Object> map = new HashMap<>();
        String msg = "";
        Map<String, Object> parameterMap = new HashMap<String, Object>();
        Integer id = sysDictType.getId();
        //新增
        if (null == id||"".equals(id)) {
            //查询已存在
            parameterMap.put("id", sysDictType.getId());
            parameterMap.put("equalLikeFlag", "equal");

//            sysDictType.setId(MyTool.getUUID());
            int n = sysDictTypeService.insertSelective(sysDictType);
            if (n == 1) {
                msg = "添加成功！";
                map.put("msg", msg);
                map.put("code", 200);
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
            parameterMap.put("id", sysDictType.getId());
            parameterMap.put("equalLikeFlag", "equal");
            parameterMap.put("start", 0);
            parameterMap.put("length", Integer.MAX_VALUE);

            PageInfo<SysDictType> dictTypes = sysDictTypeService.listSysDictTypes(0, Integer.MAX_VALUE, parameterMap);

            if (dictTypes.getSize() == 1) {
                msg = "编码已存在！";
                map.put("msg", msg);
                return map;
            }
//            int n = 
            sysDictTypeService.updateByPrimaryKeySelective(sysDictType);
            msg = "修改成功！";
            map.put("msg", msg);
            map.put("code", 200);
        }
        log.info(msg);
        return map;
    }


    /**
     * 删除
     *
     * @param ids
     * @return
     */
    @Transactional
    @PostMapping("/sysDictTypeDelete")
    @ResponseBody
    @ApiOperation(value = "删除字典管理",notes = "删除字典管理的POST方法")
    @ApiImplicitParams({@ApiImplicitParam(name = "Authorization", value = "Authorization token", required = true, dataType = "string", paramType = "header")})
    public Map<String, Object> sysDictTypeDelete(String[] ids) {
        int n = 0;
        for (String id : ids) {
            n += sysDictTypeService.deleteByPrimaryKey(id);
        }
        Map<String, Object> map = new HashMap<>();
        map.put("msg", "删除成功:" + n + "条记录！");
        if (n == ids.length) {
            map.put("code", 200);
        }
        log.info("删除成功:" + n + "条记录！");
        return map;
    }


    /**
     * 查询
     *
     * @return
     */
    @PostMapping("/getDictType")
    @ResponseBody
    @ApiOperation(value = "查询字典管理",notes = "查询字典管理的POST方法")
    @ApiImplicitParams({@ApiImplicitParam(name = "Authorization", value = "Authorization token", required = true, dataType = "string", paramType = "header")})
    public Map<String, Object> getDictType(String id) {
        Map<String, Object> map = new HashMap<>();
        String msg = "";
        SysDictType sysDictType = sysDictTypeService.selectByPrimaryKey(id);
        if (null != sysDictType) {
            map.put("code", 200);
            map.put("sysDictType", sysDictType);
            log.info("查询成功!");
        } else {
            msg = "查询失败！";
            map.put("msg", msg);
            log.info(msg);
        }
        return map;
    }
}
