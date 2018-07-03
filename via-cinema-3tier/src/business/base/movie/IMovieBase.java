package business.base.movie;

import java.util.List;

import business.domain.movie.Movie;
import util.DataAccessException;

public interface IMovieBase {

	Movie addMovie(String name, int durationMinuites, String genre, String castMembers, String description)
			throws DataAccessException;

	Movie getMovie(String movieName) throws DataAccessException;

	List<Movie> getAllMovies() throws DataAccessException;

	void updateMovie(Movie movie) throws DataAccessException;

	void removeMovie(Movie movie) throws DataAccessException;
	
}
