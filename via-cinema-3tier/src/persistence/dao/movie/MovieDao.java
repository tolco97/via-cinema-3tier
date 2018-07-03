package persistence.dao.movie;

import java.util.Collection;

import persistence.dto.movie.MovieDto;
import persistence.hibernate.helper.DatabaseHelper;
import persistence.hibernate.helper.IDatabaseHelper;
import util.DataAccessException;
import util.DtoFactory;

public class MovieDao implements IMovieDao {

	private static IMovieDao instance;
	private IDatabaseHelper<MovieDto> moviesEntity = new DatabaseHelper<>();

	private MovieDao() {}

	public static IMovieDao getInstance() {
		if (instance == null)
			instance = new MovieDao();
		return instance;
	}

	@Override
	public MovieDto create(final String name, final int durationMinuites, final String genre, final String castMembers,
			final String description) throws DataAccessException {
		MovieDto movieDto = DtoFactory.createMovieDto(name, durationMinuites, genre, description, castMembers);

		try {
			moviesEntity.create(movieDto);
		} catch (Exception e) {
			throw new DataAccessException(e.getMessage(), e);
		}

		return movieDto;
	}

	@Override
	public MovieDto read(final String movieName) throws DataAccessException {
		try {
			return moviesEntity.read(MovieDto.class, movieName);
		} catch (Exception e) {
			throw new DataAccessException(e.getMessage(), e);
		}
	}

	@Override
	public Collection<MovieDto> readAll() throws DataAccessException {
		try {
			return moviesEntity.readAll(MovieDto.class);
		} catch (Exception e) {
			throw new DataAccessException(e.getMessage(), e);
		}
	}

	@Override
	public void update(final MovieDto movie) throws DataAccessException {
		try {
			moviesEntity.update(movie);
		} catch (Exception e) {
			throw new DataAccessException(e.getMessage(), e);
		}
	}

	@Override
	public void delete(final MovieDto movie) throws DataAccessException {
		try {
			moviesEntity.delete(movie);
		} catch (Exception e) {
			throw new DataAccessException(e.getMessage(), e);
		}
	}

}
