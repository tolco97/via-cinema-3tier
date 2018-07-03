package business.domain.projection;

import java.util.Date;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import business.domain.movie.Movie;
import business.domain.seat_booking.SeatBooking;
import persistence.dto.projection.ProjectionDto;
import util.BusinessObjectFactory;
import util.NumbersUtil;

public class Projection {

	private long id;
	private Movie movie;
	private Date projectionStart;
	private Set<SeatBooking> seatBookings;

	public Projection(Movie movie, Date projectionStart) {
		this.movie = movie;
		this.projectionStart = projectionStart;
		this.seatBookings = new HashSet<>(0);
	}

	public Projection(ProjectionDto projection) {
		this(BusinessObjectFactory.createMovie(projection.getMovie()), projection.getProjectionStart());
		this.id = projection.getId();
		this.seatBookings = BusinessObjectFactory.createSeatBookingSet(projection.getSeatBookings());
	}

	public boolean addSeatBooking(SeatBooking seatBooking) {
		return (isAvailable(seatBooking) ? seatBookings.add(seatBooking) : false);
	}

	public boolean removeSeatBooking(SeatBooking seatBooking) {
		return seatBookings
				.removeIf((currentSeatBooking) -> currentSeatBooking.getSeatNumber() == seatBooking.getSeatNumber());
	}

	public boolean isAvailable(SeatBooking otherSeatBooking) {
		return seatBookings.stream().allMatch((seatBooking) -> seatBooking.getSeatNumber() != otherSeatBooking.getSeatNumber());
	}

	public Set<Integer> getAllAvailableSeatNumbers() {
		Set<Integer> availableSeatNumbers = NumbersUtil.range(1, 30);

		availableSeatNumbers.removeIf((seatNumber) -> seatBookings.stream()
				.anyMatch((seatBooking) -> seatBooking.getSeatNumber() == seatNumber));

		return availableSeatNumbers;
	}

	public long getId() {
		return id;
	}

	public Movie getMovie() {
		return movie;
	}

	public Date getProjectionStart() {
		return projectionStart;
	}

	public Set<SeatBooking> getSeatBookings() {
		return seatBookings;
	}

	@Override
	public boolean equals(Object obj) {
		if (!(obj instanceof Projection))
			return false;

		Projection other = (Projection) obj;

		boolean areEqual = other.getId() == getId() && Objects.equals(other.getSeatBookings(), getSeatBookings())
				&& Objects.equals(other.getMovie(), getMovie())
				&& Objects.equals(other.getProjectionStart(), getProjectionStart());

		return areEqual;
	}

	@Override
	public String toString() {
		return "Projection [id= " + id + ", movie= " + movie + ", projectionStart= " + projectionStart
				+ ", bookedSeats= " + seatBookings + "]";
	}

}
