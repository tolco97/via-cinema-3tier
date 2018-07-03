package util;

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

public final class BusinessObjectFactory {

	private BusinessObjectFactory() {}
	
	public static Movie createMovie(MovieDto movieDto) {
		return new Movie(movieDto);
	}
	
	public static Account createAccount(AccountDto accountDto) {
		return new Account(accountDto);
	}
	
	public static Projection createProjection(ProjectionDto projDto) {
		return new Projection(projDto);
	}
	
	public static SeatBooking createSeatBooking(SeatBookingDto seatBookingDto) {
		return new SeatBooking(seatBookingDto);
	}
	
	public static List<Account> createAccountList(List<AccountDto> allAccountDto) {
		List<Account> allAccounts = new ArrayList<>(allAccountDto.size());
		
		for (AccountDto dto : allAccountDto)
			allAccounts.add(createAccount(dto));
		
		return allAccounts;
	}
	
	public static List<Movie> createMovieList(List<MovieDto> allMovieDto) {
		List<Movie> allMovies = new ArrayList<>(allMovieDto.size());
		
		for (MovieDto dto : allMovieDto)
			allMovies.add(createMovie(dto));
		
		return allMovies;
	}
	
	public static List<Projection> createProjectionList(List<ProjectionDto> allProjDto) {
		List<Projection> allProjections = new ArrayList<>(allProjDto.size());
		
		for (ProjectionDto projDto : allProjDto)
			allProjections.add(createProjection(projDto));
		
		return allProjections;
	}
	
	public static Set<SeatBooking> createSeatBookingSet(Set<SeatBookingDto> allSeatDto) {
		Set<SeatBooking> allSeats = new HashSet<>(allSeatDto.size());
		
		for (SeatBookingDto seatDto : allSeatDto)
			allSeats.add(createSeatBooking(seatDto));
		
		return allSeats;
	}
	
}
