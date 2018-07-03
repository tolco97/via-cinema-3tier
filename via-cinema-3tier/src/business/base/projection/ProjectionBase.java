package business.base.projection;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;

import business.domain.movie.Movie;
import business.domain.projection.Projection;
import persistence.dao.projection.IProjectionDao;
import persistence.dto.projection.ProjectionDto;
import util.BusinessObjectFactory;
import util.DataAccessException;
import util.DtoFactory;
import util.Validator;

public class ProjectionBase implements IProjectionBase {

	private Map<Long, Projection> projectionCache;
	private IProjectionDao projectionDao;

	public ProjectionBase(IProjectionDao projectionDao) {
		this.projectionDao = projectionDao;
		this.projectionCache = new HashMap<>();
	}

	@Override
	public synchronized Projection addProjection(Movie movie, Date projectionStart) throws DataAccessException {
		Validator.validateObjectNotNull(movie);

		Validator.validateObjectNotNull(projectionStart);

		ProjectionDto projDto = projectionDao.create(DtoFactory.createMovieDto(movie), projectionStart);

		Projection newProj = BusinessObjectFactory.createProjection(projDto);

		projectionCache.put(newProj.getId(), newProj);

		return newProj;
	}

	@Override
	public synchronized Projection getProjection(long id) throws DataAccessException {
		if (!projectionCache.containsKey(id)) {
			ProjectionDto projDto = projectionDao.read(id);

			if (projDto == null)
				throw new NoSuchElementException("Projection with id " + id + " does not exist!");

			Projection proj = BusinessObjectFactory.createProjection(projDto);

			projectionCache.put(proj.getId(), proj);

			return proj;
		}

		return projectionCache.get(id);
	}

	@Override
	public synchronized List<Projection> getAllProjections() throws DataAccessException {
		Collection<ProjectionDto> allProjections = projectionDao.readAll();

		for (ProjectionDto projDto : allProjections)
			if (!projectionCache.containsKey(projDto.getId()))
				projectionCache.put(projDto.getId(), BusinessObjectFactory.createProjection(projDto));

		return new ArrayList<>(projectionCache.values());
	}

	@Override
	public synchronized void updateProjection(Projection proj) throws DataAccessException {
		Validator.validateObjectNotNull(proj);
	
		projectionDao.update(DtoFactory.createProjectionDto(proj));
	
		projectionCache.put(proj.getId(), proj);
	}

	@Override
	public synchronized void removeProjection(Projection proj) throws DataAccessException {
		Validator.validateObjectNotNull(proj);

		projectionDao.delete(DtoFactory.createProjectionDto(proj));

		projectionCache.remove(proj.getId(), proj);
	}

}
