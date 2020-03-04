package com.liu.auth.service;


import com.github.pagehelper.PageInfo;
import com.liu.auth.domain.SysDictType;

import java.util.List;
import java.util.Map;


public interface SysDictTypeService {
    int deleteByPrimaryKey(String id);

    int insertSelective(SysDictType sysDictType);

    SysDictType selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(SysDictType sysDictType);


    /**
     * 分页查询
     *
     * @param start        当前页起始值
     * @param length       每页显示数量
     * @param parameterMap 查询参数
     * @return 当前页List
     */
    PageInfo<SysDictType> listSysDictTypes(int start, int length, Map<String, Object> parameterMap);

    List<SysDictType> getDictByParams(Map<String, Object> params);
}
