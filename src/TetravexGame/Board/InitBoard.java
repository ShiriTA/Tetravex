package TetravexGame.Board;

import TetravexGame.Brick;

public class InitBoard implements Board  {

    //----------Fields----------
    private int size;
    private Brick[][] bricks;

    //----------Constructor----------
    public InitBoard(int size) {
        this.size = size;
        this.bricks = new Brick[size][size];
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

    public void initializeBoard() {

        bricks[0][0] = new Brick(randomizeZeroToNine(), randomizeZeroToNine(), randomizeZeroToNine(), randomizeZeroToNine());

        for (int i = 1; i < this.size; i++) {
            bricks[i][0] = new Brick(randomizeZeroToNine(), randomizeZeroToNine(), bricks[i - 1][0].getDown(), randomizeZeroToNine());
        }

        for (int j = 1; j < this.size; j++) {
            bricks[0][j] = new Brick(bricks[0][j - 1].getRight(), randomizeZeroToNine(), randomizeZeroToNine(), randomizeZeroToNine());
        }

        for (int i = 1; i < this.size; i++) {
            for (int j = 1; j < this.size; j++) {
                    bricks[i][j] = new Brick(bricks[i][j - 1].getRight(), randomizeZeroToNine(), bricks[i - 1][j].getDown(), randomizeZeroToNine());
            }

        }


    }

    private int randomizeZeroToNine() {
        double rand = Math.random() * 10;
        return (int) rand;
    }

}
