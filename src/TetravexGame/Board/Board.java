package TetravexGame.Board;

import TetravexGame.Brick;

public interface Board {

    int getSize();
    Brick getBrick(int i, int j);
    void setBrick(Brick b, int i, int j);

}
