package TetravexGame;

import TetravexGame.Board.Board;
import TetravexGame.Board.GameBoard;
import TetravexGame.Board.InitBoard;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class Gui extends JFrame {

    //----------Fields----------
    private Game game;
    private GraphicTetravex graphicTetravex;
    private int boardSize;


    //----------Constructor----------
    public Gui(int boardSize) {

        super("Tetravex");
        this.boardSize = boardSize;

        InitBoard init = new InitBoard(boardSize);
        init.initializeBoard();
        GameBoard gameBoard = new GameBoard(boardSize);
        this.game = new Game(gameBoard, init);
        this.graphicTetravex = new GraphicTetravex(game, 50, 100, null, false);
        add(graphicTetravex, BorderLayout.CENTER);

        HandlerClass handler = new HandlerClass(game, this);
        graphicTetravex.addMouseListener(handler);
        graphicTetravex.addMouseMotionListener(handler);

    }

    //----------Methods----------

    /**
     * Painting the brick in the updated location
     * @param currX the x where the brick should be painted
     * @param currY the y where the brick should be painted
     * @param currBrick the brick that should be painted
     * @param alive whether the brick is moving or static
     */
    public void paintAgain(int currX, int currY, Brick currBrick, boolean alive) {
        this.graphicTetravex.setCurrX(currX);
        this.graphicTetravex.setCurrY(currY);
        this.graphicTetravex.setCurrBrick(currBrick);
        this.graphicTetravex.setAlive(alive);
        add(this.graphicTetravex, BorderLayout.CENTER);
        this.graphicTetravex.repaint();
    }

    /**
     * Winning the game
     */
    public void win() {
        this.graphicTetravex.setWin();
        this.graphicTetravex.repaint();
    }

    private class HandlerClass implements MouseListener, MouseMotionListener {

        //----------Fields----------
        private Game game;
        private Board srcBoard;
        private int fromI;
        private int fromJ;
        private Gui gui;
        private Brick currBrick;
        private boolean alive;

        //----------Constructor----------
        public HandlerClass(Game game, Gui gui) {
            this.game = game;
            this.gui = gui;
        }

        //----------Methods----------
        @Override
        public void mouseClicked(MouseEvent e) {}


        @Override
        public void mousePressed(MouseEvent e) {
            for (int i = 0; i < boardSize; i++) {
                for (int j = 0; j < boardSize; j++) {
                    if (e.getX() >= graphicTetravex.getSelXLoc() + (50 * i) && e.getX() <= graphicTetravex.getSelXLoc() + 50 + (50 * i) && e.getY() >= 100 + (50 * j) && e.getY() <= 150 + (50 * j)) {
                        this.srcBoard = this.game.getSelBoard();
                        this.fromI = j;
                        this.fromJ = i;
                        this.currBrick = this.game.getSelBoard().getBrick(j, i);
                    } else if (e.getX() >= 50 + (50 * i) && e.getX() <= 100 + (50 * i) && e.getY() >= 100 + (50 * j) && e.getY() <= 150 + (50 * j)) {
                        this.srcBoard = this.game.getGameBoard();
                        this.fromI = j;
                        this.fromJ = i;
                        this.currBrick = this.game.getGameBoard().getBrick(j, i);
                    }
                }
            }
        }

        @Override
        public void mouseReleased(MouseEvent e) {
            this.alive = false;
            for (int i = 0; i < boardSize; i++) {
                for (int j = 0; j < boardSize; j++) {
                    if (e.getX() >= 40 + (50 * i) && e.getX() <= 110 + (50 * i) && e.getY() >= 90 + (50 * j) && e.getY() <= 160 + (50 * j)) {
                        this.game.moveBrick(srcBoard, game.getGameBoard(), fromI, fromJ, j, i);
                        this.gui.paintAgain(50, 100, this.currBrick, alive);
                    } else if (e.getX() >= graphicTetravex.getSelXLoc() - 10 + (50 * i) && e.getX() <= graphicTetravex.getSelXLoc() + 60 + (50 * i) && e.getY() >= 90 + (50 * j) && e.getY() <= 160 + (50 * j)) {
                        this.game.moveBrick(srcBoard, game.getSelBoard(), fromI, fromJ, j, i);
                        this.gui.paintAgain(50, 100, this.currBrick, alive);
                    }

                }
            }
            if (this.game.getGameBoard().isFull()) {
                win();
            }
        }

        @Override
        public void mouseEntered(MouseEvent e) {}

        @Override
        public void mouseExited(MouseEvent e) {}

        @Override
        public void mouseDragged(MouseEvent e) {
            if (currBrick != null) {
                this.alive = true;
                this.gui.paintAgain(e.getX() - (e.getX() % 50), e.getY() - (getY() % 100), this.currBrick, alive);
            }
        }

        @Override
        public void mouseMoved(MouseEvent e) {}

    }
}




