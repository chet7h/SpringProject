package stackjava.com.sbsecurityhibernate.dto;

import lombok.Data;

@Data
public class RegisterOtpDto {
	private int idInfoSendSms;
	private int userId;
	private String OTP;
	private int status;

}
