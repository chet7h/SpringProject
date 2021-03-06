package stackjava.com.sbsecurityhibernate.entities;
// Generated Sep 8, 2019 5:55:56 PM by Hibernate Tools 4.3.5.Final

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.CreationTimestamp;

/**
 * UsersRoles generated by hbm2java
 */
@Entity
@Table(name = "users_roles", catalog = "giai_phap_sms")
public class UsersRoles implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer usersRolesId;
	private Role role;
	private Users users;
	private Date createDate;
	private Date updateDate;

	public UsersRoles() {
	}

	public UsersRoles(Role role, Users users, Date createDate, Date updateDate) {
		this.role = role;
		this.users = users;
		this.createDate = createDate;
		this.updateDate = updateDate;
	}

	public UsersRoles(Role role, Users user) {
		this.role = role;
		this.users = user;
	}

	@Id
	@GeneratedValue(strategy = IDENTITY)

	@Column(name = "users_roles_id", unique = true, nullable = false)
	public Integer getUsersRolesId() {
		return this.usersRolesId;
	}

	public void setUsersRolesId(Integer usersRolesId) {
		this.usersRolesId = usersRolesId;
	}

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "role_id", nullable = false)
	public Role getRole() {
		return this.role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "user_id", nullable = false)
	public Users getUsers() {
		return this.users;
	}

	public void setUsers(Users users) {
		this.users = users;
	}

	@CreationTimestamp
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "create_date", nullable = false, length = 19)
	public Date getCreateDate() {
		return this.createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	@CreationTimestamp
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "update_date", nullable = false, length = 19)
	public Date getUpdateDate() {
		return this.updateDate;
	}

	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
	}

}
