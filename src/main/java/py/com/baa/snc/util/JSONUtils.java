package py.com.baa.snc.util;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import py.com.baa.snc.exception.MibiMonitoringApplicationException;

public class JSONUtils {
	public static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();

	private JSONUtils() {
	}

	/**
	 * Serializa un objeto a un string en formato JSON.
	 *
	 * @param pojo el objeto a ser serializado
	 * @return la representación JSON del objeto
	 * @throws MibiMonitoringApplicationException 
	 */
	public static String toJSON(Object pojo) throws MibiMonitoringApplicationException {
		try {
			return OBJECT_MAPPER.writeValueAsString(pojo);
		} catch (JsonProcessingException e) {
			throw new MibiMonitoringApplicationException(e);
		} catch (Exception e) {
			return "";
		}
	}

	public static <T> T fromJSON(String json, Class<T> clazz) throws IOException {
		return OBJECT_MAPPER.readValue(json, clazz);
	}

	public static <T> T fromMap(Object map, Class<T> clazz) {
		return OBJECT_MAPPER.convertValue(map, clazz);
	}

	public static Object objectFromJSON(String json, Class<?> clazz) throws IOException {
		return OBJECT_MAPPER.readValue(json, clazz);
	}

}
