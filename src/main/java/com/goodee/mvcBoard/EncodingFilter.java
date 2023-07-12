package com.goodee.mvcBoard;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpFilter;

@SuppressWarnings("serial")
@WebFilter("/*")
public class EncodingFilter extends HttpFilter implements Filter {

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		System.out.println("요청을 가로채서 UTF-8 인코딩...");
		request.setCharacterEncoding("UTF-8");
		chain.doFilter(request, response);
	}
}
