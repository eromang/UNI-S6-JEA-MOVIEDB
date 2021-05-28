package lu.uni.jea.exercises.moviedb.ejb;


import lu.uni.jea.exercises.moviedb.MovieDB;
import lu.uni.jea.exercises.moviedb.entities.Movie;
import lu.uni.jea.exercises.moviedb.entities.MovieExec;
import lu.uni.jea.exercises.moviedb.entities.MovieID;
import org.apache.log4j.Logger;

import javax.ejb.Stateful;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceContextType;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;

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

    private static final Logger logger = Logger.getLogger ( MovieDBEJB.class );

    @PersistenceContext(unitName = "Movie-DB")
    private EntityManager em;


    /**
     *
     * @return List of all Movies
     */

    public List<Movie> getMovieList() {
        TypedQuery<Movie> query = em.createNamedQuery("Movie.getMovieList", Movie.class);
        return query.getResultList();
    }

    /**
     *
     * @return Display a specific Movie
     */

    public Movie getMovie(String title, int year) {
        MovieID movieID = new MovieID(title,year);
        Movie movie = em.find(Movie.class, movieID);
        return movie;
    }

    /**
     * Search movies by year
     */

    public List<Movie> getMovieListByYear(int searchedMoviesYear) {
        TypedQuery<Movie> query = em.createNamedQuery("Movie.getMovieListByYear", Movie.class);
        query.setParameter("searchedYear", searchedMoviesYear);
        return query.getResultList();
    }

    /**
     * Get distinct inColor
     */

    public List<String> getDistinctInColor() {
        TypedQuery<String> query = em.createNamedQuery("Movie.getDistinctInColor", String.class);
        return query.getResultList();
    }

    /**
     * Get distinct Studio
     */

    public List<String> getDistinctStudio() {
        TypedQuery<String> query = em.createNamedQuery("Studio.getDistinctStudio", String.class);
        return query.getResultList();
    }

    /**
     * Get distinct Producer
     */

    public List<MovieExec> getDistinctProducer() {
        TypedQuery<MovieExec> query = em.createNamedQuery("MovieExec.getDistinctProducer", MovieExec.class);
        return query.getResultList();
    }

    /**
     * Get distinct StarsIn
     */

    public List<String> getDistinctStarsIn() {
        TypedQuery<String> query = em.createNamedQuery("StarsIn.getDistinctStarsIn", String.class);
        return query.getResultList();
    }

    @Transactional
    public String insertMovieWithQuery(Movie movie) {

        logger.info("----Beginning Movie Add EJP debug----");
        logger.info("Title : " + movie.getTitle());
        logger.info("Year : " + movie.getYear());
        logger.info("Length : " + movie.getLength());
        logger.info("In color : " + movie.getInColor());
        logger.info("Studio : " + movie.getStudio().getName());
        logger.info("Producer CertN : " + movie.getMovieExec().getCertN());
        //logger.info("Star Name : " + starsInName);
        logger.info("----End Movie Add EJP debug----");

        try {
            em.createNativeQuery("INSERT INTO Movie (title, year, length, inColor, studioName, producerCertN) VALUES (?,?,?,?,?,?)")
                    .setParameter(1, movie.getTitle())
                    .setParameter(2, movie.getYear())
                    .setParameter(3, movie.getLength())
                    .setParameter(4, movie.getInColor())
                    .setParameter(5, movie.getStudio().getName())
                    .setParameter(6, movie.getMovieExec().getCertN())
                    .executeUpdate();
            logger.info("----Success insert----");
            return "success";
        } catch (Exception e) {
            logger.info("----Insert error----");
            return "failure";
        }
    }
}
