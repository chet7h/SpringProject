package stackjava.com.sbsecurityhibernate.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import stackjava.com.sbsecurityhibernate.service.MyUserDetailsService;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	private MyUserDetailsService userDetailsService;

	@Bean
	public BCryptPasswordEncoder passwordEncoder() {
		BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
		return bCryptPasswordEncoder;
	}

	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
		// Sét đặt dịch vụ để tìm kiếm User trong Database.
		// Và sét đặt PasswordEncoder.
		auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
	}

	@Bean
	public AuthenticationFailureHandler customAuthenticationFailureHandler() {
		return new CustomAuthenticationFailureHandler();
	}

	@Bean
	AuthenticationSuccessHandler customAuthenticationSuccessHandler() {
		return new CustomAuthenticationSuccessHandler();
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests().antMatchers("/static**").permitAll();
		// Chỉ cho phép user có quyền ADMIN truy cập đường dẫn /admin/**
		http.authorizeRequests().antMatchers("/include/**").permitAll();
		// Chỉ cho phép user có quyền ADMIN hoặc USER truy cập đường dẫn
		// /user/**

//		http.authorizeRequests().antMatchers("/otpInput").access("hasRole('ROLE_OTP_REG_ACC')");
		String[] re = new String[] { "/admin**", "/setting**", "/apiManager**", "/profile**", "/sendSms**" };
		http.authorizeRequests().antMatchers(re).access("hasRole('ROLE_ADMIN')");

		// Khi người dùng đã login, với vai trò USER, Nhưng truy cập vào trang
		// yêu cầu vai trò ADMIN, sẽ chuyển hướng tới trang /403
		http.authorizeRequests().and().exceptionHandling().accessDeniedPage("/403");

		// Cấu hình cho Login Form.
		http.authorizeRequests().and().formLogin()//
				.loginProcessingUrl("/j_spring_security_login")//
				.loginPage("/login")//
				.successHandler(customAuthenticationSuccessHandler()).failureUrl("/login?message=error")//
				.failureHandler(customAuthenticationFailureHandler()).usernameParameter("username")//
				.passwordParameter("password")
				// Cấu hình cho Logout Page.
				.and().logout().logoutUrl("/j_spring_security_logout").logoutSuccessUrl("/login?message=logout")
				.deleteCookies("JSESSIONID").and().rememberMe().key("uniqueAndSecret")
				.tokenValiditySeconds(2 * 24 * 60 * 60);

	}

}
