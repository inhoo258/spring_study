package study.spring.emp.member.auth;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

public class LoginSuccessHandler implements AuthenticationSuccessHandler {


	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
			Authentication authentication) throws IOException, ServletException {
//		authentication = SecurityContextHolder.getContext().getAuthentication();
//		System.out.println(authentication.getPrincipal());
//		System.out.println(authentication.getCredentials());
//		System.out.println(authentication.getAuthorities());
//		System.out.println(authentication.isAuthenticated());
//		MemVO member = (MemVO) authentication.getDetails();
//		
//		request.getSession().setAttribute("userId", member.getUserId());
//		request.getSession().setAttribute("auth", member.getAuth());
//		String url = "/";
//		if(request.getSession().getAttribute("url") != null) {
//			url = (String) request.getSession().getAttribute("url");
//		}
//		response.sendRedirect("/emp/"+url);
//	}
	}
}
