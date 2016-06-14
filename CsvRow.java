package rubiconproject.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import lombok.Data;

@Data
@JsonPropertyOrder({
	"id",
	"is mobile",
	"name",
	"score"
})
public class CsvRow {

	@JsonProperty("score")
	private String score;
	@JsonProperty("is mobile")
	private String mobile;
	@JsonProperty("name")
	private String name;
	@JsonProperty("id")
	private String id;

}
