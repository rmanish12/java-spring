package com.java_spring.dao;

import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.java_spring.entity.UserEntity;
import com.java_spring.exceptions.PasswordMismatch;
import com.java_spring.exceptions.UserAlreadyExists;
import com.java_spring.exceptions.UserNotFound;

@Repository
public class UserDaoImpl implements UserDao{
	
	static Logger logger = Logger.getLogger(UserDaoImpl.class);

	@Autowired
	private SessionFactory sessionFactory;

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	
	public UserEntity getUserDetails(int id, String password) throws Exception {
		
		UserEntity user = null;
		
		try {
			Session session = sessionFactory.openSession();
			
			user = session.get(UserEntity.class, id);
			
			if(user!=null) {
				logger.info("User found");
				
				if(user.getPassword().equals(password)) {
					logger.info("Password match found");
				} else {
					throw new PasswordMismatch("Password mismatch");
				}
			} else {
				throw new UserNotFound("User not found");
			}
		} catch (Exception e) {
			logger.error(e);
			throw e;
		}
		
		return user;
	}
	
	public UserEntity insertNewUser(UserEntity user) throws Exception {
		UserEntity newUser = null;
		
		try {
			Session session = sessionFactory.openSession();
			
			newUser = session.get(UserEntity.class, user.getId());
			
			if(newUser==null) {
				session.save(user);
				session.beginTransaction().commit();
				
				logger.info("Record successfully saved.");
				
				newUser = session.get(UserEntity.class, user.getId());
			} else {
				throw new UserAlreadyExists("User already present");
			}
		} catch (Exception e) {
			logger.error(e);
			throw e;
		}
		
		return newUser;
	}
	
	public int deleteUser(int userId) throws Exception {
		int success = 0;
		UserEntity user = null;
		
		try {
			Session session = sessionFactory.openSession();
			
			user = session.get(UserEntity.class, userId);
			
			if(user==null) {
				throw new UserNotFound("User not found");
			} else {
				session.delete(user);
				session.beginTransaction().commit();
				
				success = 1;
				logger.info("Record deleted successfully");
			}
		} catch (Exception e) {
			logger.error(e);
			throw e;
		}
		
		return success;
	}
	
	public UserEntity updateUser(UserEntity user) throws Exception {
		UserEntity updatedUser = null;
		
		try {
			Session session = sessionFactory.openSession();
			
			updatedUser = session.get(UserEntity.class, user.getId());
			
			if(updatedUser==null) {
				throw new UserNotFound("User not found");
			} else {
				updatedUser.setId(user.getId());
				updatedUser.setFirstName(user.getFirstName());
				updatedUser.setLastName(user.getLastName());
				updatedUser.setGender(user.getGender());
				updatedUser.setPassword(user.getPassword());
				updatedUser.setStatus(user.getStatus());
				updatedUser.setUsername(user.getUsername());
				
				session.save(updatedUser);
				session.beginTransaction().commit();
				
				logger.info("User updated successfully");
			}
		} catch (Exception e) {
			logger.error(e);
			throw e;
		}
		
		return updatedUser;
	}
}
