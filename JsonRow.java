package rubiconproject.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import lombok.Data;

@Data
@JsonPropertyOrder({
"site_id",
"name",
"mobile",
"score"
})
public class JsonRow {

	@JsonProperty("score")
	private String score;
	@JsonProperty("mobile")
	private int mobile;
	@JsonProperty("name")
	private String name;
	@JsonProperty("site_id")
	private String id;

}
