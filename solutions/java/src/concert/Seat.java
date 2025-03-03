package concert;

public class Seat {
    private final String seatId;
    private final String seatNumber;
    private final double price;
    private final SeatType seatType;
    private SeatStatus status;

    public Seat(String seatId, String seatNumber, double price, SeatType seatType) {
        this.seatId = seatId;
        this.seatNumber = seatNumber;
        this.price = price;
        this.seatType = seatType;
        this.status = SeatStatus.AVAILABLE;
    }

    public synchronized void book(){
        if(status == SeatStatus.AVAILABLE){
            status = SeatStatus.BOOKED;
        }else{
            throw new IllegalStateException("Seat is already booked");
        }
    }

    public synchronized void cancel(){
        if(status == SeatStatus.BOOKED){
            status = SeatStatus.AVAILABLE;
        }else{
            throw new IllegalStateException("Seat is not booked");
        }
    }

    public SeatStatus getStatus() {
        return status;
    }

    public double getPrice() {
        return price;
    }
}
