/**
 * UserDao.java
 */
package com.ssm.dao;

import com.ssm.entity.User;

/**
 * @author wywl Jan 4, 2018 2:07:13 PM modified by :
 */
public interface UserDao {

	/**
	 * @param user
	 * @return
	 */
	User login(User user);

	/**
	 * @param user
	 */
	void repassword(User user);

}
