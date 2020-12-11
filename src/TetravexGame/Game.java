package TetravexGame;

import TetravexGame.Board.Board;
import TetravexGame.Board.GameBoard;
import TetravexGame.Board.InitBoard;
import TetravexGame.Board.SelectionBoard;

public class Game {

    //----------Fields----------
    private GameBoard board;
    private InitBoard initBoard;
    private SelectionBoard selBoard;
    private int boardSize;

    //----------Constructor----------
    public Game(GameBoard board, InitBoard initBoard) {
        this.board = board;
        this.initBoard = initBoard;
        this.selBoard = new SelectionBoard(board.getSize());
        this.boardSize = board.getSize();

        randomizeLocation();
    }

    //----------Methods----------

    public GameBoard getGameBoard () { return this.board;}
    public SelectionBoard getSelBoard () { return this.selBoard;}
    public InitBoard getInitBoard () { return this.initBoard;}
    public int getBoardSize () { return this.boardSize;}

    /**
     * Gives every brick a random location on the board.
     */
    public void randomizeLocation() {

        for (int i = 0; i < boardSize; i++) {
            for (int j = 0; j < boardSize; j++) {

                double dRandI = Math.random() * boardSize;
                int iRandI = (int) dRandI;

                double dRandJ = Math.random() * boardSize;
                int iRandJ = (int) dRandJ;

                while (selBoard.getBrick(iRandI, iRandJ) != null) {
                    dRandI = Math.random() * boardSize;
                    iRandI = (int) dRandI;

                    dRandJ = Math.random() * boardSize;
                    iRandJ = (int) dRandJ;
                }

                selBoard.setBrick(initBoard.getBrick(i, j), iRandI, iRandJ);
            }
        }


    }

    /**
     * Checks whether it is possible to move a brick to location (i,j) on the game board
     * @param b the brick that we try to move
     * @param i the line that we try to move the brick to
     * @param j the column that we try to move the brick to
     * @return whether it is possible to move the brick or not
     */
    public boolean ableToMove(Brick b, int i, int j) {


        if (i == 0 && j == 0) {
            Brick rightBrick = board.getBrick(i, j + 1);
            Brick downBrick = board.getBrick(i + 1, j);
            if (rightBrick != null && downBrick != null) {
                return b.getRight() == rightBrick.getLeft() && b.getDown() == downBrick.getUp();
            } else if (rightBrick != null && downBrick == null) {
                return b.getRight() == rightBrick.getLeft();
            } else if (downBrick != null && rightBrick == null) {
                return b.getDown() == downBrick.getUp();
            } else {
                return true;
            }
        } else if (i == (board.getSize() - 1) && j == (board.getSize() - 1)) {
            Brick leftBrick = board.getBrick(i, j - 1);
            Brick upBrick = board.getBrick(i - 1, j);
            if (leftBrick != null && upBrick != null) {
                return b.getLeft() == leftBrick.getRight() && b.getUp() == upBrick.getDown();
            } else if (leftBrick != null && upBrick == null) {
                return b.getLeft() == leftBrick.getRight();
            } else if (leftBrick == null && upBrick != null) {
                return b.getUp() == upBrick.getDown();
            } else {
                return true;
            }
        } else if (i == 0 && j == (board.getSize() - 1)) {
            Brick leftBrick = board.getBrick(i, j - 1);
            Brick downBrick = board.getBrick(i + 1, j);
            if (leftBrick != null && downBrick != null) {
                return b.getLeft() == leftBrick.getRight() && b.getDown() == downBrick.getUp();
            } else if (leftBrick != null && downBrick == null) {
                return b.getLeft() == leftBrick.getRight();
            } else if (leftBrick == null && downBrick != null) {
                return b.getDown() == downBrick.getUp();
            } else {
                return true;
            }
        } else if (i == (board.getSize() - 1) && j == 0) {
            Brick rightBrick = board.getBrick(i, j + 1);
            Brick upBrick = board.getBrick(i - 1, j);
            if (rightBrick != null && upBrick != null) {
                return b.getRight() == rightBrick.getLeft() && b.getUp() == upBrick.getDown();
            } else if (rightBrick != null && upBrick == null) {
                return b.getRight() == rightBrick.getLeft();
            } else if (rightBrick == null && upBrick != null) {
                return b.getUp() == upBrick.getDown();
            } else {
                return true;
            }
        } else if (i == 0) {
            Brick leftBrick = board.getBrick(i, j - 1);
            Brick rightBrick = board.getBrick(i, j + 1);
            Brick downBrick = board.getBrick(i + 1, j);
            if (leftBrick != null && rightBrick != null && downBrick != null) {
                return b.getLeft() == leftBrick.getRight() && b.getRight() == rightBrick.getLeft() && b.getDown() == downBrick.getUp();
            } else if (leftBrick != null && rightBrick != null && downBrick == null) {
                return b.getLeft() == leftBrick.getRight() && b.getRight() == rightBrick.getLeft();
            } else if (leftBrick != null && rightBrick == null && downBrick != null) {
                return b.getLeft() == leftBrick.getRight() && b.getDown() == downBrick.getUp();
            } else if (leftBrick == null && rightBrick != null && downBrick != null) {
                return b.getRight() == rightBrick.getLeft() && b.getDown() == downBrick.getUp();
            } else if (leftBrick != null && rightBrick == null && downBrick == null) {
                return b.getLeft() == leftBrick.getRight();
            } else if (leftBrick == null && rightBrick != null && downBrick == null) {
                return b.getRight() == rightBrick.getLeft();
            } else if (leftBrick == null && rightBrick == null && downBrick != null) {
                return b.getDown() == downBrick.getUp();
            } else {
                return true;
            }
        } else if (j == 0) {
            Brick rightBrick = board.getBrick(i, j + 1);
            Brick upBrick = board.getBrick(i - 1, j);
            Brick downBrick = board.getBrick(i + 1, j);
            if (rightBrick != null && upBrick != null && downBrick != null) {
                return b.getRight() == rightBrick.getLeft() && b.getUp() == upBrick.getDown() && b.getDown() == downBrick.getUp();
            } else if (rightBrick != null && upBrick != null && downBrick == null) {
                return b.getRight() == rightBrick.getLeft() && b.getUp() == upBrick.getDown();
            } else if (rightBrick != null && upBrick == null && downBrick != null) {
                return b.getRight() == rightBrick.getLeft() && b.getDown() == downBrick.getUp();
            } else if (rightBrick == null && upBrick != null && downBrick != null) {
                return b.getUp() == upBrick.getDown() && b.getDown() == downBrick.getUp();
            } else if (rightBrick != null && upBrick == null && downBrick == null) {
                return b.getRight() == rightBrick.getLeft();
            } else if (rightBrick == null && upBrick != null && downBrick == null) {
                return b.getUp() == upBrick.getDown();
            } else if (rightBrick == null && upBrick == null && downBrick != null) {
                return b.getDown() == downBrick.getUp();
            } else {
                return true;
            }
        } else if (i == (board.getSize() - 1)) {
            Brick leftBrick = board.getBrick(i, j - 1);
            Brick rightBrick = board.getBrick(i, j + 1);
            Brick upBrick = board.getBrick(i - 1, j);
            if (leftBrick != null && rightBrick != null && upBrick != null) {
                return b.getLeft() == leftBrick.getRight() && b.getRight() == rightBrick.getLeft() && b.getUp() == upBrick.getDown();
            } else if (leftBrick != null && rightBrick != null && upBrick == null) {
                return b.getLeft() == leftBrick.getRight() && b.getRight() == rightBrick.getLeft();
            } else if (leftBrick != null && rightBrick == null && upBrick != null) {
                return b.getLeft() == leftBrick.getRight() && b.getUp() == upBrick.getDown();
            } else if (leftBrick == null && rightBrick != null && upBrick != null) {
                return b.getRight() == rightBrick.getLeft() && b.getUp() == upBrick.getDown();
            } else if (leftBrick != null && rightBrick == null && upBrick == null) {
                return b.getLeft() == leftBrick.getRight();
            } else if (leftBrick == null && rightBrick != null && upBrick == null) {
                return b.getRight() == rightBrick.getLeft();
            } else if (leftBrick == null && rightBrick == null && upBrick != null) {
                return b.getUp() == upBrick.getDown();
            } else {
                return true;
            }
        } else if (j == (board.getSize() - 1)) {
            Brick leftBrick = board.getBrick(i, j - 1);
            Brick upBrick = board.getBrick(i - 1, j);
            Brick downBrick = board.getBrick(i + 1, j);
            if (leftBrick != null && upBrick != null && downBrick != null) {
                return b.getLeft() == leftBrick.getRight() && b.getUp() == upBrick.getDown() && b.getDown() == downBrick.getUp();
            } else if (leftBrick != null && upBrick != null && downBrick == null) {
                return b.getLeft() == leftBrick.getRight() && b.getUp() == upBrick.getDown();
            } else if (leftBrick != null && upBrick == null && downBrick != null) {
                return b.getLeft() == leftBrick.getRight() && b.getDown() == downBrick.getUp();
            } else if (leftBrick == null && upBrick != null && downBrick != null) {
                return b.getUp() == upBrick.getDown() && b.getDown() == downBrick.getUp();
            } else if (leftBrick != null && upBrick == null && downBrick == null) {
                return b.getLeft() == leftBrick.getRight();
            } else if (leftBrick == null && upBrick != null && downBrick == null) {
                return b.getUp() == upBrick.getDown();
            } else if (leftBrick == null && upBrick == null && downBrick != null) {
                return b.getDown() == downBrick.getUp();
            } else {
                return true;
            }
        } else {
            Brick leftBrick = board.getBrick(i, j - 1);
            Brick rightBrick = board.getBrick(i, j + 1);
            Brick upBrick = board.getBrick(i - 1, j);
            Brick downBrick = board.getBrick(i + 1, j);

            if (leftBrick != null && rightBrick != null && upBrick != null && downBrick != null) {
                return b.getLeft() == leftBrick.getRight() && b.getRight() == rightBrick.getLeft() && b.getUp() == upBrick.getDown() && b.getDown() == downBrick.getUp();
            } else if (leftBrick != null && rightBrick != null && upBrick != null && downBrick == null) {
                return b.getLeft() == leftBrick.getRight() && b.getRight() == rightBrick.getLeft() && b.getUp() == upBrick.getDown();
            } else if (leftBrick != null && rightBrick != null && upBrick == null && downBrick != null) {
                return b.getLeft() == leftBrick.getRight() && b.getRight() == rightBrick.getLeft() && b.getDown() == downBrick.getUp();
            } else if (leftBrick != null && rightBrick == null && upBrick != null && downBrick != null) {
                return b.getLeft() == leftBrick.getRight() && b.getUp() == upBrick.getDown() && b.getDown() == downBrick.getUp();
            } else if (leftBrick == null && rightBrick != null && upBrick != null && downBrick != null) {
                return b.getRight() == rightBrick.getLeft() && b.getUp() == upBrick.getDown() && b.getDown() == downBrick.getUp();
            } else if (leftBrick != null && rightBrick != null && upBrick == null && downBrick == null) {
                return b.getLeft() == leftBrick.getRight() && b.getRight() == rightBrick.getLeft();
            } else if (leftBrick != null && rightBrick == null && upBrick != null && downBrick == null) {
                return b.getLeft() == leftBrick.getRight() && b.getUp() == upBrick.getDown();
            } else if (leftBrick == null && rightBrick != null && upBrick != null && downBrick == null) {
                return b.getRight() == rightBrick.getLeft() && b.getUp() == upBrick.getDown();
            } else if (leftBrick != null && rightBrick == null && upBrick == null && downBrick != null) {
                return b.getLeft() == leftBrick.getRight() && b.getDown() == downBrick.getUp();
            } else if (leftBrick == null && rightBrick != null && upBrick == null && downBrick != null) {
                return b.getRight() == rightBrick.getLeft() && b.getDown() == downBrick.getUp();
            } else if (leftBrick == null && rightBrick == null && upBrick != null && downBrick != null) {
                return b.getUp() == upBrick.getDown() && b.getDown() == downBrick.getUp();
            } else if (leftBrick != null && rightBrick == null && upBrick == null && downBrick == null) {
                return b.getLeft() == leftBrick.getRight();
            } else if (leftBrick == null && rightBrick != null && upBrick == null && downBrick == null) {
                return b.getRight() == rightBrick.getLeft();
            } else if (leftBrick == null && rightBrick == null && upBrick != null && downBrick == null) {
                return b.getUp() == upBrick.getDown();
            } else if (leftBrick == null && rightBrick == null && upBrick == null && downBrick != null) {
                return b.getDown() == downBrick.getUp();
            } else {
                return true;
            }
        }

    }

    /**
     * Moving a brick from srcBoard to destBoard, if possible
     * @param srcBoard the board that we move the brick from
     * @param destBoard the board that we move the brick to
     * @param fromI the line on the source board that we move the brick from
     * @param fromJ the column on the source board that we move the brick from
     * @param toI the line on the destination board that we move the brick to
     * @param toJ the column on the destination board that we move the brick to
     * @return whether the moving has been performed or not
     */
    public boolean moveBrick(Board srcBoard, Board destBoard, int fromI, int fromJ, int toI, int toJ) {

        if (srcBoard.getBrick(fromI, fromJ) == null || ((srcBoard == destBoard && fromI == toI && fromJ == toJ)) || destBoard.getBrick(toI, toJ) != null) {
            return false;
        }

        Brick brick = srcBoard.getBrick(fromI, fromJ);

        if (destBoard instanceof SelectionBoard) {
            destBoard.setBrick(brick, toI, toJ);
            srcBoard.setBrick(null, fromI, fromJ);
            return true;
        }

        srcBoard.setBrick(null, fromI, fromJ);
        System.out.println(fromI);
        if (ableToMove(brick, toI, toJ)) {
            destBoard.setBrick(brick, toI, toJ);
            return true;
        }

        srcBoard.setBrick(brick, fromI, fromJ);
        return false;
    }

}