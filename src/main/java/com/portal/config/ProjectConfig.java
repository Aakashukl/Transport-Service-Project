package com.portal.config;

import java.beans.PropertyVetoException;
import java.util.Properties;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import com.mchange.v2.c3p0.ComboPooledDataSource;

@Configuration
@EnableWebMvc
@ComponentScan(basePackages = "com.portal")
public class ProjectConfig {
	
	@Bean
	public InternalResourceViewResolver viewResolver(){
		InternalResourceViewResolver vr=new InternalResourceViewResolver();
		vr.setSuffix(".jsp");
		vr.setPrefix("/WEB-INF/Views/");
		return vr;
	}
	
	
	@Bean
	public LocalSessionFactoryBean sessionFactory(){
		LocalSessionFactoryBean sessionFactory=new LocalSessionFactoryBean();
		sessionFactory.setDataSource(myDataSource());
		sessionFactory.setPackagesToScan("com.portal");
		Properties props=new Properties();
		props.put("hibernate.dialect", "org.hibernate.dialect.MySQL5Dialect");
		props.put("hibernate.show_sql", "true");
		props.put("hibernate.hbm2ddl.auto", "update");
		
		sessionFactory.setHibernateProperties(props);
		
		return sessionFactory;
	}
	
	@Bean
	public ComboPooledDataSource myDataSource(){
		ComboPooledDataSource ob=new ComboPooledDataSource();
		try {
			ob.setDriverClass("com.mysql.jdbc.Driver");
		} catch (PropertyVetoException e) {
			e.printStackTrace();
		}
		ob.setJdbcUrl("jdbc:mysql://localhost:3306/TransportProject");
		ob.setUser("root");
		ob.setPassword("root");
		
		return ob;
	}
	
	
	@Bean
	public CommonsMultipartResolver multipartResolver() {
	    CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver();
	    multipartResolver.setMaxUploadSize(5000000);
	    return multipartResolver;
	}
	
	@Bean
	public JavaMailSenderImpl mailSender(){
		JavaMailSenderImpl jms=new JavaMailSenderImpl();
		jms.setHost("smtp.gmail.com");
		jms.setPort(587);
		jms.setUsername("worldpaygte@gmail.com");
		jms.setPassword("akash@123");
		Properties properties=new Properties();
		properties.setProperty("mail.transport.protocol", "smtp");
		properties.setProperty("mail.smtp.auth", "true");
		properties.setProperty("mail.smtp.starttls.enable", "true");
		jms.setJavaMailProperties(properties);
		return jms;
	}
	
}
