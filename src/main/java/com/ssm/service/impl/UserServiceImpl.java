/**
 * UserServiceImpl.java
 */
package com.ssm.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.ssm.dao.UserDao;
import com.ssm.entity.User;
import com.ssm.service.UserService;

/**
 * @author wywl Jan 4, 2018 2:04:14 PM modified by :
 */
@Service("userService")
public class UserServiceImpl implements UserService {

	@Resource
	private UserDao userDao;

	@Override
	public User login(String name, String password) {
		return userDao.login(new User(name, password));
	}

	@Override
	public void repass(int id, String pwd) {
		User user = new User();
		user.setId(id);
		user.setPassword(pwd);
		userDao.repassword(user);
	}

}
