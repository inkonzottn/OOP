package com.lab2;

import javax.swing.*;
import java.awt.*;

public class ClockFrame2 {

    JFrame frame;
    JPanel panel;
    JLabel label;

    public ClockFrame2() {
        frame = new JFrame("My Clock2");
        panel = new JPanel();
        label = new JLabel();

        Font beautifulFont = new Font("Verdana", Font.BOLD, 14);

        label.setFont(beautifulFont);
        label.setForeground(Color.decode("#00008B"));

        frame.setSize(600, 90);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        panel.setBackground(Color.decode("#fcc5f0"));
        panel.add(label, BorderLayout.CENTER);
        frame.add(panel, BorderLayout.CENTER);


        ClockExt2 clockExt2 = new ClockExt2(12, 6, 30, 00);

        Timer timer = new Timer(100, e -> {
            clockExt2.nextMillisecond();
            label.setText(clockExt2.toString());
        });

        timer.start();

        frame.setVisible(true);


    }
}
