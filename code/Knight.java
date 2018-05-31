package code;

public class Knight extends Piece {
    private String ID;
    private Location loc;

    public Knight(String s, Location loc) {
        super(s, loc);
    }

    public boolean isValid(Location n, Board b){
        int moveRow = n.getRow();
        int moveCol = n.getCol();
        int row = this.getLocation().getRow();
        int col = this.getLocation().getCol();

        if(Math.abs(moveRow - row) == 2 &&
                Math.abs(moveCol - col) == 1 ||
                Math.abs(moveRow - row) == 1 &&
                        Math.abs(moveCol - col) == 2){
            return b.board[moveRow][moveCol] == null;
        }
            return false;
    }

    public boolean isCheck(Board b) {
        Location kingLoc = findKing(b);
        int kingRow = kingLoc.getRow();
        int kingCol = kingLoc.getCol();
        int col = this.getLocation().getCol();
        int row = this.getLocation().getRow();

        return Math.abs(row - kingRow) == 2 && Math.abs(col - kingCol) == 1 || Math.abs(row - kingRow) == 1 && Math.abs(col - kingCol) == 2;


    }
}
