package com.java_spring.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.java_spring.dao.UserDao;
import com.java_spring.entity.UserEntity;

@Service("userService")
public class UserServiceImpl implements UserService {

	@Autowired
	private UserDao userdao;
	
	private UserEntity user;
	
	public UserEntity getUserDetails(int id, String password) throws Exception {	
		try {
			user = userdao.getUserDetails(id, password);
		} catch (Exception e) {
			throw e;
		}
		
		return user;
	}

	public UserEntity insertNewUser(UserEntity user) throws Exception {
		try {
			user = userdao.insertNewUser(user);
		} catch (Exception e) {
			throw e;
		}
		return user;
	}

	public int deleteUser(int userId) throws Exception {
		int success = 0;
		try {
			success = userdao.deleteUser(userId);
		} catch (Exception e) {
			throw e;
		}
		return success;
	}

	public UserEntity updateUser(UserEntity user) throws Exception {
		UserEntity userToUpdate = null;
		try {
			userToUpdate = userdao.updateUser(user);
		} catch (Exception e) {
			throw e;
		}
		return userToUpdate;
	}
	
}
