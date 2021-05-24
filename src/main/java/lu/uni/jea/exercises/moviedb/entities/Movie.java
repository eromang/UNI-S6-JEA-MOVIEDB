package lu.uni.jea.exercises.moviedb.entities;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
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
@NamedQueries({
        @NamedQuery(name = "Movie.getMovieList",
                query = "SELECT p FROM Movie p")
})
public class Movie implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id // Primary key
    @NotNull
    private String title;

    @Id // Primary key
    @NotNull
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

    // Implement the ManyToOne from Movie to Studio
    // Movie table contain multiple occurrences of the Movie(studioName) with same Movie(producerCertN)
    // But Studio table contain only 1 occurrence of Studio(name) + Studio(presCertN)

    //@ManyToOne(fetch=FetchType.EAGER, cascade = CascadeType.ALL)
    //@JoinColumn(name="studioName", referencedColumnName="name")
    //private Studio studio;

    @ManyToOne
    @JoinColumn(name="studioName", referencedColumnName="name")
    private Studio studio;

    // Todo implement OneToOne relation with composite primary key
    // For StarsIn

    //@OneToOne
    //@PrimaryKeyJoinColumns({
    //        @PrimaryKeyJoinColumn(name="title", referencedColumnName="title"),
    //        @PrimaryKeyJoinColumn(name="year", referencedColumnName="year")
    //})
    //private StarsIn starsIn;

    // Empty constructor

    public Movie() {}

    // TODO Constructor

    public Movie(String title, int year, int length, String inColor, MovieExec movieExec, Studio studio) {
        this.setTitle(title);
        this.setYear(year);
        this.setLength(length);
        this.setInColor(inColor);
        this.setMovieExec(movieExec);
        this.setStudio(studio);
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

    public Studio getStudio() {
        return studio;
    }

    public void setStudio(Studio studio) {
        this.studio = studio;
    }
}
