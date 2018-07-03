package business.base.account;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Objects;

import business.domain.account.Account;
import persistence.dao.account.IAccountDao;
import persistence.dto.account.AccountDto;
import util.BusinessObjectFactory;
import util.DataAccessException;
import util.DtoFactory;
import util.Validator;

public class AccountBase implements IAccountBase {

	private IAccountDao accountDao;
	private Map<String, Account> accountCache;

	public AccountBase(IAccountDao accountDao) {
		this.accountDao = accountDao;
		this.accountCache = new HashMap<>();
	}

	@Override
	public synchronized Account addAccount(String email, String password, String firstName, String lastName, Date dateOfbirth) 
			throws DataAccessException {
		Validator.validateEmail(email);

		Validator.validateStringNotNullOrEmpty(password);

		Validator.validateStringNotNullOrEmpty(firstName);

		Validator.validateStringNotNullOrEmpty(lastName);

		AccountDto accountDto = accountDao.create(email, password, firstName, lastName, dateOfbirth);

		Account account = BusinessObjectFactory.createAccount(accountDto);

		accountCache.put(account.getEmail(), account);

		return account;
	}

	@Override
	public synchronized Account getAccount(String email) throws DataAccessException {
		Validator.validateEmail(email);

		if (!accountCache.containsKey(email)) {
			AccountDto accountDto = accountDao.read(email);

			if (accountDto == null)
				throw new NoSuchElementException("Account with email " + email + " does not exist!");

			Account account = BusinessObjectFactory.createAccount(accountDto);

			accountCache.put(account.getEmail(), account);

			return account;
		}

		return accountCache.get(email);
	}

	@Override
	public synchronized List<Account> getAllAccounts() throws DataAccessException {
		Collection<AccountDto> allMovieDto = accountDao.readAll();

		for (AccountDto accountDto : allMovieDto)
			if (!accountCache.containsKey(accountDto.getEmail()))
				accountCache.put(accountDto.getEmail(), BusinessObjectFactory.createAccount(accountDto));

		return new ArrayList<>(accountCache.values());
	}

	@Override
	public synchronized void updateAccount(Account account) throws DataAccessException {
		Validator.validateObjectNotNull(account);

		Validator.validateEmail(account.getEmail());

		accountDao.update(DtoFactory.createAccountDto(account));

		accountCache.put(account.getEmail(), account);
	}

	@Override
	public synchronized void removeAccount(Account account) throws DataAccessException {
		Validator.validateObjectNotNull(account);

		Validator.validateEmail(account.getEmail());

		accountDao.delete(DtoFactory.createAccountDto(account));

		accountCache.remove(account.getEmail(), account);
	}

	@Override
	public synchronized boolean login(String email, String password) throws DataAccessException {
		Validator.validateEmail(email);
		
		Validator.validateStringNotNullOrEmpty(password);

		Account account = accountCache.get(email);

		if (account == null) {
			AccountDto accountDto = accountDao.read(email);

			account = BusinessObjectFactory.createAccount(accountDto);

			accountCache.put(account.getEmail(), account);
		}

		boolean loginSuccessful = Objects.equals(password, account.getPassword());

		return loginSuccessful;
	}

}
