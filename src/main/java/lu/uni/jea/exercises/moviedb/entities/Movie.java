package lu.uni.jea.exercises.moviedb.entities;

import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.List;

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
                query = "SELECT p FROM Movie p"),
        @NamedQuery(name = "Movie.getMovieListByYear",
                query = "SELECT p FROM Movie p WHERE p.year = :searchedYear")
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
    // producerCertN mean producer in a studio
    // Movie table contain multiple occurrence of the same MovieExec
    // MovieExec table contain only a single instance of a uniq MovieExec
    // Cascade on update and delete and cannot be null

    @ManyToOne
    @JoinColumn(name="producerCertN", referencedColumnName="certN", nullable=false)
    private MovieExec movieExec;

    // Implement the ManyToOne from Movie to Studio
    // Movie table contain multiple occurrences of the Movie(studioName) with same Movie(producerCertN)
    // But Studio table contain only 1 occurrence of Studio(name) + Studio(presCertN)

    @ManyToOne
    @JoinColumn(name="studioName", referencedColumnName="name", nullable=false)
    private Studio studio;

    // OneToMany relation with composite primary key
    // A movie could have multiple stars

    @OneToMany(fetch=FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumns(value = {
            @JoinColumn(name="title", referencedColumnName = "title", nullable = false, insertable = false, updatable = false),
            @JoinColumn(name = "year", referencedColumnName = "year", nullable = false, insertable = false, updatable = false)
    })
    @NotFound(action = NotFoundAction.IGNORE)
    private List<StarsIn> starsIn;

    // Empty constructor

    public Movie() {}

    // Constructor
    // A movie has one title, one year of production, a length, a inColor, One movieExec with details

    public Movie(String title, int year, int length, String inColor, MovieExec movieExec, Studio studio, List<StarsIn> starsIn) {
        this.setTitle(title);
        this.setYear(year);
        this.setLength(length);
        this.setInColor(inColor);
        this.setMovieExec(movieExec);
        this.setStudio(studio);
        this.setStarsIn(starsIn);
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

    public List<StarsIn> getStarsIn() {
        return starsIn;
    }

    public void setStarsIn(List<StarsIn> starsIn) {
        this.starsIn = starsIn;
    }
}
