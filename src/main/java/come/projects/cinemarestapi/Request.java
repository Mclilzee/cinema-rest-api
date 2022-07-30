package come.projects.cinemarestapi;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Request {
    private final Cinema cinema;

    public Request() {
        this.cinema = new Cinema(9, 9);
    }

    @GetMapping("/seats")
    public Cinema getCinema() {
        return cinema;
    }
}
