package util;

import java.sql.Date;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import business.domain.account.Account;
import business.domain.movie.Movie;
import business.domain.projection.Projection;
import business.domain.seat_booking.SeatBooking;
import persistence.dto.account.AccountDto;
import persistence.dto.movie.MovieDto;
import persistence.dto.projection.ProjectionDto;
import persistence.dto.seat_booking.SeatBookingDto;

public final class DtoFactory {

	private DtoFactory() {}

	public static MovieDto createMovieDto(String name, int durationMinuites, String genre, String description, String castMembers) {
		return new MovieDto(name, durationMinuites, genre, description, castMembers);
	}

	public static MovieDto createMovieDto(Movie movie) {
		return new MovieDto(movie);
	}

	public static AccountDto createAccountDto(Account account) {
		return new AccountDto(account);
	}
	
	public static AccountDto createAccountDto(String email, String password, String firstName, String lastName, Date dateOfbirth) {
		return new AccountDto(email, password, firstName, lastName, dateOfbirth);
	}

	public static ProjectionDto createProjectionDto(Projection proj) {
		return new ProjectionDto(proj);
	}
	
	public static ProjectionDto createProjectionDto(MovieDto movie, Date projectionStart) {
		return new ProjectionDto(movie, projectionStart);
	}

	public static SeatBookingDto createSeatBookingDto(SeatBooking seatBooking) {
		return new SeatBookingDto(seatBooking);
	}

	public static List<AccountDto> createAccountDtoList(List<Account> allAccounts) {
		List<AccountDto> allAccountDtos = new ArrayList<>(allAccounts.size());

		for (Account account : allAccounts)
			allAccountDtos.add(createAccountDto(account));

		return allAccountDtos;
	}

	public static List<MovieDto> createMovieDtoList(List<Movie> allMovies) {
		List<MovieDto> allMovieDtos = new ArrayList<>(allMovies.size());

		for (Movie movie : allMovies)
			allMovieDtos.add(createMovieDto(movie));

		return allMovieDtos;
	}

	public static List<ProjectionDto> createProjectionDtoList(List<Projection> allProjections) {
		List<ProjectionDto> allProjectionDtos = new ArrayList<>(allProjections.size());

		for (Projection proj : allProjections)
			allProjectionDtos.add(createProjectionDto(proj));

		return allProjectionDtos;
	}

	public static Set<SeatBookingDto> createSeatBookingDtoSet(Set<SeatBooking> allSeats) {
		Set<SeatBookingDto> allSeatDtos = new HashSet<>(allSeats.size());

		for (SeatBooking seat : allSeats)
			allSeatDtos.add(createSeatBookingDto(seat));

		return allSeatDtos;
	}

}
