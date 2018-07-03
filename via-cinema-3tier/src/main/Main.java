package main;

import business.base.account.AccountBase;
import business.base.account.IAccountBase;
import business.base.movie.IMovieBase;
import business.base.movie.MovieBase;
import business.base.projection.IProjectionBase;
import business.base.projection.ProjectionBase;
import persistence.dao.account.AccountDao;
import persistence.dao.movie.MovieDao;
import persistence.dao.projection.ProjectionDao;
import util.DataAccessException;

public class Main {
	
	public static void main(String[] args) throws DataAccessException {
		IMovieBase movieBase = new MovieBase(MovieDao.getInstance());
		IAccountBase accountBase = new AccountBase(AccountDao.getInstance());
		IProjectionBase projectionBase = new ProjectionBase(ProjectionDao.getInstance());
		
		System.out.println(movieBase.getAllMovies());
		System.out.println(accountBase.getAllAccounts());
		System.out.println(projectionBase.getAllProjections());
		
	}

}
