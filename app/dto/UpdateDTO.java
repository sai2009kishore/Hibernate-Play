package dto;

import com.fasterxml.jackson.databind.JsonNode;

public class UpdateDTO {

	private JsonNode json;

	public UpdateDTO(JsonNode json) {
		this.json = json;
	}

	public Integer getOrDefault(String field, Integer value) {
		Integer val = value;
		if (json.has(field)) {
			val = json.path(field).isNull() ? null : json.path(field).asInt();
		}
		return val;
	}
}
