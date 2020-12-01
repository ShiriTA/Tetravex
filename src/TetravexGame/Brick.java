package TetravexGame;

public class Brick {

    //----------Fields----------
    private int left;
    private int right;
    private int up;
    private int down;
    private boolean blank;

    //----------Constructor----------
    public Brick (int left, int right, int up, int down, boolean blank) {
        this.left = left;
        this.right = right;
        this.up = up;
        this.down = down;
        this.blank = blank;
    }

    //----------Methods----------
    public int getLeft(){return this.left;}
    public int getRight(){return this.right;}
    public int getUp(){return this.up;}
    public int getDown(){return this.down;}
    public boolean getBlank(){return this.blank;}

}