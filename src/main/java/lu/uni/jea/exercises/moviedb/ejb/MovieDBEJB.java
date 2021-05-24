package lu.uni.jea.exercises.moviedb.ejb;


import lu.uni.jea.exercises.moviedb.entities.Movie;
import lu.uni.jea.exercises.moviedb.entities.MovieExec;
import lu.uni.jea.exercises.moviedb.entities.MovieID;

import javax.ejb.Stateful;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceContextType;
import javax.persistence.TypedQuery;

import java.util.List;

import static javax.ejb.TransactionAttributeType.MANDATORY;

/**
 *
 * @author Eric ROMANG
 * @professor Dr. Müller Volker
 * @subject UNI S6 JEA - Exercise 3 - Movie DB
 *
 */

@Stateless(name = "MovieDBEJB")
public class MovieDBEJB implements MovieDBEJBI {

    @PersistenceContext(unitName = "Movie-DB")
    private EntityManager em;

    private MovieExec movieExec;

    private String movieName;
    private String movieExecName;

    /**
     *
     * @return List of all Movies
     */

    public List<Movie> getMovieList() {
        TypedQuery<Movie> query = em.createNamedQuery("Movie.getMovieList", Movie.class);
        return query.getResultList();
    }

    // TODO: To adapt to need

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
