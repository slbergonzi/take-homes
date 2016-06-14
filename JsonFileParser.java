package rubiconproject;

import java.util.List;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.gson.Gson;

import rubiconproject.domain.JsonRow;
import rubiconproject.domain.OutputData;
import rubiconproject.domain.Site;

public class JsonFileParser {
	private final Logger LOGGER = LoggerFactory.getLogger(JsonFileParser.class);

	/**
	 * parse a JSON file
	 * @param output data record
	 * @param fileName file to be read
	 */
	public void processData(OutputData output, String fileName) {		
		LOGGER.debug("JSON parsing file: " + fileName);
		
		List<String> lines = new ArrayList<>();
		PopulateKeyword keywords = new PopulateKeyword();
		
		try (Stream<String> stream = Files.lines(Paths.get(fileName))) {

			lines = stream
					.filter(line -> !line.startsWith("["))
					.filter(line -> !line.startsWith("]"))
					.collect(Collectors.toList());
			ArrayList<Site> sites = new ArrayList();
			
			Gson gson = new Gson();
			for ( String obj : lines ) {
				if (obj.charAt(obj.length()-1)==',')
					obj = obj.substring(0, obj.length()-1);
				Site site = new Site();
				JsonRow row = gson.fromJson(obj, JsonRow.class);
				site.setName(row.getName());
				site.setScore(Integer.valueOf(row.getScore()));
				site.setMobile((row.getMobile()==1 ? true : false));
				site.setId(row.getId());
				
				site.setKeywords(keywords.resolveKeywords(site));
				
				sites.add(site);
			}
			
			output.setSites(sites);

		} catch (IOException e) {
			LOGGER.error(e.getMessage());;
		}
	}

}
