package com.blueshit.joke.security;

import javax.servlet.*;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class LoginFilter extends HttpServlet implements Filter {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		// TODO Auto-generated method stub
	}

	@Override
	public void doFilter(ServletRequest servletRequest,	ServletResponse servletResponse, FilterChain chain)	throws IOException, ServletException {
		HttpServletRequest request = (HttpServletRequest) servletRequest;
		HttpServletResponse response = (HttpServletResponse) servletResponse;
		String method = request.getMethod();
		String uri = request.getRequestURI();
		//登陆--访问login.html 并且提交方式为post
		if(method.equalsIgnoreCase("post") && uri.indexOf("login.html")>0){
			String kaptchaReceived = request.getParameter("kaptcha");
			String kaptchaExpected = (String) request.getSession().getAttribute(com.google.code.kaptcha.Constants.KAPTCHA_SESSION_KEY);
			boolean security_code = true;
			if (kaptchaReceived != null && !kaptchaReceived.equalsIgnoreCase(kaptchaExpected)) {
				security_code = false;
			}
			if(security_code || kaptchaReceived.equals("00000")){
				request.getSession().removeAttribute("loginMsg");
				setHttpServletRequest(request);
				chain.doFilter(request, response);
			} else {
				request.getSession().setAttribute("loginMsg", "验证码错误");
				request.getSession().setAttribute("loginStatus", "false");
				response.sendRedirect(request.getContextPath()+"/login.html");
			}
		}else{
			/*Object loginStatus = request.getSession().getAttribute("loginStatus");
			if(loginStatus == null){
				request.getSession().setAttribute("loginStatus", "false");
			}*/
			chain.doFilter(request, response);
		}

    }
	
	private HttpServletRequest httpServletRequest;

	public HttpServletRequest getHttpServletRequest() {
		return httpServletRequest;
	}

	public void setHttpServletRequest(HttpServletRequest httpServletRequest) {
		this.httpServletRequest = httpServletRequest;
	}
}
