package py.com.baa.snc.web.services.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import py.com.baa.snc.db.domain.TransactionsEntity;
import py.com.baa.snc.db.repository.TransactionsRepository;
import py.com.baa.snc.dto.AccreditationRequestDto;
import py.com.baa.snc.dto.TransactionsDto;
import py.com.baa.snc.exception.MibiMonitoringInvocationException;
import py.com.baa.snc.util.DateHelper;
import py.com.baa.snc.web.services.TransactionServices;

@Service
public class TransactionServicesImpl implements TransactionServices {

	private static final Logger logger = LoggerFactory.getLogger(TransactionServicesImpl.class);

	@Autowired
	TransactionsRepository txRepository;

	public List<TransactionsDto> findBetweenCreatedAtByAccreditationRequest(AccreditationRequestDto request,
			Date initialDate, Date endDate) throws MibiMonitoringInvocationException {
		return null;
	}

	public List<TransactionsDto> findBetweenCreatedAtBySenderDocNumber(String docNumber, Date initialDate, Date endDate)
			throws MibiMonitoringInvocationException {
		logger.info("#findBetweenCreatedAtBySenderDocNumber - request - docNumber:{} - initalDate: {} - enDate: {}",
				docNumber, initialDate, endDate);

		List<TransactionsDto> response = new ArrayList<TransactionsDto>();
		Optional<List<TransactionsEntity>> optional = txRepository.findBetweenCreatedAtBySenderDocNumber(docNumber,
				initialDate, endDate);
		List<TransactionsEntity> list = optional.orElse(new ArrayList<TransactionsEntity>());
		for (TransactionsEntity entity : list) {
			response.add(entity.toDTO());
		}

		logger.info("#findBetweenCreatedAtBySenderDocNumber - response - docNumber:{} - response: {}", docNumber,
				response);
		return response;
	}

	public List<TransactionsDto> findBetweenCreatedAtBySenderWalletNumber(String walletNumber, Date initialDate,
			Date endDate) throws MibiMonitoringInvocationException {
		logger.info(
				"#findBetweenCreatedAtBySenderWalletNumber - request - walletNumber:{} - initalDate: {} - enDate: {}",
				walletNumber, initialDate, endDate);

		List<TransactionsDto> response = new ArrayList<TransactionsDto>();
		Optional<List<TransactionsEntity>> optional = txRepository
				.findBetweenCreatedAtBySenderWalletNumber(walletNumber, initialDate, endDate);
		List<TransactionsEntity> list = optional.orElse(new ArrayList<TransactionsEntity>());
		for (TransactionsEntity entity : list) {
			response.add(entity.toDTO());
		}

		logger.info("#findBetweenCreatedAtBySenderWalletNumber - response - walletNumber:{} - response: {}",
				walletNumber, response);
		return response;
	}

	public Long sumAmountCompletedBySenderDocNumberAndBetweenCreatedAt(String docNumber, Date initialDate,
			Date endDate) {
		logger.info(
				"#sumAmountCompletedBySenderDocNumberAndBetweenCreatedAt - request - docNumber:{} - initalDate: {} - enDate: {}",
				docNumber, initialDate, endDate);

		Long sum = txRepository.sumAmountCompletedBySenderDocNumberAndBetweenCreatedAt(docNumber, initialDate, endDate);

		logger.info("#sumAmountCompletedBySenderDocNumberAndBetweenCreatedAt - response - docNumber:{} - response: {}",
				docNumber, sum);
		return sum;
	}

	public Long sumAmountCompletedBySenderWalletNumberAndBetweenCreatedAt(String walletNumber, Date initialDate,
			Date endDate) {
		logger.info(
				"#sumAmountCompletedBySenderWalletNumberAndBetweenCreatedAt - request - walletNumber:{} - initalDate: {} - enDate: {}",
				walletNumber, initialDate, endDate);

		Long sum = txRepository.sumAmountCompletedBySenderDocNumberAndBetweenCreatedAt(walletNumber, initialDate,
				endDate);

		logger.info(
				"#sumAmountCompletedBySenderWalletNumberAndBetweenCreatedAt - response - walletNumber:{} - response: {}",
				walletNumber, sum);
		return sum;
	}

	public List<TransactionsDto> findBetweenCreatedAtBySenderDocNumberMetOfPay(String docNumber, String methodOfPayment,
			Date initialDate, Date endDate) throws MibiMonitoringInvocationException {
		logger.info(
				"#findBetweenCreatedAtBySenderDocNumberMetOfPay - request - docNumber:{} - initalDate: {} - enDate: {}",
				docNumber, initialDate, endDate);

		List<TransactionsDto> response = new ArrayList<TransactionsDto>();
		Optional<List<TransactionsEntity>> optional = txRepository
				.findBetweenCreatedAtBySenderDocNumberMetOfPayment(docNumber, methodOfPayment, initialDate, endDate);
		List<TransactionsEntity> list = optional.orElse(new ArrayList<TransactionsEntity>());
		for (TransactionsEntity entity : list) {
			response.add(entity.toDTO());
		}

		logger.info("#findBetweenCreatedAtBySenderDocNumberMetOfPay - response - docNumber:{} - response: {}",
				docNumber, response);
		return response;
	}

	public List<TransactionsDto> findBetweenCreatedAtBySenderWalletNumberMetOfPay(String walletNumber,
			String methodOfPayment, Date initialDate, Date endDate) throws MibiMonitoringInvocationException {
		logger.info(
				"#findBetweenCreatedAtBySenderWalletNumberMetOfPay - request - walletNumber:{} - initalDate: {} - enDate: {}",
				walletNumber, initialDate, endDate);

		List<TransactionsDto> response = new ArrayList<TransactionsDto>();
		Optional<List<TransactionsEntity>> optional = txRepository.findBetweenCreatedAtBySenderWalletNumberMetOfPayment(
				walletNumber, methodOfPayment, initialDate, endDate);
		List<TransactionsEntity> list = optional.orElse(new ArrayList<TransactionsEntity>());
		for (TransactionsEntity entity : list) {
			response.add(entity.toDTO());
		}

		logger.info("#findBetweenCreatedAtBySenderWalletNumberMetOfPay - response - walletNumber:{} - response: {}",
				walletNumber, response);
		return response;
	}

	public Long sumAmountCompletedBySenderDocNumberAndBetweenCreatedAtMetOfPay(String docNumber, String methodOfPayment,
			Date initialDate, Date endDate) {
		logger.info(
				"#sumAmountCompletedBySenderDocNumberAndBetweenCreatedAt - request - docNumber:{} - initalDate: {} - enDate: {}",
				docNumber, initialDate, endDate);

		Long sum = txRepository.sumAmountCompletedBySenderDocNumberBetweenCreatedAtMetOfPayment(docNumber,
				methodOfPayment, initialDate, endDate);

		logger.info("#sumAmountCompletedBySenderDocNumberAndBetweenCreatedAt - response - docNumber:{} - response: {}",
				docNumber, sum);
		return sum;
	}

	public Long sumAmountCompletedBySenderWalletNumberAndBetweenCreatedAtMetOfPay(String walletNumber,
			String methodOfPayment, Date initialDate, Date endDate) {
		logger.info(
				"#sumAmountCompletedBySenderWalletNumberAndBetweenCreatedAt - request - walletNumber:{} - initalDate: {} - enDate: {}",
				walletNumber, initialDate, endDate);

		Long sum = txRepository.sumAmountCompletedBySenderWalletNumberBetweenCreatedAtMetOfPayment(walletNumber,
				methodOfPayment, initialDate, endDate);

		logger.info(
				"#sumAmountCompletedBySenderWalletNumberAndBetweenCreatedAt - response - walletNumber:{} - response: {}",
				walletNumber, sum);
		return sum;
	}

	public List<TransactionsDto> findBetweenCreatedAtByRecipientDocNumber(String docNumber, Date initialDate,
			Date endDate) throws MibiMonitoringInvocationException {
		logger.info("#findBetweenCreatedAtByRecipientDocNumber - request - docNumber:{} - initalDate: {} - enDate: {}",
				docNumber, initialDate, endDate);

		List<TransactionsDto> response = new ArrayList<TransactionsDto>();
		Optional<List<TransactionsEntity>> optional = txRepository.findBetweenCreatedAtByRecipientDocNumber(docNumber,
				initialDate, endDate);
		List<TransactionsEntity> list = optional.orElse(new ArrayList<TransactionsEntity>());
		for (TransactionsEntity entity : list) {
			response.add(entity.toDTO());
		}

		logger.info("#findBetweenCreatedAtByRecipientDocNumber - response - docNumber:{} - response: {}", docNumber,
				response);
		return response;
	}

	public List<TransactionsDto> findBetweenCreatedAtByRecipientWalletNumber(String walletNumber, Date initialDate,
			Date endDate) throws MibiMonitoringInvocationException {
		logger.info(
				"#findBetweenCreatedAtByRecipientWalletNumber - request - walletNumber:{} - initalDate: {} - enDate: {}",
				walletNumber, initialDate, endDate);

		List<TransactionsDto> response = new ArrayList<TransactionsDto>();
		Optional<List<TransactionsEntity>> optional = txRepository
				.findBetweenCreatedAtByRecipientWalletNumber(walletNumber, initialDate, endDate);
		List<TransactionsEntity> list = optional.orElse(new ArrayList<TransactionsEntity>());
		for (TransactionsEntity entity : list) {
			response.add(entity.toDTO());
		}

		logger.info("#findBetweenCreatedAtByRecipientWalletNumber - response - walletNumber:{} - response: {}",
				walletNumber, response);
		return response;
	}

	public List<TransactionsDto> findBetweenCreatedAtByRecipientWalletNumberBrandId(String walletNumber, Long brandId,
			Date initialDate, Date endDate) throws MibiMonitoringInvocationException {
		logger.info(
				"#findBetweenCreatedAtByRecipientWalletNumberBrandId - request - walletNumber:{} - brandId: {} - initalDate: {} - enDate: {}",
				walletNumber, brandId, initialDate, endDate);

		List<TransactionsDto> response = new ArrayList<TransactionsDto>();
		Optional<List<TransactionsEntity>> optional = txRepository
				.findBetweenCreatedAtByRecipientWalletNumber(walletNumber, initialDate, endDate);
		List<TransactionsEntity> list = optional.orElse(new ArrayList<TransactionsEntity>());
		for (TransactionsEntity entity : list) {
			response.add(entity.toDTO());
		}

		logger.info("#findBetweenCreatedAtByRecipientWalletNumberBrandId - response - walletNumber:{} - response: {}",
				walletNumber, response);
		return response;
	}

	public Long sumAmountCompletedByRecipientDocNumberAndBetweenCreatedAt(String docNumber, Date initialDate,
			Date endDate) {
		logger.info(
				"#sumAmountCompletedByRecipientDocNumberAndBetweenCreatedAt - request - docNumber:{} - initalDate: {} - enDate: {}",
				docNumber, initialDate, endDate);

		Long sum = txRepository.sumAmountCompletedByRecipientDocNumberAndBetweenCreatedAt(docNumber, initialDate,
				endDate);

		logger.info(
				"#sumAmountCompletedByRecipientDocNumberAndBetweenCreatedAt - response - docNumber:{} - response: {}",
				docNumber, sum);
		return sum;
	}

	public Long sumAmountCompletedByRecipientWalletNumberAndBetweenCreatedAt(String walletNumber, Date initialDate,
			Date endDate) {
		logger.info(
				"#sumAmountCompletedByRecipientWalletNumberAndBetweenCreatedAt - request - walletNumber:{} - initalDate: {} - enDate: {}",
				walletNumber, initialDate, endDate);

		Long sum = txRepository.sumAmountCompletedByRecipientWalletNumberAndBetweenCreatedAt(walletNumber, initialDate,
				endDate);

		logger.info(
				"#sumAmountCompletedByRecipientWalletNumberAndBetweenCreatedAt - response - walletNumber:{} - response: {}",
				walletNumber, sum);
		return sum;
	}

	public void save(TransactionsDto dto) throws MibiMonitoringInvocationException {
		dto.setCreatedAt(DateHelper.getCurrentDate());
		TransactionsEntity entity = dto.toEntity();
		txRepository.save(entity);
	}

	public void save(List<TransactionsDto> listDto) throws MibiMonitoringInvocationException {
		List<TransactionsEntity> entityList = new ArrayList<TransactionsEntity>();
		for (TransactionsDto dto : listDto) {
			dto.setCreatedAt(DateHelper.getCurrentDate());
			TransactionsEntity entity = dto.toEntity();
			entityList.add(entity);
		}
		txRepository.saveAll(entityList);
	}

	@Override
	public TransactionsDto findByRRN(String rrn) throws MibiMonitoringInvocationException {
		logger.info("#findByRRN - request - rrn:{}", rrn);

		Optional<TransactionsEntity> optional = txRepository.findByRRN(rrn);
		TransactionsDto response = optional.orElse(new TransactionsEntity()).toDTO();

		logger.info("#findByRRN - response - rrn:{} - response: {}", rrn, response);
		return response;
	}

	@Override
	public TransactionsDto findByTicketNumber(String ticketNumber) throws MibiMonitoringInvocationException {
		logger.info("#findByTicketNumber - request - ticketNumber:{}", ticketNumber);

		Optional<TransactionsEntity> optional = txRepository.findByTicketNumber(ticketNumber);
		TransactionsDto response = optional.orElse(new TransactionsEntity()).toDTO();

		logger.info("#findByTicketNumber - response - rrn:{} - response: {}", ticketNumber, response);
		return response;
	}

	@Override
	public List<TransactionsDto> findBetweenCreatedAt(Date initialDate, Date endDate)
			throws MibiMonitoringInvocationException {
		logger.info("#findBetweenCreatedAt - request - initalDate: {} - enDate: {}", initialDate, endDate);

		List<TransactionsDto> response = new ArrayList<TransactionsDto>();
		Optional<List<TransactionsEntity>> optional = txRepository.findBetweenCreatedAt(initialDate, endDate);
		List<TransactionsEntity> list = optional.orElse(new ArrayList<TransactionsEntity>());
		for (TransactionsEntity entity : list) {
			response.add(entity.toDTO());
		}

		logger.info("#findBetweenCreatedAt - response - response: {}", response);
		return response;
	}
}
