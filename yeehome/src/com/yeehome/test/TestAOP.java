package com.yeehome.test;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.yeehome.iservice.IUserService;

public class TestAOP {

	@Test
	public void testAop() throws Exception{
		ApplicationContext context=new ClassPathXmlApplicationContext("applicationContext.xml");
		IUserService userService=context.getBean(IUserService.class);
		userService.add(null);
	}
}
