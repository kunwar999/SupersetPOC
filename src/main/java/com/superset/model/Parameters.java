package com.superset.model;

import java.util.ArrayList;
import java.util.List;

import org.springframework.util.StringUtils;

import com.superset.context.ApplicationContextSupersetPOCProvider;
import com.superset.model.graph.GraphImpl;
import com.superset.service.GraphFinder;

public class Parameters {

	private String graphName;
	private List<Parameter> parameters;

	public Parameters() {
		this.parameters = new ArrayList<Parameter>();
	}

	public String createUrl() {
		GraphImpl graph = findGraph();
		return graph.createUrl();
	}

	public String validate(String invalidGraphMessage, String invalidParameterMessage) {
		GraphImpl graph = findGraph();
		if (graph == null) {
			return graphName + " " + invalidGraphMessage;
		}
		String invalidAttributes = validateAndSet(graph);
		if (!StringUtils.isEmpty(invalidAttributes)) {
			return invalidAttributes + " " + invalidParameterMessage;
		}
		return null;
	}

	private GraphImpl findGraph() {
		GraphFinder graphFinder = ApplicationContextSupersetPOCProvider.getGraphFinder();
		GraphImpl graph = graphFinder.findGraph(graphName);
		return graph;
	}

	private String validateAndSet(GraphImpl graph) {
		graph.setDefaultValues();
		StringBuffer invalidAttributes = new StringBuffer();
		parameters.stream().forEach(parameter -> {
			if (!graph.validateAndSet(parameter)) {
				invalidAttributes.append(parameter.getKey());
			}
		});
		return invalidAttributes.toString();
	}

	public String getGraphName() {
		return graphName;
	}

	public List<Parameter> getParameters() {
		return parameters;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((graphName == null) ? 0 : graphName.hashCode());
		result = prime * result + ((parameters == null) ? 0 : parameters.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Parameters other = (Parameters) obj;
		if (graphName == null) {
			if (other.graphName != null)
				return false;
		} else if (!graphName.equals(other.graphName))
			return false;
		if (parameters == null) {
			if (other.parameters != null)
				return false;
		} else if (!parameters.equals(other.parameters))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Parameters [graphName=" + graphName + ", parameters=" + parameters + "]";
	}
}