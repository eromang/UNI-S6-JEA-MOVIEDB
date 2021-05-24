package lu.uni.jea.exercises.moviedb.entities;

import org.apache.log4j.Logger;

import javax.persistence.Basic;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Objects;

/**
 *
 * @author Eric ROMANG
 * @professor Dr. Müller Volker
 * @subject UNI S6 JEA - Exercise 3 - Movie DB
 * @various: Composite primary key on title and year
 *
 */

public class MovieID implements Serializable {

    private static final long serialVersionUID = 1L;

    @Basic
    @NotNull
    private String title;

    @Basic
    @NotNull
    private int year;

    // Logging for debug
    @Transient
    private static final Logger logger = Logger.getLogger(MovieID.class);

    // Empty Constructor
    public MovieID() {}

    // Constructor
    public MovieID(String title, int year) {
        this.title = title;
        this.year = year;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MovieID movieID = (MovieID) o;
        return year == movieID.year && title.equals(movieID.title);
    }

    @Override
    public int hashCode() {
        return Objects.hash(title, year);
    }
}
