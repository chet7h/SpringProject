package stackjava.com.sbsecurityhibernate.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import stackjava.com.sbsecurityhibernate.entities.Role;
import stackjava.com.sbsecurityhibernate.entities.User;
import stackjava.com.sbsecurityhibernate.entities.UsersRoles;

@Repository(value = "userDAO")
@Transactional(rollbackFor = Exception.class)
public class UserDAO {

	@Autowired
	private SessionFactory sessionFactory;

	public User loadUserByUsername(final String username) {
		List<User> users = new ArrayList<User>();
		Session session = this.sessionFactory.getCurrentSession();
		users = session.createQuery("from User where username=?", User.class).setParameter(0, username).list();

		if (users.size() > 0) {
			return users.get(0);
		} else {
			return null;
		}

	}

	public void registerAccount(User user) {
		Session session = this.sessionFactory.getCurrentSession();
		user.setEnabled(true);
		session.save(user);
		Role role = new Role();
		role.setId(3);
		UsersRoles userRoles = new UsersRoles(role, user);
		session.save(userRoles);
	}
}
