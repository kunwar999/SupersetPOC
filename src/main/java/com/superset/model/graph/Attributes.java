package com.superset.model.graph;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import com.superset.model.Parameter;

public class Attributes {

	private Map<String, String> attributeMap;

	public Attributes(String graphName) {
		this.attributeMap = new HashMap<String, String>();
	}

	public Map<String, String> getAttributeMap() {
		return attributeMap;
	}

	public void put(String name, String value) {
		this.attributeMap.put(name, value);
	}

	public boolean validateAndSet(Parameter parameter) {
		boolean isValid = this.attributeMap.containsKey(parameter.getKey());
		if (isValid) {
			this.attributeMap.put(parameter.getKey(), parameter.getValue());
		}
		return isValid;
	}

	public String createUrl(String scheme, String hostName) {
		MultiValueMap<String, String> params = new LinkedMultiValueMap<String, String>();
		for (Entry<String, String> entry : this.attributeMap.entrySet()) {
			params.add(entry.getKey(), entry.getValue());
		}
		UriComponents uriComponents = UriComponentsBuilder.newInstance().scheme(scheme).host(hostName)
				.queryParams(params).build();
		return uriComponents.toUriString();
	}
}