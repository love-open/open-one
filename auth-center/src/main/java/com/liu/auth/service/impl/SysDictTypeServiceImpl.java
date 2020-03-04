package com.liu.auth.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.liu.auth.domain.SysDictType;
import com.liu.auth.mapper.SysDictTypeMapper;
import com.liu.auth.service.SysDictTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;


@Service
public class SysDictTypeServiceImpl implements SysDictTypeService {

    @Autowired
    SysDictTypeMapper sysDictTypeMapper;


    @Override
    public int deleteByPrimaryKey(String id) {
        return sysDictTypeMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int insertSelective(SysDictType sysDictType) {
        return sysDictTypeMapper.insertSelective(sysDictType);
    }

    @Override
    public SysDictType selectByPrimaryKey(String id) {
        return sysDictTypeMapper.selectByPrimaryKey(id);
    }

    @Override
    public int updateByPrimaryKeySelective(SysDictType sysDictType) {
        return sysDictTypeMapper.updateByPrimaryKeySelective(sysDictType);
    }


    @Override
    public PageInfo<SysDictType> listSysDictTypes(int start, int length, Map<String, Object> parameterMap) {
        PageHelper.offsetPage(start, length);
        List<SysDictType> listSysDictTypes = sysDictTypeMapper.listSysDictTypes(parameterMap);
        PageInfo<SysDictType> listSysDictTypePageInfo = new PageInfo<>(listSysDictTypes);
        return listSysDictTypePageInfo;
    }

    @Override
    public List<SysDictType> getDictByParams(Map<String, Object> params) {
        return sysDictTypeMapper.getDictByParams(params);
    }
}
