package net.icocoa.oa.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import net.icocoa.oa.po.Test;
import net.icocoa.oa.service.TestService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class TestController {

	@Autowired
	private HttpServletRequest request;

	@Autowired
	private TestService testService;

	@RequestMapping(value = "/baidu")
	public String baidu() {

		System.out.println("n=" + request.getParameter("n"));
		// return "";
		// return "redirect:http://www.baidu.com/";
		return "forward:/mv";
	}

	@RequestMapping(value = "/mv")
	public ModelAndView mv() {
		return new ModelAndView("/idx", "txt", "Test");
	}
 
	@RequestMapping(value = "/add")
	@ResponseBody
	public Map<String, String> addUser() {
		Map<String, String> map = new HashMap<String, String>(1);
		map.put("success", "true");
		return map;
	}
 
	@RequestMapping(value = "/us")
	public String us() {
		Test u = testService.findById("1");
		System.out.println("user = " + u.getName());
		// return "";
		// return "redirect:http://www.baidu.com/";
		return "forward:/mv";
	}

	@RequestMapping(value = "/in")
	public String in() {
		Test u = new Test();
		u.setName("Bok");
		u.setAge(9);
		testService.insert(u);
		System.out.println("user = " + u.getId() + ":" + u.getName());
		// return "";
		// return "redirect:http://www.baidu.com/";
		return "forward:/mv";
	}
}
