package TetravexGame;

import TetravexGame.Board.Board;

import javax.swing.*;
import java.awt.*;
import java.io.File;

public class GraphicTetravex extends JPanel {

    //----------Fields----------
    private Game game;
    private int currX;
    private int currY;
    private Brick currBrick;
    private boolean alive;
    private boolean win;
    final private int HUNDRED = 100;
    final private int TWOHUNDRED = 200;
    private int selXLoc;
    private boolean lightMode;


    //----------Constructor----------
    public GraphicTetravex(Game game, int currX, int currY, Brick currBrick, boolean alive, boolean lightMode) {
        this.game = game;
        this.currX = currX;
        this.currY = currY;
        this.currBrick = currBrick;
        this.alive = alive;
        this.selXLoc = TWOHUNDRED + (game.getBoardSize() * HUNDRED);
        this.win = false;
        this.lightMode = lightMode;
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

    public void setMode() {
        this.lightMode = !this.lightMode;
    }
    /**
     * Calls the super paint component method. Calls the paint board and paint alive methods in order
     * to paint the bricks on the boards, whether they are static or moving2.
     * @param g
     */
    public void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;

        super.paintComponent(g2);

        g2.setColor(new Color(255, 146, 166));
        g2.fill3DRect(0, 0, 50, 50, true);
        g2.setColor(Color.white);
        if (this.lightMode) {
            g2.drawString("Dark", 10, 20);
            g2.drawString("Mode", 10, 40);
        } else {
            g2.drawString("Light", 10, 20);
            g2.drawString("Mode", 10, 40);
        }

        g2.setColor(new Color(255, 146, 166));
        g2.fill3DRect(50, 0, 50, 50, true);
        g2.setColor(Color.white);
        g2.drawString("Main", 60, 20);
        g2.drawString("Menu", 60, 40);

        if (this.lightMode) {
            this.setBackground(Color.white);
        } else {
            this.setBackground(Color.darkGray);
        }

        if (alive) {
            paintAlive(g);
        }
        paintBoard(game.getSelBoard(), g, selXLoc);
        paintBoard(game.getGameBoard(), g, HUNDRED);

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
        Graphics2D g2 = (Graphics2D) g;

        g2.setRenderingHint(
                RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON);


        g2.setColor(new Color(231, 138, 176));
        g2.fill3DRect(800, 250, 200, 200, true);
        g2.setColor(Color.white);
        g2.setFont(new Font("TimesRoman", Font.BOLD, 40));
        g2.drawString("YOU", 840, 340);
        g2.drawString("WIN!!!", 840, 390);
    }

    /**
     * A helper function to the paint component method, that paints the boards and the static bricks.
     * @param board the board to paint- a selection board or a game board.
     * @param g
     * @param xLocOnBoard the x location of the left side of the board.
     */
    private void paintBoard(Board board, Graphics g, int xLocOnBoard) {
        Graphics2D g2 = (Graphics2D) g;

        g2.setRenderingHint(
                RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON);

        g2.setColor(Color.BLACK);

        //The lines and border
        for (int i = 0; i < board.getSize(); i++) {
            for (int j = 0; j < board.getSize(); j++) {
                g2.drawRect(xLocOnBoard + (j * HUNDRED), HUNDRED + (i * HUNDRED), HUNDRED, HUNDRED);
            }
        }

        for (int i = 0; i < board.getSize(); i++) {
            for (int j = 0; j < board.getSize(); j++) {

                int middleX = (((xLocOnBoard + (j * HUNDRED)) + (xLocOnBoard + HUNDRED + (j * HUNDRED))) / 2);
                int middleY = (HUNDRED + (i * HUNDRED) + TWOHUNDRED + (i * HUNDRED)) / 2;

                int firstX = xLocOnBoard + (j * HUNDRED);
                int firstY = HUNDRED + (i * HUNDRED);
                int secondX = xLocOnBoard + HUNDRED + (j * HUNDRED);
                int secondY = HUNDRED + (i * HUNDRED);

                int xPoints1[] = {firstX, secondX, middleX};
                int yPoints1[] = {firstY, secondY, middleY};

                if (board.getBrick(i, j) != null) {

                    drawBorder(g, firstX, firstY, secondX, secondY, middleX, middleY);


                    g2.setColor(chooseColor(board.getBrick(i, j).getUp()));
                    g2.fillPolygon(xPoints1, yPoints1, 3);
                    g2.setColor(new Color(46, 46, 46));
                    g2.setFont(new Font("TimesRoman", Font.BOLD, 30));
                    g2.drawString(Integer.toString(board.getBrick(i, j).getUp()), middleX - 7, ((middleY + firstY) / 2) + 5);

                    firstX = xLocOnBoard + HUNDRED + (j * HUNDRED);
                    firstY = HUNDRED + (i * HUNDRED);
                    secondX = xLocOnBoard + HUNDRED + (j * HUNDRED);
                    secondY = TWOHUNDRED + (i * HUNDRED);

                    drawBorder(g, firstX, firstY, secondX, secondY, middleX, middleY);

                    int xPoints2[] = {firstX, secondX, middleX};
                    int yPoints2[] = {firstY, secondY, middleY};
                    g2.setColor(chooseColor(board.getBrick(i, j).getRight()));
                    g2.fillPolygon(xPoints2, yPoints2, 3);
                    g2.setColor(new Color(46, 46, 46));

                    g2.setFont(new Font("TimesRoman", Font.BOLD, 30));
                    g2.drawString(Integer.toString(board.getBrick(i, j).getRight()), (middleX + firstX) / 2, middleY + 5);

                    firstX = xLocOnBoard + HUNDRED + (j * HUNDRED);
                    firstY = TWOHUNDRED + (i * HUNDRED);
                    secondX = xLocOnBoard + (j * HUNDRED);
                    secondY = TWOHUNDRED + (i * HUNDRED);

                    drawBorder(g, firstX, firstY, secondX, secondY, middleX, middleY);

                    int xPoints3[] = {firstX, secondX, middleX};
                    int yPoints3[] = {firstY, secondY, middleY};
                    g2.setColor(chooseColor(board.getBrick(i, j).getDown()));
                    g2.fillPolygon(xPoints3, yPoints3, 3);
                    g2.setColor(new Color(46, 46, 46));
                    g2.setFont(new Font("TimesRoman", Font.BOLD, 30));
                    g2.drawString(Integer.toString(board.getBrick(i, j).getDown()), middleX - 7, ((middleY + firstY) / 2) + 15);

                    firstX = xLocOnBoard + (j * HUNDRED);
                    firstY = TWOHUNDRED + (i * HUNDRED);
                    secondX = xLocOnBoard + (j * HUNDRED);
                    secondY = HUNDRED + (i * HUNDRED);

                    drawBorder(g, firstX, firstY, secondX, secondY, middleX, middleY);

                    int xPoints4[] = {firstX, secondX, middleX};
                    int yPoints4[] = {firstY, secondY, middleY};
                    g2.setColor(chooseColor(board.getBrick(i, j).getLeft()));
                    g2.fillPolygon(xPoints4, yPoints4, 3);
                    g2.setColor(new Color(46, 46, 46));

                    g2.setFont(new Font("TimesRoman", Font.BOLD, 30));
                    g2.drawString(Integer.toString(board.getBrick(i, j).getLeft()), ((middleX + firstX) / 2) - 12, middleY + 5);

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
        Graphics2D g2 = (Graphics2D) g;
        g2.setRenderingHint(
                RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON);

        g2.setColor(Color.black);



        g2.drawLine(fX, fY, sX, sY);
        g2.drawLine(sX, sY, mX, mY);
        g2.drawLine(mX, mY, fX, fY);
    }

    /**
     * A helper function to the paint component method. Paints the bricks when they are moving2.
     * @param g
     */
    private void paintAlive(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;
        g2.setRenderingHint(
                RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON);

        int middleX = (this.currX + this.currX + HUNDRED) / 2;
        int middleY = (this.currY + this.currY + HUNDRED) / 2;

        int firstX = this.currX;
        int firstY = this.currY;
        int secondX = this.currX + HUNDRED;
        int secondY = this.currY;

        drawBorder(g, firstX, firstY, secondX, secondY, middleX, middleY);

        int xPoints1[] = {firstX, secondX, middleX};
        int yPoints1[] = {firstY, secondY, middleY};

        g2.setColor(chooseColor(currBrick.getUp()));
        g2.fillPolygon(xPoints1, yPoints1, 3);
        g2.setColor(new Color(46, 46, 46));
        g2.setFont(new Font("TimesRoman", Font.BOLD, 30));
        g2.drawString(Integer.toString(currBrick.getUp()), middleX - 7, ((middleY + firstY) / 2) + 5);

        firstX = this.currX + HUNDRED;
        firstY = this.currY;
        secondX = this.currX + HUNDRED;
        secondY = this.currY + HUNDRED;

        drawBorder(g, firstX, firstY, secondX, secondY, middleX, middleY);

        int xPoints2[] = {firstX, secondX, middleX};
        int yPoints2[] = {firstY, secondY, middleY};
        g2.setColor(chooseColor(currBrick.getRight()));
        g2.fillPolygon(xPoints2, yPoints2, 3);
        g2.setColor(new Color(46, 46, 46));
        g2.setFont(new Font("TimesRoman", Font.BOLD, 30));
        g2.drawString(Integer.toString(currBrick.getRight()), (middleX + firstX) / 2, middleY + 5);

        firstX = this.currX + HUNDRED;
        firstY = this.currY + HUNDRED;
        secondX = this.currX;
        secondY = this.currY + HUNDRED;

        drawBorder(g, firstX, firstY, secondX, secondY, middleX, middleY);

        int xPoints3[] = {firstX, secondX, middleX};
        int yPoints3[] = {firstY, secondY, middleY};
        g2.setColor(chooseColor(currBrick.getDown()));
        g2.fillPolygon(xPoints3, yPoints3, 3);
        g2.setColor(new Color(46, 46, 46));
        g2.setFont(new Font("TimesRoman", Font.BOLD, 30));
        g2.drawString(Integer.toString(currBrick.getDown()), middleX - 7, ((middleY + firstY) / 2) + 15);

        firstX = this.currX;
        firstY = this.currY + HUNDRED;
        secondX = this.currX;
        secondY = this.currY;

        drawBorder(g, firstX, firstY, secondX, secondY, middleX, middleY);

        int xPoints4[] = {firstX, secondX, middleX};
        int yPoints4[] = {firstY, secondY, middleY};
        g2.setColor(chooseColor(currBrick.getLeft()));
        g2.fillPolygon(xPoints4, yPoints4, 3);
        g2.setColor(new Color(46, 46, 46));
        g2.setFont(new Font("TimesRoman", Font.BOLD, 30));
        g2.drawString(Integer.toString(currBrick.getLeft()), ((middleX + firstX) / 2) - 12, middleY + 5);


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