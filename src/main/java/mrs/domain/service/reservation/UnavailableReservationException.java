package mrs.domain.service.reservation;

@SuppressWarnings("serial")
public class UnavailableReservationException extends RuntimeException {
	public UnavailableReservationException(String message) {
		super(message);
	}
}