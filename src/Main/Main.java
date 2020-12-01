package Main;

import TetravexGame.Gui;
import TetravexGame.OpeningScreen;

import javax.swing.*;

public class Main {

    public static void main(String[] args) {

        OpeningScreen openingScreen = new OpeningScreen();


        while (!openingScreen.start()) {
            openingScreen.setSize(800, 400);
            openingScreen.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            openingScreen.setVisible(true);

        }
        int size = openingScreen.getSizeOfBoard();
        Gui gui = new Gui(size);
        openingScreen.setVisible(false);
        gui.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        gui.setSize(100 * size, 500 * size);
        gui.setVisible(true);

    }

}