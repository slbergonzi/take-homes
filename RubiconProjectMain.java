package rubiconproject;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.gson.Gson;

import rubiconproject.domain.OutputData;

public class RubiconProjectMain {

	private static final int TOTAL_PARAMETERS = 2;
	private static final String JSON_EXTENSION = "json";
	private static final String CSV_EXTENSION = "csv";

	/**
	 * pathToDirectory outputFile as parameters
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		if (args.length != RubiconProjectMain.TOTAL_PARAMETERS) {
			System.out.println("Usage: main <pathToDirectory> <outputFile>");
			System.exit(1);
		}

		RubiconProjectMain main = new RubiconProjectMain();
		try {
			main.processData(args[0], args[1]);
		} catch (IOException e) {
			System.err.println(e.getMessage());
		}

	}

	/**
	 * 
	 * @param directory
	 * @param outputFileName
	 * @throws IOException 
	 */
	private void processData(String directory, String outputFileName) throws IOException {
		final Logger LOGGER = LoggerFactory.getLogger(CsvFileParser.class);

		LOGGER.debug("Processing started %s, output to %s \n", directory, outputFileName);

		JsonFileParser json = new JsonFileParser();
		CsvFileParser csv = new CsvFileParser();

		Gson gson = new Gson();
		FileWriter file = new FileWriter(outputFileName);
		try {
			Files.walk(Paths.get(directory)).forEach(filePath -> {
				if (Files.isRegularFile(filePath)) {
					LOGGER.debug("Filepath: " + filePath);

					try {
						OutputData output = new OutputData();
						output.setCollectionId(filePath.getFileName().toString());

						if (filePath.toString().endsWith(RubiconProjectMain.JSON_EXTENSION)) {
							json.processData(output, filePath.toString());
						} else if (filePath.toString().endsWith(RubiconProjectMain.CSV_EXTENSION)) {
							csv.processData(output, filePath.toString());
						}
						
						file.write(gson.toJson(output));
					} catch (Exception e1) {
						LOGGER.error(e1.getMessage());
					}
				}
			});
		} catch (IOException e) {
			LOGGER.error(e.getMessage());
		}
		try {
			file.close();
		} catch (IOException e) {
			LOGGER.error(e.getMessage());
		}

	}

}
