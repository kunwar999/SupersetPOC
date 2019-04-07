package com.superset.model.graph;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.superset.model.Parameter;

@Component
public class Home extends GraphImpl {

	@Value("${graph.home.name}")
	private String homeGraphName;
	@Value(value = "${graph.home.url}")
	private String homeGraphUrl;

	@PostConstruct
	@Override
	public void setDefaultValues() {
		this.graphName = homeGraphName;
	}

	@Override
	public String createUrl() {
		return this.homeGraphUrl;
	}

	@Override
	public boolean validateAndSet(Parameter parameter) {
		return true;
	}
}
