package business.base.movie;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;

import business.domain.movie.Movie;
import persistence.dao.movie.IMovieDao;
import persistence.dto.movie.MovieDto;
import util.BusinessObjectFactory;
import util.DataAccessException;
import util.DtoFactory;
import util.Validator;

public class MovieBase implements IMovieBase {

	private IMovieDao movieDao;
	private Map<String, Movie> movieCache;

	public MovieBase(IMovieDao movieDao) {
		this.movieDao = movieDao;
		this.movieCache = new HashMap<>();
	}

	@Override
	public synchronized Movie addMovie(String name, int durationMinuites, String genre, String castMembers, String description) 
			throws DataAccessException {
		Validator.validateStringNotNullOrEmpty(name);

		Validator.validateMovieDuration(durationMinuites);

		Validator.validateStringNotNullOrEmpty(genre);

		Validator.validateStringNotNullOrEmpty(castMembers);

		Validator.validateStringNotNullOrEmpty(description);

		MovieDto movieDto = movieDao.create(name, durationMinuites, genre, castMembers, description);

		Movie movie = BusinessObjectFactory.createMovie(movieDto);

		movieCache.put(movie.getName(), movie);

		return movie;
	}

	@Override
	public synchronized Movie getMovie(String movieName) throws DataAccessException {
		Validator.validateStringNotNullOrEmpty(movieName);

		if (!movieCache.containsKey(movieName)) {
			MovieDto movieDto = movieDao.read(movieName);

			if (movieDto == null) throw new NoSuchElementException("Movie with name " + movieName + " does not exist!");

			Movie movie = BusinessObjectFactory.createMovie(movieDto);

			movieCache.put(movie.getName(), movie);

			return movie;
		}

		return movieCache.get(movieName);
	}

	@Override
	public synchronized List<Movie> getAllMovies() throws DataAccessException {
		Collection<MovieDto> allMovies = movieDao.readAll();

		for (MovieDto movieDto : allMovies)
			if (!movieCache.containsKey(movieDto.getName()))
				movieCache.put(movieDto.getName(), BusinessObjectFactory.createMovie(movieDto));

		return new ArrayList<>(movieCache.values());
	}

	@Override
	public synchronized void updateMovie(Movie movie) throws DataAccessException {
		Validator.validateObjectNotNull(movie);

		movieDao.update(DtoFactory.createMovieDto(movie));

		movieCache.put(movie.getName(), movie);
	}

	@Override
	public synchronized void removeMovie(Movie movie) throws DataAccessException {
		Validator.validateObjectNotNull(movie);

		movieDao.delete(DtoFactory.createMovieDto(movie));

		movieCache.remove(movie.getName(), movie);
	}

}
