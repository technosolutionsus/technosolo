package com.sollers.banking.util;

import java.util.Map;
import java.util.TreeMap;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Implementation of the HttpUtilities. This implementation uses the Apache Commons FileUploader library, which in turn uses the Apache
 * Commons IO library.
 */
public class HttpUtilities {

	private static HttpUtilities instance = null;

	public static HttpUtilities getInstance() {
		if (instance == null) {
			instance = new HttpUtilities();
		}
		return instance;
	}

	private HttpServletRequest request;

	private HttpServletResponse response;

	public HttpUtilities() {
	}

	public HttpUtilities(HttpServletRequest req, HttpServletResponse resp) {
		this.request = req;
		this.response = resp;
	}

	/**
	 * This implementation uses a custom "set-cookie" header rather than Java's cookie interface which doesn't allow the use of HttpOnly.
	 * Configure the HttpOnly and Secure settings in ESAPI.properties.
	 */
	public void addCookie(Cookie cookie) {
		addCookie(getResponse(), cookie);
	}

	public void addCookie(HttpServletResponse response, Cookie cookie) {
		String name = cookie.getName();
		String value = cookie.getValue();
		int maxAge = cookie.getMaxAge();
		String domain = cookie.getDomain();
		String path = cookie.getPath();
		boolean secure = cookie.getSecure();

		// validate the name and value - you can have a seperate validator class using regular express and pattern loaded via properties file
		String header = createCookieHeader(name, value, maxAge, domain, path, secure);
		addHeader(response, "Set-Cookie", header);
		// cookie.setSecure(secure);
		// response.addCookie(cookie);

	}

	private String createCookieHeader(String name, String value, int maxAge, String domain, String path,
			boolean secure) {
		String header = name + "=" + value;
		if (maxAge >= 0) {
			header += "; Max-Age=" + maxAge;
		}
		if (domain != null) {
			header += "; Domain=" + domain;
		}
		if (path != null) {
			header += "; Path=" + path;
		}
		if (secure) {
			header += "; Secure";
		}
		// header += "; HttpOnly";

		return header;
	}

	public String getCookie(HttpServletRequest request, String name) {
		Cookie c = getFirstCookie(request, name);
		if (c == null)
			return null;

		return c.getValue();
	}

	private Cookie getFirstCookie(HttpServletRequest request, String name) {
		Cookie[] cookies = request.getCookies();
		if (cookies != null) {
			for (Cookie cookie : cookies) {
				if (cookie.getName().equals(name)) {
					return cookie;
				}
			}
		}
		return null;
	}

	public String getCookie(String name) {
		return getCookie(getRequest(), name);
	}

	public String getHeader(HttpServletRequest request, String name) {
		return request.getHeader(name);
	}

	public String getHeader(String name) {
		return getHeader(getRequest(), name);
	}

	public void addHeader(String name, String value) {
		addHeader(getResponse(), name, value);
	}

	public void addHeader(HttpServletResponse response, String name, String value) {
		response.addHeader(name, value);
	}

	public String getParameter(HttpServletRequest request, String name) {
		return request.getParameter(name);
	}

	/**
	 * {@inheritDoc}
	 */
	public String getParameter(String name) {
		return getParameter(getRequest(), name);
	}

	/**
	 * {@inheritDoc}
	 */
	public void killAllCookies() {
		killAllCookies(getRequest(), getResponse());
	}

	/**
	 * {@inheritDoc}
	 *
	 * @param request
	 * @param response
	 */
	public void killAllCookies(HttpServletRequest request, HttpServletResponse response) {
		Cookie[] cookies = request.getCookies();
		if (cookies != null) {
			for (Cookie cookie : cookies) {
				killCookie(request, response, cookie.getName());
			}
		}
	}

	public void killCookie(HttpServletRequest request, HttpServletResponse response, String name) {
		String path = "//";
		String domain = "";
		Cookie cookie = getFirstCookie(request, name);
		if (cookie != null) {
			path = cookie.getPath();
			domain = cookie.getDomain();
		}
		Cookie deleter = new Cookie(name, "deleted");
		deleter.setMaxAge(0);
		if (domain != null)
			deleter.setDomain(domain);
		if (path != null)
			deleter.setPath(path);
		response.addCookie(deleter);
	}

	public void killCookie(String name) {
		killCookie(getRequest(), getResponse(), name);
	}

	private Map<String, String> queryToMap(String query) {
		TreeMap<String, String> map = new TreeMap<String, String>();
		String[] parts = query.split("&");
		for (String part : parts) {
			try {
				String[] nvpair = part.split("=");
				// have to decode url
				String name = nvpair[0];
				String value = nvpair[1];
				map.put(name, value);
			} catch (Exception e) {
				// skip the nvpair with the encoding problem - note this is already logged.
			}
		}
		return map;
	}

	public HttpServletRequest getRequest() {
		return request;
	}

	public void setRequest(HttpServletRequest request) {
		this.request = request;
	}

	public HttpServletResponse getResponse() {
		return response;
	}

	public void setResponse(HttpServletResponse response) {
		this.response = response;
	}

}
