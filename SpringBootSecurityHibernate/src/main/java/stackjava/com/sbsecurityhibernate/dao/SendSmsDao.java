package stackjava.com.sbsecurityhibernate.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import stackjava.com.sbsecurityhibernate.entities.InfoSendSms;

@Repository(value = "sendSmsDao")
@Transactional(rollbackFor = Exception.class)
public class SendSmsDao {
	@Autowired
	private SessionFactory sessionFactory;

	public void insertContentSms(InfoSendSms infoSendSms) {
		Session session = this.sessionFactory.getCurrentSession();
		session.save(infoSendSms);
	}
}
