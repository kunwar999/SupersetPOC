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
	private GraphImpl graph;

	public Parameters() {
		this.parameters = new ArrayList<Parameter>();
	}

	public String createUrl() {
		return graph.createUrl();
	}

	public String validate(String invalidGraphMessage, String invalidParameterMessage) {
		GraphFinder graphFinder = ApplicationContextSupersetPOCProvider.getGraphFinder();
		if (graphFinder.findGraph(graphName) == null) {
			return graphName + " " + invalidGraphMessage;
		}
		this.graph = new GraphImpl(this.graphName);
		String invalidAttributes = validateAndSet(this.graph);
		if (!StringUtils.isEmpty(invalidAttributes)) {
			return invalidAttributes + " " + invalidParameterMessage;
		}
		return null;
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
}