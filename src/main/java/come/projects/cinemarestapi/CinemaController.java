package come.projects.cinemarestapi;

import org.springframework.web.bind.annotation.*;

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
    public Seat purchaseTicket(@RequestBody Seat seat) {
        if (seat.getRow() < 1 || seat.getRow() > 9 || seat.getColumn() < 1 || seat.getColumn() > 9) {
            throw new TicketPurchasingException("The number of a row or a column is out of bounds!");
        }

        return this.cinema.purchaseSeat(seat);
    }
}
