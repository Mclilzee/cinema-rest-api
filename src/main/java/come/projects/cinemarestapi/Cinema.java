package come.projects.cinemarestapi;

import org.springframework.web.client.HttpServerErrorException;

import javax.management.RuntimeErrorException;
import java.util.ArrayList;
import java.util.List;

public class Cinema {

    private final int total_rows;
    private final int total_columns;
    private final List<Seat> available_seats;

    public Cinema(int total_rows, int total_columns) {
        this.total_rows = total_rows;
        this.total_columns = total_columns;
        this.available_seats = generateSeats();
    }

    public int getTotal_rows() {
        return total_rows;
    }

    public int getTotal_columns() {
        return total_columns;
    }

    public List<Seat> getAvailable_seats() {
        return available_seats;
    }

    private List<Seat> generateSeats() {
        List<Seat> seats = new ArrayList<>();

        for (int i = 1; i <= total_rows; i++) {
            for (int j = 1; j <= total_columns; j++) {
                seats.add(new Seat(i, j));
            }
        }

        return seats;
    }

    public Seat purchaseSeat(int row, int column) {

    }

    public boolean isSeatPurchased(int row, int column) {
        for (Seat seat : this.available_seats) {
            if (seat.getRow() == row && seat.getColumn() == column) {
                return false;
            }
        }

        return true;
    }
}