package com.sollers.banking.filters;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sollers.banking.errors.AuthenticationException;

/**
 * Servlet Filter implementation class SollersAppFilter
 */
//@WebFilter(urlPatterns = { "/" }, initParams = { @WebInitParam(name = "resourceDirectory", value = "") })
public class SollersAppFilter implements Filter {

	/**
	 * Default constructor.
	 */
	public SollersAppFilter() {

	}

	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {

	}

	/**
	 * The doFilter method of the Filter is called by the container each time a request/response pair is passed through the chain due to a
	 * client request for a resource at the end of the chain. The FilterChain passed in to this method allows the Filter to pass on the request
	 * and response to the next entity in the chain.
	 * 
	 * @param req   Request object to be processed
	 * @param resp  Response object
	 * @param chain current FilterChain
	 * @exception IOException if any occurs
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {

		System.out.println("Entering doFilter method of AppFilter");
		HttpServletRequest httpRequest = (HttpServletRequest) request;
		HttpServletResponse httpResponse = (HttpServletResponse) response;

		try {

			// Authenticate - Login Check

			chain.doFilter(request, response);
		} catch (Exception e) {
			request.setAttribute("message", "Authentication failed");
			RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/login.jsp");
			dispatcher.forward(request, response);
			return;
		}
		System.out.println("Leaving doFilter method of AppFilter");
	}

	/**
	 * Called by the web container to indicate to a filter that it is being placed into service. The servlet container calls the init method
	 * exactly once after instantiating the filter. The init method must complete successfully before the filter is asked to do any filtering
	 * work.
	 * 
	 * @param fConfig configuration object
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		String path = fConfig.getInitParameter("resourceDirectory");
		if (path != null) {

		}

	}

}
