package py.com.baa.snc.web.services.impl;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import py.com.baa.snc.dto.AccreditationRequestDto;
import py.com.baa.snc.dto.AttributeToValidateDto;
import py.com.baa.snc.dto.TransactionsDto;
import py.com.baa.snc.dto.enums.MethodOfPayment;
import py.com.baa.snc.dto.enums.TransactionStatusEnum;
import py.com.baa.snc.exception.GenericMessageKey;
import py.com.baa.snc.exception.MibiMonitoringInvocationException;
import py.com.baa.snc.response.MibiMonitoringBaseResponse;
import py.com.baa.snc.web.services.CreditLimitServices;
import py.com.baa.snc.web.services.TransactionServices;

@Service
public class CreditLimitServicesImpl implements CreditLimitServices {
	private static final Logger logger = LoggerFactory.getLogger(TransactionServicesImpl.class);

	@Value("${monitoring.validate.minimum-wage}")
	private BigDecimal minimumWage;

	@Value("${monitoring.validate.minimum-wage.cash-allowed}")
	private BigDecimal amountOfMinimumWageAllowedForCash;

	@Autowired
	TransactionServices txServices;

	public MibiMonitoringBaseResponse validateLimitByAccreditationRequest(AccreditationRequestDto dto)
			throws MibiMonitoringInvocationException {
		logger.info("#validateLimitByAccreditationRequest - request - request: {}", dto);
		MibiMonitoringBaseResponse response = new MibiMonitoringBaseResponse(GenericMessageKey.QUERY_PROCESSED);

		AttributeToValidateDto validateDto = new AttributeToValidateDto();
		BeanUtils.copyProperties(dto, validateDto);

		validateLimitBySenderDocNumber(dto.getSenderDocNumber(), validateDto);
		validateLimitByRecipientDocNumber(dto.getRecipientDocNumber(), validateDto);

		logger.info("#validateLimitByAccreditationRequest - response - response: {}", response);
		return response;
	}

	private BigDecimal minimumWageToValidate() {
		return minimumWage.multiply(amountOfMinimumWageAllowedForCash);
	}

	public MibiMonitoringBaseResponse validateLimitBySenderDocNumber(String senderDocNumber,
			AttributeToValidateDto attValidate) throws MibiMonitoringInvocationException {
		logger.info("#validateLimitBySenderDocNumber - request: {}", senderDocNumber);
		MibiMonitoringBaseResponse response = new MibiMonitoringBaseResponse();

		if (senderDocNumber == null || "".equals(senderDocNumber)) {
			return new MibiMonitoringBaseResponse(GenericMessageKey.QUERY_PROCESSED);
		}

		List<TransactionsDto> txList = txServices.findBetweenCreatedAtBySenderDocNumber(senderDocNumber,
				attValidate.getFirstDayOfCurrentMonth(), attValidate.getLastDayOfCurrentMonth());

		BigDecimal sumMethodOfPayment = Optional.ofNullable(attValidate.getAmount()).orElse(new BigDecimal(0));
		BigDecimal wageValidate = minimumWageToValidate();
		if (sumMethodOfPayment.compareTo(wageValidate) > 0) {
			throw new MibiMonitoringInvocationException(GenericMessageKey.SENDER_EXCEEDED_AMOUNT_ERROR);
		}
		for (TransactionsDto txDto : txList) {
			/*
			 * Si llega el valor de metodo de pago, realizamos la validación por dicho
			 * filtro
			 */
			if (Optional.ofNullable(attValidate.getMethodOfPayment()).isPresent()) {
				MethodOfPayment methodOfPaymentFilter = MethodOfPayment.getByCod(attValidate.getMethodOfPayment());

				if (methodOfPaymentFilter.equals((MethodOfPayment.getByCod(txDto.getMethodOfPayment())))
						&& TransactionStatusEnum.CONFIRMED.equals(txDto.getStatus())) {
					sumMethodOfPayment = sumMethodOfPayment.add(txDto.getAmount());
				}

				logger.info("#validateLimitBySenderDocNumber - wageValidate: {} - sumMethodOfPayment: {}", wageValidate,
						sumMethodOfPayment);

				if (sumMethodOfPayment.compareTo(wageValidate) > 0) {
					throw new MibiMonitoringInvocationException(GenericMessageKey.SENDER_EXCEEDED_AMOUNT_ERROR);
				}
			}

			/*
			 * Si llega el valor del Canal (channel) realizamos la validación por dicho
			 * filtro
			 */
			if (Optional.ofNullable(attValidate.getChannel()).isPresent()) {
				// actualmente no existen necesidad de realizar validación
			}
		}

		logger.info("#validateLimitBySenderDocNumber - request: {} - response: {}", senderDocNumber, response);
		return response;
	}

	public MibiMonitoringBaseResponse validateLimitByRecipientDocNumber(String recipientDocNumber,
			AttributeToValidateDto attValidate) throws MibiMonitoringInvocationException {
		logger.info("#validateLimitByRecipientDocNumber - request: {}", recipientDocNumber);
		MibiMonitoringBaseResponse response = new MibiMonitoringBaseResponse();

		if (recipientDocNumber == null || "".equals(recipientDocNumber)) {
			return new MibiMonitoringBaseResponse(GenericMessageKey.QUERY_PROCESSED);
		}

		List<TransactionsDto> txList = txServices.findBetweenCreatedAtByRecipientDocNumber(recipientDocNumber,
				attValidate.getFirstDayOfCurrentMonth(), attValidate.getLastDayOfCurrentMonth());

		BigDecimal sumMethodOfPayment = Optional.ofNullable(attValidate.getAmount()).orElse(new BigDecimal(0));
		BigDecimal wageValidate = minimumWageToValidate();
		if (sumMethodOfPayment.compareTo(wageValidate) > 0) {
			throw new MibiMonitoringInvocationException(GenericMessageKey.RECIPIENT_EXCEEDED_AMOUNT_ERROR);
		}
		for (TransactionsDto txDto : txList) {
			/*
			 * Si llega el valor de metodo de pago, realizamos la validación por dicho
			 * filtro
			 */
			if (Optional.ofNullable(attValidate.getMethodOfPayment()).isPresent()) {
				MethodOfPayment methodOfPaymentFilter = MethodOfPayment.getByCod(attValidate.getMethodOfPayment());

				if (methodOfPaymentFilter.equals((MethodOfPayment.getByCod(txDto.getMethodOfPayment())))
						&& TransactionStatusEnum.CONFIRMED.equals(txDto.getStatus())) {
					sumMethodOfPayment = sumMethodOfPayment.add(txDto.getAmount());
				}

				if (sumMethodOfPayment.compareTo(wageValidate) > 0) {
					throw new MibiMonitoringInvocationException(GenericMessageKey.RECIPIENT_EXCEEDED_AMOUNT_ERROR);
				}
			}

			/*
			 * Si llega el valor del Canal (channel) realizamos la validación por dicho
			 * filtro
			 */
			if (Optional.ofNullable(attValidate.getChannel()).isPresent()) {
				// actualmente no existen necesidad de realizar validación
			}
		}

		logger.info("#validateLimitByRecipientDocNumber - request: {} - response: {}", recipientDocNumber, response);
		return response;
	}

	public MibiMonitoringBaseResponse validateLimitByRecipientWalletNumber(String walletNumber,
			AttributeToValidateDto attValidate) throws MibiMonitoringInvocationException {
		logger.info("#validateLimitByRecipientWalletNumber - request: {}", walletNumber);
		MibiMonitoringBaseResponse response = new MibiMonitoringBaseResponse();

		List<TransactionsDto> txList = txServices.findBetweenCreatedAtByRecipientWalletNumber(walletNumber,
				attValidate.getFirstDayOfCurrentMonth(), attValidate.getLastDayOfCurrentMonth());

		BigDecimal sumMethodOfPayment = Optional.ofNullable(attValidate.getAmount()).orElse(new BigDecimal(0));
		BigDecimal wageValidate = minimumWageToValidate();
		if (sumMethodOfPayment.compareTo(wageValidate) > 0) {
			throw new MibiMonitoringInvocationException(GenericMessageKey.RECIPIENTWALLET_EXCEEDED_AMOUNT_ERROR);
		}
		for (TransactionsDto txDto : txList) {
			/*
			 * Si llega el valor de metodo de pago, realizamos la validación por dicho
			 * filtro
			 */
			if (Optional.ofNullable(attValidate.getMethodOfPayment()).isPresent()) {
				MethodOfPayment methodOfPaymentFilter = MethodOfPayment.getByCod(attValidate.getMethodOfPayment());

				if (methodOfPaymentFilter.equals((MethodOfPayment.getByCod(txDto.getMethodOfPayment())))
						&& TransactionStatusEnum.CONFIRMED.equals(txDto.getStatus())) {
					sumMethodOfPayment = sumMethodOfPayment.add(txDto.getAmount());
				}

				if (sumMethodOfPayment.compareTo(wageValidate) > 0) {
					throw new MibiMonitoringInvocationException(
							GenericMessageKey.RECIPIENTWALLET_EXCEEDED_AMOUNT_ERROR);
				}
			}

			/*
			 * Si llega el valor del Canal (channel) realizamos la validación por dicho
			 * filtro
			 */
			if (Optional.ofNullable(attValidate.getChannel()).isPresent()) {
				// actualmente no existen necesidad de realizar validación
			}
		}

		logger.info("#validateLimitByRecipientWalletNumber - request: {} - response: {}", walletNumber, response);
		return response;
	}

	public MibiMonitoringBaseResponse validateLimitByRecipientWalletAndBrand(String walletNumber, Long brandId,
			AttributeToValidateDto attValidate) throws MibiMonitoringInvocationException {
		logger.info("#validateLimitByRecipientWalletAndBrand - request: {}", walletNumber);
		MibiMonitoringBaseResponse response = new MibiMonitoringBaseResponse();

		List<TransactionsDto> txList = txServices.findBetweenCreatedAtByRecipientWalletNumberBrandId(walletNumber,
				brandId, attValidate.getFirstDayOfCurrentMonth(), attValidate.getLastDayOfCurrentMonth());

		BigDecimal sumMethodOfPayment = Optional.ofNullable(attValidate.getAmount()).orElse(new BigDecimal(0));
		BigDecimal wageValidate = minimumWageToValidate();
		if (sumMethodOfPayment.compareTo(wageValidate) > 0) {
			throw new MibiMonitoringInvocationException(GenericMessageKey.RECIPIENTWALLET_EXCEEDED_AMOUNT_ERROR);
		}
		for (TransactionsDto txDto : txList) {
			/*
			 * Si llega el valor de metodo de pago, realizamos la validación por dicho
			 * filtro
			 */
			if (Optional.ofNullable(attValidate.getMethodOfPayment()).isPresent()) {
				MethodOfPayment methodOfPaymentFilter = MethodOfPayment.getByCod(attValidate.getMethodOfPayment());

				if (methodOfPaymentFilter.equals((MethodOfPayment.getByCod(txDto.getMethodOfPayment())))
						&& TransactionStatusEnum.CONFIRMED.equals(txDto.getStatus())) {
					sumMethodOfPayment = sumMethodOfPayment.add(txDto.getAmount());
				}

				if (sumMethodOfPayment.compareTo(wageValidate) > 0) {
					throw new MibiMonitoringInvocationException(
							GenericMessageKey.RECIPIENTWALLET_EXCEEDED_AMOUNT_ERROR);
				}
			}

			/*
			 * Si llega el valor del Canal (channel) realizamos la validación por dicho
			 * filtro
			 */
			if (Optional.ofNullable(attValidate.getChannel()).isPresent()) {
				// actualmente no existen necesidad de realizar validación
			}
		}

		logger.info("#validateLimitByRecipientWalletAndBrand - request: {} - response: {}", walletNumber, response);
		return response;
	}
}