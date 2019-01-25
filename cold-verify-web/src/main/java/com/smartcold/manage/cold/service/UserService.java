package com.smartcold.manage.cold.service;

import com.smartcold.manage.cold.entity.UserEntity;



/**
 * Copyright (C) SmartCold 版权所有
 *
 * @author MaQiang34
 * @Description: 用户登录
 * @createDate:2018/7/16 0016 12:14
 * @version:V1.0
 */
public interface UserService {
	
	public UserEntity login(String username, String password);

}