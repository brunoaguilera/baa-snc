package py.com.baa.snc.exception;

public class MibiMonitoringInvocationException extends BusinessException {

	private static final long serialVersionUID = 125369358508931331L;

	public static final String TX_NOTFOUND = "mibi.tx.notfound";
	public static final String TX_INVALID_STATUS = "mibi.tx.status.invalid";
	public static final String TX_TIME_OUT = "mibi.tx.timeout";
	public static final String TX_UPDATE_FAIL = "mibi.tx.update.fail";
	public static final String TX_NOT_REVERSED_UNKWOWN = "jcard.reverse.rrn.unknown";
	public static final String TX_NOT_REVERSED_UNMODIFIED = "reverse.unmodified";
	public static final String TX_NOT_REVERSED_ERROR = "reverse.error";
	public static final String TX_NO_FEES = "mibi.tx.fees.missing";
	public static final String TX_NO_INVOICE = "mibi.tx.invoice.notFound";
	public static final String TX_NO_CREDIT_MEMO = "mibi.tx.creditMemo.notFound";
	public static final String TX_TYPE_NOT_FOUND = "mibi.tx.type.notFound";

	public static final String TRX_INVALID_FIELD = "mibi.tx.field";

	public MibiMonitoringInvocationException(String errorCode, String message) {
		super(errorCode, message);
	}

	public MibiMonitoringInvocationException(String offendingField, FIELDS_POSSIBLE_ERRORS fieldErrorType, String message) {
		super(TRX_INVALID_FIELD + "." + offendingField + "." + fieldErrorType.toString().toLowerCase(), message);
		this.offendingField = offendingField;
		this.fieldErrorType = fieldErrorType;
	}
	
	public MibiMonitoringInvocationException(GenericMessageKey messageKey) {
		super(messageKey.getKey(), messageKey.getDesc());
	}

	public MibiMonitoringInvocationException(GenericMessageKey messageKey, String msg) {
		super(messageKey, msg);
	}
}
