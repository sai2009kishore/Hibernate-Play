package enums;

public enum Tables {
	PERSON_ACCESSORY("person_accessory"),

	VEHICLE("vehicle"),

	PERSON("person"),

	ACCESSORY("accessory");

	private final String name;

	public String getName() {
		return this.name;
	}

	Tables(String name) {
		this.name = name;
	}
}
