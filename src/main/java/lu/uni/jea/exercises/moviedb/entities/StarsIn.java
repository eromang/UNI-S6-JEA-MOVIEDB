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
@Table(name = "StarsIn")
@NamedQueries({
        @NamedQuery(name = "StarsIn.getDistinctStarsIn",
                query = "SELECT distinct s.name AS name FROM StarsIn s"),
        @NamedQuery(name = "StarsIn.getMaxID",
                query = "SELECT MAX(id) FROM StarsIn")

})
public class StarsIn implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @NotNull
    @GeneratedValue(strategy=GenerationType.TABLE)
    private Integer id;

    @Basic
    @NotNull
    private String name;

    @Basic
    @NotNull
    private String title;

    @Basic
    @NotNull
    private Integer year;

    // Default constructor
    public StarsIn() {}

    // Constructor
    public StarsIn(Integer id, String title, Integer year, String name) {
        this.setId(id);
        this.setTitle(title);
        this.setYear(year);
        this.setName(name);
    }

    // Getters and Setters

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

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
}
