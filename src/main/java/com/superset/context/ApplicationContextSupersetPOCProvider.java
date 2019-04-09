package com.superset.context;

import java.util.List;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.EnvironmentAware;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import com.superset.service.GraphFinder;

@Component
public class ApplicationContextSupersetPOCProvider implements ApplicationContextAware, EnvironmentAware {

	private static ApplicationContext applicationContext;
	private static Environment environment;

	@Override
	public void setApplicationContext(ApplicationContext context) throws BeansException {
		applicationContext = context;
	}

	@Override
	public void setEnvironment(Environment env) {
		environment = env;
	}

	public static ApplicationContext getContext() {
		return applicationContext;
	}

	public static GraphFinder getGraphFinder() {
		return getContext().getBean("graphFinder", GraphFinder.class);
	}

	public static String getProperty(String propertyName) {
		return environment.getProperty(propertyName);
	}

//	public static List<String> getPropertyList(String propertyName) {
//		return environment.get
//	}
}