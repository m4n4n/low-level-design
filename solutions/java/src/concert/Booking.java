package concert;

import java.util.List;

public class Booking {

    private final String bookingId;
    private final User user;
    private final String concertId;
    private final double finalPrice;
    private final List<Seat> seats;
    private BookingStatus status;

    public Booking(String bookingId, User user, String concertId, List<Seat> seats) {
        this.bookingId = bookingId;
        this.user = user;
        this.concertId = concertId;
        this.finalPrice = calculatePrice();
        this.seats = seats;
        this.status = BookingStatus.PENDING;
    }

    private double calculatePrice() {
        return seats.stream().mapToDouble(seat -> seat.getPrice()).sum();
    }

    public void confirmBooking() {
        if(status == BookingStatus.PENDING) {
            status = BookingStatus.CONFIRMED;
        }
        // notify
    }

    public void cancelBooking() {
        if(status == BookingStatus.CONFIRMED) {
            status = BookingStatus.CANCELLED;
            for(Seat seat : seats) {
                seat.cancel();
            }
        }
        // notify
    }
    public String getBookingId() {
        return bookingId;
    }

    public User getUser() {
        return user;
    }

    public List<Seat> getSeats() {
        return seats;
    }

    public double getFinalPrice() {
        return finalPrice;
    }

    public BookingStatus getStatus() {
        return status;
    }
}
