package code;

public class Board {

    public Piece[][] board;

    public Board() {
        this.board = new Piece[8][8];
        fillAll();
    }

    public void fillAll() {
        fillPawns();
        fillRooks();
        fillKnights();
        fillBishops();
        fillQueens();
        fillKings();
    }
    public Piece[][] boardClone(Board b1) {
        this.board = new Piece[8][8];
        for(int r = 0;r < b1.board.length; r++) {
            for (int c = 0; c < b1.board[0].length; c++) {
                this.board[r][c] = b1.board[r][c];
            }
        }
        return this.board;
    }

    public Piece[][] getBoard() {

        return this.board;
    }

    public Piece getPiece(Location loc) {

        return this.board[loc.getRow()][loc.getCol()];
    }

    public void fillPawns() {
        for (int i = 0; i < board.length; i++) {
            this.board[1][i] = new Pawn("wP", new Location(1, i));
            this.board[6][i] = new Pawn("bP", new Location(6, i));
        }
    }

    public void fillRooks() {
        this.board[0][0] = new Rook("wR", new Location(0,0));
        this.board[0][7] = new Rook("wR", new Location(0,7));

        this.board[7][0] = new Rook("bR", new Location(7,0));
        this.board[7][7] = new Rook("bR", new Location(7,7));
    }

    public void fillKnights() {
        this.board[0][1] = new Knight("wN", new Location(0,1));
        this.board[0][6] = new Knight("wN", new Location(0,6));

        this.board[7][1] = new Knight("bN", new Location(7,1));
        this.board[7][6] = new Knight("bN", new Location(7,6));
    }

    public void fillBishops() {
        this.board[0][2] = new Bishop("wB", new Location(0,2));
        this.board[0][5] = new Bishop("wB", new Location(0,5));

        this.board[7][2] = new Bishop("bB", new Location(7,2));
        this.board[7][5] = new Bishop("bB", new Location(7,5));
    }
    public void fillQueens() {
        this.board[0][4] = new Queen("wQ", new Location(0,4));

        this.board[7][4] = new Queen("bQ", new Location(7,4));
    }

    public void fillKings() {
        this.board[0][3] = new King("wK", new Location(0,3));

        this.board[7][3] = new King("bK", new Location(7,3));
    }
}
