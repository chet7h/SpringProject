package stackjava.com.sbsecurityhibernate.form;

import javax.validation.constraints.NotNull;

import stackjava.com.sbsecurityhibernate.validator.customvalidator.NumberPhoneContraint;

public class SendSmsForm {

	@NotNull
	@NumberPhoneContraint
	private String numberPhone;

	@NotNull
	private String contentSms;

	public String getNumberPhone() {
		return numberPhone;
	}

	public void setNumberPhone(String numberPhone) {
		this.numberPhone = numberPhone;
	}

	public String getContentSms() {
		return contentSms;
	}

	public void setContentSms(String contentSms) {
		this.contentSms = contentSms;
	}

}
