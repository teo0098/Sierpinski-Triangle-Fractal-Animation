package com.company;

import javax.swing.*;

import static java.lang.Thread.*;

public class Main {

    private static int pause = 1000;
    private static JFrame panel = new JFrame();
    private static int depth = 10;

    public static void main(String[] args) {
        panel.setTitle("Sierpiński Triangle");
        panel.setSize(1000,600);
        panel.setLocationRelativeTo(null);
        panel.setResizable(false);
        panel.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        panel.setVisible(true);
        for (int i = 0; i < depth; i++) {
            Thread thread = new Thread(new Fractal(i, panel));
            thread.setPriority(depth - i);
            try {
                sleep(pause);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            thread.start();
        }
    }
}
