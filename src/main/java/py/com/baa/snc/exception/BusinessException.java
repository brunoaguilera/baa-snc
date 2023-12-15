package py.com.baa.snc.exception;

import py.com.baa.snc.util.MibiMonitoringUtils;

public abstract class BusinessException extends Exception {

	public enum FIELDS_POSSIBLE_ERRORS {
		REQUIRED, INVALID, DUPLICATE
	}

	private static final long serialVersionUID = 8943855572101122016L;

	private final String errorCode;

	protected String offendingField;
	public FIELDS_POSSIBLE_ERRORS fieldErrorType;

	public BusinessException(GenericMessageKey msgKey, String msg) {
		super(msg);
		errorCode = msgKey.getKey();
	}

	public BusinessException(String errorCode, String message) {
		super(message);
		this.errorCode = errorCode;
		this.offendingField = null;
		this.fieldErrorType = null;
	}

	public BusinessException(String errorCode, String message, String offendingField) {
		super(message);
		this.errorCode = errorCode;
		this.offendingField = offendingField;
		this.fieldErrorType = null;
	}

	public BusinessException(String errorCode, String message, String offendingField,
			FIELDS_POSSIBLE_ERRORS fieldErrorType) {
		super(message);
		this.errorCode = errorCode;
		this.offendingField = offendingField;
		this.fieldErrorType = fieldErrorType;
	}

	public String getErrorCode() {
		return errorCode;
	}

	@Override
	public String getMessage() {
		String message = super.getMessage();
		if (message != null) {
			return message;
		}
		if (offendingField != null) {
			if (fieldErrorType != null && fieldErrorType.equals(FIELDS_POSSIBLE_ERRORS.REQUIRED)) {
				return "The field " + MibiMonitoringUtils.formatLogString(offendingField) + " is required";
			} else {
				if (fieldErrorType != null && fieldErrorType.equals(FIELDS_POSSIBLE_ERRORS.DUPLICATE)) {
					return "The field " + MibiMonitoringUtils.formatLogString(offendingField) + " is duplicate";
				} else {
					return "The field " + MibiMonitoringUtils.formatLogString(offendingField) + " is invalid";
				}
			}
		}
		return null;
	}

	public String getOffendingField() {
		return offendingField;
	}

	public FIELDS_POSSIBLE_ERRORS getFieldErrorType() {
		return fieldErrorType;
	}

	@Override
	public String toString() {
		return "BusinessException [errorCode=" + errorCode + ", offendingField=" + offendingField + ", fieldErrorType="
				+ fieldErrorType + "]";
	}
}
