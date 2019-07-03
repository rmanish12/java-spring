package com.java_spring;

import static org.junit.Assert.assertEquals;

import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.java_spring.entity.UserEntity;
import com.java_spring.exceptions.PasswordMismatch;
import com.java_spring.exceptions.UserAlreadyExists;
import com.java_spring.exceptions.UserNotFound;
import com.java_spring.service.UserServiceImpl;

public class UserDaoImplTest {
	
	private static AbstractApplicationContext ctx;
	private static UserServiceImpl userService;
	
	@BeforeClass
	public static void setup() {
		ctx = new ClassPathXmlApplicationContext("config.xml");
		userService = (UserServiceImpl) ctx.getBean("userService");
	}
	
	@Test
	public void testGetUserDetails() throws Exception {
		UserEntity user = userService.getUserDetails(1, "e6a33eee180b07e563d74fee8c2c66b8");
		
		assertEquals("david", user.getFirstName());
		assertEquals("Female", user.getGender());
	}
	
	@Test(expected=UserNotFound.class)
	public void testGetUserDetailsUserException() throws Exception {
		UserEntity user = userService.getUserDetails(108, "e6a33eee180b07e563d74fee8c2c66b8");
		
		assertEquals("david", user.getFirstName());
		assertEquals("Female", user.getGender());
	}
	
	@Test(expected=PasswordMismatch.class)
	public void testGetUserDetailsPasswordException() throws Exception {
		UserEntity user = userService.getUserDetails(1, "e6a33eee180b07e563d74fee8c");
		
		assertEquals("david", user.getFirstName());
		assertEquals("Female", user.getGender());
	}
	
//	@Test
//	public void testInsertNewUser() throws Exception {
//		UserEntity user = new UserEntity();
//		user.setId(104);
//		user.setFirstName("michael");
//		user.setLastName("maria");
//		user.setGender("Male");
//		user.setPassword("101faf06bcf8140ead914fbe116c941a");
//		user.setStatus(1);
//		user.setUsername("cooper77");
//		
//		userService.insertNewUser(user);
//	}
	
	@Test(expected=UserAlreadyExists.class)
	public void testInsertNewUserException() throws Exception {
		UserEntity user = new UserEntity();
		user.setId(10);
		user.setFirstName("michael");
		user.setLastName("maria");
		user.setGender("Male");
		user.setPassword("101faf06bcf8140ead914fbe116c941a");
		user.setStatus(1);
		user.setUsername("cooper77");
		
		userService.insertNewUser(user);
	}
	
//	@Test
//	public void testDeleteUser() throws Exception {
//		int success = userService.deleteUser(15);
//		
//		assertEquals(1, success);
//	}
	
	@Test(expected=UserNotFound.class)
	public void testDeleteUserException() throws Exception {
		int success = userService.deleteUser(15);
		
		assertEquals(1, success);
	}
	
	@Test
	public void testUpdateUser() throws Exception {
		UserEntity userToUpdate = new UserEntity();
		userToUpdate.setId(12);
		userToUpdate.setFirstName("paul");
		userToUpdate.setLastName("michael");
		userToUpdate.setGender("Male");
		userToUpdate.setStatus(1);
		userToUpdate.setPassword("a28dca31f5aa5792e1cefd1dfd098569");
		userToUpdate.setUsername("morgan65");
		
		UserEntity user = userService.updateUser(userToUpdate);
		
		assertEquals("michael", user.getLastName());
		assertEquals("Male", user.getGender());
	}
	
	@Test(expected=UserNotFound.class)
	public void testUpdateUserException() throws Exception {
		UserEntity userToUpdate = new UserEntity();
		userToUpdate.setId(149);
		userToUpdate.setFirstName("paul");
		userToUpdate.setLastName("michael");
		userToUpdate.setGender("Male");
		userToUpdate.setStatus(1);
		userToUpdate.setPassword("a28dca31f5aa5792e1cefd1dfd098569");
		userToUpdate.setUsername("morgan65");
		
		UserEntity user = userService.updateUser(userToUpdate);
		
		assertEquals("michael", user.getLastName());
		assertEquals("Male", user.getGender());
	}
	
}
