package come.projects.cinemarestapi;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

@RestController
public class CinemaController {
    private final Cinema cinema;
    private final String MANAGER_PASSWORD = "super_secret";

    public CinemaController() {
        this.cinema = new Cinema(9, 9);

    }

    @GetMapping("/seats")
    public ResponseEntity<Object> getAvailableSeats() {
        Map<String, Object> map = new LinkedHashMap<>();
        map.put("total_rows", this.cinema.getTotalRows());
        map.put("total_columns", this.cinema.getTotalColumns());

        List<Object> availableSeats = new ArrayList<>();
        map.put("available_seats", availableSeats);

        for (Ticket ticket : this.cinema.getTickets().values()) {
            if (ticket.isAvailable()) {
                Map<String, Object> seat = new LinkedHashMap<>();
                seat.put("token", ticket.getToken());
                seat.put("ticket", ticket.getSeat());
                availableSeats.add(seat);
            }
        }

        return new ResponseEntity<>(map, HttpStatus.OK);
    }

    @PostMapping("/purchase")
    public ResponseEntity<Object> purchaseTicket(@RequestBody Seat seat) {
        if (seat.getRow() < 1 || seat.getRow() > 9 || seat.getColumn() < 1 || seat.getColumn() > 9) {
            Map<String, String> errorMessage = new HashMap<>();
            errorMessage.put("error", "The number of a row or a column is out of bounds!");
            return new ResponseEntity<>(errorMessage, HttpStatus.BAD_REQUEST);
        }

        return this.cinema.purchaseTicket(seat);
    }

    @PostMapping("/return")
    public ResponseEntity<Object> refundTicket(@RequestBody HashMap<String, String> token) {
        return this.cinema.refundTicket(token.get("token"));
    }

    @PostMapping("/stats")
    public ResponseEntity<Object> getStats(@RequestParam String password) {
        if (Objects.equals(password, this.MANAGER_PASSWORD)) {
            Map<String, Integer> map = new LinkedHashMap<>();
            generateStatistics(map);

            return new ResponseEntity<>(map, HttpStatus.OK);
        }

        return new ResponseEntity<>(new CustomErrorMessage("The password is wrong!"), HttpStatus.BAD_REQUEST);
    }

    private void generateStatistics(Map<String, Integer> map) {
        int income = 0;
        int available = 0;
        int purchased = 0;

        for (Ticket ticket : this.cinema.getTickets().values()) {
            if (!ticket.isAvailable()) {
                income += ticket.getSeat().getPrice();
                purchased++;
            } else {
                available++;
            }
        }

        map.put("current_income", income);
        map.put("number_of_available_seats", available);
        map.put("number_of_purchased_tickets", purchased);
    }

}
