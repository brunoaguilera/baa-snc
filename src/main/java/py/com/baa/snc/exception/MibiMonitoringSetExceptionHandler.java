package py.com.baa.snc.exception;

import java.util.Map;
import java.util.TreeMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import py.com.baa.snc.mapper.OrikaBeanMapper;
import py.com.baa.snc.response.MibiMonitoringBaseResponse;

@ControllerAdvice
public class MibiMonitoringSetExceptionHandler {
	private static final Logger logger = LoggerFactory.getLogger(MibiMonitoringSetExceptionHandler.class);

	private static Map<String, HttpStatus> posibleStatus = new TreeMap<String, HttpStatus>();
	private static HttpHeaders responseHeaders = new HttpHeaders();
	
	@Autowired
	private OrikaBeanMapper mapper;
	
	private MibiMonitoringSetExceptionHandler() {
		super();
	}

	static {
		posibleStatus.put(MibiMonitoringInvocationException.TX_NOTFOUND, HttpStatus.NOT_FOUND);
		posibleStatus.put(MibiMonitoringInvocationException.TX_INVALID_STATUS, HttpStatus.CONFLICT);
		posibleStatus.put(MibiMonitoringInvocationException.TX_NO_FEES, HttpStatus.CONFLICT);
		posibleStatus.put(MibiMonitoringInvocationException.TX_TIME_OUT, HttpStatus.REQUEST_TIMEOUT);
		posibleStatus.put(MibiMonitoringInvocationException.TX_NOT_REVERSED_UNMODIFIED, HttpStatus.NOT_MODIFIED);
		posibleStatus.put(MibiMonitoringInvocationException.TX_NO_INVOICE, HttpStatus.NOT_FOUND);

		responseHeaders.set(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE);
	}
	
	@ExceptionHandler(MibiMonitoringApplicationException.class)
	public ResponseEntity<MibiMonitoringBaseResponse> handlerError(MibiMonitoringApplicationException e) {
		logger.error("#handlerError - MibiMonitoringException: {}", e.getMessage());
		MibiMonitoringBaseResponse response = mapper.map(e, MibiMonitoringBaseResponse.class);
		HttpStatus status = HttpStatus.CONFLICT;
		String errorCode = e.getMessage(); //e.getErrorCode();

		if (posibleStatus.containsKey(errorCode)) {
			status = posibleStatus.get(errorCode);
		} else {
			logger.warn("Unable to translate {}: {}", MibiMonitoringApplicationException.class.getName(), e);
		}
		
		logger.error("#handlerError - Response: {}", response);
		return new ResponseEntity<MibiMonitoringBaseResponse>(response, responseHeaders, status);
	}
}
