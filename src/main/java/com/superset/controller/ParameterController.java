package com.superset.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.superset.model.Parameters;

@RestController
public class ParameterController {

	@Value("${graph.common.noUrl}")
	private String noUrl;
	@Value("${graph.common.success}")
	private String successMessage;
	@Value("${graph.common.invalidGraph}")
	private String invalidGraphMessage;
	@Value("${graph.common.invalidParameter}")
	private String invalidParameterMessage;

	private Parameters parameters = null;

	@GetMapping("/superset/showGraph/get")
	public String getParameters() {
		String returnUrl = null;
		if (this.parameters != null) {
			returnUrl = this.parameters.createUrl();
			this.parameters = null;
		} else {
			returnUrl = this.noUrl;
		}
		return returnUrl;

	}

	@PutMapping("/superset/showGraph/put")
	public ResponseEntity<String> setParameters(@RequestBody Parameters parameter) {
		return createResponse(parameter);
	}

	public ResponseEntity<String> createResponse(Parameters parameter) {
		HttpStatus status = null;
		String message = parameter.validateAndSet(invalidGraphMessage, invalidParameterMessage);
		if (message == null) {
			this.parameters = parameter;
			message = this.successMessage;
			status = HttpStatus.OK;
		} else {
			status = HttpStatus.BAD_REQUEST;
		}
		return new ResponseEntity<String>(message, status);
	}
}
