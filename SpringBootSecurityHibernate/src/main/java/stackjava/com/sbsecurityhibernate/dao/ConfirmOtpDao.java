package stackjava.com.sbsecurityhibernate.dao;

import org.hibernate.Criteria;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.hibernate.sql.Update;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import stackjava.com.sbsecurityhibernate.entities.OtpInput;
import stackjava.com.sbsecurityhibernate.entities.Role;
import stackjava.com.sbsecurityhibernate.entities.User;
import stackjava.com.sbsecurityhibernate.entities.UsersRoles;

@Repository(value = "confirmOtpDao")
@Transactional(rollbackFor = Exception.class)
public class ConfirmOtpDao {
	@Autowired
	private SessionFactory sessionFactory;

	public String getOtp(int user) {
		Session session = this.sessionFactory.getCurrentSession();
		return session.createQuery("from OtpInput where userId=?", OtpInput.class).setParameter(0, user)
				.getSingleResult().getOTP();
	}

	public void updateStatustOtp(int user, int otpInput) {
		Session session = this.sessionFactory.getCurrentSession();
		String hqlUpdate1 = "update UsersRoles c set c.role.id = 1 where c.users.id = :userId";
		int updatedEntities1 = session.createQuery(hqlUpdate1).setParameter("userId", user).executeUpdate();

		String hqlUpdate2 = "update OtpInput c set c.status = 2 where c.userId = :userId";
		int updatedEntities2 = session.createQuery(hqlUpdate2).setParameter("userId", user).executeUpdate();

	}

	public void getInfoOtp(String name) {
		Session session = this.sessionFactory.getCurrentSession();
		session.createQuery("");
		String sql = "SELECT * FROM EMPLOYEE";
		SQLQuery query = session.createSQLQuery(sql);
		
	}
}
