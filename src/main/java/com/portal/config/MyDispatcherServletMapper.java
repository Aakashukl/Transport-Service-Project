package com.portal.config;

import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

public class MyDispatcherServletMapper extends AbstractAnnotationConfigDispatcherServletInitializer {
	protected Class<?>[] getRootConfigClasses() {
		// TODO Auto-generated method stub
		return null;
	}

	protected Class<?>[] getServletConfigClasses() {
		return new Class[] { ProjectConfig.class };
	}

	protected String[] getServletMappings() {
		return new String[] { "/" };
	}
}
