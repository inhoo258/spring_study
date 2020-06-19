package study.spring.emp.member.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import study.spring.emp.member.service.IMemService;

@Controller
public class LoginController {

	@Autowired
	IMemService memService;

	@RequestMapping("/")
	public String home() {
		return "home";
	}
	
	@GetMapping("/login")
	public void login(Model model) {
	}
	
	@RequestMapping("/loginCheck")
	public String loginCheck() {
		return null;
	}
	
}
