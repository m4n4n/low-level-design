package concert;

public class User {

    private final String userId;
    private final String name;
    private final String mailId;
    private final String phoneNumber;
    //private final List<Seat> seats;

    public User(String userId, String name, String mailId, String phoneNumber) {
        this.userId = userId;
        this.name = name;
        this.mailId = mailId;
        this.phoneNumber = phoneNumber;
        //this.seats = new HashMap<>();
    }

    public String getUserId() {
        return userId;
    }

}
