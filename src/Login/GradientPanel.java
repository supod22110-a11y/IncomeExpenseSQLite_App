
package Login;

import java.awt.*;
import javax.swing.*;

public class GradientPanel extends JPanel {

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;

        int width = getWidth();
        int height = getHeight();

        Color color1 = new Color(52, 152, 219);
        Color color2 = new Color(155, 89, 182);

        GradientPaint gp = new GradientPaint(
                0, 0, color1,
                width, height, color2);

        g2d.setPaint(gp);
        g2d.fillRect(0, 0, width, height);
    }
}
