package persistence.dao.movie;

import java.util.Collection;

import persistence.dto.movie.MovieDto;
import util.DataAccessException;

public interface IMovieDao {

	MovieDto create(final String name, final int durationMinuites, final String genre, final String castMembers,
			final String description) throws DataAccessException;

	MovieDto read(final String movieName) throws DataAccessException;

	Collection<MovieDto> readAll() throws DataAccessException;

	void update(final MovieDto movie) throws DataAccessException;

	void delete(final MovieDto movie) throws DataAccessException;

}
