package py.com.baa.snc.web.services;

import py.com.baa.snc.dto.AccreditationRequestDto;
import py.com.baa.snc.dto.AttributeToValidateDto;
import py.com.baa.snc.exception.MibiMonitoringInvocationException;
import py.com.baa.snc.response.MibiMonitoringBaseResponse;

public interface CreditLimitServices {
	MibiMonitoringBaseResponse validateLimitByAccreditationRequest(AccreditationRequestDto dto)
			throws MibiMonitoringInvocationException;

	MibiMonitoringBaseResponse validateLimitBySenderDocNumber(String senderDocNumber,
			AttributeToValidateDto attValidate) throws MibiMonitoringInvocationException;

	MibiMonitoringBaseResponse validateLimitByRecipientDocNumber(String recipientDocNumber,
			AttributeToValidateDto attValidate) throws MibiMonitoringInvocationException;

	MibiMonitoringBaseResponse validateLimitByRecipientWalletNumber(String walletNumber,
			AttributeToValidateDto attValidate) throws MibiMonitoringInvocationException;

	MibiMonitoringBaseResponse validateLimitByRecipientWalletAndBrand(String walletNumber, Long brandId,
			AttributeToValidateDto attValidate) throws MibiMonitoringInvocationException;
}
