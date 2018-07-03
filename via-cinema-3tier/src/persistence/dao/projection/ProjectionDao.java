package persistence.dao.projection;

import java.sql.Date;
import java.util.Collection;

import persistence.dto.movie.MovieDto;
import persistence.dto.projection.ProjectionDto;
import persistence.hibernate.helper.DatabaseHelper;
import persistence.hibernate.helper.IDatabaseHelper;
import util.DataAccessException;
import util.DtoFactory;

public class ProjectionDao implements IProjectionDao {

	private static IProjectionDao instance;
	private IDatabaseHelper<ProjectionDto> projectionsEntity = new DatabaseHelper<>();

	private ProjectionDao() {}

	public static IProjectionDao getInstance() {
		if (instance == null)
			instance = new ProjectionDao();
		return instance;
	}

	@Override
	public ProjectionDto create(final MovieDto movie, final Date projectionStart) throws DataAccessException {
		ProjectionDto projectionDto = DtoFactory.createProjectionDto(movie, projectionStart);

		try {
			Long generatedId = (Long) projectionsEntity.create(projectionDto);
			projectionDto.setId(generatedId);
		} catch (Exception e) {
			throw new DataAccessException(e.getMessage(), e);
		}

		return projectionDto;
	}

	@Override
	public ProjectionDto read(final long id) throws DataAccessException {
		try {
			return projectionsEntity.read(ProjectionDto.class, id);
		} catch (Exception e) {
			throw new DataAccessException(e.getMessage(), e);
		}
	}

	@Override
	public Collection<ProjectionDto> readAll() throws DataAccessException {
		try {
			return projectionsEntity.readAll(ProjectionDto.class);
		} catch (Exception e) {
			throw new DataAccessException(e.getMessage(), e);
		}
	}

	@Override
	public void update(final ProjectionDto proj) throws DataAccessException {
		try {
			projectionsEntity.update(proj);
		} catch (Exception e) {
			throw new DataAccessException(e.getMessage(), e);
		}
	}

	@Override
	public void delete(final ProjectionDto proj) throws DataAccessException {
		try {
			projectionsEntity.delete(proj);
		} catch (Exception e) {
			throw new DataAccessException(e.getMessage(), e);
		}
	}

}
