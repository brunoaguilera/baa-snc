package py.com.baa.snc.db.repository;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import py.com.baa.snc.db.domain.TransactionsEntity;
import py.com.baa.snc.exception.MibiMonitoringInvocationException;

@Repository
public interface TransactionsRepository extends JpaRepository<TransactionsEntity, Long> {

	@Query(value = "select t from TransactionsEntity t where t.senderDocNumber = :ci and t.createdAt >= :initial and t.createdAt <= :end", nativeQuery = false)
	Optional<List<TransactionsEntity>> findBetweenCreatedAtBySenderDocNumber(@Param("ci") String ci,
			@Param("initial") Date initial, @Param("end") Date end) throws MibiMonitoringInvocationException;

	@Query(value = "select t from TransactionsEntity t where t.senderWalletNumber = :wallet and t.createdAt >= :initial and t.createdAt <= :end", nativeQuery = false)
	Optional<List<TransactionsEntity>> findBetweenCreatedAtBySenderWalletNumber(@Param("wallet") String wallet,
			@Param("initial") Date initial, @Param("end") Date end) throws MibiMonitoringInvocationException;

	@Query(value = "select sum(t.amount) from TransactionsEntity t where t.senderDocNumber = :ci and t.createdAt >= :initial and t.createdAt <= :end and status = 'CONFIRMED'", nativeQuery = false)
	Long sumAmountCompletedBySenderDocNumberAndBetweenCreatedAt(@Param("ci") String ci, @Param("initial") Date initial,
			@Param("end") Date end);

	@Query(value = "select sum(t.amount) from TransactionsEntity t where t.senderWalletNumber = :wallet and t.createdAt >= :initial and t.createdAt <= :end and status = 'CONFIRMED'", nativeQuery = false)
	Long sumAmountCompletedBySenderWalletNumberAndBetweenCreatedAt(@Param("wallet") String wallet,
			@Param("initial") Date initial, @Param("end") Date end);

	@Query(value = "select t from TransactionsEntity t where t.senderDocNumber = :ci and t.methodOfPayment = :metOfPay and t.createdAt >= :initial and t.createdAt <= :end", nativeQuery = false)
	Optional<List<TransactionsEntity>> findBetweenCreatedAtBySenderDocNumberMetOfPayment(@Param("ci") String ci,
			@Param("metOfPay") String metOfPay, @Param("initial") Date initial, @Param("end") Date end)
			throws MibiMonitoringInvocationException;

	@Query(value = "select t from TransactionsEntity t where t.senderWalletNumber = :wallet and t.methodOfPayment = :metOfPay and t.createdAt >= :initial and t.createdAt <= :end", nativeQuery = false)
	Optional<List<TransactionsEntity>> findBetweenCreatedAtBySenderWalletNumberMetOfPayment(
			@Param("wallet") String wallet, @Param("metOfPay") String metOfPay, @Param("initial") Date initial,
			@Param("end") Date end) throws MibiMonitoringInvocationException;

	@Query(value = "select sum(t.amount) from TransactionsEntity t where t.senderDocNumber = :ci and t.methodOfPayment = :metOfPay and t.createdAt >= :initial and t.createdAt <= :end and status = 'CONFIRMED'", nativeQuery = false)
	Long sumAmountCompletedBySenderDocNumberBetweenCreatedAtMetOfPayment(@Param("ci") String ci,
			@Param("metOfPay") String metOfPay, @Param("initial") Date initial, @Param("end") Date end);

	@Query(value = "select sum(t.amount) from TransactionsEntity t where t.senderWalletNumber = :wallet and t.methodOfPayment = :metOfPay and t.createdAt >= :initial and t.createdAt <= :end and status = 'CONFIRMED'", nativeQuery = false)
	Long sumAmountCompletedBySenderWalletNumberBetweenCreatedAtMetOfPayment(@Param("wallet") String wallet,
			@Param("metOfPay") String metOfPay, @Param("initial") Date initial, @Param("end") Date end);

	@Query(value = "select t from TransactionsEntity t where t.recipientDocNumber = :ci and t.createdAt >= :initial and t.createdAt <= :end", nativeQuery = false)
	Optional<List<TransactionsEntity>> findBetweenCreatedAtByRecipientDocNumber(@Param("ci") String ci,
			@Param("initial") Date initial, @Param("end") Date end) throws MibiMonitoringInvocationException;

	@Query(value = "select t from TransactionsEntity t where t.recipientWalletNumber = :wallet and t.createdAt >= :initial and t.createdAt <= :end", nativeQuery = false)
	Optional<List<TransactionsEntity>> findBetweenCreatedAtByRecipientWalletNumber(@Param("wallet") String wallet,
			@Param("initial") Date initial, @Param("end") Date end) throws MibiMonitoringInvocationException;

	@Query(value = "select t from TransactionsEntity t where t.recipientWalletNumber = :wallet and t.recipientBrandId = :brandId and t.createdAt >= :initial and t.createdAt <= :end", nativeQuery = false)
	Optional<List<TransactionsEntity>> findBetweenCreatedAtByRecipientWalletNumberBrandId(
			@Param("wallet") String wallet, @Param("brandId") Long brandId, @Param("initial") Date initial,
			@Param("end") Date end) throws MibiMonitoringInvocationException;

	@Query(value = "select sum(t.amount) from TransactionsEntity t where t.recipientDocNumber = :ci and t.createdAt >= :initial and t.createdAt <= :end and status = 'CONFIRMED'", nativeQuery = false)
	Long sumAmountCompletedByRecipientDocNumberAndBetweenCreatedAt(@Param("ci") String ci,
			@Param("initial") Date initial, @Param("end") Date end);

	@Query(value = "select sum(t.amount) from TransactionsEntity t where t.recipientWalletNumber = :wallet and t.createdAt >= :initial and t.createdAt <= :end and status = 'CONFIRMED'", nativeQuery = false)
	Long sumAmountCompletedByRecipientWalletNumberAndBetweenCreatedAt(@Param("wallet") String wallet,
			@Param("initial") Date initial, @Param("end") Date end);

	@Query(value = "select t from TransactionsEntity t where t.rrn = :rrn", nativeQuery = false)
	Optional<TransactionsEntity> findByRRN(@Param("rrn") String rrn) throws MibiMonitoringInvocationException;

	@Query(value = "select t from TransactionsEntity t where t.ticketNumber = :ticketNumber", nativeQuery = false)
	Optional<TransactionsEntity> findByTicketNumber(@Param("ticketNumber") String ticketNumber)
			throws MibiMonitoringInvocationException;

	@Query(value = "select t from TransactionsEntity t where t.createdAt >= :initial and t.createdAt <= :end", nativeQuery = false)
	Optional<List<TransactionsEntity>> findBetweenCreatedAt(@Param("initial") Date initial, @Param("end") Date end)
			throws MibiMonitoringInvocationException;
}
