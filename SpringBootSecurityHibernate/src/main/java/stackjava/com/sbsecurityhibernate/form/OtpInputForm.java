package stackjava.com.sbsecurityhibernate.form;

import org.springframework.context.annotation.Scope;

import lombok.Data;

@Data
@Scope(scopeName = "Sesssion")
public class OtpInputForm {
	private int userId;
	private String user;
	private String Otp;
	private int otpInputId;

}
