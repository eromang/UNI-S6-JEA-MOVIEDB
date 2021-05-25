# UNI-S6-JEA-MOVIEDB

UNI S6 JEA Exercise 3 MOVIE DB

## Exercise 3 - requirements

In the file movie.sql you will find a dump for a small database with information on a few (rather old) movies.

Import this file into the provided Docker-based MySQL database "javaee".

Then develop JPA code that can Search in the database:
- Find all movies of a certain year including respective executives and participating stars (name).
- List all movie titles and year, then let the user choose one movie and show all details for that movie.
- Provide a view to insert a new movie to the DB.

Develop all required JSF views, named beans and EJBs for the requested operations.

Upload your complete project (including source code, a Maven project configuration file, a textual README and all other files required to build/deploy the project) - however, no need to include the DB dump file.

Please do also not upload your Docker installation, since the MySQL directory will typically be huge - I will use a local copy of the Docker application that is available on Moodle.

**Small subtlety:** one of the tables has a composite primary key. Check in the JavaEE tutorial how to write an entity class in this situation (the tutorial names these keys "Compound Primary Keys").

25.5.: since there was some confusion about the meaning of the different columns in tables (no foreign keys were listed in the dump), I created a new dump with foreign keys added. Please pick up the new dump below and replace the previous one with the new dump.


## Project description

- context-root: /moviedb
- groupId: lu.uni.jea.exercises
- artifactId: moviedb
- name: moviedb
- Depedencies:
    - org.languagetool
    - org.primefaces
- URL: http://0.0.0.0:8080/moviedb/

## Database

### DB setup

- MYSQL_ROOT_PASSWORD: secret
- MYSQL_DATABASE: javaee 
- MYSQL_USER: javaee 
- MYSQL_PASSWORD: eeavaj 

### DB Tables

#### Movie

```sql
CREATE TABLE Movie (
	title 		VARCHAR(255),
	year 		INTEGER,
	length 		INTEGER,
	inColor		CHAR(1),
	studioName	VARCHAR(255) REFERENCES Studio(name) ON DELETE CASCADE ON UPDATE CASCADE,
	producerCertN 	INTEGER REFERENCES MovieExec(certN) ON DELETE CASCADE ON UPDATE CASCADE,
	PRIMARY KEY (title, year)
);
```

- Table storing the movies
- Each movie has:
	- A uniq studio: aka OneToOne relation
	- A uniq producer: aka OneToOne relation

Composite primary key on title and year is managed by ``MovieID.class``

#### StarsIn

```sql
CREATE TABLE StarsIn (
	id      INTEGER,
	name 	VARCHAR(30),
	title 	VARCHAR(255),
	year	INTEGER,
	PRIMARY KEY (id),
	FOREIGN KEY (title, year) REFERENCES Movie(title, year) ON DELETE CASCADE ON UPDATE CASCADE
);
```

- Table storing the stars acting in movies
- Each star has a uniq relation with a movie / year set
	- It is a OneToOne relation


#### Studio

```sql
CREATE TABLE Studio (
	name	 	CHAR(30) PRIMARY KEY,
	address		VARCHAR(255),
	presCertN   INTEGER REFERENCES MovieExec(certN) ON DELETE CASCADE ON UPDATE CASCADE
);
```

- Table storing the movies studios
- Each studio can have multiple producers
	- It is a one to many relation

#### MovieExec

```sql
CREATE TABLE MovieExec (
	name 		CHAR(30),
	address		VARCHAR(255),
	certN   	INTEGER PRIMARY KEY,
	netWorth	INTEGER
);
```

- Table storing the producers
- Each producers has a uniq ID


## Docker

- docker ps
- docker exec -it 3edbafe3af28 /bin/bash

## Build

1. cd in the zip repository
2. mvn clean package wildfly:deploy

## MVN

- mvn clean package wildfly:deploy

## Wildfly logs

- /opt/jboss/wildfly/standalone/log