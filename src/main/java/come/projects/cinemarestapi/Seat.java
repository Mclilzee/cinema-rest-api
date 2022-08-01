package come.projects.cinemarestapi;

public class Seat {
    private final int row;
    private final int column;
    private final int price;

    public Seat(int row, int column) {
        this.row = row;
        this.column = column;
        this.price = this.row <= 4 ? 10 : 8;
    }

    public int getRow() {
        return this.row;
    }

    public int getColumn() {
        return this.column;
    }

    public int getPrice() {
        return price;
    }
}