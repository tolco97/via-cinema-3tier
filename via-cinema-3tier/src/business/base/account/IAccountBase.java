package business.base.account;

import java.sql.Date;
import java.util.List;

import business.domain.account.Account;
import util.DataAccessException;

public interface IAccountBase {

	Account addAccount(String email, String password, String firstName, String lastName, Date dateOfbirth)
			throws DataAccessException;

	Account getAccount(String email) throws DataAccessException;

	List<Account> getAllAccounts() throws DataAccessException;

	void updateAccount(Account account) throws DataAccessException;

	void removeAccount(Account account) throws DataAccessException;

	boolean login(String email, String password) throws DataAccessException;

}
