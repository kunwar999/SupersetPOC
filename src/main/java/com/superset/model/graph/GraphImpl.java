package com.superset.model.graph;

import java.util.List;

import com.superset.context.ApplicationContextSupersetPOCProvider;
import com.superset.model.Parameter;
import com.superset.model.Parameters;

public class GraphImpl implements Graph {

	private String graphName;
	private Attributes attributes;

	public GraphImpl(String graphName) {
		this.graphName = graphName;
		this.attributes = new Attributes(graphName);
	}

	@Override
	public String createUrl() {
		String urlPrefixProperty = "graph.common.hostName";
		String urlSuffixProperty = "graph." + this.graphName + ".url";
		String urlPrefix = ApplicationContextSupersetPOCProvider.getProperty(urlPrefixProperty);
		String urlSuffix = ApplicationContextSupersetPOCProvider.getProperty(urlSuffixProperty);
		if (this.graphName.equalsIgnoreCase(urlSuffix))
			return urlSuffix;
		return urlPrefix + urlSuffix;

	}

	@Override
	public String validateAndSet(List<Parameter> parameters) {
		String urlSuffixProperty = "graph." + this.graphName + ".url";
		String urlSuffix = ApplicationContextSupersetPOCProvider.getProperty(urlSuffixProperty);
		if (this.graphName.equalsIgnoreCase(urlSuffix))
			return null;
		StringBuffer invalidAttributes = new StringBuffer();
		parameters.stream().forEach(parameter -> {
			if (!graph.validateAndSet(parameter)) {
				invalidAttributes.append(parameter.getKey());
			}
		});
		return invalidAttributes.toString();

	}
}