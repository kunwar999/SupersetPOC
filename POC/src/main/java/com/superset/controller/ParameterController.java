package com.superset.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.superset.model.Parameter;
import com.superset.model.Parameters;

@RestController
public class ParameterController {

	Parameters parameters = null;
	private static final Parameters DEFAULT_PARAMETER=new Parameters();

	public ParameterController() {
		DEFAULT_PARAMETER.add(new Parameter("NO_DATA", "NO_DATA"));
	}

	@GetMapping("/superset/showGraph/get")
	public Parameters getParameters() {
		Parameters returnParameters=null;
		if(parameters!=null) {
			returnParameters= parameters;
			parameters=null;
		}
		else {
			returnParameters=DEFAULT_PARAMETER;
		}
		return returnParameters;
		
	}

	@PutMapping("/superset/showGraph/put")
	public void setParameters(@RequestBody Parameters parameter) {
		parameters = parameter;
	}

}
