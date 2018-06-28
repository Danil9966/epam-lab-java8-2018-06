package game.bounceball;

import game.bounceball.controller.Controller;
import game.bounceball.model.Model;
import game.bounceball.view.SwingGraphicsAdapter;
import game.bounceball.view.View;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Launcher {

    public static void main(String[] args) throws FileNotFoundException {
        JFrame mainFrame = new JFrame();
        mainFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        JPanel rootPanel = new JPanel() {
            @Override
            public void paint(Graphics g) {
                super.paint(g);
            }
        };
        rootPanel.setPreferredSize(new Dimension(400, 700));

        mainFrame.add(rootPanel);
        mainFrame.pack();
        mainFrame.setLocationRelativeTo(null);
        mainFrame.setVisible(true);


        Scanner scanner = new Scanner(new File("field1.dat"));
        Model model = Model.restore(scanner);
        View view = new View();
        view.setGraphics(new SwingGraphicsAdapter((Graphics2D) rootPanel.getGraphics()));

        Controller controller = new Controller(model, view);



    }
}
