package stackjava.com.sbsecurityhibernate.validator.customvalidator;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.ElementType.METHOD;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

@Documented
@Constraint(validatedBy = UsernameValidator.class)
@Target({ METHOD, FIELD })
@Retention(RetentionPolicy.RUNTIME)
public @interface UsernameContraint {
	String message() default "Invalid Username";

	Class<?>[] groups() default {};

	Class<? extends Payload>[] payload() default {};
}
