package py.com.baa.snc.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.wordnik.swagger.annotations.ApiModelProperty;

import py.com.baa.snc.exception.GenericMessageKey;

@JsonIgnoreProperties(ignoreUnknown = true)
public class MibiMonitoringBaseResponse {

	@JsonProperty(value = "status")
	private boolean status;

	@JsonProperty(value = "errorCode")
	private String errorCode;

	@JsonProperty(value = "message")
	private String message;
	
	
	public MibiMonitoringBaseResponse() {
		super();
	}

	public MibiMonitoringBaseResponse(boolean success, String errorCode, String message) {
		super();
		this.status = success;
		this.errorCode = errorCode;
		this.message = message;
	}

	public MibiMonitoringBaseResponse(String message, boolean status) {
		super();
		this.message = message;
		this.status = status;
	}

	public MibiMonitoringBaseResponse(GenericMessageKey genericMsg) {
		super();
		this.message = genericMsg.getDesc();
		this.status = levelToStatus(genericMsg.getLevel());
		this.errorCode = genericMsg.getKey();
	}

	public static boolean levelToStatus(String level) {
		if (GenericMessageKey.success().equals(level)) {
			return true;
		}
		return false;
	}

	/**
	 * Detalle de la respuesta
	 *
	 * @return message
	 */
	@ApiModelProperty(notes = "detalle de respuesta")
	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	/**
	 * Indicador de respuesta
	 *
	 * @return status
	 */
	@ApiModelProperty(notes = "indicador de resultado")
	public boolean isStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}

	/**
	 * Código de error
	 *
	 * @return errorCode
	 */
	@ApiModelProperty(notes = "código de error")
	public String getErrorCode() {
		return errorCode;
	}

	public void setErrorCode(String errorCode) {
		this.errorCode = errorCode;
	}

	@Override
	public String toString() {
		return "MibiMonitoringBaseResponse [status=" + status + ", errorCode=" + errorCode + ", message=" + message
				+ "]";
	}
}