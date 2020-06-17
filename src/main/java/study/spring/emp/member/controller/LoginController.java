package study.spring.emp.member.controller;

import java.time.LocalDateTime;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import study.spring.emp.member.model.MemVO;
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

//	@PostMapping("/logout")
//	public String logout(Model model, HttpSession session) {
//		System.out.println("logout");
//		session.invalidate();
//		return "redirect:/home";
//	}


	@RequestMapping("/loginCheck")
	public String loginCheck(Model model, HttpSession session) {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		System.out.println(authentication.getPrincipal());
		System.out.println(authentication.getCredentials());
		System.out.println(authentication.getAuthorities());
		System.out.println(authentication.isAuthenticated());
			MemVO member = (MemVO) authentication.getDetails();
			session.setAttribute("userId", member.getUserId());
			session.setAttribute("auth", member.getAuth());
			session.setAttribute("startTime", LocalDateTime.now());
			String url = "/";
			if (session.getAttribute("url") != null) {
				url = (String) session.getAttribute("url");
			}
			return "redirect:/" + url;
	}

}
