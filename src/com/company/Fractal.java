package com.company;

import javax.swing.*;
import java.awt.*;

public class Fractal extends JPanel implements Runnable {

    private int depth;
    private JFrame panel;
    private static Color color1 = Color.BLACK;
    private static Color color2 = Color.WHITE;

    public Fractal(int depth, JFrame panel) {
        this.depth = depth;
        this.panel = panel;
    }

    private void triangle(Graphics g, int x1, int x2, int x3, int y1, int y2, int y3, int yPeak, Color colour, int depth) {
        if (depth >= 0) {
            if (colour == Color.BLACK) {
                g.setColor(colour);
                g.drawPolygon(new int[] {x1, x2, x3}, new int[] {y1, y2, y3}, 3);
                g.setColor(colour);
                g.fillPolygon(new int[] {x1, x2, x3}, new int[] {y1, y2, y3}, 3);
                triangle(g, ((x1 + x2) / 2), x2, ((x2 + x3) / 2), ((y1 + y2) / 2), y1, ((y2 + y3) / 2), yPeak, color2, depth - 1);
            } else {
                g.setColor(colour);
                g.drawPolygon(new int[] {x1, x2, x3}, new int[] {y1, y2, y3}, 3);
                triangle(g, (((x1 + x2) / 2) - (x2 - x1)), x1, ((x1 + x2) / 2), ((y1 + y2) / 2), y2, ((y2 + y3) / 2), ((y1 + yPeak) - yPeak), color2, depth - 1);
                triangle(g, ((x2 + x3) / 2), x3, (((x2 + x3) / 2) + (x3 - x2)), ((y1 + y2) / 2), y2, ((y2 + y3) / 2), ((y3 + yPeak) - yPeak), color2,depth - 1);
                triangle(g, ((x1 + x2) / 2), x2, ((x2 + x3) / 2), ((y1 + yPeak) / 2), y1, ((y3 + yPeak) / 2), yPeak, color2, depth - 1);
            }
        }
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        triangle(g, 100,500,900,550,10,550, 10, color1, depth);
    }

    public void run() {
        panel.setContentPane(this);
        panel.setVisible(true);
        System.out.println("Thread ID: " + Thread.currentThread().getId() + ", Thread priority: " + Thread.currentThread().getPriority() + ", Thread: " + Thread.currentThread().toString());
    }
}
