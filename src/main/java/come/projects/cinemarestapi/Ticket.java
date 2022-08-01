package come.projects.cinemarestapi;

public class Ticket {
    private final String token;
    private final Seat seat;
    private boolean available;

    public Ticket(String token, Seat seat) {
        this.token = token;
        this.seat = seat;
        this.available = true;
    }

    public boolean isAvailable() {
        return available;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }

    public String getToken() {
        return this.token;
    }

    public Seat getSeat() {
        return this.seat;
    }
}
