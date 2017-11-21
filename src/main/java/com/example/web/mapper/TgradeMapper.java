package com.example.web.mapper;

import com.example.web.entity.Tgrade;

public interface TgradeMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Tgrade record);

    int insertSelective(Tgrade record);

    Tgrade selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Tgrade record);

    int updateByPrimaryKey(Tgrade record);
    
}