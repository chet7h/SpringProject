package stackjava.com.sbsecurityhibernate.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import stackjava.com.sbsecurityhibernate.dto.RegisterOtpDto;
import stackjava.com.sbsecurityhibernate.entities.OtpInput;

@Repository(value = "addOtpDao")
@Transactional(rollbackFor = Exception.class)
public class AddOtpDao {

	@Autowired
	private SessionFactory sessionFactory;

	public void registerOtp(RegisterOtpDto registerOtpDto) {
		OtpInput otpInput = new OtpInput();
		BeanUtils.copyProperties(registerOtpDto, otpInput);
		Session session = this.sessionFactory.getCurrentSession();
//		otpInput.setIdInfoSendSms(idInfoSendSms);
//		otpInput.setUserId(userId);
//		otpInput.setOTP(OTP);
//		otpInput.setStatus(1);
//		otpInput.setType(1);
		session.save(otpInput);
	}

}
