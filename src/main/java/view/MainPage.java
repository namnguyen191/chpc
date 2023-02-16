package view;

import javax.swing.*;

public class MainPage {

    public static void main(String[] args) {

        JFrame frame = MainUI.getInstance();
        frame.setSize(900, 600);
        frame.pack();
        frame.setVisible(true);
    }
}
