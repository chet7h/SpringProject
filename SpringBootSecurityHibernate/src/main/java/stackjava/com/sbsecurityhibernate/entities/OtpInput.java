package stackjava.com.sbsecurityhibernate.entities;

import static javax.persistence.GenerationType.IDENTITY;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.CreationTimestamp;

import lombok.Data;

@Data
@Entity
@Table(name = "otp_input", catalog = "giai_phap_sms")
public class OtpInput {

	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "otp_input_id")
	private int otpInputId;

	@Column(name = "user_id")
	private int userId;

	@Column(name = "id_info_send_sms")
	private int idInfoSendSms;

	@Column(name = "OTP")
	private String OTP;

	@Column(name = "status")
	private int status;

	@Column(name = "type")
	private int type;

	@CreationTimestamp
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "create_date")
	private Date createDate;

	@CreationTimestamp
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "update_date")
	private Date updateDate;

}
