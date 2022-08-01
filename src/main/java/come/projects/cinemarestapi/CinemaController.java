package come.projects.cinemarestapi;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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
    public Seat purchaseTicket(@RequestParam int row,@RequestParam int column) {
        if (row < 1 || row > 9 || column < 1 || column > 9) {
            throw new TicketPurchasingException("The number of a row or a column is out of bounds!");
        }

        return this.cinema.purchaseSeat(row, column);
    }
}
