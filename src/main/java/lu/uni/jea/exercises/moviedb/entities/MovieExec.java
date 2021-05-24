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
@Table(name = "MovieExec")
public class MovieExec implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Integer certN;

    @Basic
    private String name;

    @Basic
    private String address;

    @Basic
    private Integer netWorth;

    // Empty Constructor
    public MovieExec() {}

    // Constructor
    public MovieExec(String name, String address, int netWorth) {
        this.setName(name);
        this.setAddress(address);
        this.setNetWorth(netWorth);
    }

    // Getters and Setters

    public Integer getCertN() {
        return certN;
    }

    public void setCertN(Integer certN) {
        this.certN = certN;
    }

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

    public Integer getNetWorth() {
        return netWorth;
    }

    public void setNetWorth(Integer netWorth) {
        this.netWorth = netWorth;
    }

    @Override
    public String toString() {
        return "Producer ID : " + this.certN + ", name : " + this.name + ", address : " + address + ", networth : " + netWorth;
    }
}
