package py.com.baa.snc.util;

import java.text.DateFormat;
import java.text.DateFormatSymbols;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;
import java.util.Optional;

import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;

public class DateHelper {

	public static final int NUMBER_OF_MONTHS_IN_A_YEAR = 12;
	public static final int NUMBER_OF_DAYS_IN_A_YEAR = 365;
	public static final int ONE_DAY_IN_HOUR = 24;
	public static final long ONE_MINUTE_IN_MILLIS = 60000;
	public static final long ONE_HOUR_IN_MILLIS = 3600000;
	public static final long DAY_LONG = ONE_HOUR_IN_MILLIS * ONE_DAY_IN_HOUR;
	public static final String ddMM = "ddMM";
	public static final String FERIADO_BANCARIO_ddMM = "3112";
	public static final String SIMPLE_DAY_FORMAT = "dd/MM/yyyy";
	public static final String SIMPLE_DAY_FORMAT_YYYYMMDD = "yyyyMMdd";
	public static final String SIMPLE_DAY_FORMAT_IC_MOTOR = "yyyy-MM-dd";
	public static final String DEFAULT_DATE_TIME_MILL_FORMAT = "yyyy-MM-dd HH:mm:ss.SSS";
	public static final String SIMPLE_DAY_TIME_FORMAT_Tss = "yyyy-MM-dd'T'HH:mm:ss";
	public static final String SIMPLE_DAY_TIME_FORMAT_TsssZ = "yyyy-MM-dd'T'HH:mm:sssZ";
	public static final String DEFAULT_DATE_TIME_FORMAT = "yyyy-MM-dd HH:mm:ss";
	public static final String SIMPLE_DAY_TIME_FORMAT_FOR_UQ_FILE = "yyyy-MM-dd-HHmmss";
	public static final String TIME_FORMAT_HHMMSS = "HHmmss";
	public static final String TIME_FORMAT_HH_MM_SS = "HH:mm:ss";
	public static final String DAY_EEEE = "EEEE";
	public static final String MONTH_MMMM = "MMMM";
	public static final String SHORT_TIME_FORMAT = "HH:mm";
	public static final String FORMAT_DATE_TIME_STRING = SIMPLE_DAY_FORMAT_IC_MOTOR + " " + SHORT_TIME_FORMAT;
	public static final String DATETIME_FORMAT = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'";
	public static final Locale DEFAULT_LOCALE = new Locale("es", "ES");
	public static final DateFormat sdf = new SimpleDateFormat(SIMPLE_DAY_FORMAT_IC_MOTOR);
	public static final DateFormat defaultDateTimeFormat = new SimpleDateFormat(DateHelper.DEFAULT_DATE_TIME_FORMAT);

	private DateHelper() {
	}

	public static String yesterdayAsString() {
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.DATE, -1);
		return defaultDateTimeFormat.format(cal.getTime());
	}

	public static String formatDate(Date date) {
		return defaultDateTimeFormat.format(date);
	}

	public static String formatDate(XMLGregorianCalendar date) {
		Calendar c = date.toGregorianCalendar();
		sdf.setTimeZone(c.getTimeZone());
		return sdf.format(c.getTime());
	}

	public static XMLGregorianCalendar getXMLGregorianCalendar(String dateStr)
			throws ParseException, DatatypeConfigurationException {
		Date date = sdf.parse(dateStr);
		GregorianCalendar cal = new GregorianCalendar();
		cal.setTime(date);
		return DatatypeFactory.newInstance().newXMLGregorianCalendar(cal);
	}

	public static String fromDate(final Date date) {
		return new SimpleDateFormat(SIMPLE_DAY_FORMAT_IC_MOTOR).format(date);
	}

	public static String fromDate(final Date date, String format) {
		return fromDate(date, format, DEFAULT_LOCALE);
	}

	public static String fromDate(final Date date, String format, Locale locale) {
		return new SimpleDateFormat(format, locale).format(date);
	}

	public static Date toDate(final String string) throws ParseException {
		return toDate(string, SIMPLE_DAY_FORMAT_IC_MOTOR);
	}

	public static Date toDate(final String string, String format) throws ParseException {
		SimpleDateFormat sdf = new SimpleDateFormat(format);
		return sdf.parse(string);
	}

	public static int dayOfWeek(Date date) {
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		return c.get(Calendar.DAY_OF_WEEK);
	}

	public static boolean feriadoBancario(Date date) {
		return (DateHelper.FERIADO_BANCARIO_ddMM.equals(DateHelper.fromDate(date, DateHelper.ddMM)));
	}

	public static String now() {
		return fromDate(new Date());
	}

	public static String now(String format) {
		return fromDate(new Date(), format);
	}

	public static Date getDateWFirstHms(long datelong) {
		return new Date(setHmsFirst(datelong).getTimeInMillis());
	}

	public static Date getDateWLastHms(long datelong) {
		return new Date(setHmsLast(datelong).getTimeInMillis());
	}

	public static Calendar setHmsFirst(long dateTime) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTimeInMillis(dateTime);
		setCalendarHmsToFirst(calendar);
		return calendar;
	}

	public static Calendar setHmsLast(long dateTime) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTimeInMillis(dateTime);
		setCalendarHmsToLast(calendar);
		return calendar;
	}

	private static void setCalendarHmsToFirst(Calendar calendar) {
		calendar.set(Calendar.HOUR_OF_DAY, 0);
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.MILLISECOND, 0);
		calendar.set(Calendar.SECOND, 0);
	}

	private static void setCalendarHmsToLast(Calendar calendar) {
		calendar.set(Calendar.HOUR_OF_DAY, 23);
		calendar.set(Calendar.MINUTE, 59);
		calendar.set(Calendar.MILLISECOND, 0);
		calendar.set(Calendar.SECOND, 59);
	}

	public static Date getCurrentDate() {
		return new Date(System.currentTimeMillis());
	}

	public static boolean isBeforeToday(Date date) {
		Date dateNow = getCurrentDateWOHms();
		return date.before(dateNow);
	}

	public static boolean isBefore(Date date, Date when) {
		if (date == null || when == null)
			return false;
		return getDateWFirstHms(date.getTime()).before(getDateWFirstHms(when.getTime()));
	}

	public static boolean isAfter(Date date, Date when) {
		if (date == null || when == null)
			return false;
		return getDateWFirstHms(date.getTime()).after(getDateWFirstHms(when.getTime()));
	}

	public static Date getCurrentDateWOHms() {
		return new Date(setHmsFirst(getCurrentDate().getTime()).getTime().getTime());
	}

	public static boolean daysBetweenDates(Date dateStart, Date dateEnd, int daysQuantity) {
		long dayDiff = (dateEnd.getTime() - dateStart.getTime()) / DAY_LONG;
		return (dayDiff < daysQuantity);
	}

	public static Date addMonth(Date date, int add) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(Calendar.MONTH, add);
		return calendar.getTime();
	}

	public static Date addDate(Date date, int add) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(Calendar.DATE, add);
		return calendar.getTime();
	}

	public static Date addHour(Date date, int add) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(Calendar.HOUR, add);
		return calendar.getTime();
	}

	public static Date addMinute(Date date, int add) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(Calendar.MINUTE, add);
		return calendar.getTime();
	}
	
	public static Date subtractMonth(Date date, int subtract) {
		return DateHelper.addMonth(date, subtract * -1);
	}

	/**
	 * 
	 * @param startDate
	 *            No debe ser null
	 * @param endDate
	 *            Si null, toma el valor new Date()
	 * @return diferencia entre las fechas en segundos
	 */
	public static long getSecondsBetweenDate(Date startDate, Date endDate) {
		long endTime = Optional.ofNullable(endDate).orElse(new Date()).getTime();
		long diferenciaMils = endTime - startDate.getTime();
		return diferenciaMils / 1000;
	}

	/**
	 * 
	 * @param startDate
	 *            No debe ser null
	 * @param endDate
	 *            Si null, toma el valor new Date()
	 * @return diferencia entre las fechas en minutos
	 */
	public static Integer getMinuteBetweenDate(Date startDate, Date endDate) {
		long segundos = getSecondsBetweenDate(startDate, endDate);
		return Integer.valueOf(String.valueOf(segundos / 60));
	}

	public static String getMonthDescriptionByDate(Date date) {
		if (date == null)
			return "";
		return (new SimpleDateFormat("MMMM", new Locale("es", "ES"))).format(date);
	}

	public static double daysBetweenDate(Date initialDate, Date endDate) {
		return (int) ((endDate.getTime() - initialDate.getTime()) / DAY_LONG);
	}

	public static long monthsBetweenTwoDates(Date fechaInicio, Date fechaFin) {
		try {
			Calendar startCalendar = Calendar.getInstance();
			startCalendar.setTime(fechaInicio);
			Calendar endCalendar = Calendar.getInstance();
			endCalendar.setTime(fechaFin);
			long startMes = (long) ((startCalendar.get(Calendar.YEAR) * 12) + startCalendar.get(Calendar.MONTH));
			long endMes = (long) ((endCalendar.get(Calendar.YEAR) * 12) + endCalendar.get(Calendar.MONTH));
			return (endMes - startMes);
		} catch (Exception e) {
			return 0;
		}
	}

	public static double yearsBetweenTwoDates(Date fechaInicio, Date fechaFin) {
		try {
			return (int) (monthsBetweenTwoDates(fechaInicio, fechaFin) / NUMBER_OF_MONTHS_IN_A_YEAR);
		} catch (Exception e) {
			return 0;
		}
	}

	public static String getMonthSpanish(int monthint) {
		String monthstring = new DateFormatSymbols(new Locale("es")).getMonths()[monthint - 1];
		if (monthstring != null) {
			monthstring = monthstring.toUpperCase();
		}
		return monthstring;
	}

	public static String getMonthForInt(int num) {
		String month = "MES_INCORRECTO";
		DateFormatSymbols dfs = new DateFormatSymbols();
		String[] months = dfs.getMonths();
		if (num >= 0 && num <= 11) {
			month = months[num];
		}
		return month;
	}

	public static Date firstDay(int year, int month) {
		return DateHelper.dateByYearMonthDay(year, month, 1);
	}

	public static Date lastDay(int year, int month) {
		Calendar calendar = Calendar.getInstance();
		Date date = dateByYearMonth(year, month);
		calendar.setTime(date);
		calendar.set(Calendar.DAY_OF_MONTH, calendar.getActualMaximum(Calendar.DAY_OF_MONTH));
		return calendar.getTime();
	}

	public static Date firstDayOfCurrent() {
		Date currentDate = DateHelper.getCurrentDate();
		Integer month = DateHelper.extractMonthByDate(currentDate);
		Integer year = DateHelper.extractYearByDate(currentDate);
		return firstDay(year, month);
	}
	
	public static Date lastDayOfCurrent() {
		Date currentDate = DateHelper.getCurrentDate();
		Integer month = DateHelper.extractMonthByDate(currentDate);
		Integer year = DateHelper.extractYearByDate(currentDate);
		return lastDay(year, month);
	}

	public static int extractYearByDate(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		return calendar.get(Calendar.YEAR);
	}

	public static Date dateByYearMonthDay(int year, int month, int day) {
		Calendar calendar = Calendar.getInstance();
		calendar.set(year, month - 1, day);
		return calendar.getTime();
	}

	public static Date dateByYearMonth(int year, int month) {
		return dateByYearMonthDay(year, month, 1);
	}

	public static int extractMonthByDate(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		return calendar.get(Calendar.MONTH) + 1;
	}
}