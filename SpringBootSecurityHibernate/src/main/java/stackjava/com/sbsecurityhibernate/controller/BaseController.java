package stackjava.com.sbsecurityhibernate.controller;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import stackjava.com.module.sim900a.SendSMS;
import stackjava.com.sbsecurityhibernate.dao.UserDAO;
import stackjava.com.sbsecurityhibernate.entities.InfoSendSms;
import stackjava.com.sbsecurityhibernate.entities.User;
import stackjava.com.sbsecurityhibernate.form.LoginForm;
import stackjava.com.sbsecurityhibernate.form.RegisterForm;
import stackjava.com.sbsecurityhibernate.form.SendSmsForm;
import stackjava.com.sbsecurityhibernate.service.SendSmsService;
import stackjava.com.sbsecurityhibernate.validator.LoginValidator;

@Controller
public class BaseController {

	@Autowired
	private UserDAO userDAO;

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Autowired
	private LoginValidator loginValidator;

	@Autowired
	HttpServletRequest request;

	@Autowired
	SendSmsService sendSmsService;

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
//			dataBinder.setValidator(registerValidator);
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
	public String saveRegister(Model model, @Valid RegisterForm registerForm, BindingResult result) {
		// Validate result
		if (result.hasErrors()) {
			model.addAttribute("registerForm", registerForm);
			return "register";
		}
		User user = new User();
		BeanUtils.copyProperties(registerForm, user);
		user.setPassword(passwordEncoder.encode(registerForm.getPassword()));
		userDAO.registerAccount(user);
		model.addAttribute("loginForm", new LoginForm());
		return "otp-input";
	}

	@GetMapping("/sendSms")
	public String sendSms() {
		return "send-sms";
	}

	@PostMapping("/sendSms")
	public String sendSmsFromDatabase(Model model, @Valid SendSmsForm sendSmsForm, BindingResult result) {

		InfoSendSms infoSendSms = new InfoSendSms();
		infoSendSms.setContent(sendSmsForm.getContentSms());
		infoSendSms.setNumberPhone(sendSmsForm.getNumberPhone());
		infoSendSms.setSender("000000000");
		infoSendSms.setStatus(1);
		infoSendSms.setSendNow(sendSmsForm.isSendNow());
		infoSendSms.setDateTimeSend(sendSmsForm.getDateTimeSend());
		infoSendSms.setRepeatTime(sendSmsForm.getRepeatTime());
		sendSmsService.insertContentSms(infoSendSms);
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

	@RequestMapping("/profile")
	public String profile() {
		return "profile";
	}

	@RequestMapping("/setting")
	public String setting() {
		return "setting";
	}

	@RequestMapping("/activityLog")
	public String activityLog() {
		return "activity-log";
	}

	@RequestMapping("/otpInput")
	public String otpInput() {
		return "otp-input";
	}

	@RequestMapping("/403")
	public String accessDenied() {
		return "403";
	}
}
