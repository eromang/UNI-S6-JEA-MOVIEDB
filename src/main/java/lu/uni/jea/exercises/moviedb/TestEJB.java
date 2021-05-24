package lu.uni.jea.exercises.moviedb;

import lu.uni.jea.exercises.moviedb.ejb.MovieDBEJBI;
import org.apache.log4j.Logger;

import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import java.io.Serializable;

@Named("moviedbejb")
@SessionScoped
public class TestEJB implements Serializable {

    private static final Logger logger = Logger.getLogger ( TestEJB.class );

    @EJB
    private MovieDBEJBI movieDBEJBI;

    private String movieName;
    private String movieExecName;

    public String getMovieName() {
        movieName = movieDBEJBI.getMovieName();
        return movieName;
    }

    public String getMovieExecName() {
        movieExecName = movieDBEJBI.getMovieExecName();
        return movieExecName;
    }
}
