package come.projects.cinemarestapi;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class Cinema {

    private final int total_rows;
    private final int total_columns;
    private final List<Ticket> available_seats;

    public Cinema(int total_rows, int total_columns) {
        this.total_rows = total_rows;
        this.total_columns = total_columns;
        this.available_seats = generateTickets();
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

    private List<Ticket> generateTickets() {
        List<Ticket> tickets = new ArrayList<>();

        for (int i = 1; i <= total_rows; i++) {
            for (int j = 1; j <= total_columns; j++) {
                Seat seat = new Seat(i, j);
                Ticket ticket = new Ticket(seat, UUID.randomUUID().toString());
                tickets.add(ticket);
            }
        }

        return tickets;
    }

    public Seat purchaseSeat(Seat purchaseSeat) {
//        for (int i = 0; i < this.available_seats.size(); i++) {
//            Seat seat = this.available_seats.get(i);
//            if (seat.getRow() == purchaseSeat.getRow() && seat.getColumn() == purchaseSeat.getColumn()) {
//                this.available_seats.remove(seat);
//                return seat;
//            }
//        }

        throw new TicketPurchasingException("The ticket has been already purchased!");
    }
}