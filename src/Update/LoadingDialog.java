package ui;

import javax.swing.*;
import java.awt.*;

public class LoadingDialog extends JDialog {

    public LoadingDialog(Frame parent, String message) {
        super(parent, true);

        setTitle("กำลังทำงาน...");
        setSize(300, 100);
        setLocationRelativeTo(parent);
        setLayout(new BorderLayout());

        JLabel label = new JLabel(message, SwingConstants.CENTER);

        JProgressBar progressBar = new JProgressBar();
        progressBar.setIndeterminate(true);

        add(label, BorderLayout.CENTER);
        add(progressBar, BorderLayout.SOUTH);
    }
}