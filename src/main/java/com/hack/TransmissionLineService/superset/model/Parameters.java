package com.hack.TransmissionLineService.superset.model;

import java.util.ArrayList;
import java.util.List;

import org.springframework.util.StringUtils;

import com.hack.TransmissionLineService.superset.constant.SupersetConstants;
import com.hack.TransmissionLineService.superset.context.ApplicationContextSupersetPOCProvider;
import com.hack.TransmissionLineService.superset.model.graph.GraphImpl;
import com.hack.TransmissionLineService.superset.service.GraphFinder;

public class Parameters {

	private String graphName;
	private List<Parameter> parameters;
	private GraphImpl graph;

	public Parameters() {
		this.parameters = new ArrayList<Parameter>();
	}

	public String createUrl() {
		return this.graph.createUrl();
	}

	public String validateAndSet() {
		GraphFinder graphFinder = ApplicationContextSupersetPOCProvider.getGraphFinder();
		if (graphFinder.findGraph(this.graphName) == null) {
			return this.graphName + SupersetConstants.SPACE
					+ ApplicationContextSupersetPOCProvider.getProperty(SupersetConstants.INVALID_GRAPH_PROPERTY);
		}
		this.graph = new GraphImpl(this.graphName);
		String invalidAttributes = this.graph.validateAndSet(this.parameters);
		if (!StringUtils.isEmpty(invalidAttributes)) {
			return invalidAttributes + SupersetConstants.SPACE + getInvalidParameterMessageSuffix(invalidAttributes);
		}
		return null;
	}

	private String getInvalidParameterMessageSuffix(String invalidAttributes) {
		String invalidParameterMessageSuffix = ApplicationContextSupersetPOCProvider
				.getProperty(SupersetConstants.INVALID_PARAMETER_PROPERTY);
		if (invalidAttributes.indexOf(SupersetConstants.COMMA) == -1) {
			invalidParameterMessageSuffix = invalidParameterMessageSuffix.replace(SupersetConstants.ARE,
					SupersetConstants.IS);
		}
		return invalidParameterMessageSuffix;
	}

	public boolean homeGraphSelected() {
		return this.graph.isHomeGraph();
	}

	public String getGraphName() {
		return this.graphName;
	}

	public List<Parameter> getParameters() {
		return this.parameters;
	}
}