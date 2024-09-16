package codes.domino.ui;

import javax.swing.*;
import java.awt.*;

public class TrainerUI {
    public static void startUI() {
        JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 400);
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);
        JLabel label = new JLabel("Press F5 to start training");
        label.setFont(new Font("Arial", Font.BOLD, 30));
        label.setHorizontalAlignment(JLabel.CENTER);
        frame.add(label);
        frame.setVisible(true);
    }
}
