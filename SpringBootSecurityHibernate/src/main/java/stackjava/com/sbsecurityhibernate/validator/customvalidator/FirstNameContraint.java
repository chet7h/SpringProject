package stackjava.com.sbsecurityhibernate.validator.customvalidator;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

@Documented
@Constraint(validatedBy = FirstNameValidator.class)
@Target(FIELD)
@Retention(RUNTIME)
public @interface FirstNameContraint {
	String message() default "Invalid First Name";

	Class<?>[] groups() default {};

	Class<? extends Payload>[] payload() default {};
}
