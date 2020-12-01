package TetravexGame;

import javax.swing.*;
import java.awt.*;

public class GraphicOpening extends JPanel {

    //----------Fields----------

    //----------Constructor----------
    public GraphicOpening () {}

    //----------Methods----------

    /**
     * Calls the super paint component method. Paints the squares that represent the options of board size.
     * @param g
     */
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        this.setBackground(Color.white);

//        g.setColor(new Color(255, 146, 166));
//        g.fill3DRect(250, 25, 50, 50, true);

        g.setColor(new Color(255, 146, 166));
        g.fill3DRect(100, 100, 50, 50, true);
        g.fill3DRect(200, 100, 50, 50, true);
        g.fill3DRect(300, 100, 50, 50, true);
        g.fill3DRect(400, 100, 50, 50, true);
        g.fill3DRect(500, 100, 50, 50, true);

        g.setColor(Color.white);
        g.drawString("2", 125, 125);
        g.drawString("3", 225, 125);
        g.drawString("4", 325, 125);
        g.drawString("5", 425, 125);
        g.drawString("6", 525, 125);
    }
}
