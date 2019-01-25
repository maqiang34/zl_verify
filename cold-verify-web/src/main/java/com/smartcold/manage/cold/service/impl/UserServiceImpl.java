package com.smartcold.manage.cold.service.impl;

import com.smartcold.manage.cold.dao.UserMapper;
import com.smartcold.manage.cold.entity.UserEntity;
import com.smartcold.manage.cold.service.UserService;
import com.smartcold.manage.cold.util.MD5Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service("userService")
public class UserServiceImpl implements UserService {
	@Autowired
	private UserMapper userDao;
	@Autowired
	private UserMapper userMapper;

	@Override
	public UserEntity login(String username, String password) {
		try {
			return userDao.login(username, MD5Utils.encrypt(username, password));
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
}
	

