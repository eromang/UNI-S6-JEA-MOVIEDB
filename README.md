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
    - org.primefaces
- URL: http://0.0.0.0:8080/moviedb/

## Database

### DB setup

- MYSQL_ROOT_PASSWORD: secret
- MYSQL_DATABASE: javaee 
- MYSQL_USER: javaee 
- MYSQL_PASSWORD: eeavaj 

## Build

1. cd in the zip repository
2. mvn clean package wildfly:deploy

## Wildfly logs

- /opt/jboss/wildfly/standalone/log