package py.com.baa.snc.dto.enums;

public enum MethodOfPayment {
	CASH("00", "Efectivo"), 
	CREDIT_CARD("01", "Tarjeta de credito"), 
	DEBIT_CARD("02", "Tarjeta de debito"), 
	CHECK("03",	"Cheque"), 
	DEBIT_ACCOUNT("04", "Debito en cuenta"), 
	WALLET("05", "Billetera"), 
	NOT_APPLY("??", "No aplica");

	private String cod;
	private String desc;

	MethodOfPayment(String cod, String desc) {
		this.cod = cod;
		this.desc = desc;
	}

	public String getCod() {
		return this.cod;
	}

	public String getDesc() {
		return this.desc;
	}

	public static MethodOfPayment getDefault() {
		return NOT_APPLY;
	}

	public static MethodOfPayment getByCod(String cod) {
		for (MethodOfPayment val : MethodOfPayment.values()) {
			if (val.cod.equals(cod)) {
				return val;
			}
		}
		return getDefault();
	}
}