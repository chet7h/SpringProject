package stackjava.com.sbsecurityhibernate.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
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
		Role r = new Role();
		r.setId(1);
		UsersRoles ur = new UsersRoles();
		User u = new User();
		u.setId(user);
		ur.setRole(r);
		ur.setUsers(u);
		session.update(ur);

		String hqlUpdate2 = "update OtpInput c set c.status = 2 where c.userId = :userId";
		int updatedEntities2 = session.createQuery(hqlUpdate2, OtpInput.class).setParameter("userId", user)
				.executeUpdate();

	}
}
