package com.superset.context;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

import com.superset.model.graph.GraphImpl;
import com.superset.model.graph.Home;
import com.superset.service.GraphFinder;

@Component
public class ApplicationContextSupersetPOCProvider implements ApplicationContextAware {

	private static ApplicationContext applicationContext;

	@Override
	public void setApplicationContext(ApplicationContext context) throws BeansException {
		applicationContext = context;
	}

	public static ApplicationContext getContext() {
		return applicationContext;
	}

	public static GraphImpl getHomeGraph() {
		return getContext().getBean("home", Home.class);
	}

	public static GraphFinder getGraphFinder() {
		return getContext().getBean("graphFinder", GraphFinder.class);
	}
}