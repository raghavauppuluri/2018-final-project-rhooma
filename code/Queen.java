package code;

public class Queen extends Piece {
    private String ID;
    private Location loc;

    public Queen(String s, Location loc) {
        super(s, loc);
    }

    public boolean isValid(Location loc, Board b) {
        return super.DiagonalMove(loc, b) || super.HorizontalMove(loc, b) || super.VerticalMove(loc, b);
    }

    public boolean isCheck(Board b) {
        return super.checkDiagonal(b) || super.checkHorizontal(b) || super.checkVertical(b);
    }
}
