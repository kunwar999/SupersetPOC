package com.superset.model.graph;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.superset.model.Parameter;

@Component
public class G1 extends GraphImpl {

	@Value("${graph.g1.name}")
	private String g1Name;
	@Value("${graph.g1.assetKey}")
	private String assetKey;
	@Value("${graph.g1.assetValue}")
	private String assetValue;
	@Value("${graph.g1.timeRangeKey}")
	private String timeRangeKey;
	@Value("${graph.g1.timeRangeValue}")
	private String timeRangeValue;

	@PostConstruct
	@Override
	public void setDefaultValues() {
		this.graphName = this.g1Name;
		this.attributes.put(this.assetKey, this.assetValue);
		this.attributes.put(this.timeRangeKey, this.timeRangeValue);
	}

	@Override
	public String createUrl() {
		return this.attributes.createUrl(this.scheme, this.hostName);
	}

	@Override
	public boolean validateAndSet(Parameter parameter) {
		return this.attributes.validateAndSet(parameter);
	}
}