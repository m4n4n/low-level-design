package concert;


public interface NotificationService {

    void sendBookingCompleted(Booking booking, User user);
    void sendBookingCancelled(Booking booking, User user);
}
