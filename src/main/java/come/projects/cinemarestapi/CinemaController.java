package come.projects.cinemarestapi;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
public class CinemaController {
    private final Cinema cinema;

    public CinemaController() {
        this.cinema = new Cinema(9, 9);
    }

    @GetMapping("/seats")
    public Cinema getCinema() {
        return cinema;
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
}
