package concert;

import java.sql.Time;
import java.time.LocalDateTime;
import java.util.*;


public class Concert {
    private final String concertId = UUID.randomUUID().toString();
    private final String artistId;
    private final String venue;
    private final LocalDateTime datetime;
    private final List<Seat> seats;
    private final Queue<User> waitList;

    public Concert(String artistId, String venue, Date date, LocalDateTime datetime) {
        this.artistId = artistId;
        this.venue = venue;
        this.datetime = datetime;
        this.seats = new ArrayList<>();
        this.waitList = new LinkedList<>();
    }

    public String getConcertId() {
        return this.concertId;
    }

    public String getArtist() {
        return this.artistId;
    }

    public String getVenue() {
        return this.venue;
    }

    public LocalDateTime getDatetime() {
        return this.datetime;
    }






}
