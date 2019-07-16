package stackjava.com.sbsecurityhibernate.validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import stackjava.com.sbsecurityhibernate.dao.UserDAO;
import stackjava.com.sbsecurityhibernate.entities.User;
import stackjava.com.sbsecurityhibernate.form.LoginForm;

@Component
public class LoginValidator implements Validator {

	@Autowired
	private UserDAO userDAO;

	@Override
	public boolean supports(Class<?> clazz) {
		return clazz == LoginForm.class;
	}

	@Override
	public void validate(Object target, Errors errors) {
		LoginForm form = (LoginForm) target;
		ValidationUtils.rejectIfEmpty(errors, "username", "NotEmpty.registerForm.username");
		if (errors.hasErrors())
			return;
		ValidationUtils.rejectIfEmpty(errors, "password", "NotEmpty.registerForm.password");
		if (errors.hasErrors())
			return;
		if (!errors.hasFieldErrors("username")) {
			User dbUser = userDAO.loadUserByUsername(form.getUsername());
			if (dbUser != null) {
				// Tên tài khoản đã bị sử dụng bởi người khác.
				errors.rejectValue("username", "Duplicate.registerForm.userName");
				return;
			}
		}

	}

}
