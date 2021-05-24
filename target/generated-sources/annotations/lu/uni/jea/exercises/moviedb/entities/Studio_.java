package lu.uni.jea.exercises.moviedb.entities;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Studio.class)
public abstract class Studio_ {

	public static volatile SingularAttribute<Studio, String> address;
	public static volatile SingularAttribute<Studio, MovieExec> movieExec;
	public static volatile SingularAttribute<Studio, String> name;

	public static final String ADDRESS = "address";
	public static final String MOVIE_EXEC = "movieExec";
	public static final String NAME = "name";

}

