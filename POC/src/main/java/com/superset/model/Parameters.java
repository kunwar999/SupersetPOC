package com.superset.model;

import java.util.ArrayList;
import java.util.List;

public class Parameters {

	private List<Parameter> parameters;

	public Parameters() {
		this.parameters = new ArrayList<Parameter>();
	}

	public void add(Parameter parameter) {
		this.parameters.add(parameter);
	}

	public List<Parameter> getParameters() {
		return parameters;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((parameters == null) ? 0 : parameters.hashCode());
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
		Parameters other = (Parameters) obj;
		if (parameters == null) {
			if (other.parameters != null)
				return false;
		} else if (!parameters.equals(other.parameters))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Parameters [parameters=" + parameters + "]";
	}
}