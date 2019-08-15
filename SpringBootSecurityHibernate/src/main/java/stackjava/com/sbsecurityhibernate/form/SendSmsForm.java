package stackjava.com.sbsecurityhibernate.form;

import java.util.Date;

import javax.validation.constraints.NotNull;

import lombok.Data;
import stackjava.com.sbsecurityhibernate.validator.customvalidator.NumberPhoneContraint;

@Data
public class SendSmsForm {

	@NotNull
	@NumberPhoneContraint
	private String numberPhone;

	@NotNull
	private String contentSms;

	private boolean sendNow;
	
	private Date dateTimeSend;
	
	private int repeatTime;
}
