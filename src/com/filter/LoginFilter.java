package com.filter;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet Filter implementation class LoginFilter
 */
@WebFilter("/*")
public class LoginFilter implements Filter {

    /**
     * Default constructor. 
     */
    public LoginFilter() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
		// TODO Auto-generated method stub
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest)request;
		HttpServletResponse res = (HttpServletResponse)response;
		String uri = req.getRequestURI();
		System.out.println(uri);

		if(uri.contains("login.html") || uri.contains("frontend/member/member")) {
			HttpSession session = req.getSession();
			Object user = session.getAttribute("user");			
			if(user != null) {
				if(uri.contains("/frontend/member/login.html")) {
				res.sendRedirect(req.getContextPath()+"/frontend/member/memberCenter.jsp");	
				}else {
					chain.doFilter(request, response);					
				}
			}else {
				if(!uri.contains("/frontend/member/login.html")) {
					res.sendRedirect(req.getContextPath()+"/frontend/member/login.html");				
				}else {
					chain.doFilter(request, response);		
				}				
			}
		}else {
			chain.doFilter(request, response);
		}
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}

}
