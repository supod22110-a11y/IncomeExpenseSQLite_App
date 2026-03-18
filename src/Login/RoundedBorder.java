package Login;

import java.awt.*;
import javax.swing.border.Border;

public class RoundedBorder implements Border {

    private int radius;
    private Color color;

    public RoundedBorder(int radius) {
        this(radius, new Color(200, 200, 200));
    }

    public RoundedBorder(int radius, Color color) {
        this.radius = radius;
        this.color = color;
    }

    @Override
    public Insets getBorderInsets(Component c) {
        return new Insets(5, 10, 5, 10);
    }

    @Override
    public boolean isBorderOpaque() {
        return false;
    }

    @Override
    public void paintBorder(Component c, Graphics g, int x, int y, int width, int height) {
        g.setColor(color);
        g.drawRoundRect(x, y, width - 1, height - 1, radius, radius);
    }
}