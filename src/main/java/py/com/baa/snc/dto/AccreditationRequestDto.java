package py.com.baa.snc.dto;

import java.math.BigDecimal;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({ "senderDocNumber", "senderWalletNumber", "senderBirthDate", "senderFullName", "recipientDocNumber",
		"recipientWalletNumber", "recipientBirthDate", "recipientFullName", "channel", "methodOfPayment", "productId",
		"productName", "recipientBrandId", "amount" })
public class AccreditationRequestDto {

	@JsonProperty(value = "senderDocNumber")
	private String senderDocNumber;
	@JsonProperty(value = "senderWalletNumber")
	private String senderWalletNumber;
	@JsonProperty(value = "senderBirthDate")
	private String senderBirthDate;
	@JsonProperty(value = "senderFullName")
	private String senderFullName;

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

	@JsonProperty(value = "amount")
	private BigDecimal amount;

	@JsonIgnore
	private Map<String, Object> additionalData;

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

	public Long getRecipientBrandId() {
		return recipientBrandId;
	}

	public void setRecipientBrandId(Long recipientBrandId) {
		this.recipientBrandId = recipientBrandId;
	}

	public BigDecimal getAmount() {
		return amount;
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}

	@JsonAnyGetter
	public Map<String, Object> getAdditionalData() {
		return additionalData;
	}

	@JsonAnySetter
	public void setAdditionalData(Map<String, Object> additionalData) {
		this.additionalData = additionalData;
	}

	@Override
	public String toString() {
		return "AccreditationRequestDto [senderDocNumber=" + senderDocNumber + ", senderWalletNumber="
				+ senderWalletNumber + ", senderBirthDate=" + senderBirthDate + ", senderFullName=" + senderFullName
				+ ", recipientDocNumber=" + recipientDocNumber + ", recipientWalletNumber=" + recipientWalletNumber
				+ ", recipientBirthDate=" + recipientBirthDate + ", recipientFullName=" + recipientFullName
				+ ", channel=" + channel + ", methodOfPayment=" + methodOfPayment + ", productId=" + productId
				+ ", productName=" + productName + ", recipientBrandId=" + recipientBrandId + ", amount=" + amount
				+ ", additionalData=" + additionalData + "]";
	}
}