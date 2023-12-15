package py.com.baa.snc.response;

import java.util.HashMap;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import py.com.baa.snc.exception.GenericMessageKey;

@JsonIgnoreProperties(ignoreUnknown = true)
public class MibiMonitoringDataResponse<T> extends MibiMonitoringBaseResponse {

	private T data;

	@JsonIgnore
	private Map<String, Object> additionalProperties = new HashMap<>();

	public void byBaseResponse(MibiMonitoringBaseResponse response) {
		this.setStatus(response.isStatus());
		this.setMessage(response.getMessage());
		this.setErrorCode(response.getErrorCode());
	}

	public T getData() {
		return data;
	}

	public void setData(T data) {
		this.data = data;
	}

	public void setOk() {
		this.setStatus(true);
		this.setMessage(GenericMessageKey.SUCCESSFULL_INVOCATION.getDesc());
	}

	public void setOk(String msg) {
		this.setStatus(true);
		this.setMessage(msg);
	}

	public void setError() {
		this.setStatus(false);
		this.setMessage(GenericMessageKey.SUCCESSFULL_INVOCATION.getDesc());
	}

	public void setError(String msg) {
		this.setStatus(false);
		this.setMessage(msg);
	}

	@JsonAnyGetter
	public Map<String, Object> getAdditionalProperties() {
		return this.additionalProperties;
	}

	@JsonAnySetter
	public void setAdditionalProperty(String name, Object value) {
		this.additionalProperties.put(name, value);
	}

	@Override
	public String toString() {
		return "MibiMonitoringDataResponse [data=" + data + ", getMessage()=" + getMessage() + ", isStatus()="
				+ isStatus() + ", getErrorCode()=" + getErrorCode() + "]";
	}
}
