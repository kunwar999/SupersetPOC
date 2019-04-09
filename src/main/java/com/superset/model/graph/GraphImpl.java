package com.superset.model.graph;

import com.superset.context.ApplicationContextSupersetPOCProvider;
import com.superset.model.Parameter;

public class GraphImpl implements Graph {

	private String graphName;
	private Attributes attributes;

	public GraphImpl(String graphName) {
		this.graphName = graphName;
		this.attributes = new Attributes();
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
	public boolean validateAndSet(Parameter parameter) {
		String urlSuffixProperty = "graph." + this.graphName + ".url";
		String urlSuffix = ApplicationContextSupersetPOCProvider.getProperty(urlSuffixProperty);
		if (this.graphName.equalsIgnoreCase(urlSuffix))
			return true;
		return true;

	}

	public void setDefaultValues() {
		// TODO Auto-generated method stub

	}
}