package WarstwaAplikacji;
import WarstwaBiznesowa.*;
import Model.*;

import javax.swing.*;

public class Aplikacja{

    public static boolean admin = false;
    public static String login ="";

    public static void main(String[] args) {
        JFrame frame = new JFrame("Start");
        frame.setContentPane(new Start().Start);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }

}
