package code;

import java.util.Scanner;

public class Pawn extends Piece {

    private String ID;
    private Location loc;
    private boolean isFirstMove = true;

    public Pawn(String s, Location loc) {
        super(s, loc);
    }

    public boolean isValid(Location move, Board b) {
        int moveRow = move.getRow();
        int moveCol = move.getCol();
        int row = this.getLocation().getRow();
        int col = this.getLocation().getCol();

        boolean anyMovementWhite = moveRow - row == 1 && moveCol - col == 0;
        boolean firstMoveOnlyWhite = moveRow - row == 2 && moveCol - col == 0 && b.board[row + 1][col] == null;
        boolean killMoveWhite = moveRow - row == 1 && Math.abs(moveCol - col) == 1;
        boolean anyMovementBlack = row - moveRow == 1 && moveCol - col == 0;
        boolean firstMoveOnlyBlack = row - moveRow == 2 && moveCol - col == 0;
        boolean killMoveBlack = row - moveRow == 1 && Math.abs(col - moveCol) == 1;

        if (b.board[moveRow][moveCol] != null && this.getID().charAt(0) == b.board[moveRow][moveCol].getID().charAt(0)) {
            return false;
        }

        if (this.getID().charAt(0) == 'w') {
            if (isFirstMove) {
                return anyMovementWhite || firstMoveOnlyWhite;
            } else if (killMoveWhite) {
                return true;
            } else {
                return anyMovementWhite;
            }
        } else {
            if (isFirstMove) {
                return anyMovementBlack || firstMoveOnlyBlack;
            } else if (killMoveBlack) {
                return true;
            } else {
                return anyMovementBlack;
            }
        }

    }

    public boolean isCheck(Board b) {
        Location kingLoc = findKing(b);
        int kingRow = kingLoc.getRow();
        int kingCol = kingLoc.getCol();
        int col = this.getLocation().getCol();
        int row = this.getLocation().getRow();

        boolean killMoveWhite = kingRow - row == 1 && Math.abs(kingCol - col) == 1;

        boolean killMoveBlack = row - kingRow == 1 && Math.abs(col - kingCol) == 1;

        if (this.getID().charAt(0) == 'w') {
            return killMoveWhite;
        } else {
            return killMoveBlack;
        }
    }

    public void move(Location loc, Board b) {
        if (isValid(loc, b)) {
            changeLocation(loc, b);
            this.isFirstMove = false;
            if(canPromote(loc, b)) {
                PawnPromotion(b);
            }
        }
        else {
            System.out.println("INCORRECT MOVE");
        }
        Piece thisPiece = b.getPiece(this.getLocation());
        if (thisPiece.isCheck(b)) {
            System.out.println("CHECK");
        }
    }

    public boolean canPromote(Location move, Board b) {
        int row = move.getRow();
        int col = move.getCol();

        if (this.getID().charAt(0) == 'w') {
            return b.board[row][col] != null && row == 7;
        } else {
            return b.board[row][col] != null && row == 0;
        }
    }

    public void PawnPromotion(Board b) {
        int row = this.getLocation().getRow();
        int col = this.getLocation().getCol();
        Scanner keyboard = new Scanner(System.in);

        if (this.getID().charAt(0) == 'w') {
            System.out.println("What Piece ya want ?");
            String piece = keyboard.nextLine();
            String zone = "";
            while (zone.equals("")) {
                if (piece.equals("Q")) {
                    b.board[row][col] = new Queen("wQ", this.getLocation());
                    zone = "2";
                } else if (piece.equals("N")) {
                    b.board[row][col] = new Knight("wN", this.getLocation());
                    zone = "2";
                } else if (piece.equals("B")) {
                    b.board[row][col] = new Bishop("wB", this.getLocation());
                    zone = "2";
                } else if (piece.equals("R")) {
                    b.board[row][col] = new Rook("wR", this.getLocation());
                    zone = "2";
                } else {
                    System.out.println("What Piece ya want ?");
                    piece = keyboard.nextLine();
                }
            }
        } else {
            System.out.println("What Piece ya want ?");
            String piece = keyboard.nextLine();
            String zone = "";
            while (zone.equals("")) {
                if (piece.equals("Q")) {

                    b.board[row][col] = new Queen("bQ", this.getLocation());
                    zone = "2";
                } else if (piece.equals("N")) {
                    b.board[row][col] = new Knight("bN", this.getLocation());
                    zone = "2";
                } else if (piece.equals("B")) {
                    b.board[row][col] = new Bishop("bB", this.getLocation());
                    zone = "2";
                } else if (piece.equals("R")) {
                    b.board[row][col] = new Rook("bR", this.getLocation());
                    zone = "2";
                } else {
                    System.out.println("What Piece ya want ?");
                    piece = keyboard.nextLine();
                }
            }
        }
    }


}
