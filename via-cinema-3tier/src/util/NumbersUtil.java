package util;

import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public final class NumbersUtil {

	private NumbersUtil() {}

	public static Set<Integer> range(final int inclusiveMin, final int inclusiveMax) {
		if (inclusiveMin > inclusiveMax)
			throw new IllegalArgumentException("minimum value can't be larger than the maximum value");

		return IntStream.range(inclusiveMin, inclusiveMax + 1).boxed().collect(Collectors.toSet());
	}

}
