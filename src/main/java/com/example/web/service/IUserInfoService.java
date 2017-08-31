package com.example.web.service;

import com.example.web.entity.UserInfo;

public interface IUserInfoService {
	public int deleteByPrimaryKey(Long id);

	public int insert(UserInfo record);

	public int insertSelective(UserInfo record);

	public UserInfo selectByPrimaryKey(Long id);

	public int updateByPrimaryKeySelective(UserInfo record);

	public int updateByPrimaryKey(UserInfo record);
}
