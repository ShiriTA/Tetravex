package TetravexGame;

import TetravexGame.Board.Board;

import javax.swing.*;
import java.awt.*;

public class GraphicTetravex extends JPanel {

    //----------Fields----------
    private Game game;
    private int currX;
    private int currY;
    private Brick currBrick;
    private boolean alive;
    private boolean win;
    final private int FIFTY = 50;
    final private int HUNDRED = 100;
    final private int HUNDREDFIFTY = 150;
    private int selXLoc;


    //----------Constructor----------
    public GraphicTetravex(Game game, int currX, int currY, Brick currBrick, boolean alive) {
        this.game = game;
        this.currX = currX;
        this.currY = currY;
        this.currBrick = currBrick;
        this.alive = alive;
        this.selXLoc = HUNDREDFIFTY + (game.getBoardSize() * FIFTY);
        this.win = false;
    }

    //----------Methods----------
    public int getSelXLoc () {return this.selXLoc;}
    public void setCurrX(int currX) {
        this.currX = currX;
    }
    public void setCurrY(int currY) {
        this.currY = currY;
    }
    public void setCurrBrick(Brick currBrick) {
        this.currBrick = currBrick;
    }
    public void setAlive(boolean alive) {
        this.alive = alive;
    }
    public void setWin() {this.win = true;}


    /**
     * Calls the super paint component method. Calls the paint board and paint alive methods in order
     * to paint the bricks on the boards, whether they are static or moving.
     * @param g
     */
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        this.setBackground(Color.white);

        if (alive) {
            paintAlive(g);
        }
        paintBoard(game.getSelBoard(), g, selXLoc);
        paintBoard(game.getGameBoard(), g, FIFTY);

        if (win) {
            win(g);
        }
    }

    /**
     * Calls the super repaint method.
     */
    public void repaint() {
        super.repaint();
    }

    /**
     * Paints a winning message.
     * @param g
     */
    public void win(Graphics g) {

        g.setColor(new Color(231, 138, 176));
        g.fill3DRect(800, 250, 200, 200, true);
        g.setColor(Color.white);
        g.drawString("YOU WON!!!", 870, 350);
    }

    /**
     * A helper function to the paint component method, that paints the boards and the static bricks.
     * @param board the board to paint- a selection board or a game board.
     * @param g
     * @param xLocOnBoard the x location of the left side of the board.
     */
    private void paintBoard(Board board, Graphics g, int xLocOnBoard) {

        g.setColor(Color.BLACK);

        //The lines and border
        for (int i = 0; i < board.getSize(); i++) {
            for (int j = 0; j < board.getSize(); j++) {
                g.drawRect(xLocOnBoard + (j * FIFTY), HUNDRED + (i * FIFTY), FIFTY, FIFTY);
            }
        }

        for (int i = 0; i < board.getSize(); i++) {
            for (int j = 0; j < board.getSize(); j++) {

                int middleX = (((xLocOnBoard + (j * FIFTY)) + (xLocOnBoard + FIFTY + (j * FIFTY))) / 2);
                int middleY = (HUNDRED + (i * FIFTY) + HUNDREDFIFTY + (i * FIFTY)) / 2;

                int firstX = xLocOnBoard + (j * FIFTY);
                int firstY = HUNDRED + (i * FIFTY);
                int secondX = xLocOnBoard + FIFTY + (j * FIFTY);
                int secondY = HUNDRED + (i * FIFTY);

                int xPoints1[] = {firstX, secondX, middleX};
                int yPoints1[] = {firstY, secondY, middleY};

                if (board.getBrick(i, j) != null) {

                    drawBorder(g, firstX, firstY, secondX, secondY, middleX, middleY);


                    g.setColor(chooseColor(board.getBrick(i, j).getUp()));
                    g.fillPolygon(xPoints1, yPoints1, 3);
                    g.setColor(new Color(46, 46, 46));
                    g.drawString(Integer.toString(board.getBrick(i, j).getUp()), middleX - 2, ((middleY + firstY) / 2) + 5);

                    firstX = xLocOnBoard + FIFTY + (j * FIFTY);
                    firstY = HUNDRED + (i * FIFTY);
                    secondX = xLocOnBoard + FIFTY + (j * FIFTY);
                    secondY = HUNDREDFIFTY + (i * FIFTY);

                    drawBorder(g, firstX, firstY, secondX, secondY, middleX, middleY);

                    int xPoints2[] = {firstX, secondX, middleX};
                    int yPoints2[] = {firstY, secondY, middleY};
                    g.setColor(chooseColor(board.getBrick(i, j).getRight()));
                    g.fillPolygon(xPoints2, yPoints2, 3);
                    g.setColor(new Color(46, 46, 46));
                    g.drawString(Integer.toString(board.getBrick(i, j).getRight()), (middleX + firstX) / 2, middleY + 2);

                    firstX = xLocOnBoard + FIFTY + (j * FIFTY);
                    firstY = HUNDREDFIFTY + (i * FIFTY);
                    secondX = xLocOnBoard + (j * FIFTY);
                    secondY = HUNDREDFIFTY + (i * FIFTY);

                    drawBorder(g, firstX, firstY, secondX, secondY, middleX, middleY);

                    int xPoints3[] = {firstX, secondX, middleX};
                    int yPoints3[] = {firstY, secondY, middleY};
                    g.setColor(chooseColor(board.getBrick(i, j).getDown()));
                    g.fillPolygon(xPoints3, yPoints3, 3);
                    g.setColor(new Color(46, 46, 46));
                    g.drawString(Integer.toString(board.getBrick(i, j).getDown()), middleX - 2, ((middleY + firstY) / 2) + 5);

                    firstX = xLocOnBoard + (j * FIFTY);
                    firstY = HUNDREDFIFTY + (i * FIFTY);
                    secondX = xLocOnBoard + (j * FIFTY);
                    secondY = HUNDRED + (i * FIFTY);

                    drawBorder(g, firstX, firstY, secondX, secondY, middleX, middleY);

                    int xPoints4[] = {firstX, secondX, middleX};
                    int yPoints4[] = {firstY, secondY, middleY};
                    g.setColor(chooseColor(board.getBrick(i, j).getLeft()));
                    g.fillPolygon(xPoints4, yPoints4, 3);
                    g.setColor(new Color(46, 46, 46));
                    g.drawString(Integer.toString(board.getBrick(i, j).getLeft()), ((middleX + firstX) / 2) - 5, middleY + 2);

                }
            }

        }

    }

    /**
     * Paints the border of each triangle
     * @param g
     * @param fX first x
     * @param fY first y
     * @param sX second x
     * @param sY second y
     * @param mX third x
     * @param mY third y
     */
    private void drawBorder(Graphics g, int fX, int fY, int sX, int sY, int mX, int mY) {
        g.setColor(Color.black);
        g.drawLine(fX, fY, sX, sY);
        g.drawLine(sX, sY, mX, mY);
        g.drawLine(mX, mY, fX, fY);
    }

    /**
     * A helper function to the paint component method. Paints the bricks when they are moving.
     * @param g
     */
    private void paintAlive(Graphics g) {


        int middleX = (this.currX + this.currX + 50) / 2;
        int middleY = (this.currY + this.currY + 50) / 2;

        int firstX = this.currX;
        int firstY = this.currY;
        int secondX = this.currX + 50;
        int secondY = this.currY;

        drawBorder(g, firstX, firstY, secondX, secondY, middleX, middleY);

        int xPoints1[] = {firstX, secondX, middleX};
        int yPoints1[] = {firstY, secondY, middleY};

        g.setColor(chooseColor(currBrick.getUp()));
        g.fillPolygon(xPoints1, yPoints1, 3);
        g.setColor(new Color(46, 46, 46));
        g.drawString(Integer.toString(currBrick.getUp()), middleX - 2, ((middleY + firstY) / 2) + 5);

        firstX = this.currX + 50;
        firstY = this.currY;
        secondX = this.currX + 50;
        secondY = this.currY + 50;

        drawBorder(g, firstX, firstY, secondX, secondY, middleX, middleY);

        int xPoints2[] = {firstX, secondX, middleX};
        int yPoints2[] = {firstY, secondY, middleY};
        g.setColor(chooseColor(currBrick.getRight()));
        g.fillPolygon(xPoints2, yPoints2, 3);
        g.setColor(new Color(46, 46, 46));
        g.drawString(Integer.toString(currBrick.getRight()), (middleX + firstX) / 2, middleY + 2);

        firstX = this.currX + 50;
        firstY = this.currY + 50;
        secondX = this.currX;
        secondY = this.currY + 50;

        drawBorder(g, firstX, firstY, secondX, secondY, middleX, middleY);

        int xPoints3[] = {firstX, secondX, middleX};
        int yPoints3[] = {firstY, secondY, middleY};
        g.setColor(chooseColor(currBrick.getDown()));
        g.fillPolygon(xPoints3, yPoints3, 3);
        g.setColor(new Color(46, 46, 46));
        g.drawString(Integer.toString(currBrick.getDown()), middleX - 2, ((middleY + firstY) / 2) + 5);

        firstX = this.currX;
        firstY = this.currY + 50;
        secondX = this.currX;
        secondY = this.currY;

        drawBorder(g, firstX, firstY, secondX, secondY, middleX, middleY);

        int xPoints4[] = {firstX, secondX, middleX};
        int yPoints4[] = {firstY, secondY, middleY};
        g.setColor(chooseColor(currBrick.getLeft()));
        g.fillPolygon(xPoints4, yPoints4, 3);
        g.setColor(new Color(46, 46, 46));
        g.drawString(Integer.toString(currBrick.getLeft()), ((middleX + firstX) / 2) - 5, middleY + 2);


    }

    /**
     * A helper function that matches every digit a specific color.
     * @param digit the digit that will be matched to a specific color.
     * @return the color that is matched to the digit.
     */
    private Color chooseColor(int digit) {

        switch (digit) {
            case 0:
                return new Color(226, 226, 226);
            case 1:
                return new Color(255, 168, 211);
            case 2:
                return new Color(255, 225, 239);
            case 3:
                return new Color(153, 217, 234);
            case 4:
                return new Color(200, 191, 231);
            case 5:
                return new Color(0, 163, 240);
            case 6:
                return new Color(255, 146, 166);
            case 7:
                return new Color(172, 89, 255);
            case 8:
                return new Color(227, 221, 246);
            case 9:
                return Color.cyan;
        }
        return null; // case default
    }
}