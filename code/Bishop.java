package code;

public class Bishop extends Piece {
    private String ID;
    private Location loc;


    public Bishop(String s, Location loc) {
        super(s, loc);
    }
    public boolean isValid(Location loc, Board b){
        return super.DiagonalMove(loc, b);
    }
    public boolean isCheck(Board b) {
        return super.checkDiagonal(b);
    }

}
