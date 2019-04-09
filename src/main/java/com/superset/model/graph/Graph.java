package com.superset.model.graph;

import java.util.List;

import com.superset.model.Parameter;

public interface Graph {

	public String createUrl();

	public String validateAndSet(List<Parameter> parameters);

	public boolean isHomeGraph();
}
