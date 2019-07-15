package stackjava.com.sbsecurityhibernate.entities;

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

@Entity
@Table(name = "users", catalog = "giai_phap_sms", uniqueConstraints = @UniqueConstraint(columnNames = "username"))
public class User implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	private Integer id;
	private String username;
	private String password;
	private Boolean enabled;

	private String firstName;
	private String lastName;
	private String numberPhone;
	private Date createDate;
	private Date updateDate;

	private Set<UsersRoles> usersRoleses = new HashSet<UsersRoles>(0);

	public User() {
	}

	public User(Integer id, String username, String password, Boolean enabled, String firstName, String lastName,
			String numberPhone, Date createDate, Date updateDate, Set<UsersRoles> usersRoleses) {
		super();
		this.id = id;
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

	@Column(name = "id", unique = true, nullable = false)
	public Integer getId() {
		return this.id;
	}

	public void setId(final Integer id) {
		this.id = id;
	}

	@Column(name = "username", unique = true, length = 45)
	public String getUsername() {
		return this.username;
	}

	public void setUsername(final String username) {
		this.username = username;
	}

	@Column(name = "password")
	public String getPassword() {
		return this.password;
	}

	public void setPassword(final String password) {
		this.password = password;
	}

	@Column(name = "enabled", nullable = false, columnDefinition = "TINYINT(1)")
	public Boolean getEnabled() {
		return this.enabled;
	}

	public void setEnabled(final Boolean enabled) {
		this.enabled = enabled;
	}

	@OneToMany(fetch = FetchType.EAGER, mappedBy = "users")
	public Set<UsersRoles> getUsersRoleses() {
		return this.usersRoleses;
	}

	public void setUsersRoleses(final Set<UsersRoles> usersRoleses) {
		this.usersRoleses = usersRoleses;
	}

	@Column(name = "first_name")
	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	@Column(name = "last_name")
	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	@Column(name = "number_phone")
	public String getNumberPhone() {
		return numberPhone;
	}

	public void setNumberPhone(String numberPhone) {
		this.numberPhone = numberPhone;
	}

	@CreationTimestamp
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "create_date", insertable = false)
	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	@Column(name = "update_date")
	public Date getUpdateDate() {
		return updateDate;
	}

	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
	}

	@Transient
	public List<GrantedAuthority> getAuthorities() {
		List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
		for (UsersRoles usersRoles : this.usersRoleses) {
			authorities.add(new SimpleGrantedAuthority(usersRoles.getRole().getName()));
		}
		return authorities;
	}

}
