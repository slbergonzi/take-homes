package rubiconproject.domain;

import lombok.Data;

@Data
public class Site {

	private String id;
	private String name;
	private Boolean mobile;
	private String keywords;
	private Integer score;

}