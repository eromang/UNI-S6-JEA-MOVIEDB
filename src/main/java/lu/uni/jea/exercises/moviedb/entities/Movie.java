package lu.uni.jea.exercises.moviedb.entities;

import javax.persistence.*;
import java.io.Serializable;

/**
 *
 * @author Eric ROMANG
 * @professor Dr. Müller Volker
 * @subject UNI S6 JEA - Exercise 3 - Movie DB
 * @various: Composite primary key on title and year
 *
 */

@Entity
@IdClass(MovieID.class) // for composite primary key
@Table(name = "Movie")
public class Movie implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id // Primary key
    private String title;

    @Id // Primary key
    private Integer year;

    @Basic
    private Integer length;

    @Basic
    private String inColor;

    // Implementation of the ManyToOne to MovieExec
    // Movie table contain multiple occurrence of the same MovieExec
    // MovieExec table contain only a single instance of a uniq MovieExec
    // Cascade on update and delete and cannot be null

    @ManyToOne(fetch=FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name="producerCertN", nullable=false)
    private MovieExec movieExec;

    // TODO : Implement the ManyToMany to Studio
    // Movie table contain multiple occurrence of the same studio
    // But studio table could contain multiple instances of the same studio



    // Todo implement OneToOne relation with composite primary key
    // For StarsIn

    // Empty constructor

    public Movie() {}

    // TODO Constructor

    public Movie(String title, int year, int length, String inColor, MovieExec movieExec) {
        this.setTitle(title);
        this.setYear(year);
        this.setLength(length);
        this.setInColor(inColor);
        this.setMovieExec(movieExec);
    }

    // Getters and Setters

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    public Integer getLength() {
        return length;
    }

    public void setLength(Integer length) {
        this.length = length;
    }

    public String getInColor() {
        return inColor;
    }

    public void setInColor(String inColor) {
        this.inColor = inColor;
    }

    public MovieExec getMovieExec() {
        return movieExec;
    }

    public void setMovieExec(MovieExec movieExec) {
        this.movieExec = movieExec;
    }

}
