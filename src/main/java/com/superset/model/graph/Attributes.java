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

	public Attributes() {
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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((attributeMap == null) ? 0 : attributeMap.hashCode());
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
		Attributes other = (Attributes) obj;
		if (attributeMap == null) {
			if (other.attributeMap != null)
				return false;
		} else if (!attributeMap.equals(other.attributeMap))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Attributes [attributeMap=" + attributeMap + "]";
	}
}