package py.com.baa.snc.web.controller;

import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import py.com.baa.snc.dto.TransactionsDto;
import py.com.baa.snc.exception.GenericMessageKey;
import py.com.baa.snc.exception.MibiMonitoringApplicationException;
import py.com.baa.snc.exception.MibiMonitoringInvocationException;
import py.com.baa.snc.response.MibiMonitoringDataResponse;
import py.com.baa.snc.rest.ApiPaths;
import py.com.baa.snc.util.JSONUtils;
import py.com.baa.snc.web.services.TransactionServices;

@RestController
public class TransactionsController {
	private static final Logger logger = LoggerFactory.getLogger(TransactionsController.class);

	@Autowired
	TransactionServices txServices;

	@PostMapping(value = ApiPaths.TX_CREATE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<MibiMonitoringDataResponse<TransactionsDto>> create(@RequestBody TransactionsDto dto)
			throws MibiMonitoringInvocationException, MibiMonitoringApplicationException {
		logger.info("#create - REQUEST - dto: {}", dto);
		MibiMonitoringDataResponse<TransactionsDto> response = new MibiMonitoringDataResponse<>();
		txServices.save(dto);
		TransactionsDto saved = txServices.findByRRN(dto.getRrn());
		response.setData(saved);
		response.setOk(GenericMessageKey.INSERT_PROCESSED.getDesc());

		logger.info("#create - RESPONSE - response: {}", JSONUtils.toJSON(response));
		return ResponseEntity.ok(response);
	}

	@GetMapping(value = ApiPaths.TX_BETWEEN_DATE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<MibiMonitoringDataResponse<List<TransactionsDto>>> findBetweenDate(
			@RequestParam("initial") Date initial, @RequestParam("initial") Date end)
			throws MibiMonitoringInvocationException, MibiMonitoringApplicationException {
		logger.info("#findBetweenDate - REQUEST - initial: {} - end: {}", initial, end);
		MibiMonitoringDataResponse<List<TransactionsDto>> response = new MibiMonitoringDataResponse<>();

		List<TransactionsDto> list = txServices.findBetweenCreatedAt(initial, end);
		response.setData(list);
		response.setOk(GenericMessageKey.QUERY_PROCESSED.getDesc());

		logger.info("#findBetweenDate - RESPONSE - response: {}", JSONUtils.toJSON(response));
		return ResponseEntity.ok(response);
	}

	@GetMapping(value = ApiPaths.TX_BETWEEN_DATE_SENDER_CI, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<MibiMonitoringDataResponse<List<TransactionsDto>>> findBetweenCreatedAtBySenderDocNumber(
			@RequestParam("initial") Date initial, @RequestParam("initial") Date end,
			@RequestParam("sender-ci") String senderCI)
			throws MibiMonitoringInvocationException, MibiMonitoringApplicationException {
		logger.info("#findBetweenCreatedAtBySenderDocNumber - REQUEST - initial: {} - end: {}", initial, end);
		MibiMonitoringDataResponse<List<TransactionsDto>> response = new MibiMonitoringDataResponse<>();

		List<TransactionsDto> list = txServices.findBetweenCreatedAtBySenderDocNumber(senderCI, initial, end);
		response.setData(list);
		response.setOk(GenericMessageKey.QUERY_PROCESSED.getDesc());

		logger.info("#findBetweenCreatedAtBySenderDocNumber - RESPONSE - response: {}", JSONUtils.toJSON(response));
		return ResponseEntity.ok(response);
	}

	@GetMapping(value = ApiPaths.TX_BY_RRN, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<MibiMonitoringDataResponse<TransactionsDto>> findByRRN(@PathVariable("rrn") String rrn)
			throws MibiMonitoringInvocationException, MibiMonitoringApplicationException {
		logger.info("#findByRRN - REQUEST - rrn: {}", rrn);
		MibiMonitoringDataResponse<TransactionsDto> response = new MibiMonitoringDataResponse<>();

		TransactionsDto dto = txServices.findByRRN(rrn);
		response.setData(dto);
		response.setOk(GenericMessageKey.QUERY_PROCESSED.getDesc());

		logger.info("#findByRRN - RESPONSE - response: {}", JSONUtils.toJSON(response));
		return ResponseEntity.ok(response);
	}

	@GetMapping(value = ApiPaths.TX_BY_TICKET_NUMBER, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<MibiMonitoringDataResponse<TransactionsDto>> findByTicketNumber(
			@PathVariable("ticket-number") String ticketNumber)
			throws MibiMonitoringInvocationException, MibiMonitoringApplicationException {
		logger.info("#findByTicketNumber - REQUEST - ticketNumber: {}", ticketNumber);
		MibiMonitoringDataResponse<TransactionsDto> response = new MibiMonitoringDataResponse<>();

		TransactionsDto dto = txServices.findByTicketNumber(ticketNumber);
		response.setData(dto);
		response.setOk(GenericMessageKey.QUERY_PROCESSED.getDesc());

		logger.info("#findByTicketNumber - RESPONSE - response: {}", JSONUtils.toJSON(response));
		return ResponseEntity.ok(response);
	}
}