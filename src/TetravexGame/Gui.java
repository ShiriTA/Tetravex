package TetravexGame;

import TetravexGame.Board.Board;
import TetravexGame.Board.GameBoard;
import TetravexGame.Board.InitBoard;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.Scanner;

public class Gui extends JFrame {

    //----------Fields----------
    private Game game;
    private GraphicTetravex graphicTetravex;
    private OpeningScreen openingScreen;
    private int boardSize;
    final private int HUNDRED = 100;
    final private int TWOHUNDRED = 200;


    //----------Constructor----------
    public Gui(int boardSize, GraphicOpening graphicOpening, OpeningScreen openingScreen) {

        super("Tetravex");
        this.boardSize = boardSize;
        this.openingScreen = openingScreen;

        InitBoard init = new InitBoard(boardSize);
        init.initializeBoard();
        GameBoard gameBoard = new GameBoard(boardSize);
        this.game = new Game(gameBoard, init);
        this.graphicTetravex = new GraphicTetravex(game, HUNDRED, HUNDRED, null, false, graphicOpening.getMode());
        add(graphicTetravex, BorderLayout.CENTER);

        HandlerClass handler = new HandlerClass(game, this);
        graphicTetravex.addMouseListener(handler);
        graphicTetravex.addMouseMotionListener(handler);

    }

    //----------Methods----------

    public void setNewOpeningScreen() {
        this.openingScreen = new OpeningScreen();
    }
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
        private boolean pressed = false;

        //----------Constructor----------
        public HandlerClass(Game game, Gui gui) {
            this.game = game;
            this.gui = gui;
        }

        //----------Methods----------
        @Override
        public void mouseClicked(MouseEvent e) {
            if (e.getX() >= 0 && e.getX() <= 50 && e.getY() >= 0 && e.getY() <= 50) {
                graphicTetravex.setMode();
            } else if (e.getX() >= 50 && e.getX() <= 100 && e.getY() >= 0 && e.getY() <= 50) {
                Object[] options = {"Yes",
                        "No"};
                int quit = JOptionPane.showOptionDialog(new JFrame(),
                        "Are you sure you want to quit to main menu?",
                        "Quit?",
                        JOptionPane.YES_NO_CANCEL_OPTION,
                        JOptionPane.QUESTION_MESSAGE,
                        null,
                        options,
                        options[0]);
                if (quit != 1) {
                    this.gui.setVisible(false);
                    openingScreen.setSize(1500, 1500);
                    openingScreen.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                    openingScreen.setVisible(true);



                }

            }
        }


        @Override
        public void mousePressed(MouseEvent e) {
            for (int i = 0; i < boardSize; i++) {
                for (int j = 0; j < boardSize; j++) {
                    if (e.getX() >= graphicTetravex.getSelXLoc() + (HUNDRED * i) && e.getX() <= graphicTetravex.getSelXLoc() + HUNDRED + (HUNDRED * i) && e.getY() >= HUNDRED + (HUNDRED * j) && e.getY() <= TWOHUNDRED + (HUNDRED * j)) {
                        this.srcBoard = this.game.getSelBoard();
                        this.fromI = j;
                        this.fromJ = i;
                        this.currBrick = this.game.getSelBoard().getBrick(j, i);
                        this.pressed = true;
                    } else if (e.getX() >= HUNDRED + (HUNDRED * i) && e.getX() <= TWOHUNDRED + (HUNDRED * i) && e.getY() >= HUNDRED + (HUNDRED * j) && e.getY() <= TWOHUNDRED + (HUNDRED * j)) {
                        this.srcBoard = this.game.getGameBoard();
                        this.fromI = j;
                        this.fromJ = i;
                        this.currBrick = this.game.getGameBoard().getBrick(j, i);
                        this.pressed = true;
                    }
                }
            }
        }

        @Override
        public void mouseReleased(MouseEvent e) {
            this.alive = false;
            for (int i = 0; i < boardSize; i++) {
                for (int j = 0; j < boardSize; j++) {

                    if (e.getX() >= HUNDRED + (HUNDRED * i) && e.getX() <= TWOHUNDRED + (HUNDRED * i) && e.getY() >= HUNDRED + (HUNDRED * j) && e.getY() <= TWOHUNDRED + (HUNDRED * j)) {
                        this.game.moveBrick(srcBoard, game.getGameBoard(), fromI, fromJ, j, i);
                        this.gui.paintAgain(HUNDRED + (HUNDRED * i), HUNDRED + (HUNDRED * j), this.currBrick, alive);
                    } else if (e.getX() >= graphicTetravex.getSelXLoc() + (HUNDRED * i) && e.getX() <= graphicTetravex.getSelXLoc() + HUNDRED + (HUNDRED * i) && e.getY() >= HUNDRED + (HUNDRED * j) && e.getY() <= TWOHUNDRED + (HUNDRED * j)) {
                        this.game.moveBrick(srcBoard, game.getSelBoard(), fromI, fromJ, j, i);
                        this.gui.paintAgain(HUNDRED + (HUNDRED * i), HUNDRED + (HUNDRED * j), this.currBrick, alive);
                    } else {
                        this.gui.paintAgain(fromI, fromJ, this.currBrick, alive);
                    }

                }
            }
            if (this.game.getGameBoard().isFull()) {
                win();
            }
            this.pressed = false;
        }

        @Override
        public void mouseEntered(MouseEvent e) {}

        @Override
        public void mouseExited(MouseEvent e) {}

        @Override
        public void mouseDragged(MouseEvent e) {
            if (currBrick != null && this.pressed) {
                this.alive = true;
                this.gui.paintAgain(e.getX() - (e.getX() % HUNDRED), e.getY() - (e.getY() % HUNDRED), this.currBrick, this.alive);

            }
        }

        @Override
        public void mouseMoved(MouseEvent e) {}

    }
}




