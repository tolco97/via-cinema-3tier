package persistence.dto.account;

import java.io.Serializable;
import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.DynamicUpdate;

import business.domain.account.Account;

@Entity
@Table(name = "accounts")
@DynamicUpdate(value = true)
public class AccountDto implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "email")
	private String email;

	@Column(name = "password")
	private String password;

	@Column(name = "first_name")
	private String firstName;

	@Column(name = "last_name")
	private String lastName;

	@Column(name = "date_of_birth")
	private Date dateOfbirth;

	public AccountDto() {}
	
	// Used in DAO
	public AccountDto(String email, String password, String firstName, String lastName, Date dateOfbirth) {
		setEmail(email);
		setPassword(password);
		setFirstName(firstName);
		setLastName(lastName);
		setDateOfbirth(dateOfbirth);
	}

	// Used in the business logic
	public AccountDto(Account account) {
		this(account.getEmail(), account.getPassword(), account.getFirstName(), account.getLastName(), account.getDateOfbirth());
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public Date getDateOfbirth() {
		return dateOfbirth;
	}

	public void setDateOfbirth(Date dateOfbirth) {
		this.dateOfbirth = dateOfbirth;
	}

	@Override
	public String toString() {
		return "AccountDto [email=" + email + ", password=" + password + ", firstName=" + firstName + ", lastName="
				+ lastName + ", dateOfbirth=" + dateOfbirth + "]";
	}

}
