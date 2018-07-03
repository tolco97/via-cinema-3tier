package business.domain.seat_booking;

import java.util.Objects;

import persistence.dto.seat_booking.SeatBookingDto;

public class SeatBooking {

	private long id;
	private int seatNumber;
	private String ownerEmail;

	public SeatBooking(int seatNumber, String ownerEmail) {
		this.seatNumber = seatNumber;
		this.ownerEmail = ownerEmail;
	}

	public SeatBooking(SeatBookingDto seatBooking) {
		this(seatBooking.getSeatNumber(), seatBooking.getOwnerEmail());
		this.id = seatBooking.getId();
	}

	public long getId() {
		return id;
	}

	public int getSeatNumber() {
		return seatNumber;
	}

	public String getOwnerEmail() {
		return ownerEmail;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (getId() ^ (getId() >>> 32));
		result = prime * result + ((getOwnerEmail() == null) ? 0 : getOwnerEmail().hashCode());
		result = prime * result + getSeatNumber();
		return result;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (!(obj instanceof SeatBooking))
			return false;
		
		SeatBooking other = (SeatBooking) obj;
		 
		boolean areEqual = other.getId() == getId() 
				&& other.getSeatNumber() == getSeatNumber() 
				&& Objects.equals(other.getOwnerEmail(), getOwnerEmail());
		
		return areEqual;
	}

	@Override
	public String toString() {
		return "SeatBooking [id=" + id + ", seatNumber=" + seatNumber + ", ownerEmail=" + ownerEmail + "]";
	}

}
