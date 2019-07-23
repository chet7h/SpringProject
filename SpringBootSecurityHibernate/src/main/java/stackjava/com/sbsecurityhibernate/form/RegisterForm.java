package stackjava.com.sbsecurityhibernate.form;

import javax.validation.constraints.NotNull;

import stackjava.com.sbsecurityhibernate.validator.customvalidator.FirstNameContraint;
import stackjava.com.sbsecurityhibernate.validator.customvalidator.LastNameContraint;
import stackjava.com.sbsecurityhibernate.validator.customvalidator.NumberPhoneContraint;
import stackjava.com.sbsecurityhibernate.validator.customvalidator.PasswordContraint;
import stackjava.com.sbsecurityhibernate.validator.customvalidator.UsernameContraint;

@PasswordContraint(first = "password", second = "passwordConfirm")
public class RegisterForm {

	@NotNull
	@LastNameContraint
	private String lastName;

	@NotNull
	@FirstNameContraint
	private String firstName;

	@NotNull
	@UsernameContraint
	private String username;

	@NotNull
	@NumberPhoneContraint
	private String numberPhone;

	private String password;

	private String passwordConfirm;

	public RegisterForm() {
		super();
	}

	public RegisterForm(String lastName, String firstName, String username, String numberPhone, String password,
			String passwordConfirm) {
		super();
		this.lastName = lastName;
		this.firstName = firstName;
		this.username = username;
		this.numberPhone = numberPhone;
		this.password = password;
		this.passwordConfirm = passwordConfirm;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getNumberPhone() {
		return numberPhone;
	}

	public void setNumberPhone(String numberPhone) {
		this.numberPhone = numberPhone;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getPasswordConfirm() {
		return passwordConfirm;
	}

	public void setPasswordConfirm(String passwordConfirm) {
		this.passwordConfirm = passwordConfirm;
	}

}
