CREATE TABLE accessory(
	id INT PRIMARY KEY auto_increment,
	accessory_type VARCHAR(100)
);

CREATE TABLE person(
	id INT PRIMARY KEY auto_increment,
	name VARCHAR(100),
	age INT,
	area VARCHAR(100),
	city VARCHAR(100),
	state VARCHAR(100),
	country VARCHAR(100),
	zip_code VARCHAR(100)
);

CREATE TABLE vehicle(
	id INT PRIMARY KEY auto_increment,
	vehicle_type VARCHAR(100),
	person_id int,
	FOREIGN KEY fk_vehicle_person(person_id)
	REFERENCES person(id)
);

CREATE TABLE person_accessory(
	person_id int,
	accessory_id int
);