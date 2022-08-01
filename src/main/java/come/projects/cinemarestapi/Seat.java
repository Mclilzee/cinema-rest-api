package come.projects.cinemarestapi;

public class Seat {
    private final int row;
    private final int column;
    private final int price;
    private boolean purchased;

    public Seat(int row, int column) {
        this.row = row;
        this.column = column;
        this.purchased = false;
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

    public boolean isPurchased() {
        return purchased;
    }

    public void setPurchased(boolean purchased) {
        this.purchased = purchased;
    }
}