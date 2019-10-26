package stackjava.com.sbsecurityhibernate.validator.customvalidator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;

import stackjava.com.sbsecurityhibernate.dao.UserDAO;

public class NumberPhoneInsueValidator implements ConstraintValidator<NumberPhoneContraint, String> {

	@Autowired
	private UserDAO userDAO;

	@Override
	public boolean isValid(String contactField, ConstraintValidatorContext context) {
		return userDAO.hasNumberPhone(contactField);
	}

}
