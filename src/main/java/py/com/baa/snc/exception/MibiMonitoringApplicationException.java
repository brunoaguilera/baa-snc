package py.com.baa.snc.exception;

public class MibiMonitoringApplicationException extends BusinessException {

	private static final long serialVersionUID = 5029508179517400869L;

	public MibiMonitoringApplicationException(String message) {
		super(GenericMessageKey.UNKNOWN_ERROR.getKey(), message);
	}

	public MibiMonitoringApplicationException(Throwable e) {
		super(GenericMessageKey.UNKNOWN_ERROR.getKey(), e.getMessage());
	}
}
