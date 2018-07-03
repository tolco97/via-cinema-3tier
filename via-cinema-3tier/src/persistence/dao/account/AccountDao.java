package persistence.dao.account;

import java.sql.Date;
import java.util.Collection;

import persistence.dto.account.AccountDto;
import persistence.hibernate.helper.DatabaseHelper;
import persistence.hibernate.helper.IDatabaseHelper;
import util.DataAccessException;
import util.DtoFactory;

public class AccountDao implements IAccountDao {

	private static IAccountDao instance;
	private IDatabaseHelper<AccountDto> accountsEntity = new DatabaseHelper<>();

	private AccountDao() {}

	public static IAccountDao getInstance() {
		if (instance == null)
			instance = new AccountDao();
		return instance;
	}

	@Override
	public AccountDto create(final String email, final String password, final String firstName, final String lastName,
			final Date dateOfbirth) throws DataAccessException {
		AccountDto accountDto = DtoFactory.createAccountDto(email, password, firstName, lastName, dateOfbirth);

		try {
			accountsEntity.create(accountDto);
		} catch (Exception e) {
			throw new DataAccessException(e.getMessage(), e);
		}

		return accountDto;
	}

	@Override
	public AccountDto read(final String email) throws DataAccessException {
		try {
			return accountsEntity.read(AccountDto.class, email);
		} catch (Exception e) {
			throw new DataAccessException(e.getMessage(), e);
		}
	}

	@Override
	public Collection<AccountDto> readAll() throws DataAccessException {
		try {
			return accountsEntity.readAll(AccountDto.class);
		} catch (Exception e) {
			throw new DataAccessException(e.getMessage(), e);
		}
	}

	@Override
	public void update(final AccountDto account) throws DataAccessException {
		try {
			accountsEntity.update(account);
		} catch (Exception e) {
			throw new DataAccessException(e.getMessage(), e);
		}
	}

	@Override
	public void delete(final AccountDto account) throws DataAccessException {
		try {
			accountsEntity.delete(account);
		} catch (Exception e) {
			throw new DataAccessException(e.getMessage(), e);
		}
	}

}
