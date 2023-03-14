/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import javax.swing.JPanel;

public class ChessBoardPanel extends JPanel {
    private int N; // Number of cases in each row and column

    public ChessBoardPanel(int N) {
        this.N = N;
        setPreferredSize(new Dimension(50 * N, 50 * N));
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        int squareSize = getWidth() / N;
        for (int row = 0; row < N; row++) {
            for (int col = 0; col < N; col++) {
                Color color = ((row + col) % 2 == 0) ? Color.WHITE : Color.GRAY;
                g.setColor(color);
                g.fillRect(col * squareSize, row * squareSize, squareSize, squareSize);
            }
        }
    }
}
