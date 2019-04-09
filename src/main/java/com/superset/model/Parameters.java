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

	public String validateAndSet(String invalidGraphMessage, String invalidParameterMessage) {
		GraphFinder graphFinder = ApplicationContextSupersetPOCProvider.getGraphFinder();
		if (graphFinder.findGraph(graphName) == null) {
			return graphName + " " + invalidGraphMessage;
		}
		this.graph = new GraphImpl(this.graphName);
		String invalidAttributes = this.graph.validateAndSet(this.parameters);
		if (!StringUtils.isEmpty(invalidAttributes)) {
			return invalidAttributes + " " + invalidParameterMessage;
		}
		return null;
	}

	public String getGraphName() {
		return graphName;
	}

	public List<Parameter> getParameters() {
		return parameters;
	}
}