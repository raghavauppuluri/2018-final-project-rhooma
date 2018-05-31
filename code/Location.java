package code;

public class Location {
    private int row;
    private int col;

    public Location(int row, int col) {
        this.row = row;
        this.col = col;
    }

    public int getCol() {
        return col;
    }

    public int getRow() {
        return row;
    }

    public void setLocation(Location otherLoc) {
        this.row = otherLoc.getRow();
        this.col = otherLoc.getCol();
    }

    public boolean equals(Location other) {
        return other.getCol() == this.getCol() && other.getRow() == this.getRow();
    }

    public String toString() {
        return " Row: " + row + " Column: " + col + " ";
    }
}
