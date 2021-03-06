package stackjava.com.sbsecurityhibernate.entities;
// Generated Sep 8, 2019 5:55:56 PM by Hibernate Tools 4.3.5.Final

import static javax.persistence.GenerationType.IDENTITY;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;
import javax.persistence.UniqueConstraint;

import org.hibernate.annotations.CreationTimestamp;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

/**
 * Users generated by hbm2java
 */
@Entity
@Table(name = "users", catalog = "giai_phap_sms", uniqueConstraints = @UniqueConstraint(columnNames = "username"))
public class Users implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer userId;
	private String username;
	private String password;
	private int enabled;
	private String firstName;
	private String lastName;
	private String numberPhone;
	private Date createDate;
	private Date updateDate;
	private Set<OtpInput> otpInputs = new HashSet<OtpInput>(0);
	private Set<UsersRoles> usersRoleses = new HashSet<UsersRoles>(0);

	public Users() {
	}

	public Users(String username, String password, int enabled, String firstName, String lastName, String numberPhone,
			Date createDate, Date updateDate) {
		this.username = username;
		this.password = password;
		this.enabled = enabled;
		this.firstName = firstName;
		this.lastName = lastName;
		this.numberPhone = numberPhone;
		this.createDate = createDate;
		this.updateDate = updateDate;
	}

	public Users(String username, String password, int enabled, String firstName, String lastName, String numberPhone,
			Date createDate, Date updateDate, Set<OtpInput> otpInputs, Set<UsersRoles> usersRoleses) {
		this.username = username;
		this.password = password;
		this.enabled = enabled;
		this.firstName = firstName;
		this.lastName = lastName;
		this.numberPhone = numberPhone;
		this.createDate = createDate;
		this.updateDate = updateDate;
		this.otpInputs = otpInputs;
		this.usersRoleses = usersRoleses;
	}

	public Users(String username, String password, int enabled, String firstName, String lastName, String numberPhone,
			Date createDate, Date updateDate, Set<UsersRoles> usersRoleses) {
		this.username = username;
		this.password = password;
		this.enabled = enabled;
		this.firstName = firstName;
		this.lastName = lastName;
		this.numberPhone = numberPhone;
		this.createDate = createDate;
		this.updateDate = updateDate;
		this.usersRoleses = usersRoleses;
	}
	
	@Id
	@GeneratedValue(strategy = IDENTITY)

	@Column(name = "user_id", unique = true, nullable = false)
	public Integer getUserId() {
		return this.userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	@Column(name = "username", unique = true, nullable = false, length = 45)
	public String getUsername() {
		return this.username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	@Column(name = "password", nullable = false)
	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Column(name = "enabled", nullable = false)
	public int getEnabled() {
		return this.enabled;
	}

	public void setEnabled(int enabled) {
		this.enabled = enabled;
	}

	@Column(name = "first_name", nullable = false, length = 15)
	public String getFirstName() {
		return this.firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	@Column(name = "last_name", nullable = false, length = 15)
	public String getLastName() {
		return this.lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	@Column(name = "number_phone", nullable = false, length = 15)
	public String getNumberPhone() {
		return this.numberPhone;
	}

	public void setNumberPhone(String numberPhone) {
		this.numberPhone = numberPhone;
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

	@OneToMany(fetch = FetchType.EAGER, mappedBy = "users")
	public Set<OtpInput> getOtpInputs() {
		return this.otpInputs;
	}

	public void setOtpInputs(Set<OtpInput> otpInputs) {
		this.otpInputs = otpInputs;
	}

	@OneToMany(fetch = FetchType.EAGER, mappedBy = "users")
	public Set<UsersRoles> getUsersRoleses() {
		return this.usersRoleses;
	}

	public void setUsersRoleses(Set<UsersRoles> usersRoleses) {
		this.usersRoleses = usersRoleses;
	}

	@Transient
	public List<GrantedAuthority> getAuthorities() {
		List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
		for (UsersRoles usersRoles : this.usersRoleses) {
			authorities.add(new SimpleGrantedAuthority(usersRoles.getRole().getNameRole()));
		}
		return authorities;
	}
}
