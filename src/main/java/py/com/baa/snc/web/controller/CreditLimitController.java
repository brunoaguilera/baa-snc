package py.com.baa.snc.web.controller;

import javax.websocket.server.PathParam;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import py.com.baa.snc.dto.AccreditationRequestDto;
import py.com.baa.snc.dto.AttributeToValidateDto;
import py.com.baa.snc.exception.GenericMessageKey;
import py.com.baa.snc.exception.MibiMonitoringApplicationException;
import py.com.baa.snc.exception.MibiMonitoringInvocationException;
import py.com.baa.snc.response.MibiMonitoringBaseResponse;
import py.com.baa.snc.response.MibiMonitoringDataResponse;
import py.com.baa.snc.rest.ApiPaths;
import py.com.baa.snc.util.JSONUtils;
import py.com.baa.snc.web.services.CreditLimitServices;

@RestController
public class CreditLimitController {
	private static final Logger logger = LoggerFactory.getLogger(CreditLimitController.class);

	@Autowired
	CreditLimitServices creditLimit;

	@PostMapping(value = ApiPaths.LIMIT_BY_ACREDITATION_REQUEST, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<MibiMonitoringDataResponse<String>> validateLimitByAccreditationRequest(
			@RequestBody AccreditationRequestDto dto)
			throws MibiMonitoringInvocationException, MibiMonitoringApplicationException {
		logger.info("#validateLimitByAccreditationRequest - REQUEST - dto: {}", JSONUtils.toJSON(dto));
		MibiMonitoringDataResponse<String> response = new MibiMonitoringDataResponse<String>();
		MibiMonitoringBaseResponse validate = creditLimit.validateLimitByAccreditationRequest(dto);
		response.byBaseResponse(validate);
		response.setData(GenericMessageKey.SUCCESSFULL_INVOCATION.getDesc());
		logger.info("#validateLimitByAccreditationRequest - RESPONSE - response: {}", JSONUtils.toJSON(response));
		return ResponseEntity.ok(response);
	}

	@PostMapping(value = ApiPaths.LIMIT_BY_SENDER_CI, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<MibiMonitoringDataResponse<String>> validateLimitBySenderCI(@PathParam("sender-ci") String senderCI,
			@RequestBody AttributeToValidateDto dto)
			throws MibiMonitoringInvocationException, MibiMonitoringApplicationException {
		logger.info("#validateLimitBySenderCI - REQUEST - senderCI: {} - dto: {}", senderCI, dto);
		MibiMonitoringDataResponse<String> response = new MibiMonitoringDataResponse<String>();
		MibiMonitoringBaseResponse validate = creditLimit.validateLimitBySenderDocNumber(senderCI, dto);
		response.byBaseResponse(validate);
		logger.info("#validateLimitBySenderCI - RESPONSE - response: {}", JSONUtils.toJSON(response));
		return ResponseEntity.ok(response);
	}

	@PostMapping(value = ApiPaths.LIMIT_BY_RECIPIENT_CI, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<MibiMonitoringDataResponse<String>> validateLimitByRecipientCI(
			@PathParam("recipient-ci") String recipientCI, @RequestBody AttributeToValidateDto dto)
			throws MibiMonitoringInvocationException, MibiMonitoringApplicationException {
		logger.info("#validateLimitByRecipientCI - REQUEST - recipientCI: {} - dto: {}", recipientCI, dto);
		MibiMonitoringDataResponse<String> response = new MibiMonitoringDataResponse<String>();
		MibiMonitoringBaseResponse validate = creditLimit.validateLimitByRecipientDocNumber(recipientCI, dto);
		response.byBaseResponse(validate);
		logger.info("#validateLimitByRecipientCI - RESPONSE - response: {}", JSONUtils.toJSON(response));
		return ResponseEntity.ok(response);
	}

	@PostMapping(value = ApiPaths.LIMIT_BY_RECIPIENT_WALLET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<MibiMonitoringDataResponse<String>> validateLimitByRecipientWallet(
			@PathParam("recipient-wallet") String wallet, @RequestBody AttributeToValidateDto dto)
			throws MibiMonitoringInvocationException, MibiMonitoringApplicationException {
		logger.info("#validateLimitByRecipientCI - REQUEST - recipientCI: {} - dto: {}", wallet, dto);
		MibiMonitoringDataResponse<String> response = new MibiMonitoringDataResponse<String>();
		MibiMonitoringBaseResponse validate = creditLimit.validateLimitByRecipientWalletNumber(wallet, dto);
		response.byBaseResponse(validate);
		logger.info("#validateLimitByRecipientCI - RESPONSE - response: {}", JSONUtils.toJSON(response));
		return ResponseEntity.ok(response);
	}
}