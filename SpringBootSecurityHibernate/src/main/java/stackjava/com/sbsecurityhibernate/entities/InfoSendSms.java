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
@Table(name = "info_send_sms", catalog = "giai_phap_sms")
public class InfoSendSms {
	
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "id_info_send_sms")
	private int idInfoSendSms;
	
	@Column(name = "number_phone", length = 15)
	private String numberPhone;

	@Column(name = "content", length = 1000)
	private String content;
	
	@Column(name = "status", length = 11)
	private int status;
	
	@Column(name = "type_send")
	private int typeSend;
	
	@Column(name = "sender", length = 15)
	private String sender;
	
	@CreationTimestamp
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "create_date")
	
	private Date createDate;
	@Column(name = "update_date")
	private Date updateDate;
	
	@Column(name = "send_now")
	private boolean sendNow;
	
	@Column(name = "date_time_send")
	private Date dateTimeSend;

	@Column(name = "repeat_time")
	private int repeatTime;

	public InfoSendSms() {
		super();
	}

	public InfoSendSms(int idInfoSendSms, String numberPhone, String content, int status, int typeSend, String sender,
			Date createDate, Date updateDate) {
		super();
		this.idInfoSendSms = idInfoSendSms;
		this.numberPhone = numberPhone;
		this.content = content;
		this.status = status;
		this.typeSend = typeSend;
		this.sender = sender;
		this.createDate = createDate;
		this.updateDate = updateDate;
	}

}
