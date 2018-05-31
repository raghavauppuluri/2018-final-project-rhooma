package UI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLOutput;
import java.util.ArrayList;

import code.Location;
import code.Board;
import code.Piece;
import code.King;
import code.Knight;
import code.Pawn;
import code.Queen;
import code.Rook;



public class BoardUI extends JFrame {
    private JButton[][] pieces = new JButton[8][8];
    private Board chessBoard;
    private Icon selected;
    private int x;
    int y;
    public static void main(String args[]) {
        BoardUI b = new BoardUI();
    }

    public BoardUI() {

        //create Board
        chessBoard = new Board();

        //setup window
        JFrame frame = new JFrame();
        JPanel p = new JPanel();
        p.add
        pack();
        setSize(625, 650);
        setResizable(false);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        //panel grid layout && window placement
        p.setLayout(new GridLayout(8, 8));
        setLocation(400, 10);

        //setup for buttons
        for (int r = 0; r < 8; r++) {
            for (int c = 0; c < 8; c++) {
                pieces[r][c] = new JButton();
                pieces[r][c].setOpaque(true);
                pieces[r][c].setBorderPainted(false);

                //coloring background
                if ((r % 2 == 1 && c % 2 == 1)
                        || (r % 2 == 0 && c % 2 == 0)) {
                    pieces[r][c].setBackground(new Color(255, 170, 100));
                } else {
                    pieces[r][c].setBackground(new Color(150, 70, 5));
                }

                pieces[r][c].addActionListener(new ButtonThing());
                p.add(pieces[r][c]);
            }
        }
        SetUp();

        System.out.println("-");

        add(p);

        setVisible(true);
    }

    //the action listener
    private class ButtonThing implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            Object source = e.getSource();

            //checks all the boxes if they match the one clicked, or "source"
            for (int r = 0; r < 8; r++) {
                for (int c = 0; c < 8; c++) {

                    //adds new action listener and then moves to the if
                    //when it matches source
                    pieces[r][c].addActionListener(new Move());
                    if (source == pieces[r][c]) {
                        int i = r;
                        int j = c;
                        while(i< 8 && j< 8) {

                            //adds new action listener to each button, probably needs
                            //to be done a differnet way
                            pieces[i][j].addActionListener(new Move());
                            i++;
                            j++;

                        }
                        //adds a new action listener to each tile, so it can technically select
                        //two tiles at a time

                        //sets the field selected as the icon that was pressed and
                        //x & y as the coordinates
                        selected = pieces[r][c].getIcon();
                        x = r;
                        y = c;
                        return;
                    }
                }
            }

        }

    }

    private class Move implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            Object source = e.getSource();

            //checks all the boxes if they match the one clicked, or "source"
            for (int r = 0; r < 8; r++) {
                for (int c = 0; c < 8; c++) {

                    //once it works, it sends it to the final class for the selection
                    if (source == pieces[r][c]) {
                        processClick(r, c);
                        return;
                    }
                }
            }

        }

    }

    /*sends selected's icon (data packet computer) to correct
         piece class, like Knight(selected); ,
         to see if valid move. just returns true for now*/
    private boolean isValidMove(int x, int y) {
        return true;
    }

    //action listener extention
    private void processClick(int r, int c) {

        /* this is where you would send the piece type
        (selected.toString().charAt(62)) to another method
        to check if the move is valid
        */
        if (!isValidMove(r, c)) {
            return;
        }

        //prints coordinates for troubleshooting
        System.out.println(x + ", " + y);
        System.out.println(r + ", " + c);
        System.out.println("---");

        //should set the new select piece as the old selected icon
        pieces[r][c].setIcon(selected);

        //should set the first selected to null
        pieces[x][y].setIcon(null);

        //checks if white or black
        /*if(selected.toString().charAt(62) == 'w') {
            pieces[r + 1][c].setIcon(selected);
            pieces[r][c].setIcon(null);
        } else {
            pieces[r - 1][c].setIcon(selected);
            pieces[r][c].setIcon(null);
        }*/
    }

    //board setup, not finished
    public void SetUp() {
        ArrayList<String> names = new ArrayList<String>();
        names.add("wP.png");
        names.add("wR.png");
        names.add("wB.png");
        names.add("wN.png");
        names.add("wQ.png");
        names.add("wK.png");
        names.add("bP.png");
        names.add("bR.png");
        names.add("bB.png");
        names.add("bN.png");
        names.add("bQ.png");
        names.add("bK.png");
        int con = 0;
        for(int r = 0; r< 8 ; r++) {
            for (int c = 0; c < 8; c++) {

                if (r < 2) {
                    pieces[r][c].setIcon(new ImageIcon(
                            getClass().getResource("w" +names.get(con).substring(1))));

                } else if (r > 5) {
                    pieces[r][c].setIcon(new ImageIcon(
                            getClass().getResource("b" + names.get(con).substring(1))));
                }
                con++;
                con %= 12;
            }
        }
    }
}
