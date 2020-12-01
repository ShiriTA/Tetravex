package TetravexGame;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class OpeningScreen extends JFrame {

    private GraphicOpening graphicOpening;
    private HandlerClass handler;


    public OpeningScreen() {
        super("Tetravex");

        this.graphicOpening = new GraphicOpening();
        add(graphicOpening, BorderLayout.CENTER);

        this.handler = new HandlerClass(this);
        graphicOpening.addMouseListener(handler);
        graphicOpening.addMouseMotionListener(handler);

    }

    public int getSizeOfBoard () {
        return this.handler.getSize();
    }

    public boolean start () { return this.handler.start();}

    private class HandlerClass implements MouseListener, MouseMotionListener {

        private OpeningScreen openingScreen;
        private int size;
        private boolean startGame;


        public HandlerClass(OpeningScreen openingScreen) {
            this.openingScreen = openingScreen;
            this.startGame = false;
        }

        public int getSize () {return this.size;}

        public boolean start () { return this.startGame == true;}

        @Override
        public void mouseClicked(MouseEvent e) {

            if (e.getX() >= 100 && e.getX() <= 150 && e.getY() >= 100 && e.getY() <= 150) {
                this.size = 2;
                this.startGame = true;
            } else if (e.getX() >= 200 && e.getX() <= 250 && e.getY() >= 100 && e.getY() <= 150) {
                this.size = 3;
                this.startGame = true;
            } else if (e.getX() >= 300 && e.getX() <= 350 && e.getY() >= 100 && e.getY() <= 150) {
                this.size = 4;
                this.startGame = true;
            } else if (e.getX() >= 400 && e.getX() <= 450 && e.getY() >= 100 && e.getY() <= 150) {
                this.size = 5;
                this.startGame = true;
            } else if (e.getX() >= 500 && e.getX() <= 550 && e.getY() >= 100 && e.getY() <= 150) {
                this.size = 6;
                this.startGame = true;
            } else if (e.getX() >= 600 && e.getX() <= 650 && e.getY() >= 100 && e.getY() <= 150) {
                this.size = 7;
                this.startGame = true;
            } else {
                this.size = 0;
            }
        }


        @Override
        public void mousePressed(MouseEvent e) {}

        @Override
        public void mouseReleased(MouseEvent e) {}

        @Override
        public void mouseEntered(MouseEvent e) {}

        @Override
        public void mouseExited(MouseEvent e) {}

        @Override
        public void mouseDragged(MouseEvent e) {}

        @Override
        public void mouseMoved(MouseEvent e) {}
    }
}




