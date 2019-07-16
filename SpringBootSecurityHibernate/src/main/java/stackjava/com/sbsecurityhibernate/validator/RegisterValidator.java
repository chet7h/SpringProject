package stackjava.com.sbsecurityhibernate.validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import stackjava.com.sbsecurityhibernate.dao.UserDAO;
import stackjava.com.sbsecurityhibernate.entities.User;
import stackjava.com.sbsecurityhibernate.form.RegisterForm;

@Component
public class RegisterValidator implements Validator {

	@Autowired
	private UserDAO userDAO;

	@Override
	public boolean supports(Class<?> clazz) {
		return clazz == RegisterForm.class;
	}

	@Override
	public void validate(Object target, Errors errors) {
		RegisterForm form = (RegisterForm) target;
		ValidationUtils.rejectIfEmpty(errors, "firstName", "NotEmpty.registerForm.firstName");
		if (errors.hasErrors())
			return;
		ValidationUtils.rejectIfEmpty(errors, "lastName", "NotEmpty.registerForm.lastName");
		if (errors.hasErrors())
			return;
		ValidationUtils.rejectIfEmpty(errors, "username", "NotEmpty.registerForm.username");
		if (errors.hasErrors())
			return;
		ValidationUtils.rejectIfEmpty(errors, "numberPhone", "NotEmpty.registerForm.numberPhone");
		if (errors.hasErrors())
			return;
		ValidationUtils.rejectIfEmpty(errors, "password", "NotEmpty.registerForm.password");
		if (errors.hasErrors())
			return;
		ValidationUtils.rejectIfEmpty(errors, "passwordConfirm", "NotEmpty.registerForm.passwordConfirm");
		if (errors.hasErrors())
			return;
		if (form.getFirstName().length() > 15 || form.getFirstName().length() < 6) {
			errors.rejectValue("firstName", "Length.registerForm.firstName");
			return;
		}

		if (form.getLastName().length() > 15 || form.getLastName().length() < 6) {
			errors.rejectValue("lastName", "Length.registerForm.lastName");
			return;
		}

		if (form.getUsername().length() > 15 || form.getUsername().length() < 6) {
			errors.rejectValue("username", "Length.registerForm.userName");
			return;
		}

		if (!errors.hasFieldErrors("username")) {
			User dbUser = userDAO.loadUserByUsername(form.getUsername());
			if (dbUser != null) {
				// Tên tài khoản đã bị sử dụng bởi người khác.
				errors.rejectValue("username", "Duplicate.registerForm.userName");
				return;
			}
		}

		if (form.getPassword().length() > 15 || form.getPassword().length() < 6) {
			errors.rejectValue("password", "Length.registerForm.password");
			return;
		}
		if (!(errors.hasFieldErrors("password") && errors.hasFieldErrors("password"))) {
			if (form.getPassword().equals(form.getPasswordConfirm())) {
				errors.rejectValue("password", "Diff.registerForm.password");
				return;
			}
		}

	}

}
