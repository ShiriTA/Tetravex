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
        Graphics2D g2 = (Graphics2D) g;
        super.paintComponent(g2);
        this.setBackground(Color.white);

//        g2.setColor(new Color(255, 146, 166));
//        g2.fill3DRect(250, 25, 50, 50, true);



        g2.setRenderingHint(
                RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON);

        g2.setColor(new Color(255, 146, 166));
        g2.fill3DRect(100, 100, 50, 50, true);
        g2.fill3DRect(200, 100, 50, 50, true);
        g2.fill3DRect(300, 100, 50, 50, true);
        g2.fill3DRect(400, 100, 50, 50, true);
        g2.fill3DRect(500, 100, 50, 50, true);

        g2.setColor(Color.white);
        g2.drawString("2", 125, 125);
        g2.drawString("3", 225, 125);
        g2.drawString("4", 325, 125);
        g2.drawString("5", 425, 125);
        g2.drawString("6", 525, 125);
    }
}
