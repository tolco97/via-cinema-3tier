package util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public final class Validator {

	private Validator() {}

	private static final Pattern VALID_EMAIL_ADDRESS_REGEX = Pattern
			.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);

	public static void validateStringNotNullOrEmpty(String str) {
		if (str == null)
			throw new NullPointerException("String is null");
		
		if (str.isEmpty())
			throw new IllegalArgumentException("String is empty");
	}

	public static void validateObjectNotNull(Object obj) {
		if (obj == null)
			throw new NullPointerException("Object is null");
	}

	public static void validateEmail(String email) {
		validateStringNotNullOrEmpty(email);
		
		Matcher matcher = VALID_EMAIL_ADDRESS_REGEX.matcher(email);
		
		boolean isValid = matcher.find();
		
		if (!isValid)
			throw new IllegalArgumentException(email + " does not fit the email pattern!");
	}
	
	public static void validateMovieDuration(int durationMinuites) {
		if (durationMinuites <= 0)
			throw new IllegalArgumentException("Movie duration needs to be a positive number above 0");
	}

}
