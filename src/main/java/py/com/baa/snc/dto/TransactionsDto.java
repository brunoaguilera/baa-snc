package py.com.baa.snc.dto;

import java.math.BigDecimal;
import java.util.Date;
import java.util.Map;

import org.springframework.beans.BeanUtils;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import py.com.baa.snc.db.domain.TransactionsEntity;
import py.com.baa.snc.dto.enums.TransactionStatusEnum;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({ "id", "rrn", "createdAt", "senderPeopleUid", "senderDocNumber", "senderWalletNumber",
		"senderBirthDate", "senderFullName", "recipientPeopleUid", "recipientDocNumber", "recipientWalletNumber",
		"recipientBirthDate", "recipientFullName", "channel", "productId", "productName", "recipientBrandId",
		"ticketNumber", "amount", "totalFees", "status" })
public class TransactionsDto {

	@JsonProperty(value = "id")
	public Long id;
	@JsonProperty(value = "rrn")
	private String rrn;
	@JsonProperty(value = "createdAt")
	private Date createdAt;

	@JsonProperty(value = "senderPeopleUid")
	private Long senderPeopleUid;
	@JsonProperty(value = "senderDocNumber")
	private String senderDocNumber;
	@JsonProperty(value = "senderWalletNumber")
	private String senderWalletNumber;
	@JsonProperty(value = "senderBirthDate")
	private String senderBirthDate;
	@JsonProperty(value = "senderFullName")
	private String senderFullName;

	@JsonProperty(value = "recipientPeopleUid")
	private Long recipientPeopleUid;
	@JsonProperty(value = "recipientDocNumber")
	private String recipientDocNumber;
	@JsonProperty(value = "recipientWalletNumber")
	private String recipientWalletNumber;
	@JsonProperty(value = "recipientBrandId")
	private Long recipientBrandId;
	@JsonProperty(value = "recipientBirthDate")
	private String recipientBirthDate;
	@JsonProperty(value = "recipientFullName")
	private String recipientFullName;

	@JsonProperty(value = "channel")
	private String channel;
	@JsonProperty(value = "methodOfPayment")
	private String methodOfPayment;
	@JsonProperty(value = "productId")
	private Long productId;
	@JsonProperty(value = "productName")
	private String productName;

	@JsonProperty(value = "ticketNumber")
	private Long ticketNumber;
	@JsonProperty(value = "amount")
	private BigDecimal amount;
	@JsonProperty(value = "totalFees")
	private BigDecimal totalFees;
	@JsonProperty(value = "status")
	private TransactionStatusEnum status;

	@JsonIgnore
	private Map<String, Object> additionalData;

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

	public String getRecipientFullName() {
		return recipientFullName;
	}

	public void setRecipientFullName(String recipientFullName) {
		this.recipientFullName = recipientFullName;
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

	@JsonAnyGetter
	public Map<String, Object> getAdditionalData() {
		return additionalData;
	}

	@JsonAnySetter
	public void setAdditionalData(Map<String, Object> additionalData) {
		this.additionalData = additionalData;
	}

	public TransactionsEntity toEntity() {
		TransactionsEntity entity = new TransactionsEntity();
		BeanUtils.copyProperties(this, entity);
		return entity;
	}

	@Override
	public String toString() {
		return "TransactionsDto [id=" + id + ", rrn=" + rrn + ", createdAt=" + createdAt + ", senderPeopleUid="
				+ senderPeopleUid + ", senderDocNumber=" + senderDocNumber + ", senderWalletNumber="
				+ senderWalletNumber + ", senderBirthDate=" + senderBirthDate + ", senderFullName=" + senderFullName
				+ ", recipientPeopleUid=" + recipientPeopleUid + ", recipientDocNumber=" + recipientDocNumber
				+ ", recipientWalletNumber=" + recipientWalletNumber + ", recipientBirthDate=" + recipientBirthDate
				+ ", recipientFullName=" + recipientFullName + ", channel=" + channel + ", methodOfPayment="
				+ methodOfPayment + ", productId=" + productId + ", productName=" + productName + ", recipientBrandId="
				+ recipientBrandId + ", ticketNumber=" + ticketNumber + ", amount=" + amount + ", totalFees="
				+ totalFees + ", status=" + status + ", additionalData=" + additionalData + "]";
	}
}