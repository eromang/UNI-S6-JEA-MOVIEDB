package lu.uni.jea.exercises.moviedb.entities;

import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Movie.class)
public abstract class Movie_ {

	public static volatile SingularAttribute<Movie, Studio> studio;
	public static volatile SingularAttribute<Movie, MovieExec> movieExec;
	public static volatile SingularAttribute<Movie, Integer> year;
	public static volatile SingularAttribute<Movie, Integer> length;
	public static volatile SingularAttribute<Movie, String> inColor;
	public static volatile SingularAttribute<Movie, String> title;
	public static volatile ListAttribute<Movie, StarsIn> starsIn;

	public static final String STUDIO = "studio";
	public static final String MOVIE_EXEC = "movieExec";
	public static final String YEAR = "year";
	public static final String LENGTH = "length";
	public static final String IN_COLOR = "inColor";
	public static final String TITLE = "title";
	public static final String STARS_IN = "starsIn";

}

