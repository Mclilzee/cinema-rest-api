package come.projects.cinemarestapi;

public class Ticket {
    private final Seat ticket;
    private final String token;

    public Ticket(Seat ticket, String token) {
        this.ticket = ticket;
        this.token = token;
    }

    public String getToken() {
        return token;
    }

    public Seat getTicket() {
        return ticket;
    }
}
