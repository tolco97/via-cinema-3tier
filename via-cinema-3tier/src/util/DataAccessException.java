package util;

public class DataAccessException extends Exception {

	private static final long serialVersionUID = 1L;

	public DataAccessException(String message, Throwable t) {
		super(message, t);
	}

}
