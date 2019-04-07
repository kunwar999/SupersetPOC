package com.superset.model.graph;

import com.superset.context.ApplicationContextSupersetPOCProvider;
import com.superset.model.Parameter;

public interface Graph {

	default boolean isExpectedGraph(String name) {
		return false;
	}

	default String createUrl() {
		GraphImpl home = ApplicationContextSupersetPOCProvider.getHomeGraph();
		return home.createUrl();
	}

	default boolean validateAndSet(Parameter parameter) {
		GraphImpl home = ApplicationContextSupersetPOCProvider.getHomeGraph();
		return home.validateAndSet(parameter);
	}

	public void setDefaultValues();
}
