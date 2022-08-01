package come.projects.cinemarestapi;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

public class Cinema {

    private final int totalRows;
    private final int totalColumns;
    private final Map<String, Ticket> tickets;

    public Cinema(int totalRows, int totalColumns) {
        this.totalRows = totalRows;
        this.totalColumns = totalColumns;
        this.tickets = new ConcurrentHashMap<>();

        generateTickets();
    }

    public int getTotalRows() {
        return totalRows;
    }

    public int getTotalColumns() {
        return totalColumns;
    }

    public Map<String, Ticket> getTickets() {
        return tickets;
    }

    private void generateTickets() {
        for (int i = 1; i <= totalRows; i++) {
            for (int j = 1; j <= totalColumns; j++) {
                String token = UUID.randomUUID().toString();
                Seat seat = new Seat(i, j);
                Ticket ticket = new Ticket(token, seat);
                this.tickets.put(token, ticket);
            }
        }
    }

    public ResponseEntity<Object> purchaseTicket(Seat seat) {
        Ticket ticket = findSeatTicket(seat);
        if (ticket != null && ticket.isAvailable()) {
            ticket.setAvailable(false);
            Map<String, Object> map = new LinkedHashMap<>();
            map.put("token", ticket.getToken());
            map.put("ticket", ticket.getSeat());
            return new ResponseEntity<>(map, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(new CustomErrorMessage("The ticket has been already purchased!"), HttpStatus.BAD_REQUEST);

        }
    }

    private Ticket findSeatTicket(Seat seatNumber) {
        for (Ticket ticket : this.tickets.values()) {
            Seat seat = ticket.getSeat();
            if (seat.getRow() == seatNumber.getRow() && seat.getColumn() == seatNumber.getColumn()) {
                return ticket;
            }
        }

        return null;
    }

    public ResponseEntity<Object> refundTicket(String token) {
        Ticket ticket = tickets.get(token);
        if (ticket != null && !ticket.isAvailable()) {
            ticket.setAvailable(true);
            HashMap<String, Object> map = new HashMap<>();
            map.put("returned_ticket", ticket.getSeat());
            return new ResponseEntity<>(map, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(new CustomErrorMessage("Wrong token!"), HttpStatus.BAD_REQUEST);
        }
    }
}