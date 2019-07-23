/**
 * 
 */
package stackjava.com.sbsecurityhibernate.validator.customvalidator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;

import stackjava.com.sbsecurityhibernate.dao.UserDAO;
import stackjava.com.sbsecurityhibernate.entities.User;

/**
 * @author CuongNV20
 *
 */
public class UsernameValidator implements ConstraintValidator<UsernameContraint, String> {

	@Autowired
	private UserDAO userDAO;

	@Override
	public boolean isValid(String username, ConstraintValidatorContext context) {
		boolean isValidOk = true;
		isValidOk = username != null && username.matches("[0-9a-zA-Z_]{5,15}");
		if (isValidOk) {
			User dbUser = userDAO.loadUserByUsername(username);
			if (dbUser != null) {
				return false;
			}
		}
		return isValidOk;
	}

}
