package fjs.cs.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class MyFilter implements Filter {

	/**
	 * Request processing.
	 */
	@Override
	public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain)
			throws IOException, ServletException {
		// Khoi tao HttpServletRequest.
		HttpServletRequest request = (HttpServletRequest) req;
		// Khoi tao 		HttpServletResponse.
		HttpServletResponse response = (HttpServletResponse) res;
		HttpSession session = request.getSession(false);
		// Khoi tao url login string.
		String loginURI = request.getContextPath() + "/loginAction.do";
		// True neu session != null va sesion login != null.
		boolean loggedIn = session != null && session.getAttribute("login") != null;
		// True neu request = loginUrl.
		boolean loginRequest = request.getRequestURI().equals(loginURI);
		// Neu loggedIn hoac loginRequest = true.
		if (loggedIn || loginRequest) {
			chain.doFilter(request, response);
		} else {
			// Dieu huong ve Login.
			response.sendRedirect(loginURI);
		}
	}

	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {
		// TODO Auto-generated method stub
		
	}

}