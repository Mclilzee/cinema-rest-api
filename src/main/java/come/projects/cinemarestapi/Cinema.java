package come.projects.cinemarestapi;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

public class Cinema {

    private final int total_rows;
    private final int total_columns;
    private final List<Ticket> available_seats;
    private final List<Ticket> purchasedTickets;

    public Cinema(int total_rows, int total_columns) {
        this.total_rows = total_rows;
        this.total_columns = total_columns;
        this.available_seats = new ArrayList<>();
        this.purchasedTickets = new ArrayList<>();

        generateTickets();
    }

    public int getTotal_rows() {
        return total_rows;
    }

    public int getTotal_columns() {
        return total_columns;
    }

    public List<Ticket> getAvailable_seats() {
        return available_seats;
    }

    private void generateTickets() {
        for (int i = 1; i <= total_rows; i++) {
            for (int j = 1; j <= total_columns; j++) {
                String uniqueID = UUID.randomUUID().toString();
                Seat seat = new Seat(i, j);
                Ticket ticket = new Ticket(seat, uniqueID);
                this.available_seats.add(ticket);
            }
        }
    }

    public Ticket purchaseTicket(Seat purchaseSeat) {
        for (int i = 0; i < this.available_seats.size(); i++) {
            Ticket ticket = this.available_seats.get(i);
            Seat seat = ticket.getTicket();
            if (seat.getRow() == purchaseSeat.getRow() && seat.getColumn() == purchaseSeat.getColumn()) {
                this.available_seats.remove(ticket);
                this.purchasedTickets.add(ticket);
                return ticket;
            }
        }

        throw new TicketPurchasingException("The ticket has been already purchased!");
    }

    public ResponseEntity<Object> refundTicket(String token) {
        for (Ticket ticket : this.purchasedTickets) {
            if (ticket.getToken().equals(token)) {
                HashMap<String, Object> map = new HashMap<>();
                map.put("returned_ticket", ticket.getTicket());
                return new ResponseEntity<Object>(map, HttpStatus.ACCEPTED);
            }
        }

        return new ResponseEntity<>(new CustomErrorMessage("Wrong token!"), HttpStatus.BAD_REQUEST);
    }
}