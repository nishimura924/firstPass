package tool;

import java.io.*;
import javax.servlet.*;
import javax.servlet.annotation.*;

@WebFilter(urlPatterns={"/*"})
	
public class EncodingFilter implements Filter
{
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException
	{
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
//		System.out.println("フィルタの前処理");
		
		chain.doFilter(request, response);
//		System.out.println("フィルタの後処理");
	}
	
	public void init(FilterConfig filterConfig){}
	public void destoroy(){}
}