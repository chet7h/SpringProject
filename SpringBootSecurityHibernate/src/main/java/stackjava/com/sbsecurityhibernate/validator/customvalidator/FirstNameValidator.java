package stackjava.com.sbsecurityhibernate.validator.customvalidator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class FirstNameValidator implements ConstraintValidator<FirstNameContraint, String> {

	@Override
	public boolean isValid(String firstName, ConstraintValidatorContext context) {
		return (firstName.length() > 1 || firstName.length() < 10);
	}

}
