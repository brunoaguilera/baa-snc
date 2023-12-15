package py.com.baa.snc.web.controller;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.TypeMismatchException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.client.ResourceAccessException;

import py.com.baa.snc.exception.GenericMessageKey;
import py.com.baa.snc.exception.MibiMonitoringApplicationException;
import py.com.baa.snc.exception.MibiMonitoringInvocationException;
import py.com.baa.snc.mapper.OrikaBeanMapper;
import py.com.baa.snc.response.MibiMonitoringBaseResponse;
import py.com.baa.snc.util.MibiMonitoringUtils;

@ControllerAdvice
public class CommonErrorControllerAdvice {

	private static final Logger LOGGER = LoggerFactory.getLogger(CommonErrorControllerAdvice.class);

	private static HttpHeaders responseHeaders = new HttpHeaders();

	@Autowired
	private OrikaBeanMapper mapper;

	@ExceptionHandler(MibiMonitoringApplicationException.class)
	public ResponseEntity<MibiMonitoringBaseResponse> handleWalletException(MibiMonitoringApplicationException e) {
		LOGGER.error("#handleWalletException - MibiWalletException: {}", e.getMessage());
		MibiMonitoringBaseResponse response = mapper.map(e, MibiMonitoringBaseResponse.class);
		HttpStatus status = HttpStatus.CONFLICT;

		LOGGER.error("#handleWalletException - status: {}, Response: {}", status, response);
		return new ResponseEntity<>(response, responseHeaders, status);
	}

	@ExceptionHandler(MibiMonitoringInvocationException.class)
	public ResponseEntity<MibiMonitoringBaseResponse> handleWalletNotFound(MibiMonitoringInvocationException e) {
		LOGGER.error("#handleWalletNotFound - MibiWalletNotFoundException: {}", e.getMessage());
		MibiMonitoringBaseResponse response = mapper.map(e, MibiMonitoringBaseResponse.class);

		LOGGER.error("#handleWalletNotFound#MibiWalletNotFoundException - Response: {}", response);
		return new ResponseEntity<>(response, responseHeaders, HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler(TypeMismatchException.class)
	public ResponseEntity<MibiMonitoringBaseResponse> handleTypeMismatch(TypeMismatchException e) {
		LOGGER.error("#handleTypeMismatch - TypeMismatchException: {}", e.getErrorCode());
		MibiMonitoringBaseResponse response = new MibiMonitoringBaseResponse(false,
				GenericMessageKey.INVALID_PARAMETERS.getDesc(), e.getMessage());
		response.setMessage(GenericMessageKey.INVALID_PARAMETERS.getDesc());

		LOGGER.error("#handleTypeMismatch#TypeMismatchException - Response: {}", response);
		return new ResponseEntity<>(response, responseHeaders, HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(HttpMessageNotReadableException.class)
	public ResponseEntity<MibiMonitoringBaseResponse> handleTypeMismatch(HttpMessageNotReadableException e) {
		LOGGER.error("#handleTypeMismatch - HttpMessageNotReadableException: {}", e.getMessage());
		MibiMonitoringBaseResponse response = new MibiMonitoringBaseResponse(false,
				GenericMessageKey.INVALID_BODY.getDesc(), e.getMessage());
		response.setMessage(GenericMessageKey.INVALID_BODY.getDesc());

		LOGGER.error("#handleTypeMismatch#HttpMessageNotReadableException - Response: {}", response);
		return new ResponseEntity<>(response, responseHeaders, HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(IllegalArgumentException.class)
	public ResponseEntity<MibiMonitoringBaseResponse> handleTypeMismatch(IllegalArgumentException e) {
		LOGGER.error("#handleTypeMismatch - IllegalArgumentException: {}", e.getMessage());
		MibiMonitoringBaseResponse response = new MibiMonitoringBaseResponse(false,
				GenericMessageKey.INVALID_ARGUMENT.getDesc(), e.getMessage());
		response.setMessage(GenericMessageKey.INVALID_ARGUMENT.getDesc());
		LOGGER.error("Got an java.lang.IllegalArgumentException ", e);
		return new ResponseEntity<>(response, responseHeaders, HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(RuntimeException.class)
	public ResponseEntity<MibiMonitoringBaseResponse> handleRuntimeException(RuntimeException e) {
		LOGGER.error("#handleRuntimeException - RuntimeException message: {}",
				MibiMonitoringUtils.getRuntimeExceptionMsg(e));
		MibiMonitoringBaseResponse response = new MibiMonitoringBaseResponse();
		response.setStatus(false);
		response.setErrorCode(GenericMessageKey.UNKNOWN_ERROR.getKey());
		response.setMessage(GenericMessageKey.UNKNOWN_ERROR.getDesc());

		LOGGER.error("#handleRuntimeException - Response: {}", response);
		return new ResponseEntity<>(response, responseHeaders, HttpStatus.BAD_GATEWAY);
	}

	@ExceptionHandler(ResourceAccessException.class)
	public ResponseEntity<MibiMonitoringBaseResponse> handleResourceAccessException(ResourceAccessException e) {
		LOGGER.error("Integration Error - ClassName: {} - Message: {}", e.getClass().getName(), e.getMessage());
		LOGGER.error(e.getMessage(), e);
		MibiMonitoringBaseResponse response = new MibiMonitoringBaseResponse();
		response.setStatus(false);
		response.setErrorCode(GenericMessageKey.FATAL_EXTERNAL_SYSTEM.getKey());
		response.setMessage("External system integration error");

		LOGGER.error("#handleResourceAccessException - Response: {}", response);
		return new ResponseEntity<>(response, responseHeaders, HttpStatus.BAD_GATEWAY);
	}

	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<MibiMonitoringBaseResponse> handleWebValidationError(HttpServletRequest req,
			MethodArgumentNotValidException e) {
		LOGGER.error("Invalid parameter: error={}", e.getMessage());
		MibiMonitoringBaseResponse response = new MibiMonitoringBaseResponse(false,
				GenericMessageKey.INVALID_PARAMETERS.getDesc(), MibiMonitoringUtils.getErrorMessage(e));
		response.setMessage(GenericMessageKey.INVALID_PARAMETERS.getDesc());

		LOGGER.error("#handleWebValidationError - Response: {}", response);
		return new ResponseEntity<>(response, responseHeaders, HttpStatus.BAD_REQUEST);
	}
}