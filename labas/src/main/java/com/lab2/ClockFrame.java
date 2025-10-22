package com.lab2;

import javax.swing.*;
import java.awt.*;


public class ClockFrame {
    JFrame frame;
    JPanel panel;
    JLabel label;

    public ClockFrame() {
        frame = new JFrame("My Clock");
        panel = new JPanel();
        label = new JLabel();


        frame.setSize(400, 70);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        panel.add(label, BorderLayout.CENTER);
        frame.add(panel, BorderLayout.CENTER);


        ClockExt clockExt = new ClockExt(12, 6, 30);

        Timer timer = new Timer(1000, e -> {
           clockExt.nextSecond();
           label.setText(clockExt.toString());
        });

        timer.start();

        frame.setVisible(true);


    }
}
