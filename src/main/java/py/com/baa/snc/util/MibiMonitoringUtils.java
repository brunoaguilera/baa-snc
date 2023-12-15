package py.com.baa.snc.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.MessageFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;

import py.com.baa.snc.exception.MibiMonitoringApplicationException;

public class MibiMonitoringUtils {

	private static final Pattern INTERPOLATION_PATTERN = Pattern.compile("\\{(\\w+)(.*?)\\}");

	private static final Logger LOGGER = LoggerFactory.getLogger(MibiMonitoringUtils.class);

	private static final int BASE_MAX = 11;
	
	public static final String LEVEL_STATUS_SUCCESS = "success";
	public static final String LEVEL_STATUS_INFO = "info";
	public static final String LEVEL_STATUS_ERROR = "error";

	private static Map<Character, Character> spanish2ASCII = new HashMap<Character, Character>();

	static {
		char[] spanish = new char[] { 'á', 'é', 'í', 'ó', 'ú', 'ü', 'ñ', 'Á', 'É', 'Í', 'Ó', 'Ú', 'Ü', 'Ñ', 'ô', '&' };
		char[] us = new char[] { 'a', 'e', 'i', 'o', 'u', 'u', 'n', 'A', 'E', 'I', 'O', 'U', 'U', 'N', 'o', 'y' };
		for (int i = 0; i < spanish.length; i++) {
			spanish2ASCII.put(spanish[i], us[i]);
		}
	}

	private MibiMonitoringUtils() {

	}

	/**
	 * Retorna el IP del cliente asumiendo que el Web Server está detras de un proxy
	 *
	 * @param request
	 *            petición http
	 * @return dirección ip
	 */
	public static String getClientIpAddr(HttpServletRequest request) {
		String ip = null;
		for (String header : Arrays.asList("X-Forwarded-For", "Proxy-Client-IP", "WL-Proxy-Client-IP", "HTTP_CLIENT_IP",
				"HTTP_X_FORWARDED_FOR")) {
			ip = request.getHeader(header);
			if (checkIp(ip)) {
				return ip;
			}
		}
		ip = request.getRemoteAddr();
		return ip;
	}

	private static boolean checkIp(String ipAddr) {
		if (ipAddr == null) {
			return false;
		}
		if (ipAddr.length() == 0) {
			return false;
		}
		if ("unknown".equalsIgnoreCase(ipAddr)) {
			return false;
		}
		return true;
	}

	public static String join(Collection<?> list, String separator) {
		return StringUtils.collectionToDelimitedString(list, separator);
	}

	public static String formatLogString(String s) {
		return "\"" + s + "\"";
	}

	public static String formatLogString(Object s) {
		return "\"" + s.toString() + "\"";
	}

	public static String formatLogId(Object s) {
		return "#" + s.toString();
	}

	public static String formatMap(String format, Map<String, Object> values) {
		StringBuilder formatter = new StringBuilder(format);
		List<Object> valueList = new ArrayList<Object>();

		Matcher matcher = INTERPOLATION_PATTERN.matcher(format);

		while (matcher.find()) {
			String key = matcher.group(1);
			String rest = matcher.group(2);

			String formatKey = String.format("{%s%s}", key, rest);
			int index = formatter.indexOf(formatKey);

			if (index != -1) {
				Object value = null;
				if (values != null) {
					value = values.get(key);
				}
				if (value != null) {
					String formatValue = String.format("{%d%s}", valueList.size(), rest);
					formatter.replace(index, index + formatKey.length(), formatValue);
					valueList.add(value);
				} else {
					throw new ArrayIndexOutOfBoundsException(
							String.format("Pattern key %s not found in dictionary", key));
				}
			}
		}

		return MessageFormat.format(formatter.toString(), valueList.toArray());
	}

	public static String formatMoney(double amount) {
		DecimalFormat df = new DecimalFormat("###,###");
		return df.format(amount);
	}

	public static String formatRrn(String rrn) {
		return rrn.substring(rrn.length() - 6);
	}

	public static String getErrorMessage(MethodArgumentNotValidException e) {
		StringBuilder sb = new StringBuilder();
		BindingResult bindingResult = e.getBindingResult();
		List<FieldError> fieldErrors = bindingResult.getFieldErrors();
		for (FieldError error : fieldErrors) {
			sb.append(error.getField() + ":" + error.getDefaultMessage());
			sb.append(". ");
		}
		return sb.toString();
	}

	public static boolean matchString(String pattern, String value) {
		return Pattern.matches(pattern, value);
	}

	public static boolean compareWithEnum(String value, Enum<?> type) {
		String enumValue = type.name();
		return enumValue.equals(value);
	}

	public static boolean checkForUnique(String str) {
		boolean containsUnique = false;

		for (char c : str.toCharArray()) {
			if (str.indexOf(c) == str.lastIndexOf(c)) {
				containsUnique = true;
			} else {
				containsUnique = false;
			}
		}

		return containsUnique;
	}

	@SuppressWarnings("resource")
	public static String readFileToString(File file) throws Exception {
		String line;
		FileReader fr = null;
		StringBuilder everything = new StringBuilder();
		try {
			fr = new FileReader(file);
		} catch (FileNotFoundException f) {
			throw new Exception("Archivo no encontrado");
		}
		BufferedReader br = new BufferedReader(fr);
		while ((line = br.readLine()) != null) {
			everything.append(line);
		}
		return everything.toString();
	}

	/**
	 * Este método se utiliza para obtener sólo la fecha a partir de un tipo Date
	 * con fecha y hora
	 * 
	 * @return fecha con hora 00:00:00
	 */
	public static Date getDateWithZeroTime(Date date) {
		Date withZeroTime = null;
		try {
			DateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
			withZeroTime = formatter.parse(formatter.format(date));
		} catch (ParseException e) {
			LOGGER.error(
					"No fue posible formatear la fecha para la comparación. No se realiza la comparación con la fecha",
					e);
		}
		return withZeroTime;
	}

	/**
	 * Retorna el dígito verificador del RUC para un número de cédula
	 * 
	 * @param number
	 *            cédula
	 * @return dígito verificador
	 */
	public static int getCheckDigit(String number) {
		int total;
		int resto;
		int k;
		int numberAux;
		int digit;
		String numberString = "";

		for (int i = 0; i < number.length(); i++) {
			char c = number.charAt(i);
			if (Character.isDigit(c)) {
				numberString += c;
			} else {
				numberString += (int) c;
			}
		}

		k = 2;
		total = 0;

		for (int i = numberString.length() - 1; i >= 0; i--) {
			k = k > BASE_MAX ? 2 : k;
			numberAux = numberString.charAt(i) - 48;
			total += numberAux * k++;
		}

		resto = total % 11;
		digit = resto > 1 ? 11 - resto : 0;

		return digit;
	}

	public static String getMibiWalletExceptionMsg(MibiMonitoringApplicationException e) {
		try {
			if (e == null)
				return "";
			return e.toString();
		} catch (Exception e2) {
			return "";
		}
	}

	public static String getRuntimeExceptionMsg(RuntimeException e) {
		try {
			if (e == null)
				return "";
			return e.getMessage();
		} catch (Exception e2) {
			return "";
		}
	}

	public static String nvl(String data) {
		return nvl(data, "-");
	}

	public static String nvl(String data, String value) {
		return trim(((data == null || "".equals(data)) ? value : data));
	}

	public static String nvlNoTrim(String data, String value) {
		return (data == null || "".equals(data) ? value : data);
	}

	public static String trim(String value) {
		return ("".equals(value.trim()) ? "" : value);
	}

	public static String stringToNullIfZero(String number) {
		if (number == null) {
			return null;
		}

		if (number.isEmpty()) {
			return null;
		}

		if (0 == Long.valueOf(number)) {
			return null;
		}

		if ("0".equals(number.trim())) {
			return null;
		}
		return number;
	}
}
