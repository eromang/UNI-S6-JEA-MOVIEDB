package lu.uni.jea.exercises.moviedb.ejb;

import lu.uni.jea.exercises.moviedb.entities.Movie;

import java.util.List;

public interface MovieDBEJBI {
    public List<Movie> getMovieList();
    public Movie getMovie(String title, int year);
    public List<Movie> getMovieListByYear(int searchedMoviesYear);
}
