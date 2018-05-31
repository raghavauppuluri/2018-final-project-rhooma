package UI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Tester {

    JButton[][] grid = new JButton[2][1];
    int X;
    int Y;
    Icon select;

    public static void main(String[] args) {
        Tester test = new Tester();

    }

    public Tester() {
        JFrame but = new JFrame("demo");
        JPanel p = new JPanel();
        but.pack();
        but.setSize(400, 400);
        but.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        but.setLocationRelativeTo(null);

        p.setLayout(new GridLayout(2, 1));

        JMenuBar menuBar = new JMenuBar();
        but.setJMenuBar(menuBar);

        JMenu file = new JMenu("file");
        file.setBorderPainted(true);
        menuBar.add(file);
        JMenu exit = new JMenu("xit");
        menuBar.add(exit);
        JMenu x = new JMenu("Help");
        menuBar.add(x);


        /*JButton button = new JButton();

        button.setBorderPainted(false);
        button.setBackground(Color.GREEN);
        button.setOpaque(true);*/

        JMenuBar m = new JMenuBar();
        m.add(new JMenuItem("Menu"));
        but.getContentPane().add(m);

        for (int r = 0; r< 2; r++)
            for (int c = 0; c < 1; c++) {
                grid[r][c] = new JButton();
                grid[r][c].setOpaque(true);
                grid[r][c].setBorderPainted(false);
                grid[r][c].setBackground(Color.magenta);

                if (r != 0) grid[r][c].setIcon(new ImageIcon(getClass().getResource("bK.png")));
                else {
                    grid[r][c].setIcon(new ImageIcon(getClass().getResource("wQ.png")));
                }
                grid[r][c].addActionListener(new ButtonThin());
                p.add(grid[r][c]);
            }

        but.add(p);


        but.setVisible(true);

    }

    private class ButtonThin implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            Object source = e.getSource();

            for (int r = 0; r < 2; r++) {
                for (int c = 0; c < 1; c++) {

                    grid[r][c].addActionListener(new Mov());
                    if (source == grid[r][c]) {
                        int i = r;
                        int j = c;
                        select = grid[r][c].getIcon();
                        while (i < 2 && j < 1) {

                            grid[i][j].addActionListener(new Mov());
                            i++;
                            j++;
                        }
                        select = grid[r][c].getIcon();
                        X = r;
                        Y = c;
                        return;

                    }
                }
            }
        }
    private class Mov implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            Object source = e.getSource();

            //checks all the boxes if they match the one clicked, or "source"
            for (int r = 0; r < 2; r++) {
                for (int c = 0; c < 1; c++) {

                    if (source == grid[r][c]) {
                        processClick(r, c);
                        return;
                    }
                }
            }

        }
        private boolean isValidMove(int x, int y) {
            return true;
        }

        private void processClick(int r, int c) {

            if (!isValidMove(r, c)) {
                return;
            }
            grid[X][Y].setIcon(new ImageIcon(getClass().getResource("wR.png")));
            grid[r][c].setIcon(null);

        }
    }

    }

}

