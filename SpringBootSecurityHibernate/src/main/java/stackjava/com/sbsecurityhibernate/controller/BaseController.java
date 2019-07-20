package stackjava.com.sbsecurityhibernate.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.context.annotation.RequestScope;

import stackjava.com.sbsecurityhibernate.dao.UserDAO;
import stackjava.com.sbsecurityhibernate.entities.User;
import stackjava.com.sbsecurityhibernate.form.LoginForm;
import stackjava.com.sbsecurityhibernate.form.RegisterForm;
import stackjava.com.sbsecurityhibernate.validator.LoginValidator;
import stackjava.com.sbsecurityhibernate.validator.RegisterValidator;

@Controller
public class BaseController {

	@Autowired
	private UserDAO userDAO;

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Autowired
	private RegisterValidator registerValidator;

	@Autowired
	private LoginValidator loginValidator;

	@Autowired
	HttpServletRequest request;

	// Set a form validator
	@InitBinder
	protected void initBinder(WebDataBinder dataBinder) {
		// Form mục tiêu
		Object target = dataBinder.getTarget();
		if (target == null) {
			return;
		}
		System.out.println("Target=" + target);

		if (target.getClass() == RegisterForm.class) {
			dataBinder.setValidator(registerValidator);
		}

		if (target.getClass() == LoginForm.class) {
			dataBinder.setValidator(loginValidator);
		}
	}

	@RequestMapping(value = { "/", "/login" }, method = RequestMethod.GET)
	public String login(Model model, LoginForm form) {
		String message = (String) request.getSession().getAttribute("message");
		request.getSession().removeAttribute("message");
		if (!StringUtils.isEmpty(message)) {
			model.addAttribute("message", message);
		}
		model.addAttribute("loginForm", form);
		return "login";
	}

	@RequestMapping("/user")
	public String user() {
		return "user";
	}

	@RequestMapping("/admin")
	public String admin() {
		return "admin";
	}

	@RequestMapping(value = "/register", method = RequestMethod.GET)
	public String register(Model model) {
		RegisterForm registerForm = new RegisterForm();
		model.addAttribute("registerForm", registerForm);
		return "register";
	}

	@RequestMapping(value = "/register", method = RequestMethod.POST)
	public String saveRegister(Model model, @Validated RegisterForm registerForm, BindingResult result) {
		// Validate result
		if (result.hasErrors()) {
//            List<Country> countries = countryDAO.getCountries();
//            model.addAttribute("countries", countries);
			model.addAttribute("registerForm", registerForm);
			return "register";
		}
		User user = new User();
		BeanUtils.copyProperties(registerForm, user);
		user.setPassword(passwordEncoder.encode(registerForm.getPassword()));
		userDAO.registerAccount(user);
		model.addAttribute("loginForm", new LoginForm());
		return "login";
	}

	@RequestMapping("/sendSms")
	public String sendSms() {
		return "send-sms";
	}
	
	@RequestMapping("/detailSecret")
	public String detailSecret() {
		return "secret-key-detail";
	}

	@RequestMapping("/forgotPassword")
	public String forgotPassword() {
		return "forgot-password";
	}

	@RequestMapping("/403")
	public String accessDenied() {
		return "403";
	}
}
