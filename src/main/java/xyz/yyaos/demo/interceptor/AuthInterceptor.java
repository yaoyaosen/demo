package xyz.yyaos.demo.interceptor;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class AuthInterceptor implements HandlerInterceptor {

	@Override
	public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object object) throws Exception {
		return true;
	}

	@Override
	public void postHandle(HttpServletRequest httpServletRequest,
	                       HttpServletResponse httpServletResponse,
	                       Object o, ModelAndView modelAndView) throws Exception {

		System.out.println("AuthInterceptor");

	}

	@Override
	public void afterCompletion(HttpServletRequest httpServletRequest,
	                            HttpServletResponse httpServletResponse,
	                            Object o, Exception e) throws Exception {
	}
}
