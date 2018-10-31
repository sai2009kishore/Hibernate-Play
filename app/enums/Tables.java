package enums;

public enum Tables {
	PERSON("person");

	private final String name;

	public String getName() {
		return this.name;
	}

	Tables(String name) {
		this.name = name;
	}
}
