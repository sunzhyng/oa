package net.icocoa.oa.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import net.icocoa.oa.po.User;
import net.icocoa.oa.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class Test {

	@Autowired
	private HttpServletRequest request;

	@Autowired
	private UserService userService;

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
		User u = userService.findById("1");
		System.out.println("user = " + u.getName());
		// return "";
		// return "redirect:http://www.baidu.com/";
		return "forward:/mv";
	}

	@RequestMapping(value = "/in")
	public String in() {
		User u = new User();
		u.setName("Bok");
		u.setAge(9);
		userService.insert(u);
		System.out.println("user = " + u.getId() + ":" + u.getName());
		// return "";
		// return "redirect:http://www.baidu.com/";
		return "forward:/mv";
	}
}
