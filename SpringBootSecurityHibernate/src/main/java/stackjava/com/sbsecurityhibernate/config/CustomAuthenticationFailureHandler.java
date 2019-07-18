package stackjava.com.sbsecurityhibernate.config;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;

public class CustomAuthenticationFailureHandler implements AuthenticationFailureHandler {
	@Override
	public void onAuthenticationFailure(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse,
			AuthenticationException exp) throws IOException, ServletException {
//		httpServletRequest.getSession().setAttribute("xxx", "cccccccc");
//		httpServletResponse.sendRedirect("/login");
		
		String errMsg = "";
		if (exp.getClass().isAssignableFrom(BadCredentialsException.class)) {
			errMsg = "Invalid username or password.";
		} else {
			httpServletResponse.sendRedirect("/error"); 
		}
		httpServletRequest.getSession().setAttribute("message", errMsg);
		httpServletResponse.sendRedirect("/login"); // Redirect user to login page with error message.
	}
}
