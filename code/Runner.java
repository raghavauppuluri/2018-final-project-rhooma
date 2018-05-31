package code;

public class Runner {
    public static void main(String[] args) {
        Board b = new Board();
        printBoard(b);
    }

    public static void printBoard(Board b) {
        for (Piece[] col : b.board) {
            for (Piece p : col) {
                if(p != null) {
                    System.out.print(p.toString());
                }
                else {
                    System.out.print(" - ");
                }
            }
            System.out.println();
        }
    }
}
