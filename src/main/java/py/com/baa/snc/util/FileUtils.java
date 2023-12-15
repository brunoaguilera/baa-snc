package py.com.baa.snc.util;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URISyntaxException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

import org.apache.commons.io.IOUtils;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvException;

public class FileUtils {

	/**
	 * Lee un archivo de texto y lo carga a un String
	 *
	 * @param path
	 *            El Path del archivo en cuestión
	 * @param encoding
	 *            El charset que se utilizará para leer el archivo
	 * @return fileString
	 * @throws IOException
	 */
	public static String readFile(Path path, Charset encoding) throws IOException {
		byte[] encoded = Files.readAllBytes(path);
		return new String(encoded, encoding);
	}

	public static String readFile(InputStream stream, Charset encoding) throws IOException {
		return IOUtils.toString(stream, encoding);
	}

	/**
	 * Lee un archivo de texto desde el classpath y lo carga a un String
	 *
	 * @param classpath
	 *            ruta del archivo
	 * @param encoding
	 *            codificación
	 * @return fileString
	 * @throws IOException
	 */
	public static String readFromClasspath(String classpath, Charset encoding) throws IOException {
		Resource res = new ClassPathResource(classpath);

		if (res.exists()) {
			return readFile(res.getInputStream(), encoding);
		} else {
			throw new FileNotFoundException(classpath);
		}
	}

	/**
	 * Lee un archivo de texto desde el classpath y lo carga a un String
	 *
	 * @param classpath
	 *            ruta del archivo
	 * @return fileString
	 * @throws URISyntaxException
	 * @throws IOException
	 */
	public static String readFromClasspath(String classpath) throws IOException {
		return readFromClasspath(classpath, StandardCharsets.UTF_8);
	}

	/**
	 * Lee un archivo de texto desde el classpath y lo carga a byte
	 *
	 * @param classpath
	 *            ruta del archivo
	 * @return fileByes
	 * @throws URISyntaxException
	 * @throws IOException
	 */
	public static byte[] readFromClasspathBytes(String classpath) throws IOException {
		Resource res = new ClassPathResource(classpath);

		if (res.exists()) {
			return IOUtils.toByteArray(res.getInputStream());
		} else {
			throw new FileNotFoundException(classpath);
		}
	}

	public static List<String[]> readAndSplitLines(MultipartFile file) throws IOException, CsvException {
		CSVReader csvReader = null;

		try {
			csvReader = new CSVReader(new InputStreamReader(file.getInputStream(), StandardCharsets.UTF_8));
			return csvReader.readAll();

		} catch (CsvException e) {
			throw e;
		} catch (IOException e) {
			throw e;
		} finally {
			if (csvReader != null) {
				csvReader.close();
			}
		}
	}
}