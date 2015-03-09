package net.icocoa.oa.controller;

import java.util.Map;
import java.util.TreeMap;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

public class BaseController {
	@ExceptionHandler(RuntimeException.class)
	@ResponseBody
	public Map<String, Object> runtimeExceptionHandler(RuntimeException runtimeException) {
		System.out.println(runtimeException.getLocalizedMessage());

		Map<String, Object> model = new TreeMap<String, Object>();
		model.put("status", false);
		return model;
	}
}
