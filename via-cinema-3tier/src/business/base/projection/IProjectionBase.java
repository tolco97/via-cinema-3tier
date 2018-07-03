package business.base.projection;

import java.sql.Date;
import java.util.List;

import business.domain.movie.Movie;
import business.domain.projection.Projection;
import util.DataAccessException;

public interface IProjectionBase {
	
	Projection addProjection(Movie movie, Date projectionStart) throws DataAccessException;

	Projection getProjection(long id) throws DataAccessException;

	List<Projection> getAllProjections() throws DataAccessException;

	void updateProjection(Projection proj) throws DataAccessException;

	void removeProjection(Projection proj) throws DataAccessException;
	
}
