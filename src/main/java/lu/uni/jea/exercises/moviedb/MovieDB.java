package lu.uni.jea.exercises.moviedb;

import lu.uni.jea.exercises.moviedb.ejb.MovieDBEJBI;
import lu.uni.jea.exercises.moviedb.entities.Movie;
import lu.uni.jea.exercises.moviedb.entities.StarsIn;
import org.apache.log4j.Logger;

import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import java.io.Serializable;
import java.util.*;

@Named("moviedb")
@SessionScoped
public class MovieDB implements Serializable {

    private static final String EDIT_MOVIE = "editMovie";
    private static final String BACK = "back";
    private static final String SEARCH = "search";
    private static final String ADD_MOVIE = "addmovie";

    private static final Logger logger = Logger.getLogger ( MovieDB.class );

    private List<Movie> movieList = new ArrayList<>();
    private List<Movie> searchedMoviesList = new ArrayList<>();
    private List<StarsIn> starsInMovieList = new ArrayList<>();
    private Movie movie;

    private String movieTitle;
    private int movieYear;
    private int movieLength;
    private String movieInColor;

    private String movieExecName;
    private String movieExecAddress;
    private int movieExecNetWorth;

    private String movieStudio;
    private String movieStudioAddress;
    private String movieStudioPresident;
    private String movieStudioPresidentAddress;
    private int movieStudioPresidentNetWorth;

    private int starsInCount;
    private String starsInName;

    private int searchedMoviesYear;

    @EJB
    private MovieDBEJBI movieDBEJBI;

    // Constructor
    public MovieDB() {
        logger.info("Start Movie DB");
    }

    // Navigation

    public String editMovie(String title, int year) {
        logger.info("Clicked on view button");
        logger.info("View movie " + title + " released in " + year);

        starsInMovieList.clear();

        movie = movieDBEJBI.getMovie(title,year);

        movieTitle = movie.getTitle();
        movieYear = movie.getYear();
        movieLength = movie.getLength();
        movieInColor = movie.getInColor();

        movieExecName = movie.getMovieExec().getName();
        movieExecAddress = movie.getMovieExec().getAddress();
        movieExecNetWorth = movie.getMovieExec().getNetWorth();

        movieStudio = movie.getStudio().getName();
        movieStudioAddress = movie.getStudio().getAddress();
        movieStudioPresident = movie.getStudio().getStudioPresident().getName();
        movieStudioPresidentAddress = movie.getStudio().getStudioPresident().getAddress();
        movieStudioPresidentNetWorth = movie.getStudio().getStudioPresident().getNetWorth();

        // For debugging purpose
        if(movie.getStarsIn().isEmpty()) {
            logger.info("No stars in the movie");

        } else {
            // Debub
            logger.info("Stars in the movie : " + movie.getStarsIn().size());
            Iterator<StarsIn> iterator = movie.getStarsIn().iterator();
            while (iterator.hasNext()) {
                StarsIn starsIn = iterator.next();
                logger.info("Star : " + starsIn.getName());
                starsInMovieList.add(starsIn);
            }

            // Debug
            logger.info("Stars in the movie : " + starsInMovieList.size());
            Iterator<StarsIn> iterator2 = starsInMovieList.iterator();
            while (iterator2.hasNext()) {
                StarsIn starsIn = iterator2.next();
                logger.info("Star : " + starsIn.getName());

            }
        }

        return EDIT_MOVIE;
    }

    public String back() {
        FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
        logger.info("Return back");
        return BACK;
    }

    public String addMovie() {
        logger.info("Add movie");
        return ADD_MOVIE;
    }

    public String searchMoviesByYear() {
        this.setSearchedMoviesYear(searchedMoviesYear);
        searchedMoviesList = movieDBEJBI.getMovieListByYear(searchedMoviesYear);
        return SEARCH;
    }

    // Getters and Setters

    // Get list of all movies
    public List<Movie> getMovieList() {
        movieList = movieDBEJBI.getMovieList();
        return movieList;
    }

    public String getMovieTitle() {
        return movieTitle;
    }

    public void setMovieTitle(String movieTitle) {
        this.movieTitle = movieTitle;
    }

    public int getMovieYear() {
        return movieYear;
    }

    public void setMovieYear(int movieYear) {
        this.movieYear = movieYear;
    }

    public int getMovieLength() {
        return movieLength;
    }

    public void setMovieLength(int movieLength) {
        this.movieLength = movieLength;
    }

    public String getMovieInColor() {
        return movieInColor;
    }

    public void setMovieInColor(String movieInColor) {
        this.movieInColor = movieInColor;
    }

    public String getMovieExecName() {
        return movieExecName;
    }

    public void setMovieExecName(String movieExecName) {
        this.movieExecName = movieExecName;
    }

    public String getMovieStudio() {
        return movieStudio;
    }

    public void setMovieStudio(String movieStudio) {
        this.movieStudio = movieStudio;
    }

    public String getMovieExecAddress() {
        return movieExecAddress;
    }

    public void setMovieExecAddress(String movieExecAddress) {
        this.movieExecAddress = movieExecAddress;
    }

    public int getMovieExecNetWorth() {
        return movieExecNetWorth;
    }

    public void setMovieExecNetWorth(int movieExecNetWorth) {
        this.movieExecNetWorth = movieExecNetWorth;
    }

    public String getMovieStudioAddress() {
        return movieStudioAddress;
    }

    public void setMovieStudioAddress(String movieStudioAddress) {
        this.movieStudioAddress = movieStudioAddress;
    }

    public String getMovieStudioPresident() {
        return movieStudioPresident;
    }

    public void setMovieStudioPresident(String movieStudioPresident) {
        this.movieStudioPresident = movieStudioPresident;
    }

    public String getMovieStudioPresidentAddress() {
        return movieStudioPresidentAddress;
    }

    public void setMovieStudioPresidentAddress(String movieStudioPresidentAddress) {
        this.movieStudioPresidentAddress = movieStudioPresidentAddress;
    }

    public int getMovieStudioPresidentNetWorth() {
        return movieStudioPresidentNetWorth;
    }

    public void setMovieStudioPresidentNetWorth(int movieStudioPresidentNetWorth) {
        this.movieStudioPresidentNetWorth = movieStudioPresidentNetWorth;
    }

    public String getStarsInName() {
        return starsInName;
    }

    public void setStarsInName(String starsInName) {
        this.starsInName = starsInName;
    }

    public List<StarsIn> getStarsInMovieList() {
        return starsInMovieList;
    }

    public void setStarsInMovieList(List<StarsIn> starsInMovieList) {
        this.starsInMovieList = starsInMovieList;
    }

    public int getSearchedMoviesYear() {
        return searchedMoviesYear;
    }

    public void setSearchedMoviesYear(int searchedMoviesYear) {
        this.searchedMoviesYear = searchedMoviesYear;
    }

    public List<Movie> getSearchedMoviesList() {
        return searchedMoviesList;
    }

    public void setSearchedMoviesList(List<Movie> searchedMoviesList) {
        this.searchedMoviesList = searchedMoviesList;
    }
}
