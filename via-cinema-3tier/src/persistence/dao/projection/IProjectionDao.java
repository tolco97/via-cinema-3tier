package persistence.dao.projection;

import java.sql.Date;
import java.util.Collection;
import persistence.dto.movie.MovieDto;
import persistence.dto.projection.ProjectionDto;
import util.DataAccessException;

public interface IProjectionDao {

	ProjectionDto create(final MovieDto movie, final Date projectionStart) throws DataAccessException;

	ProjectionDto read(final long id) throws DataAccessException;

	Collection<ProjectionDto> readAll() throws DataAccessException;

	void update(final ProjectionDto proj) throws DataAccessException;

	void delete(final ProjectionDto proj) throws DataAccessException;
	
}
