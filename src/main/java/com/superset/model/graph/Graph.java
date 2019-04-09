package com.superset.model.graph;

import com.superset.model.Parameter;

public interface Graph {

	public String createUrl();

	public boolean validateAndSet(Parameter parameter);
}
