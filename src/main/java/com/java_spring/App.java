package com.java_spring;

import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.java_spring.entity.UserEntity;
import com.java_spring.service.UserServiceImpl;

public class App 
{
    public static void main( String[] args )
    {
        AbstractApplicationContext ctx = new ClassPathXmlApplicationContext("config.xml");
        
        UserServiceImpl userService = (UserServiceImpl) ctx.getBean("userService");
//        userService.getUserDetails(200, "e6a33eee180b07e563d74fee8c2c66b8");
        
//        userService.getUserDetails(2, "2e7dc6b8a1598f4f75c3eaa4795");
        
//        System.out.println(userService.getUserDetails(2, "2e7dc6b8a1598f4f75c3eaa47958ee2f"));
        
        UserEntity user = new UserEntity();
        user.setId(102);
        user.setFirstName("daniel");
        user.setLastName("michael");
        user.setGender("Female");
        user.setPassword("b7f72d6eb92b45458020748c8d1a3573");
        user.setStatus(1);
        user.setUsername("smith67");
        
        try {
        	userService.insertNewUser(user);
        } catch (Exception e) {
			e.printStackTrace();
		}
       
        ctx.close();
    }
}
