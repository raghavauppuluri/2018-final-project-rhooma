package code;

public abstract class Piece {

    private String ID;
    private Location location;
    private boolean hasMoved = false;

    public Piece(String s, Location loc) {
        this.ID = s;

        this.location = loc;
    }

    public String toString() {
        return "ID: " + this.ID + " ";
    }

    public String getID() {
        return this.ID;
    }

    public Location getLocation() {
        return this.location;
    }

    public abstract boolean isValid(Location loc, Board b);

    public void changeLocation(Location loc, Board b) {
        int tempRow = this.getLocation().getRow();
        int tempCol = this.getLocation().getCol();
        this.getLocation().setLocation(loc);
        b.board[loc.getRow()][loc.getCol()] = this;
        b.board[tempRow][tempCol] = null;
    }



    public void move(Location loc, Board b) {
        Location kingLoc = findKing(b);
        King king = (King) b.getPiece(kingLoc);
        if (isValid(loc, b)) {
            changeLocation(loc, b);
            this.hasMoved = true;
        }
        else {
            System.out.println("INCORRECT MOVE");
        }
        if(isCheck(b)) {
            king.checkKing();
            System.out.println("CHECK");
        }
    }

    public boolean getHasMoved() {
        return this.hasMoved;
    }

    public abstract boolean isCheck(Board b);

    public boolean HorizontalMove(Location n, Board b) {
        int moveRow = n.getRow();
        int moveCol = n.getCol();
        int row = this.getLocation().getRow();
        int col = this.getLocation().getCol();

        if (moveRow == row && moveCol != col) {
            if (b.board[moveRow][moveCol] != null && b.board[moveRow][moveCol].getID().charAt(0) == b.board[row][col].getID().charAt(0)) {
                return false;
            } else {
                while (col != moveCol) {
                    if (col > moveCol) {
                        col--;
                    } else {
                        col++;
                    }
                    if (b.board[row][col] != null && (col != moveCol)) {
                        return false;
                    }
                }
                return true;
            }
        }
        else {
            return false;
        }
    }

    public boolean VerticalMove(Location n, Board b) {
        int moveRow = n.getRow();
        int moveCol = n.getCol();
        int row = this.getLocation().getRow();
        int col = this.getLocation().getCol();

        if (moveCol == col && moveRow != row) {
            if (b.board[moveRow][moveCol] != null && b.board[moveRow][moveCol].getID().charAt(0) == b.board[row][col].getID().charAt(0)) {
                return false;
            }
            while (row != moveRow) {
                if (moveRow > row) {
                    row++;
                } else {
                    row--;
                }
                if (b.board[row][col] != null && (row != moveRow)) {
                    return false;
                }
            }
            return true;
        }
        else {
            return false;
        }
    }

    public boolean DiagonalMove(Location n, Board b) {
        int moveRow = n.getRow();
        int moveCol = n.getCol();
        int row = this.getLocation().getRow();
        int col = this.getLocation().getCol();

        if (Math.abs(moveCol - col) == Math.abs(moveRow - row) && Math.abs(moveRow - row) != 0) {
            if (b.board[moveRow][moveCol] != null && b.board[moveRow][moveCol].getID().charAt(0) == b.board[row][col].getID().charAt(0)) {
                return false;
            }
            while (row != moveRow && moveCol != col) {

                if (moveRow > row && moveCol > col) {
                    row++;
                    col++;
                }
                else if (moveRow > row && moveCol < col) {
                    row++;
                    col--;
                }
                else if(moveRow < row && moveCol < col) {
                    row--;
                    col--;
                }
                else {
                    row--;
                    col++;
                }
                if (b.board[row][col] != null && (row != moveRow && moveCol != col)) {
                    return false;
                }
            }
            return true;
        }
        else {
            return false;
        }
    }

    public boolean checkHorizontal(Board b) {
        Piece[][] board = b.getBoard();
        Location kingLoc = findKing(b);
        int kingRow = kingLoc.getRow();
        int kingCol = kingLoc.getCol();
        int col = this.location.getCol();
        int row = this.location.getRow();

        if(kingRow != row) {
            return false;
        }
        else {
            while (col != kingCol) {
                if (col > kingCol) {
                    col--;
                }
                else {
                    col++;
                }
                if (b.board[row][col] != null && !b.board[row][col].getLocation().equals(kingLoc)) {
                    return false;
                }
            }
            return true;
        }
    }

    public Location findKing(Board b) {
        Piece[][] board = b.getBoard();
        for (Piece[] row :  board) {
            for (Piece p : row) {
                if(p != null) {
                    if (p.getID().charAt(0) != this.getID().charAt(0) && p.getID().charAt(1) == 'K') {
                        return p.getLocation();
                    }
                }
            }
        }
        return null;
    }

    public boolean checkVertical(Board b) {
        Location kingLoc = findKing(b);
        int kingCol = kingLoc.getCol();
        int kingRow = kingLoc.getRow();
        int col = this.getLocation().getCol();
        int row = this.getLocation().getRow();

        if(kingCol != col) {
            return false;
        }
        else {
            while (row != kingRow) {
                if (kingRow > row) {
                    row++;
                } else {
                    row--;
                }
                if (b.board[row][col] != null && !b.board[row][col].getLocation().equals(kingLoc)) {
                    return false;
                }
            }
            return true;
        }
    }

    public boolean checkDiagonal(Board b) {
        Location kingLoc = findKing(b);
        int kingRow = kingLoc.getRow();
        int kingCol = kingLoc.getCol();
        int col = this.getLocation().getCol();
        int row = this.getLocation().getRow();

        if (kingCol - col != kingRow - row) {
            return false;
        }
        else {
            if (kingRow > row && kingCol > col) {
                row++;
                col++;
            }
            else if (kingRow > row && kingCol < col){
                row++;
                col--;
            }
            else if(kingRow < row && kingCol < col){
                row--;
                col--;
            }
            else {
                row--;
                col++;
            }
            if (b.board[row][col] != null && !b.board[row][col].getLocation().equals(kingLoc)) {
                return false;
            }

        }
        return true;
    }
}
