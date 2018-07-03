package persistence.dto.seat_booking;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.DynamicUpdate;

import business.domain.seat_booking.SeatBooking;

@Entity
@Table(name = "seat_bookings")
@DynamicUpdate(value = true)
public class SeatBookingDto implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	@Column(name = "seat_number")
	private int seatNumber;

	@Column(name = "owner_email")
	private String ownerEmail;

	public SeatBookingDto() {}

	// Used in DAO
	public SeatBookingDto(int seatNumber, String ownerEmail) {
		setSeatNumber(seatNumber);
		setOwnerEmail(ownerEmail);
	}

	// Used in the business logic
	public SeatBookingDto(SeatBooking seatBooking) {
		this(seatBooking.getSeatNumber(), seatBooking.getOwnerEmail());
		setId(seatBooking.getId());
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getOwnerEmail() {
		return ownerEmail;
	}

	public void setOwnerEmail(String ownerEmail) {
		this.ownerEmail = ownerEmail;
	}

	public int getSeatNumber() {
		return seatNumber;
	}

	public void setSeatNumber(int seatNumber) {
		this.seatNumber = seatNumber;
	}

	@Override
	public String toString() {
		return "SeatBookingDto [id=" + id + ", seatNumber=" + seatNumber + ", ownerEmail=" + ownerEmail + "]";
	}

}
