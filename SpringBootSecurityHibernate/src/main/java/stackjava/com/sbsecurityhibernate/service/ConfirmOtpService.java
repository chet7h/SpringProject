package stackjava.com.sbsecurityhibernate.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import stackjava.com.sbsecurityhibernate.dao.ConfirmOtpDao;
import stackjava.com.sbsecurityhibernate.form.OtpInputForm;

@Service
public class ConfirmOtpService {

	@Autowired
	ConfirmOtpDao confirmOtpDao;

	public void updateStatustOtp(OtpInputForm form) {
		String Otp = confirmOtpDao.getOtp(form.getUserId());
		if (form.getOtp().equals(Otp)) {
			confirmOtpDao.updateStatustOtp(form.getUserId(), form.getOtpInputId());
		}
	}

	public void getInfoOtp(String name) {
		confirmOtpDao.getInfoOtp(name);
		
	}

}
