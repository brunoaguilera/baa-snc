package py.com.baa.snc.db.domain;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;

import org.springframework.beans.BeanUtils;

import py.com.baa.snc.dto.TransactionsDto;
import py.com.baa.snc.dto.enums.TransactionStatusEnum;

@Entity
@Table(name = "transactions", schema = "ctrllimit")
public class TransactionsEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "transactions_id_seq")
	@SequenceGenerator(name = "transactions_id_seq", sequenceName = "ctrllimit.transactions_id_seq", schema = "ctrllimit", initialValue = 1, allocationSize = 1)
	@Column(name = "id", nullable = false, unique = true)
	public Long id;
	@Column(name = "rrn")
	private String rrn;

	@Column(name = "created_at", updatable = false, nullable = false)
	@Temporal(TemporalType.TIMESTAMP)
	@NotNull
	private Date createdAt;

	@Column(name = "sender_person_uid")
	private Long senderPeopleUid;
	@Column(name = "sender_doc_number")
	private String senderDocNumber;
	@Column(name = "sender_wallet_number")
	private String senderWalletNumber;
	@Column(name = "sender_birth_date")
	private String senderBirthDate;
	@Column(name = "sender_full_name")
	private String senderFullName;

	@Column(name = "recipient_person_uid")
	private Long recipientPeopleUid;
	@Column(name = "recipient_doc_number")
	private String recipientDocNumber;
	@Column(name = "recipient_wallet_number")
	private String recipientWalletNumber;
	@Column(name = "recipient_brand_id")
	private Long recipientBrandId;
	@Column(name = "recipient_birth_date")
	private String recipientBirthDate;

	@Column(name = "channel")
	private String channel;
	@Column(name = "method_of_payment")
	private String methodOfPayment;
	@Column(name = "product_id")
	private Long productId;
	@Column(name = "product_name")
	private String productName;

	@Column(name = "ticket_number")
	private Long ticketNumber;
	@Column(name = "amount", nullable = false)
	private BigDecimal amount;
	@Column(name = "total_fees", nullable = false)
	private BigDecimal totalFees;
	@Enumerated(EnumType.STRING)
	@Column(name = "status")
	private TransactionStatusEnum status;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getRrn() {
		return rrn;
	}

	public void setRrn(String rrn) {
		this.rrn = rrn;
	}

	public Date getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}

	public Long getSenderPeopleUid() {
		return senderPeopleUid;
	}

	public void setSenderPeopleUid(Long senderPeopleUid) {
		this.senderPeopleUid = senderPeopleUid;
	}

	public String getSenderDocNumber() {
		return senderDocNumber;
	}

	public void setSenderDocNumber(String senderDocNumber) {
		this.senderDocNumber = senderDocNumber;
	}

	public String getSenderWalletNumber() {
		return senderWalletNumber;
	}

	public void setSenderWalletNumber(String senderWalletNumber) {
		this.senderWalletNumber = senderWalletNumber;
	}

	public String getSenderBirthDate() {
		return senderBirthDate;
	}

	public void setSenderBirthDate(String senderBirthDate) {
		this.senderBirthDate = senderBirthDate;
	}

	public String getSenderFullName() {
		return senderFullName;
	}

	public void setSenderFullName(String senderFullName) {
		this.senderFullName = senderFullName;
	}

	public Long getRecipientPeopleUid() {
		return recipientPeopleUid;
	}

	public void setRecipientPeopleUid(Long recipientPeopleUid) {
		this.recipientPeopleUid = recipientPeopleUid;
	}

	public String getRecipientDocNumber() {
		return recipientDocNumber;
	}

	public void setRecipientDocNumber(String recipientDocNumber) {
		this.recipientDocNumber = recipientDocNumber;
	}

	public String getRecipientWalletNumber() {
		return recipientWalletNumber;
	}

	public void setRecipientWalletNumber(String recipientWalletNumber) {
		this.recipientWalletNumber = recipientWalletNumber;
	}

	public String getRecipientBirthDate() {
		return recipientBirthDate;
	}

	public void setRecipientBirthDate(String recipientBirthDate) {
		this.recipientBirthDate = recipientBirthDate;
	}

	public String getChannel() {
		return channel;
	}

	public void setChannel(String channel) {
		this.channel = channel;
	}

	public String getMethodOfPayment() {
		return methodOfPayment;
	}

	public void setMethodOfPayment(String methodOfPayment) {
		this.methodOfPayment = methodOfPayment;
	}

	public Long getProductId() {
		return productId;
	}

	public void setProductId(Long productId) {
		this.productId = productId;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public Long getTicketNumber() {
		return ticketNumber;
	}

	public void setTicketNumber(Long ticketNumber) {
		this.ticketNumber = ticketNumber;
	}

	public BigDecimal getAmount() {
		return amount;
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}

	public BigDecimal getTotalFees() {
		return totalFees;
	}

	public void setTotalFees(BigDecimal totalFees) {
		this.totalFees = totalFees;
	}

	public Long getRecipientBrandId() {
		return recipientBrandId;
	}

	public void setRecipientBrandId(Long recipientBrandId) {
		this.recipientBrandId = recipientBrandId;
	}

	public TransactionStatusEnum getStatus() {
		return status;
	}

	public void setStatus(TransactionStatusEnum status) {
		this.status = status;
	}

	public TransactionsDto toDTO() {
		TransactionsDto dto = new TransactionsDto();
		BeanUtils.copyProperties(this, dto);
		return dto;
	}

	@Override
	public String toString() {
		return "TransactionsEntity [id=" + id + ", rrn=" + rrn + ", createdAt=" + createdAt + ", senderPeopleUid="
				+ senderPeopleUid + ", senderDocNumber=" + senderDocNumber + ", senderWalletNumber="
				+ senderWalletNumber + ", senderBirthDate=" + senderBirthDate + ", senderFullName=" + senderFullName
				+ ", recipientPeopleUid=" + recipientPeopleUid + ", recipientDocNumber=" + recipientDocNumber
				+ ", recipientWalletNumber=" + recipientWalletNumber + ", recipientBirthDate=" + recipientBirthDate
				+ ", channel=" + channel + ", methodOfPayment=" + methodOfPayment + ", productId=" + productId
				+ ", productName=" + productName + ", ticketNumber=" + ticketNumber + ", amount=" + amount
				+ ", totalFees=" + totalFees + ", recipientBrandId=" + recipientBrandId + ", status=" + status + "]";
	}
}