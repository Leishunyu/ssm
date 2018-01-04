/**
 * UserService.java
 */
package com.ssm.service;

import com.ssm.entity.User;

/**
 * @author wywl Jan 4, 2018 2:03:29 PM modified by :
 */
public interface UserService {

	/**
	 * @param name
	 * @param string2md5
	 * @return
	 */
	User login(String name, String string2md5);

	/**
	 * @param id
	 * @param string2md5
	 */
	void repass(int id, String string2md5);

}
