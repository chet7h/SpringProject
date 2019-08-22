package stackjava.com.sbsecurityhibernate.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import stackjava.com.sbsecurityhibernate.form.OtpInputForm;
import stackjava.com.sbsecurityhibernate.service.ConfirmOtpService;

@Controller
public class OtpInputController {

	@Autowired
	ConfirmOtpService confirmOtpService;

	@RequestMapping(value = "/otpInput", method = RequestMethod.GET)
	public String otpInput() {
		return "otp-input";
	}

	@RequestMapping(value = "/otpInput", method = RequestMethod.POST)
	public String otpInputOk(Model model, OtpInputForm form) {
		confirmOtpService.updateStatustOtp(form);
		return "login";
	}
}
