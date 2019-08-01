package stackjava.com.sbsecurityhibernate.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import stackjava.com.sbsecurityhibernate.dao.SendSmsDao;
import stackjava.com.sbsecurityhibernate.entities.InfoSendSms;

@Service
public class SendSmsService {

	@Autowired
	SendSmsDao sendSmsDao;

	public void insertContentSms(InfoSendSms infoSendSms) {
		sendSmsDao.insertContentSms(infoSendSms);
	}
}
