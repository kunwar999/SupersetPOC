package com.superset.model.graph;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import org.assertj.core.util.Arrays;

import com.superset.constant.SupersetConstants;
import com.superset.context.ApplicationContextSupersetPOCProvider;
import com.superset.model.Parameter;

public class Attributes {

	private Map<String, String> attributeMap;

	public Attributes(String graphName) {
		this.attributeMap = new HashMap<String, String>();
		String attributesProperty = SupersetConstants.GRAPH_WITH_DOT + graphName
				+ SupersetConstants.DOT_WITH_ATTRIBUTES;
		String attributesMapString = ApplicationContextSupersetPOCProvider.getProperty(attributesProperty);
		if (attributesMapString != null) {
			String[] attributesMapArray = attributesMapString.split(SupersetConstants.COMMA);
			Arrays.asList(attributesMapArray).forEach(singleAttributeMapString -> {
				String[] singleAttributeMapArray = ((String) singleAttributeMapString).split(SupersetConstants.COLON);
				this.attributeMap.put(singleAttributeMapArray[SupersetConstants.KEY_INDEX],
						singleAttributeMapArray[SupersetConstants.VALUE_INDEX]);
			});
		}
	}

	public boolean validateAndSet(Parameter parameter) {
		boolean isValid = this.attributeMap.containsKey(parameter.getKey());
		if (isValid) {
			this.attributeMap.put(parameter.getKey(), parameter.getValue());
		}
		return isValid;
	}

	public String updateUrlSuffix(String urlSuffix) {
		String updatedUrlSuffix = urlSuffix;
		for (Entry<String, String> entry : this.attributeMap.entrySet()) {
			updatedUrlSuffix = updatedUrlSuffix.replace(
					SupersetConstants.CURLY_START + entry.getKey() + SupersetConstants.CURLY_END, entry.getValue());
		}
		return updatedUrlSuffix;
	}
}