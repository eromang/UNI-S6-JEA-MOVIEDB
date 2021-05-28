package lu.uni.jea.exercises.moviedb.entities;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
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
@NamedQuery(name = "Studio.getDistinctStudio",
        query = "SELECT distinct s.name AS name FROM Studio s")
public class Studio implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @NotNull
    private String name;

    @Basic
    private String address;

    @Basic
    private Integer presCertN;

    // Implementation of the ManyToOne to MovieExec
    // presCertN is the president of the Studio
    // Studio table contain multiple occurrence of the same MovieExec
    // MovieExec table contain only a single instance of a uniq MovieExec
    // Cascade on update and delete and cannot be null

    @ManyToOne
    @JoinColumn(name="presCertN", referencedColumnName="certN", nullable=false,
            insertable = false, updatable = false)
    private MovieExec studioPresident;

    // Empty constructor
    public Studio() {}

    // Constructor
    public Studio(String name, String address, MovieExec studioPresident) {
        this.setName(name);
        this.setAddress(address);
        this.setStudioPresident(studioPresident);
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

    public Integer getPresCertN() {
        return presCertN;
    }

    public void setPresCertN(Integer presCertN) {
        this.presCertN = presCertN;
    }

    public MovieExec getStudioPresident() {
        return studioPresident;
    }

    public void setStudioPresident(MovieExec studioPresident) {
        this.studioPresident = studioPresident;
    }
}
