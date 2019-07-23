package stackjava.com.sbsecurityhibernate.validator.customvalidator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.apache.commons.beanutils.PropertyUtils;

public class PasswordValidator implements ConstraintValidator<PasswordContraint, Object> {
	private String password;
	private String passwordConfirm;

	public void initialize(final PasswordContraint passwordContraint) {
		this.password = passwordContraint.first();
		this.passwordConfirm = passwordContraint.second();
	}

	@Override
	public boolean isValid(Object object, ConstraintValidatorContext context) {
		try {
			final Object firstObj = PropertyUtils.getProperty(object, this.password);
			final Object secondObj = PropertyUtils.getProperty(object, this.passwordConfirm);
			return firstObj == null && secondObj == null || firstObj != null && firstObj.equals(secondObj)
					&& password.matches("^(?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])[^\\s]{8,}$");
		} catch (final Exception ex) {
			return false;
		}
	}

}
