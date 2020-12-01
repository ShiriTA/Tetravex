package TetravexGame.Board;

import TetravexGame.Brick;

public class SelectionBoard implements Board {

    //----------Fields----------
    private int size;
    private Brick[][] bricks;

    //----------Constructor----------
    public SelectionBoard(int size) {
        this.size = size;

        this.bricks = new Brick[size][size];

        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                bricks[i][j] = null;
            }
        }
    }

    //----------Methods----------
    public int getSize() {
        return this.size;
    }

    public Brick getBrick(int i, int j) {
        return this.bricks[i][j];
    }

    public void setBrick(Brick b, int i, int j) {
        this.bricks[i][j] = b;
    }


}