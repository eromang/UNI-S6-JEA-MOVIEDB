package lu.uni.jea.exercises.moviedb;

import lu.uni.jea.exercises.moviedb.ejb.MovieDBEJBI;
import lu.uni.jea.exercises.moviedb.entities.Movie;
import lu.uni.jea.exercises.moviedb.entities.MovieExec;
import lu.uni.jea.exercises.moviedb.entities.StarsIn;
import lu.uni.jea.exercises.moviedb.entities.Studio;
import org.apache.log4j.Logger;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import java.awt.event.ActionEvent;
import java.io.Serializable;
import java.util.*;

/**
 *
 * @author Eric ROMANG
 * @professor Dr. Müller Volker
 * @subject UNI S6 JEA - Exercise 3 - Movie DB
 *
 */

@Named("moviedb")
@SessionScoped
public class MovieDB implements Serializable {

    private static final String EDIT_MOVIE = "editMovie";
    private static final String HOME = "home";
    private static final String SEARCH = "search";
    private static final String ADD_MOVIE = "addmovie";

    private static final Logger logger = Logger.getLogger ( MovieDB.class );

    private List<Movie> movieList = new ArrayList<>();
    private List<Movie> searchedMoviesList = new ArrayList<>();
    private List<StarsIn> starsInMovieList = new ArrayList<>();
    private List<String> distinctInColor = new ArrayList<>();
    private List<String> distinctStudio = new ArrayList<>();
    private List<MovieExec> distinctProducer = new ArrayList<>();
    private List<String> distinctStarsIn = new ArrayList<>();
    private Movie movie;

    private String movieTitle;
    private Integer movieYear;
    private Integer movieLength;
    private String movieInColor;

    private String movieExecName;
    private Integer movieExecCertN;
    private String movieExecAddress;
    private int movieExecNetWorth;

    private String movieStudio;
    private String movieStudioAddress;
    private String movieStudioPresident;
    private String movieStudioPresidentAddress;
    private Integer movieStudioPresidentNetWorth;

    private String starsInName;
    private Integer maxID;

    private int searchedMoviesYear;

    private String insertMovieResult = "No";
    private String insertStartResult = "No";
    private String insertResult = "No";

    @EJB
    private MovieDBEJBI movieDBEJBI;

    // Constructor
    public MovieDB() {
        logger.info("Start Movie DB");
        //insertResult = "No";
    }

    // Navigation

    public String editMovie(String title, Integer year) {
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

    public String addMovie() {

        setMovieTitle(null);
        setMovieYear(null);
        setMovieLength(null);
        setMovieInColor(null);
        setMovieStudio(null);
        setMovieExecName(null);
        setStarsInName(null);

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

    // Get distinct inColor
    public List<String> getDistinctInColor() {
        distinctInColor = movieDBEJBI.getDistinctInColor();

        // Debug
        logger.info("Distinct inColor : " + distinctInColor.size());
        Iterator<String> iterator = distinctInColor.iterator();
        while (iterator.hasNext()) {
            String inColor = iterator.next();
            logger.info("inColor : " + inColor);

        }
        return distinctInColor;
    }

    // Get distinct Studio
    public List<String> getDistinctStudio() {
        distinctStudio = movieDBEJBI.getDistinctStudio();
        return distinctStudio;
    }

    // Get distinct Producer
    public List<MovieExec> getDistinctProducer() {
        distinctProducer = movieDBEJBI.getDistinctProducer();
        return distinctProducer;
    }

    // Get distinct StarsIn
    public List<String> getDistinctStarsIn() {
        distinctStarsIn = movieDBEJBI.getDistinctStarsIn();
        return distinctStarsIn;
    }

    // Save Movie

    public String saveMovie() {

        // Debug
        logger.info("----Beginning Movie Add debug----");
        logger.info("Title : " + movieTitle);
        logger.info("Year : " + movieYear);
        logger.info("Length : " + movieLength);
        logger.info("In color : " + movieInColor);
        logger.info("Studio : " + movieStudio);
        logger.info("Producer CertN : " + movieExecCertN);
        logger.info("Star Name : " + starsInName);
        logger.info("----End Movie Add debug----");

        MovieExec movieExec = new MovieExec(movieExecCertN);
        Studio studio = new Studio(movieStudio);
        Movie movie = new Movie(movieTitle, movieYear, movieLength, movieInColor, movieExec, studio);
        String movieResult = movieDBEJBI.insertMovieWithQuery(movie);

        if(movieResult.equals("success")) {
            insertMovieResult = "success";
        } else {
            insertMovieResult = "error";
        }

        if(starsInName == null) {
            insertStartResult = "success";
        } else {

            // Get the latest starsIn MAX(id)
            maxID = movieDBEJBI.getMaxStarsInID();
            logger.info("Max ID Bean : " + maxID);
            maxID = maxID + 1;
            logger.info("Max ID Bean ++ : " + maxID);

            StarsIn starsIn = new StarsIn(maxID, movieTitle, movieYear, starsInName);
            String starsInResult = movieDBEJBI.insertMovieStarWithQuery(starsIn);

            if(starsInResult.equals("success")) {
                insertStartResult = "success";
            } else {
                insertStartResult = "error";
            }
        }

        if(insertMovieResult.equals("success") && insertStartResult.equals("success")) {
            insertResult = "success";
        } else {
            insertResult = "error";
        }

        return HOME;
    }



    public String getMovieTitle() {
        return movieTitle;
    }

    public void setMovieTitle(String movieTitle) {
        this.movieTitle = movieTitle;
    }

    public Integer getMovieYear() {
        return movieYear;
    }

    public void setMovieYear(Integer movieYear) {
        this.movieYear = movieYear;
    }

    public Integer getMovieLength() {
        return movieLength;
    }

    public void setMovieLength(Integer movieLength) {
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

    public Integer getMovieExecNetWorth() {
        return movieExecNetWorth;
    }

    public void setMovieExecNetWorth(Integer movieExecNetWorth) {
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

    public Integer getMovieExecCertN() {
        return movieExecCertN;
    }

    public void setMovieExecCertN(Integer movieExecCertN) {
        this.movieExecCertN = movieExecCertN;
    }

    public String getInsertMovieResult() {
        return insertMovieResult;
    }

    public void setInsertMovieResult(String insertMovieResult) {
        this.insertMovieResult = insertMovieResult;
    }

    public String getInsertStartResult() {
        return insertStartResult;
    }

    public void setInsertStartResult(String insertStartResult) {
        this.insertStartResult = insertStartResult;
    }

    public String getInsertResult() {
        return insertResult;
    }

    public void setInsertResult(String insertResult) {
        this.insertResult = insertResult;
    }
}
