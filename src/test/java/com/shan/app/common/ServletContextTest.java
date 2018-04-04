package com.shan.app.common;

import java.util.Arrays;
import java.util.Enumeration;

import javax.servlet.ServletContext;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.context.WebApplicationContext;

import com.shan.app.config.CommonConfig;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ServletContextTest {
	
	@Autowired
	private WebApplicationContext wac;
	
	private int i = 1;
		
	@Test
	public void servletTest() {
		ServletContext servletContext = wac.getServletContext();
		System.out.println("servletContext.getEffectiveMajorVersion() = " + servletContext.getEffectiveMajorVersion());
		System.out.println("servletContext.getEffectiveMinorVersion() = " + servletContext.getEffectiveMinorVersion());
		System.out.println("servletContext.getMajorVersion() = " + servletContext.getMajorVersion());
		System.out.println("servletContext.getMinorVersion() = " + servletContext.getMinorVersion());
		System.out.println("servletContext.getContextPath() = " + servletContext.getContextPath());
		System.out.println("servletContext.getRealPath(\"/\") = " + servletContext.getRealPath("/"));
		System.out.println("servletContext.getServletContextName() = " + servletContext.getServletContextName());
		System.out.println("servletContext.getServerInfo() = " + servletContext.getServerInfo());
		System.out.println("servletContext.getInitParameter(\"contextConfigLocation\") = " + servletContext.getInitParameter("contextConfigLocation"));
		
		Enumeration enumeration = servletContext.getServletNames();
		while(enumeration.hasMoreElements()) {
			System.out.println("servletContext.getServletNames() = " + enumeration.nextElement().toString());
		}
		
		Enumeration enumeration2 = servletContext.getServlets();
		while(enumeration2.hasMoreElements()) {
			System.out.println("servletContext.getServlets() = " + enumeration2.nextElement().toString());
		}
	}
	
	@Test
	public void applicationContextTest() {
		System.out.println("startCurrentTimeMillis = " + System.currentTimeMillis());
		ApplicationContext applicationContext = new AnnotationConfigApplicationContext(CommonConfig.class);
		System.out.println("applicationContext = " + applicationContext);
		
		ModelMapper modelMapper = applicationContext.getBean(ModelMapper.class);
		System.out.println("modelMapper = " + modelMapper);
		
		Arrays.asList(applicationContext.getBeanDefinitionNames()).forEach(bean -> {
			System.out.println(i++ + " AnnotationConfigApplicationContext bean name = " + bean);
		});
		
		i = 1;
		Arrays.asList(wac.getBeanDefinitionNames()).forEach(bean -> {
			System.out.println(i++ + " WebApplicationContext bean name = " + bean);
		});
	}

}
