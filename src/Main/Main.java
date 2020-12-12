package Main;

import TetravexGame.Gui;
import TetravexGame.OpeningScreen;

import javax.swing.*;

public class Main {

    public static void main(String[] args) {

        OpeningScreen openingScreen = new OpeningScreen();

            while (!openingScreen.start()) {
                openingScreen.setSize(1500, 1500);
                openingScreen.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                openingScreen.setVisible(true);

            }


            int size = openingScreen.getSizeOfBoard();
            Gui gui = new Gui(size, openingScreen.getGraphicOpening(), openingScreen);
            openingScreen.setVisible(false);
            gui.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            gui.setSize(1500, 1500);
            gui.setVisible(true);

    }

}