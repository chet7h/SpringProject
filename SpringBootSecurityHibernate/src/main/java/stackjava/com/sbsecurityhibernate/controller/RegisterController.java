package stackjava.com.sbsecurityhibernate.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import stackjava.com.sbsecurityhibernate.form.OtpInputForm;
import stackjava.com.sbsecurityhibernate.form.RegisterForm;
import stackjava.com.sbsecurityhibernate.service.RegisterService;

@Controller
public class RegisterController {

	@Autowired
	RegisterService registerService;

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
		int userId = registerService.registerAccount(registerForm);
		OtpInputForm otpInputForm = new OtpInputForm();
		otpInputForm.setUserId(userId);
		otpInputForm.setUser(registerForm.getUsername());
		model.addAttribute("otpInputForm", otpInputForm);
		return "otp-input";
	}
}
