package com.superset.service;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.superset.model.graph.GraphImpl;

@Service
public class GraphFinder {

	@Autowired
	@Qualifier(value = "home")
	private GraphImpl home;
	@Autowired
	@Qualifier(value = "g1")
	private GraphImpl g1;

	private Map<String, GraphImpl> graphMap;

	@PostConstruct
	public void init() {
		this.graphMap = new HashMap<String, GraphImpl>();
		this.graphMap.put(this.home.getGraphName(), home);
		this.graphMap.put(this.g1.getGraphName(), g1);
	}

	public GraphImpl findGraph(String graphName) {
		return this.graphMap.get(graphName);
	}
}
