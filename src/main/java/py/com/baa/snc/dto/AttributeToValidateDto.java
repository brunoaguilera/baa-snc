package py.com.baa.snc.dto;

import java.math.BigDecimal;
import java.util.Date;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import py.com.baa.snc.util.DateHelper;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({ "channel", "methodOfPayment", "productId", "productName", "recipientBrandId", "amount" })
public class AttributeToValidateDto {

	@JsonProperty(value = "firstDayOfCurrentMonth")
	private Date firstDayOfCurrentMonth = DateHelper.getDateWFirstHms(DateHelper.firstDayOfCurrent().getTime());;
	@JsonProperty(value = "lastDayOfCurrentMonth")
	private Date lastDayOfCurrentMonth = DateHelper.getDateWLastHms(DateHelper.lastDayOfCurrent().getTime());

	@JsonProperty(value = "channel")
	private String channel;
	@JsonProperty(value = "methodOfPayment")
	private String methodOfPayment;
	@JsonProperty(value = "productId")
	private Long productId;
	@JsonProperty(value = "productName")
	private String productName;

	@JsonProperty(value = "recipientBrandId")
	private Long recipientBrandId;
	@JsonProperty(value = "amount")
	private BigDecimal amount;

	@JsonIgnore
	private Map<String, Object> additionalData;

	public Date getFirstDayOfCurrentMonth() {
		return firstDayOfCurrentMonth;
	}

	public void setFirstDayOfCurrentMonth(Date firstDayOfCurrentMonth) {
		this.firstDayOfCurrentMonth = firstDayOfCurrentMonth;
	}

	public Date getLastDayOfCurrentMonth() {
		return lastDayOfCurrentMonth;
	}

	public void setLastDayOfCurrentMonth(Date lastDayOfCurrentMonth) {
		this.lastDayOfCurrentMonth = lastDayOfCurrentMonth;
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
		return "AttributeToValidateDto [firstDayOfCurrentMonth=" + firstDayOfCurrentMonth + ", lastDayOfCurrentMonth="
				+ lastDayOfCurrentMonth + ", channel=" + channel + ", methodOfPayment=" + methodOfPayment
				+ ", productId=" + productId + ", productName=" + productName + ", recipientBrandId=" + recipientBrandId
				+ ", amount=" + amount + ", additionalData=" + additionalData + "]";
	}
}