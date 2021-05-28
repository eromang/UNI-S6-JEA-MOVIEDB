package lu.uni.jea.exercises.moviedb.ejb;

import lu.uni.jea.exercises.moviedb.entities.Movie;
import lu.uni.jea.exercises.moviedb.entities.MovieExec;

import java.util.List;

public interface MovieDBEJBI {
    public List<Movie> getMovieList();
    public Movie getMovie(String title, int year);
    public List<Movie> getMovieListByYear(int searchedMoviesYear);
    public List<String> getDistinctInColor();
    public List<String> getDistinctStudio();
    public List<MovieExec> getDistinctProducer();
    public List<String> getDistinctStarsIn();
    public void insertMovieWithQuery(Movie movie);
}
