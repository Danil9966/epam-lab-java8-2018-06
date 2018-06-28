package game.bounceball;

import javax.swing.*;
import java.awt.*;

public class Launcher {

    public static void main(String[] args) {
        JFrame mainFrame = new JFrame();
        mainFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        JPanel rootPanel = new JPanel() {
            @Override
            public void paint(Graphics g) {
                super.paint(g);
            }
        };
        rootPanel.setPreferredSize(new Dimension(400, 700));




    }
}
