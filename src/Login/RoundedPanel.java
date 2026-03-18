package Login;

import java.awt.*;
import javax.swing.*;

public class RoundedPanel extends JPanel {

    private final int cornerRadius;

    public RoundedPanel(int radius) {
        super();
        cornerRadius = radius;
        setOpaque(false);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON);

        g2.setColor(Color.WHITE);
        g2.fillRoundRect(0, 0, getWidth(), getHeight(),
                cornerRadius, cornerRadius);

        g2.setColor(new Color(0, 0, 0, 40));
        g2.fillRoundRect(5, 5, getWidth() - 5, getHeight() - 5,
                cornerRadius, cornerRadius);
    }

}
