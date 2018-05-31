package code;

public class Rook extends Piece {
    private String ID;
    private Location loc;
    private boolean hasMoved;

    public Rook(String s, Location loc) {
        super(s, loc);
    }

    public boolean isValid(Location loc, Board b) {
        return super.HorizontalMove(loc, b) || super.VerticalMove(loc, b);
    }

    public boolean isCheck(Board b) {
        return checkVertical(b) || checkHorizontal(b);
    }
}
