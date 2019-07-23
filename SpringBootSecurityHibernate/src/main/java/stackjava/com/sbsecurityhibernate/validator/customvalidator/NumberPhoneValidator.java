package stackjava.com.sbsecurityhibernate.validator.customvalidator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class NumberPhoneValidator implements ConstraintValidator<NumberPhoneContraint, String> {

	@Override
	public boolean isValid(String contactField, ConstraintValidatorContext context) {
		return contactField != null && contactField.matches("[0-9]{8,14}");
	}

}
