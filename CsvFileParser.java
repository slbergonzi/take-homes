package rubiconproject;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import org.json.CDL;
import org.json.JSONArray;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.gson.Gson;

import rubiconproject.domain.CsvRow;
import rubiconproject.domain.OutputData;
import rubiconproject.domain.Site;

public class CsvFileParser {
	private final Logger LOGGER = LoggerFactory.getLogger(CsvFileParser.class);

	/**
	 * parse a CSV file
	 * @param output data record
	 * @param fileName file to be read
	 */
	public void processData(OutputData output, String fileName) {
		LOGGER.debug("CSV parsing file: " + fileName);

		String csvData = null;
		try {
			csvData = this.readFile(fileName);
		} catch (IOException e) {
			LOGGER.error(e.getMessage());
		}

		JSONArray array = CDL.toJSONArray(csvData);
		ArrayList<Site> sites = new ArrayList();
		Gson gson = new Gson();
		
		for ( Object obj : array ) {
			Site site = new Site();
			CsvRow row = gson.fromJson(obj.toString(), CsvRow.class);
			site.setName(row.getName());
			site.setScore(Integer.valueOf(row.getScore()));
			site.setMobile(Boolean.valueOf(row.getMobile()));
			site.setId(row.getId());
			
			sites.add(site);
		}
		
		output.setSites(sites);
	}

	/**
	 * Read in the data within the file
	 * @param file
	 * @return String
	 * @throws IOException
	 */
	private String readFile(String file) throws IOException {
	    BufferedReader reader = new BufferedReader(new FileReader (file));
	    String         line = null;
	    StringBuilder  stringBuilder = new StringBuilder();
	    String         ls = System.getProperty("line.separator");

	    try {
	        while((line = reader.readLine()) != null) {
	            stringBuilder.append(line);
	            stringBuilder.append(ls);
	        }

	        return stringBuilder.toString();
	    } finally {
	        reader.close();
	    }
	}
}
