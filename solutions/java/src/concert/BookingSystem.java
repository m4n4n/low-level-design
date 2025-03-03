package concert;

import java.time.LocalDateTime;
import java.util.ConcurrentModificationException;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

public class BookingSystem {

    private final HashMap<String, Concert> concerts;
    private final ConcurrentHashMap<String, Booking> bookings;
    private final Object lock = new Object();
    private NotificationFactory notificationFactory;

    private BookingSystem(NotificationFactory notificationFactory) {
        this.concerts = new HashMap<>();
        this.bookings = new ConcurrentHashMap<>();
        this.notificationFactory = notificationFactory;
    }

    public void addConcert(Concert concert) {
        concerts.put(concert.getConcertId(), concert);
    }

    public Concert getConcert(String concertId) {
        return concerts.get(concertId);
    }

    public List<Concert> searchConcert(String artistId, String venue, LocalDateTime localDateTime){
        return concerts.values().stream()
                .filter(concert -> concert.getArtist().equals(artistId) && concert.getVenue().equals(venue) &&
                        concert.getDatetime().equals(localDateTime))
                .collect(Collectors.toList());


    }

    public Booking bookTickets(String concertId, User user, List<Seat> seats) {

        synchronized (lock){
            for(Seat seat : seats){
                if(seat.getStatus() != SeatStatus.AVAILABLE){
                    throw new IllegalStateException("Seat status is not AVAILABLE");
                }
            }

            seats.forEach(Seat::book);

            String bookingId = "BKG" + UUID.randomUUID().toString();
            Booking booking = new Booking(
                    bookingId,
                    user,
                    concertId,
                    seats
            );

            //process Payment

            booking.confirmBooking();
            notificationFactory.getNotificationService(NotificationType.EMAIL)
                    .sendBookingCompleted(booking, user);


            return booking;
        }
    }

    public void cancelBooking(String bookingId) {
        Booking booking = bookings.get(bookingId);
        if(booking != null){
            booking.cancelBooking();
            bookings.remove(bookingId);
        }
    }

}
