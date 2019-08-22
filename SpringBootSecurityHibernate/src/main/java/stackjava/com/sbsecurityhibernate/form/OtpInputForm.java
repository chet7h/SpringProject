package stackjava.com.sbsecurityhibernate.form;

import lombok.Data;

@Data
public class OtpInputForm {
	private int userId;
	private String user;
	private String Otp;
	private int otpInputId;

}
