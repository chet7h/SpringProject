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

@Entity
@Table(name = "info_send_sms", catalog = "giai_phap_sms")
public class InfoSendSms {

	private int idInfoSendSms;

	private String numberPhone;

	private String content;

	private int status;

	private int typeSend;

	private String sender;

	private Date createDate;

	private Date updateDate;
	
	private boolean sendNow;
	
	private Date dateTimeSend;
	
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

	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "id_info_send_sms")
	public int getIdInfoSendSms() {
		return idInfoSendSms;
	}

	public void setIdInfoSendSms(int idInfoSendSms) {
		this.idInfoSendSms = idInfoSendSms;
	}

	@Column(name = "number_phone", length = 15)
	public String getNumberPhone() {
		return numberPhone;
	}

	public void setNumberPhone(String numberPhone) {
		this.numberPhone = numberPhone;
	}

	@Column(name = "content", length = 1000)
	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	@Column(name = "status", length = 11)
	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	@Column(name = "type_send", length = 11)
	public int getTypeSend() {
		return typeSend;
	}

	public void setTypeSend(int typeSend) {
		this.typeSend = typeSend;
	}

	@Column(name = "sender", length = 15)
	public String getSender() {
		return sender;
	}

	public void setSender(String sender) {
		this.sender = sender;
	}

	@CreationTimestamp
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "create_date")
	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	@Column(name = "update_date")
	public Date getUpdateDate() {
		return updateDate;
	}

	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
	}

	@Column(name = "send_now")
	public boolean isSendNow() {
		return sendNow;
	}

	public void setSendNow(boolean sendNow) {
		this.sendNow = sendNow;
	}

	@Column(name = "date_time_send")
	public Date getDateTimeSend() {
		return dateTimeSend;
	}

	public void setDateTimeSend(Date dateTimeSend) {
		this.dateTimeSend = dateTimeSend;
	}

	@Column(name = "repeat_time")
	public int getRepeatTime() {
		return repeatTime;
	}

	public void setRepeatTime(int repeatTime) {
		this.repeatTime = repeatTime;
	}
	
	
}
