package persistence.dto.projection;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.DynamicUpdate;

import business.domain.projection.Projection;
import persistence.dto.movie.MovieDto;
import persistence.dto.seat_booking.SeatBookingDto;
import util.DtoFactory;

@Entity
@Table(name = "projections")
@DynamicUpdate(value = true)
public class ProjectionDto implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	@OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
	private MovieDto movie;

	@Column(name = "projection_start")
	private Date projectionStart;

	@OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
	private Set<SeatBookingDto> seatBookings;

	public ProjectionDto() {}

	// Used in DAO
	public ProjectionDto(MovieDto movie, Date projectionStart) {
		setMovie(movie);
		setProjectionStart(projectionStart);
		setSeatBookings(new HashSet<>(0));
	}

	// Used in the business logic
	public ProjectionDto(Projection projection) {
		this(DtoFactory.createMovieDto(projection.getMovie()), projection.getProjectionStart());
		setId(projection.getId());
		setSeatBookings(DtoFactory.createSeatBookingDtoSet(projection.getSeatBookings()));
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public MovieDto getMovie() {
		return movie;
	}

	public void setMovie(MovieDto movie) {
		this.movie = movie;
	}

	public Date getProjectionStart() {
		return projectionStart;
	}

	public void setProjectionStart(Date projectionStart) {
		this.projectionStart = projectionStart;
	}

	public Set<SeatBookingDto> getSeatBookings() {
		return seatBookings;
	}

	public void setSeatBookings(Set<SeatBookingDto> bookedSeats) {
		this.seatBookings = bookedSeats;
	}

	@Override
	public String toString() {
		return "ProjectionDto [id=" + id + ", movie=" + movie + ", projectionStart=" + projectionStart
				+ ", bookedSeats=" + seatBookings + "]";
	}

}
