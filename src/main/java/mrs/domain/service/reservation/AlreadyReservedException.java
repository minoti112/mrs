package mrs.domain.service.reservation;

@SuppressWarnings("serial")
public class AlreadyReservedException extends RuntimeException {
	public AlreadyReservedException(String message) {
		super(message);
	}
}