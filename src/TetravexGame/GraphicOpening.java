package TetravexGame;

import javax.swing.*;
import java.awt.*;

public class GraphicOpening extends JPanel {


    //----------Fields----------
    private boolean lightMode;

    //----------Constructor----------
    public GraphicOpening (boolean lightMode) {
        this.lightMode = lightMode;
    }

    //----------Methods----------
    public boolean getMode() {
        return this.lightMode;
    }

    public void setMode() {
        this.lightMode = !this.lightMode;
    }
    /**
     * Calls the super paint component method. Paints the squares that represent the options of board size.
     * @param g
     */
    public void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;
        super.paintComponent(g2);

        g2.setRenderingHint(
                RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON);

        if (this.lightMode) {
            this.setBackground(Color.white);
        } else {
            this.setBackground(Color.darkGray);
        }

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

    /**
     * Calls the super repaint method.
     */
    public void repaint() {
        super.repaint();
    }
}
