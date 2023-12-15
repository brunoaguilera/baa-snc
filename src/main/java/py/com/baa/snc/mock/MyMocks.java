package py.com.baa.snc.mock;

import java.math.BigDecimal;
import java.util.Calendar;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Optional;

import py.com.baa.snc.dto.AccreditationRequestDto;
import py.com.baa.snc.util.DateHelper;

public class MyMocks {
	private static final Logger logger = LoggerFactory.getLogger(MyMocks.class);

	public MyMocks() {
		// misPruebasDeFechas();
		misPruebasOptional();
	}

	private void misPruebasOptional() {
		AccreditationRequestDto request = new AccreditationRequestDto();
		request.setMethodOfPayment("00");
		request.setAmount(new BigDecimal(20));
		// request = null;
		Optional<AccreditationRequestDto> optionalAccreditacionReq = Optional.ofNullable(request);

		if (optionalAccreditacionReq.isPresent()) {
			logger.info("si esta presente");
		} else {
			logger.info("no presente");
		}

		BigDecimal valueAmount = Optional.ofNullable(request.getAmount()).orElse(new BigDecimal(0));

		logger.info("valueAmount: {}", valueAmount);

	}

	private void misPruebasDeFechas() {
		Date currentDate = DateHelper.getCurrentDate();
		Date firstDay = DateHelper.getDateWFirstHms(currentDate.getTime());
		Date lastDay = DateHelper.getDateWLastHms(currentDate.getTime());

		logger.info("currentDate: {}", DateHelper.fromDate(currentDate, DateHelper.DEFAULT_DATE_TIME_FORMAT));
		logger.info("firstDay: {}", DateHelper.fromDate(firstDay, DateHelper.DEFAULT_DATE_TIME_FORMAT));
		logger.info("lastDay: {}", DateHelper.fromDate(lastDay, DateHelper.DEFAULT_DATE_TIME_FORMAT));

		Integer month = DateHelper.extractMonthByDate(currentDate);
		Integer year = DateHelper.extractYearByDate(currentDate);

		logger.info("month: {}", month);
		logger.info("year: {}", year);
		logger.info("");

		Date previousMonth = DateHelper.subtractMonth(DateHelper.getCurrentDate(), 1);
		Integer monthPrevious = DateHelper.extractMonthByDate(previousMonth);
		Integer yearPrevious = DateHelper.extractYearByDate(previousMonth);

		logger.info("first day of previous month: {}", DateHelper.dateByYearMonthDay(yearPrevious, monthPrevious, 1));
		logger.info("last day of previous month: {}", DateHelper.lastDay(yearPrevious, monthPrevious));
		logger.info("");

		logger.info("dateByYearMonthDay: {}", DateHelper.dateByYearMonthDay(year, month, 1));
		logger.info("lastDay: {}", DateHelper.lastDay(year, month));
		logger.info("");

		logger.info("firstDayOfCurrent {}", DateHelper.firstDayOfCurrent());
		logger.info("lastDayOfCurrent {}", DateHelper.lastDayOfCurrent());
		logger.info("");

		logger.info("getDateWFirstHms {}", DateHelper.getDateWFirstHms(DateHelper.firstDayOfCurrent().getTime()));
		logger.info("getDateWLastHms {}", DateHelper.getDateWLastHms(DateHelper.lastDayOfCurrent().getTime()));
		logger.info("");

		logger.info("Calendar.DAY_OF_YEAR: {}", Calendar.DAY_OF_YEAR);
		logger.info("Calendar.DAY_OF_MONTH: {}", Calendar.DAY_OF_MONTH);
	}

	public static void main(String[] args) {
		MyMocks misPruebas = new MyMocks();
	}
}
