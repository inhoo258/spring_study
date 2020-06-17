package study.spring.emp.util;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

public class LoginIntercepter implements HandlerInterceptor {


//	인증 들어 가는 순간
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		HttpSession session = request.getSession();
		String contextName = request.getContextPath();
		String url = request.getRequestURI().replaceFirst(contextName, "");
		String param = request.getQueryString();
		
		if(!url.contains("login") && !url.contains("logout")) {
			session.setAttribute("url", url);
			session.setAttribute("param", param);
		}
		return true;
	}

//	인증후 처리해줌
	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		// TODO Auto-generated method stub
		
	}

//	모든걸 끝내고 다시 나오는 순간 입구에서 잡음
	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		// TODO Auto-generated method stub
		
	}

}
