package lu.uni.jea.exercises.moviedb;

import lu.uni.jea.exercises.moviedb.entities.Movie;
import lu.uni.jea.exercises.moviedb.entities.MovieExec;
import lu.uni.jea.exercises.moviedb.entities.MovieID;
import org.apache.log4j.Logger;

import javax.annotation.Resource;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import javax.persistence.*;
import javax.transaction.UserTransaction;
import java.io.Serializable;

@Named("moviedb")
@SessionScoped
public class Test implements Serializable {

    private MovieExec movieExec;

    private String movieName;
    private String movieExecName;

    public Test() {}

    @PersistenceContext(unitName = "Movie-DB")
    private EntityManager em;

    private static final Logger logger = Logger.getLogger ( Test.class );

    @Resource
    private UserTransaction userTransaction;


    public void setMovieName(String movieName) {
        this.movieName = movieName;
    }

    public String getMovieName() {
        MovieID movieID = new MovieID("Skyfall",2012);
        Movie movie = em.find(Movie.class, movieID);

        movieName = movie.getTitle();
        return movieName;
    }

    public String getMovieExecName() {
        MovieID movieID = new MovieID("Skyfall",2012);
        Movie movie = em.find(Movie.class, movieID);

        movieExec = movie.getMovieExec();
        movieExecName = movieExec.getName();
        return movieExecName;
    }
}
