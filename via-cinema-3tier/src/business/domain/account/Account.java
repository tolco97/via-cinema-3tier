package business.domain.account;

import java.sql.Date;
import java.util.Objects;

import persistence.dto.account.AccountDto;

public class Account {

	private String email, password;
	private String firstName, lastName;
	private Date dateOfbirth;

	public Account(String email, String password, String firstName, String lastName, Date dateOfbirth) {
		this.email = email;
		this.password = password;
		this.firstName = firstName;
		this.lastName = lastName;
		this.dateOfbirth = dateOfbirth;
	}

	public Account(AccountDto account) {
		this(account.getEmail(), account.getPassword(), account.getFirstName(), account.getLastName(),
				account.getDateOfbirth());
	}

	public String getEmail() {
		return email;
	}

	public String getPassword() {
		return password;
	}

	public String getFirstName() {
		return firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public Date getDateOfbirth() {
		return dateOfbirth;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((getDateOfbirth() == null) ? 0 : getDateOfbirth().hashCode());
		result = prime * result + ((getEmail() == null) ? 0 : getEmail().hashCode());
		result = prime * result + ((getFirstName() == null) ? 0 : getFirstName().hashCode());
		result = prime * result + ((getLastName() == null) ? 0 : getLastName().hashCode());
		result = prime * result + ((getPassword() == null) ? 0 : getPassword().hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (!(obj instanceof Account))
			return false;

		Account other = (Account) obj;

		boolean areEqual = Objects.equals(other.getEmail(), getEmail()) && Objects.equals(other.getPassword(), getPassword())
				&& Objects.equals(other.getFirstName(), getFirstName()) && Objects.equals(other.getLastName(), getLastName())
				&& Objects.equals(other.getDateOfbirth(), getDateOfbirth());

		return areEqual;
	}

	@Override
	public String toString() {
		return "Account [email= " + email + ", password= " + password + ", firstName= " + firstName + ", lastName= "
				+ lastName + " , dateOfbirth= " + dateOfbirth + "]";
	}
	
}
