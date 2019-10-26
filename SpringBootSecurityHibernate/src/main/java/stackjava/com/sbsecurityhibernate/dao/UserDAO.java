package stackjava.com.sbsecurityhibernate.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import lombok.extern.slf4j.Slf4j;
import stackjava.com.sbsecurityhibernate.entities.Role;
import stackjava.com.sbsecurityhibernate.entities.Users;
import stackjava.com.sbsecurityhibernate.entities.UsersRoles;

@Repository(value = "userDAO")
@Transactional(rollbackFor = Exception.class)
@Slf4j
public class UserDAO {

	@Autowired
	private SessionFactory sessionFactory;

	public Users loadUserByUsername(final String username) {
		Session session = this.sessionFactory.getCurrentSession();
		Users results = null;
		try {

			results = session.createQuery("from Users where username = ?", Users.class).setParameter(0, username)
					.getSingleResult();

		} catch (Exception e) {
			log.debug(e.getMessage());
		}
		return results;

	}

	public void registerAccount(Users user) {
		Session session = this.sessionFactory.getCurrentSession();
		user.setEnabled(1);
		session.save(user);
		Role role = new Role();
		role.setRoleId(3);
		UsersRoles userRoles = new UsersRoles(role, user);
		session.save(userRoles);
	}

	public boolean hasNumberPhone(String numberPhone) {
		Session session = this.sessionFactory.getCurrentSession();
		List<Users> results = session.createQuery("from Users where numberPhone = ?", Users.class)
				.setParameter(0, numberPhone).getResultList();
		if (results.size() > 0) {
			return false;
		}
		return true;
	}
}
