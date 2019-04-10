package com.hack.TransmissionLineService.superset.model.graph;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import org.assertj.core.util.Arrays;

import com.hack.TransmissionLineService.superset.constant.SupersetConstants;
import com.hack.TransmissionLineService.superset.context.ApplicationContextSupersetPOCProvider;
import com.hack.TransmissionLineService.superset.model.Parameter;

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
		boolean isValid = this.attributeMap.containsKey(parameter.getKey().toLowerCase());
		if (isValid) {
			this.attributeMap.put(parameter.getKey().toLowerCase(), parameter.getValue());
		}
		return isValid;
	}

	public String updateUrlSuffix(String urlSuffix) {
		String updatedUrlSuffix = urlSuffix;
		for (Entry<String, String> entry : this.attributeMap.entrySet()) {
			updatedUrlSuffix = updatedUrlSuffix.replace(
					SupersetConstants.CURLY_START + entry.getKey().toLowerCase() + SupersetConstants.CURLY_END,
					entry.getValue());
		}
		return updatedUrlSuffix;
	}
}