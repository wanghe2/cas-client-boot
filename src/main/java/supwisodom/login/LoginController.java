package supwisodom.login;

import java.io.IOException;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import supwisodom.util.CasUtils;
import supwisodom.util.Constants;
import supwisodom.util.LoginUser;

@RestController
@RequestMapping("sso")
public class LoginController {

	@RequestMapping("login")
	public void login(HttpServletRequest request,HttpServletResponse response) throws IOException {
		HttpSession session = request.getSession();
		String targetUrl = CasUtils.getTargetUrl(request);
		if (CasUtils.isLogin(session)) {
			@SuppressWarnings("unused")
			LoginUser loginUser = (LoginUser)session.getAttribute(Constants.LOGIN_USER_KEY);
			response.sendRedirect(targetUrl);
		} else {
			if (CasUtils.hasTicket(request)) {
				LoginUser loginUser = CasUtils.getLoginUser(request);
				if (loginUser.isLogin() && doLogin(loginUser, request)) {
					CasUtils.login(loginUser, session);
					response.sendRedirect(targetUrl);
				} else {
					
					// TODO 可选：业务系统可根据实际情况进行处理
					response.sendRedirect(CasUtils.getErrorUrl(request));
				}
			} else {
				String loginUrl = CasUtils.getLoginUrl(request);
				response.sendRedirect(loginUrl);
			}
		}
	}
	
	
	
	public boolean doLogin(LoginUser loginUser, HttpServletRequest request) {
		HttpSession session = request.getSession();
		@SuppressWarnings("unused")
		ServletContext application = session.getServletContext();
		// 如果使用了Spring可以用下面的方法获取spring的context对象
		// WebApplicationContext appContext = WebApplicationContextUtils.getWebApplicationContext(application);
		// 如果需要使用SpringMVC上下文、可以用下面的方法获取springMVC的context对象
		// WebApplicationContext mvcContext = RequestContextUtils.getWebApplicationContext(request);
		
		// TODO 需要业务系统重写
		
		
		return true;
	}
	
	
	@RequestMapping("error")
	public String error() {
		return "认证失败";
	}
	
	@RequestMapping("index")
	public String index() {
		return "认证成功";
	}
	
}
