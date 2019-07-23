/**
 * 
 */
package stackjava.com.sbsecurityhibernate.validator.customvalidator;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

/**
 * @author CuongNV20
 *
 */
@Documented
@Constraint(validatedBy = NumberPhoneValidator.class)
@Target({ ElementType.METHOD, ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
public @interface NumberPhoneContraint {
	String message() default "{Error.registerForm.firstName}";

	Class<?>[] groups() default {};

	Class<? extends Payload>[] payload() default {};
}
