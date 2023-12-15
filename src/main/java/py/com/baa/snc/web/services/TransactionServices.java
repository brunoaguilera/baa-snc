package py.com.baa.snc.web.services;

import java.util.Date;
import java.util.List;

import py.com.baa.snc.dto.AccreditationRequestDto;
import py.com.baa.snc.dto.TransactionsDto;
import py.com.baa.snc.exception.MibiMonitoringInvocationException;

public interface TransactionServices {

	List<TransactionsDto> findBetweenCreatedAtByAccreditationRequest(AccreditationRequestDto request, Date initialDate,
			Date endDate) throws MibiMonitoringInvocationException;

	List<TransactionsDto> findBetweenCreatedAtBySenderDocNumber(String docNumber, Date initialDate, Date endDate)
			throws MibiMonitoringInvocationException;

	List<TransactionsDto> findBetweenCreatedAtBySenderWalletNumber(String walletNumber, Date initialDate, Date endDate)
			throws MibiMonitoringInvocationException;

	Long sumAmountCompletedBySenderDocNumberAndBetweenCreatedAt(String docNumber, Date initialDate, Date endDate);

	Long sumAmountCompletedBySenderWalletNumberAndBetweenCreatedAt(String walletNumber, Date initialDate, Date endDate);

	List<TransactionsDto> findBetweenCreatedAtByRecipientDocNumber(String docNumber, Date initialDate, Date endDate)
			throws MibiMonitoringInvocationException;

	List<TransactionsDto> findBetweenCreatedAtByRecipientWalletNumber(String walletNumber, Date initialDate,
			Date endDate) throws MibiMonitoringInvocationException;

	List<TransactionsDto> findBetweenCreatedAtByRecipientWalletNumberBrandId(String walletNumber, Long brandId,
			Date initialDate, Date endDate) throws MibiMonitoringInvocationException;

	Long sumAmountCompletedByRecipientDocNumberAndBetweenCreatedAt(String docNumber, Date initialDate, Date endDate);

	Long sumAmountCompletedByRecipientWalletNumberAndBetweenCreatedAt(String walletNumber, Date initialDate,
			Date endDate);

	void save(TransactionsDto dto) throws MibiMonitoringInvocationException;

	void save(List<TransactionsDto> listDto) throws MibiMonitoringInvocationException;

	TransactionsDto findByRRN(String rrn) throws MibiMonitoringInvocationException;

	TransactionsDto findByTicketNumber(String ticketNumber) throws MibiMonitoringInvocationException;

	List<TransactionsDto> findBetweenCreatedAt(Date initialDate, Date endDate) throws MibiMonitoringInvocationException;
}