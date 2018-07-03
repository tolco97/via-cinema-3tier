package persistence.dao.account;

import java.sql.Date;
import java.util.Collection;

import persistence.dto.account.AccountDto;
import util.DataAccessException;

public interface IAccountDao {

	AccountDto create(final String email, final String password, final String firstName, final String lastName,
			final Date dateOfbirth) throws DataAccessException;

	AccountDto read(final String email) throws DataAccessException;

	Collection<AccountDto> readAll() throws DataAccessException;

	void update(final AccountDto account) throws DataAccessException;

	void delete(final AccountDto account) throws DataAccessException;

}
