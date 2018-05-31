package code;

public class King extends Piece {
    private String ID;
    private Location loc;
    private boolean notChecked = true;
    private boolean hasMoved = false;
    private boolean hasCastled = false;

    public King(String s, Location loc) {
        super(s, loc);
    }

    public boolean isValid(Location move, Board b) {
        Board fakeB = new Board();
        fakeB.boardClone(b);
        int moveRow = move.getRow();
        int moveCol = move.getCol();
        int col = this.getLocation().getCol();
        int row = this.getLocation().getRow();

        if(fakeB.board[moveRow][moveCol] != null && this.getID().charAt(0) == fakeB.board[moveRow][moveCol].getID().charAt(0)) {
            return false;
        }

        boolean movement = Math.abs(moveRow - row) == 1 && Math.abs(moveCol - col) == 0 ||
                Math.abs(moveRow - row) == 0 && Math.abs(moveCol - col) == 1 ||
                Math.abs(moveRow - row) == 1 && Math.abs(moveCol - col) == 1;

        if(movement) {
            return canCheck(move,fakeB);
        }
        else {
            return false;
        }
    }

    public boolean canCheck(Location move, Board b) {
        int moveRow = move.getRow();
        int moveCol = move.getCol();
        King k = new King(this.getID(), this.getLocation());
        Board fakeB = new Board();
        fakeB.boardClone(b);

        if(fakeB.board[moveRow][moveCol] != null && this.getID().charAt(0) == fakeB.board[moveRow][moveCol].getID().charAt(0)) {
            return false;
        }

        k.changeLocation(k.getLocation(), fakeB);
        for(int r = 0;r < fakeB.board.length; r++) {
            for (int c = 0; c < fakeB.board[0].length; c++) {
                if(fakeB.board[r][c] != null && this.getID().charAt(0) != fakeB.board[r][c].getID().charAt(0)) {
                    if(fakeB.board[r][c].isCheck(fakeB)) {
                        return false;
                    }
                }
            }
        }
        return true;
    }

    public boolean getHasCastled() {
        return this.hasCastled;
    }

    public void move(Location loc, Board b) {
        if(!getHasCastled() && canCheck(loc, b)) {
            castle(loc, b);//if location and castling rules are broken castling will not occur
            return;
        }
        else {
            System.out.println("INCORRECT CASTLING");
        }
        if (isValid(loc, b)) {
            changeLocation(loc, b);
            this.hasMoved = true;
        }
        else {
            System.out.println("INCORRECT MOVE");
        }
    }

    public void checkKing() {
        this.notChecked = false;
    }

    public void castle(Location move, Board b) {
        Location WKkingMoveLoc = new Location(0,1);
        Location WKrookMoveLoc = new Location(0,2);
        Location WQkingMoveLoc = new Location(0,5);
        Location WQrookMoveLoc = new Location(0,4);

        Location BKkingMoveLoc = new Location(7,1);
        Location BKrookMoveLoc = new Location(7,2);
        Location BQkingMoveLoc = new Location(7,5);
        Location BQrookMoveLoc = new Location(7,4);

        if(this.getID().charAt(0) == 'w') {
            if(move.equals(WKkingMoveLoc)) {
                if (KingSide(b)) {
                    Rook r = (Rook) b.getPiece(new Location(0,0));
                    changeLocation(WKkingMoveLoc, b);
                    r.changeLocation(WKrookMoveLoc, b);
                    this.hasCastled = true;
                }
            }
            else if(move.equals(WQkingMoveLoc)) {
                if(QueenSide(b)) {
                    Rook r = (Rook) b.getPiece(new Location(0,7));
                    this.changeLocation(WQkingMoveLoc,b);
                    r.changeLocation(WQrookMoveLoc,b);
                    this.hasCastled = true;
                }
            }
        }
        else {
            if(move.equals(BKkingMoveLoc)) {
                if (KingSide(b)) {
                    Rook r = (Rook) b.getPiece(new Location(7,0));
                    changeLocation(BKkingMoveLoc, b);
                    r.changeLocation(BKrookMoveLoc, b);
                    this.hasCastled = true;
                }
            }
            else if(move.equals(BQkingMoveLoc)) {
                if(QueenSide(b)) {
                    Rook r = (Rook) b.getPiece(new Location(7,7));
                    this.changeLocation(BQkingMoveLoc,b);
                    r.changeLocation(BQrookMoveLoc,b);
                    this.hasCastled = true;
                }
            }
        }
    }

    public boolean isCheck(Board b) { //kings cannot check other kings
        return false;
    }

    public boolean KingSide(Board b){
        Location WrookLoc = new Location(0,0);
        Location WkingLoc = new Location(0,3);
        Location BrookLoc = new Location(7,0);
        Location BkingLoc = new Location(7,3);
        boolean noChecks = this.notChecked;

        if(this.getID().charAt(0) == 'w') {
            if(b.getPiece(WrookLoc) == null || b.getPiece(WkingLoc) == null) {
                return false;
            }

            boolean WinitalPosition = this.getID().charAt(0) == 'w' && b.getPiece(WkingLoc).getID().equals("wK");
            boolean WthereIsARook = b.getPiece(WrookLoc).getID().equals("wR");
            boolean WnoMovement = !(this.hasMoved && b.getPiece(WrookLoc).getHasMoved());
            boolean WnothingBetween = b.getPiece(WrookLoc).isValid(new Location(0,2), b);

            if(WinitalPosition){
                if(WthereIsARook){
                    if(WnoMovement){
                        if(noChecks){
                            if(WnothingBetween) {

                                return true;
                            }
                            else{
                                return false;
                            }
                        }
                        else{
                            return false;
                        }
                    }
                    else{
                        return false;
                    }
                }else{
                    return false;
                }
            }
            else {
                return false;
            }
        }
        else {
            if(b.getPiece(BrookLoc) == null || b.getPiece(BkingLoc) == null) {
                return false;
            }

            boolean BinitalPosition = this.getID().charAt(0) == 'b' && b.getPiece(BkingLoc).getID().equals("bK");
            boolean BthereIsARook = b.getPiece(BrookLoc).getID().equals("bR");
            boolean BnoMovement = !(this.hasMoved && b.getPiece(BkingLoc).getHasMoved());
            boolean BnothingBetween = b.getPiece(BrookLoc).isValid(new Location(7,2), b);

            if(BinitalPosition){
                if(BthereIsARook){
                    if(BnoMovement){
                        if(noChecks){
                            if(BnothingBetween){
                                return true;
                            }
                            else{
                                return false;
                            }
                        }
                        else{
                            return false;
                        }
                    }
                    else{
                        return false;
                    }
                }
                else{
                    return false;
                }
            }
            else{
                return false;
            }
        }
    }
    public boolean QueenSide(Board b) {
        Location WrookLoc = new Location(0, 7);
        Location WkingLoc = new Location(0, 3);
        Location BrookLoc = new Location(7, 7);
        Location BkingLoc = new Location(7, 3);
        boolean noChecks = this.notChecked;

        if (this.getID().charAt(0) == 'w') {
            if (b.getPiece(WrookLoc) == null || b.getPiece(WkingLoc) == null) {
                return false;
            }

            boolean WinitalPosition = this.getID().charAt(0) == 'w' && b.getPiece(WkingLoc).getID().equals("wK");
            boolean WthereIsARook = b.getPiece(WrookLoc).getID().equals("wR");
            boolean WnoMovement = !(this.hasMoved && b.getPiece(WrookLoc).getHasMoved());
            boolean WnothingBetween = b.getPiece(WrookLoc).isValid(new Location(0, 4), b);

            if (WinitalPosition) {
                if (WthereIsARook) {
                    if (WnoMovement) {
                        if (noChecks) {
                            if (WnothingBetween) {

                                return true;
                            } else {
                                return false;
                            }
                        } else {
                            return false;
                        }
                    } else {
                        return false;
                    }
                } else {
                    return false;
                }
            } else {
                return false;
            }
        } else {
            if (b.getPiece(BrookLoc) == null || b.getPiece(BkingLoc) == null) {
                return false;
            }

            boolean BinitalPosition = this.getID().charAt(0) == 'b' && b.getPiece(BkingLoc).getID().equals("bK");
            boolean BthereIsARook = b.getPiece(BrookLoc).getID().equals("bR");
            boolean BnoMovement = !(this.hasMoved && b.getPiece(BkingLoc).getHasMoved());
            boolean BnothingBetween = b.getPiece(BrookLoc).isValid(new Location(7, 4), b);

            if (BinitalPosition) {
                if (BthereIsARook) {
                    if (BnoMovement) {
                        if (noChecks) {
                            if (BnothingBetween) {
                                return true;
                            } else {
                                return false;
                            }
                        } else {
                            return false;
                        }
                    } else {
                        return false;
                    }
                } else {
                    return false;
                }
            } else {
                return false;
            }
        }
    }
}
