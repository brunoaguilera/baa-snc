package py.com.baa.snc.rest;

public final class ApiPaths {

	private ApiPaths() {
	}

	public static final String API_PATTERN = "/api/.*";

	/**
	 * Control de límites de crédito
	 */
	public static final String LIMIT_BY_ACREDITATION_REQUEST = "/api/credit/limit-by-acreditation-request";
	public static final String LIMIT_BY_SENDER_CI = "/api/credit/limit-by-sender-ci/{sender-ci}";
	public static final String LIMIT_BY_RECIPIENT_CI = "/api/credit/limit-by-recipient-ci";
	public static final String LIMIT_BY_RECIPIENT_WALLET = "/api/credit/limit-by-recipient-wallet";

	/**
	 * Transacciones
	 */
	public static final String TX_CREATE = "/api/tx/create";
	public static final String TX_BETWEEN_DATE = "/api/tx/between/initial/{initial}/end/{end}";
	public static final String TX_BETWEEN_DATE_SENDER_CI = "/api/tx/between/initial/{initial}/end/{end}/sender-ci/{sender-ci}";
	public static final String TX_BY_RRN = "/api/tx/get-by-rrn/{rrn}";
	public static final String TX_BY_TICKET_NUMBER = "/api/tx/get-by-ticket-number/{ticket-number}";
}