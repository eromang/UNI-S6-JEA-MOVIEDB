package lu.uni.jea.exercises.moviedb.ejb;

import lu.uni.jea.exercises.moviedb.entities.Movie;
import lu.uni.jea.exercises.moviedb.entities.MovieExec;

import java.util.List;

/**
 *
 * @author Eric ROMANG
 * @professor Dr. Müller Volker
 * @subject UNI S6 JEA - Exercise 3 - Movie DB
 *
 */

public interface MovieDBEJBI {
    public List<Movie> getMovieList();
    public Movie getMovie(String title, int year);
    public List<Movie> getMovieListByYear(int searchedMoviesYear);
    public List<String> getDistinctInColor();
    public List<String> getDistinctStudio();
    public List<MovieExec> getDistinctProducer();
    public List<String> getDistinctStarsIn();
    public String insertMovieWithQuery(Movie movie);
}
