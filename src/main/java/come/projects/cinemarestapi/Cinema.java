package come.projects.cinemarestapi;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.*;

public class Cinema {

    private final int total_rows;
    private final int total_columns;
    private final List<Ticket> available_seats;

    public Cinema(int total_rows, int total_columns) {
        this.total_rows = total_rows;
        this.total_columns = total_columns;
        this.available_seats = new ArrayList<>();

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
                Ticket ticket = new Ticket(uniqueID, seat);
                this.available_seats.add(ticket);
            }
        }
    }

    public ResponseEntity<Object> purchaseTicket(Seat seat) {
        Ticket ticket = findSeatTicket(seat);
        if (ticket != null && ticket.isAvailable()) {
            ticket.setAvailable(false);
            HashMap<String, Object> map = new HashMap<>();
            map.put("ticket", ticket.getSeat());
            map.put("token", ticket.getToken());
            return new ResponseEntity<>(map, HttpStatus.ACCEPTED);
        } else {
            return new ResponseEntity<>(new CustomErrorMessage("The ticket has been already purchased!"), HttpStatus.BAD_REQUEST);

        }
    }

    private Ticket findSeatTicket(Seat seatNumber) {
        for (Ticket ticket : this.available_seats) {
            Seat seat = ticket.getSeat();
            if (seat.getRow() == seatNumber.getRow() && seat.getColumn() == seatNumber.getColumn()) {
                return ticket;
            }
        }

        return null;
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