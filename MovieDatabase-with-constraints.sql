-- MovieExec

CREATE TABLE MovieExec (
	name 		CHAR(30),
	address		VARCHAR(255),
	certN   	INTEGER PRIMARY KEY,
	netWorth	INTEGER
);

-- Studio

CREATE TABLE Studio (
	name	 	CHAR(30) PRIMARY KEY,
	address		VARCHAR(255),
	presCertN   	INTEGER REFERENCES MovieExec(certN) ON DELETE CASCADE ON UPDATE CASCADE
);

-- Movie

CREATE TABLE Movie (
	title 		VARCHAR(255),
	year 		INTEGER,
	length 		INTEGER,
	inColor		CHAR(1),
	studioName	VARCHAR(255) REFERENCES Studio(name) ON DELETE CASCADE ON UPDATE CASCADE,
	producerCertN 	INTEGER REFERENCES MovieExec(certN) ON DELETE CASCADE ON UPDATE CASCADE,
	PRIMARY KEY (title, year)
);

-- StarsIn

CREATE TABLE StarsIn (
	id      INTEGER,
	name 	VARCHAR(30),
	title 	VARCHAR(255),
	year	INTEGER,
	PRIMARY KEY (id),
	FOREIGN KEY (title, year) REFERENCES Movie(title, year) ON DELETE CASCADE ON UPDATE CASCADE
);

-- And finally insert some data

INSERT INTO MovieExec VALUES ('Cameron', 'LA', 555, 1000000);
INSERT INTO MovieExec VALUES ('Spielberg', 'NYC', 456, 25000000);
INSERT INTO MovieExec VALUES ('Broccoli', 'SF', 333, 500000);

INSERT INTO Studio VALUES ('Fox', 'LA', 555);
INSERT INTO Studio VALUES ('Disney', 'LA', 555);
INSERT INTO Studio VALUES ('Hemdale', 'LA', 555);
INSERT INTO Studio VALUES ('Carcolo', 'NYC', 456);
INSERT INTO Studio VALUES ('IMF', 'LV', 456);
INSERT INTO Studio VALUES ('Warner', 'LA', 333);

INSERT INTO Movie VALUES ('Skyfall', 2012, 120, 'T', 'Fox', 333);
INSERT INTO Movie VALUES ('QuantumOfSolace', 2008, 134, 'C', 'Fox', 333);
INSERT INTO Movie VALUES ('MightyDucks', 1990, 90, 'T', 'Disney', 456);
INSERT INTO Movie VALUES ('Terminator', 1984, 108, 'F', 'Hemdale', 555);
INSERT INTO Movie VALUES ('Terminator', 1991, 139, 'T', 'Carcolo', 555);
INSERT INTO Movie VALUES ('Terminator', 2003, 109, 'T', 'IMF', 555);
INSERT INTO Movie VALUES ('Star Wars', 1997, 129, 'T', 'Warner', 456);

INSERT INTO StarsIn VALUES (1, 'Arnold', 'Terminator', 1984);
INSERT INTO StarsIn VALUES (2, 'Arnold', 'Terminator', 1991);
INSERT INTO StarsIn VALUES (3, 'Arnold', 'Terminator', 2003);
INSERT INTO StarsIn VALUES (4, 'Nick', 'Terminator', 2003);

-- 