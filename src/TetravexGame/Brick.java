package TetravexGame;

public class Brick {

    //----------Fields----------
    private int left;
    private int right;
    private int up;
    private int down;

    //----------Constructor----------
    public Brick (int left, int right, int up, int down) {
        this.left = left;
        this.right = right;
        this.up = up;
        this.down = down;
    }

    //----------Methods----------
    public int getLeft(){return this.left;}
    public int getRight(){return this.right;}
    public int getUp(){return this.up;}
    public int getDown(){return this.down;}

}