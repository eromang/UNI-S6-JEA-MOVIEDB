package lu.uni.jea.exercises.moviedb.entities;

import javax.persistence.*;
import java.io.Serializable;

/**
 *
 * @author Eric ROMANG
 * @professor Dr. Müller Volker
 * @subject UNI S6 JEA - Exercise 3 - Movie DB
 *
 */

@Entity
@Table(name = "Studio")
public class Studio implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    private String name;

    @Basic
    private String address;

    // Implementation of the ManyToOne to MovieExec
    // Studio table contain multiple occurrence of the same MovieExec
    // MovieExec table contain only a single instance of a uniq MovieExec
    // Cascade on update and delete and cannot be null

    @ManyToOne(fetch=FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name="presCertN", nullable=false)
    private MovieExec movieExec;

    // Empty constructor
    public Studio() {}

    // Constructor
    public Studio(String name, String address, MovieExec movieExec) {
        this.setName(name);
        this.setAddress(address);
        this.setMovieExec(movieExec);
    }

    // Getters and Setters

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public MovieExec getMovieExec() {
        return movieExec;
    }

    public void setMovieExec(MovieExec movieExec) {
        this.movieExec = movieExec;
    }

}
