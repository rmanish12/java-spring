package com.java_spring.dao;

import com.java_spring.entity.UserEntity;

public interface UserDao {
	public UserEntity getUserDetails(int id, String password) throws Exception;
	public UserEntity insertNewUser(UserEntity user) throws Exception;
	public int deleteUser(int userId) throws Exception;
	public UserEntity updateUser(UserEntity user) throws Exception;
}
