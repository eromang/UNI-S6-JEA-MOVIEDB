package lu.uni.jea.exercises.moviedb;

import lu.uni.jea.exercises.moviedb.ejb.MovieDBEJBI;
import lu.uni.jea.exercises.moviedb.entities.Movie;
import org.apache.log4j.Logger;

import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Named("moviedb")
@SessionScoped
public class MovieDB implements Serializable {

    private static final Logger logger = Logger.getLogger ( MovieDB.class );

    private List<Movie> movieList = new ArrayList<>();

    @EJB
    private MovieDBEJBI movieDBEJBI;

    private String movieName;
    private String movieExecName;

    // Get list of all movies
    public List<Movie> getMovieList() {
        movieList = movieDBEJBI.getMovieList();
        return movieList;
    }

    // TODO to adapt to need

    public String getMovieName() {
        movieName = movieDBEJBI.getMovieName();
        return movieName;
    }

    public String getMovieExecName() {
        movieExecName = movieDBEJBI.getMovieExecName();
        return movieExecName;
    }
}
