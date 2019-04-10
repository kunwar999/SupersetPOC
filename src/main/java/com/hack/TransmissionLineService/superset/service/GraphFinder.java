package com.hack.TransmissionLineService.superset.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class GraphFinder {

	@Value("${graph.common.list}")
	private List<String> graphNames;

	public String findGraph(String graphName) {
		return this.graphNames.stream().filter(name -> name.equalsIgnoreCase(graphName)).findAny().orElse(null);
	}
}
