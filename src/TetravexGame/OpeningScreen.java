package TetravexGame;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class OpeningScreen extends JFrame {

    private GraphicOpening graphicOpening;
    private HandlerClass handler;

    public GraphicOpening getGraphicOpening() {
        return this.graphicOpening;
    }


    public OpeningScreen() {
        super("Tetravex");

        this.graphicOpening = new GraphicOpening(true);
        add(this.graphicOpening, BorderLayout.CENTER);

        this.handler = new HandlerClass(this.graphicOpening);
        this.graphicOpening.addMouseListener(handler);
        this.graphicOpening.addMouseMotionListener(handler);



    }

    public int getSizeOfBoard () {
        return this.handler.getSize();
    }

    public boolean start () { return this.handler.start();}



    private class HandlerClass implements MouseListener, MouseMotionListener {

        private int size;
        private boolean startGame;
        private GraphicOpening graphicOpening;


        public HandlerClass(GraphicOpening graphicOpening) {
            this.startGame = false;
            this.graphicOpening = graphicOpening;
        }

        public int getSize () {return this.size;}

        public boolean start () { return this.startGame == true;}



        @Override
        public void mouseClicked(MouseEvent e) {

            if (e.getX() >= 0 && e.getX() <= 50 && e.getY() >= 0 && e.getY() <= 50) {
                this.graphicOpening.setMode();
                this.graphicOpening.repaint();
            } else if (e.getX() >= 100 && e.getX() <= 150 && e.getY() >= 100 && e.getY() <= 150) {
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




