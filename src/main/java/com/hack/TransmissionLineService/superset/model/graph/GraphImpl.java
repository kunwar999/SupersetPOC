package com.hack.TransmissionLineService.superset.model.graph;

import java.util.List;

import com.hack.TransmissionLineService.superset.constant.SupersetConstants;
import com.hack.TransmissionLineService.superset.context.ApplicationContextSupersetPOCProvider;
import com.hack.TransmissionLineService.superset.model.Parameter;

public class GraphImpl implements Graph {

	private String graphName;
	private Attributes attributes;

	public GraphImpl(String graphName) {
		this.graphName = graphName.toLowerCase();
		this.attributes = new Attributes(graphName.toLowerCase());
	}

	@Override
	public String createUrl() {
		String urlPrefixProperty = SupersetConstants.HOSTNAME_PROPERTY;
		String urlSuffixProperty = SupersetConstants.GRAPH_WITH_DOT + this.graphName + SupersetConstants.DOT_WITH_URL;
		String urlPrefix = ApplicationContextSupersetPOCProvider.getProperty(urlPrefixProperty);
		String urlSuffix = ApplicationContextSupersetPOCProvider.getProperty(urlSuffixProperty);
		if (isHomeGraph()) {
			return urlSuffix;
		}
		return urlPrefix + this.attributes.updateUrlSuffix(urlSuffix);

	}

	@Override
	public String validateAndSet(List<Parameter> parameters) {
		if (isHomeGraph())
			return null;
		StringBuffer invalidAttributes = new StringBuffer();
		parameters.stream().forEach(parameter -> {
			if (!this.attributes.validateAndSet(parameter)) {
				invalidAttributes.append(parameter.getKey() + SupersetConstants.COMMA);
			}
		});
		return invalidAttributes.length() > 0 ? invalidAttributes.substring(0, invalidAttributes.length() - 1)
				: invalidAttributes.toString();
	}

	@Override
	public boolean isHomeGraph() {
		String urlSuffixProperty = SupersetConstants.GRAPH_WITH_DOT + this.graphName + SupersetConstants.DOT_WITH_URL;
		String urlSuffix = ApplicationContextSupersetPOCProvider.getProperty(urlSuffixProperty);
		return this.graphName.equalsIgnoreCase(urlSuffix);
	}
}