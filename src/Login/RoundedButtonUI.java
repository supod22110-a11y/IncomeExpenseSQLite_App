/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Login;

import java.awt.*;
import javax.swing.*;
import javax.swing.plaf.basic.BasicButtonUI;

/**
 * Simple UI delegate that paints a button with rounded corners.
 * Extends BasicButtonUI so it can be installed via setUI().
 */
public class RoundedButtonUI extends BasicButtonUI {

    private final int radius;

    public RoundedButtonUI() {
        this(15);
    }

    public RoundedButtonUI(int radius) {
        this.radius = radius;
    }

    @Override
    public void installUI(JComponent c) {
        super.installUI(c);
        AbstractButton button = (AbstractButton) c;
        button.setOpaque(false); // we'll paint the background ourselves
        button.setBorder(new RoundedBorder(radius));
    }

    @Override
    public void paint(Graphics g, JComponent c) {
        AbstractButton b = (AbstractButton) c;
        paintBackground(g, b);
        super.paint(g, c);
    }

    private void paintBackground(Graphics g, AbstractButton b) {
        Graphics2D g2 = (Graphics2D) g.create();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        int w = b.getWidth();
        int h = b.getHeight();
        g2.setColor(b.getBackground());
        g2.fillRoundRect(0, 0, w, h, radius, radius);
        g2.dispose();
    }
}

