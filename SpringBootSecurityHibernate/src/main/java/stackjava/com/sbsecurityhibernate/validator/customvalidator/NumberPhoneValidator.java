package stackjava.com.sbsecurityhibernate.validator.customvalidator;

import java.util.List;
import java.util.Map;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;

import stackjava.com.sbsecurityhibernate.properties.NumberPhoneBeginProperties;

public class NumberPhoneValidator implements ConstraintValidator<NumberPhoneContraint, String> {
	@Autowired
	private NumberPhoneBeginProperties numberPhoneBeginProperties;

	@Override
	public boolean isValid(String contactField, ConstraintValidatorContext context) {
		Map<String, String> a = numberPhoneBeginProperties.getSynonyms();
		System.out.println(a);
		return contactField != null && contactField.matches("^0[0-9]");
	}

}
