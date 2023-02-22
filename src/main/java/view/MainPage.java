package view;

import controller.Bar;
import controller.View;
import controller.ViewFactory;

import javax.swing.*;

public class MainPage {

    public static void main(String[] args) {

        JFrame frame = MainUI.getInstance();
        frame.setSize(900, 600);
        frame.pack();
        frame.setVisible(true);

        //JFrame view = ViewFactory.createView("Bar");
    }
}
