package stackjava.com.sbsecurityhibernate.service;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import stackjava.com.sbsecurityhibernate.dao.AddOtpDao;
import stackjava.com.sbsecurityhibernate.dao.SendSmsDao;
import stackjava.com.sbsecurityhibernate.dao.UserDAO;
import stackjava.com.sbsecurityhibernate.dto.RegisterOtpDto;
import stackjava.com.sbsecurityhibernate.entities.InfoSendSms;
import stackjava.com.sbsecurityhibernate.entities.User;
import stackjava.com.sbsecurityhibernate.form.RegisterForm;
import stackjava.com.sbsecurityhibernate.utils.StringsUtils;

@Service
public class RegisterService {
	@Autowired
	private UserDAO userDAO;
	@Autowired
	SendSmsDao sendSmsDao;

	@Autowired
	AddOtpDao addOtpDao;
	@Autowired
	private PasswordEncoder passwordEncoder;

	public int registerAccount(RegisterForm registerForm) {
		User user = new User();
		BeanUtils.copyProperties(registerForm, user);
		user.setPassword(passwordEncoder.encode(registerForm.getPassword()));
		userDAO.registerAccount(user);
		String OTP = StringsUtils.createRandomNumber(6);
		InfoSendSms infoSendSms = new InfoSendSms();
		infoSendSms.setStatus(1);
		infoSendSms.setSendNow(true);
		infoSendSms.setSender("0000000000");

		infoSendSms.setNumberPhone(registerForm.getNumberPhone());
		infoSendSms.setContent("ma OTP cua ban la: " + OTP);
		sendSmsDao.insertContentSms(infoSendSms);

		RegisterOtpDto registerOtpDto = new RegisterOtpDto();
		registerOtpDto.setIdInfoSendSms(infoSendSms.getIdInfoSendSms());
		registerOtpDto.setUserId(user.getId());
		registerOtpDto.setStatus(1);
		registerOtpDto.setOTP(OTP);
		addOtpDao.registerOtp(registerOtpDto);
		return user.getId();
	}
}
