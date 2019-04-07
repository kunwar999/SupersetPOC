package com.superset.model.graph;

import org.springframework.beans.factory.annotation.Value;

public abstract class GraphImpl implements Graph {

	@Value("${graph.common.hostName}")
	protected String hostName;
	@Value("${graph.common.scheme}")
	protected String scheme;

	protected String graphName;
	protected Attributes attributes;

	public GraphImpl() {
		this.attributes = new Attributes();
	}

	public String getGraphName() {
		return graphName;
	}

	@Override
	public boolean isExpectedGraph(String graphName) {
		return this.graphName.equalsIgnoreCase(graphName);
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((graphName == null) ? 0 : graphName.hashCode());
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
		GraphImpl other = (GraphImpl) obj;
		if (graphName == null) {
			if (other.graphName != null)
				return false;
		} else if (!graphName.equals(other.graphName))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "GraphImpl [graphName=" + graphName + ", attributes=" + attributes + "]";
	}
}