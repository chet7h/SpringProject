package stackjava.com.sbsecurityhibernate.validator.customvalidator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class LastNameValidator implements ConstraintValidator<LastNameContraint, String> {

	@Override
	public boolean isValid(String lastName, ConstraintValidatorContext context) {
		return (lastName.length() > 1 || lastName.length() < 15);
	}

}
