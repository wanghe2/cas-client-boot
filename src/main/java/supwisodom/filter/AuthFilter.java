package supwisodom.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import supwisodom.util.CasUtils;

/**
 * 认证过滤器，没有登录不允许访问页面
 * @author wanghe
 *
 */
public class AuthFilter implements Filter {
		@Override
		public void init(FilterConfig filterConfig) throws ServletException {
			
		}

		@Override
		public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain chain)
				throws IOException, ServletException {
			HttpServletRequest request=(HttpServletRequest)servletRequest;
			HttpServletResponse response=(HttpServletResponse)servletResponse;
			if(CasUtils.isLogin(request.getSession())) {//已经验证过，session中有相关数据，直接放行
				chain.doFilter(request,response);
			}else{//重定向到cas验证
				response.sendRedirect("/sso/login");
			}
		}

		@Override
		public void destroy() {
		
		}

}
