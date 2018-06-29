package game.bounceball.swing;

import game.bounceball.controller.Controller;
import game.bounceball.model.Model;
import game.bounceball.view.View;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Launcher {

    public static void main(String[] args) {
        SwingUtilities.invokeLater(Launcher::createAndShowGUI);
    }

    private static void createAndShowGUI() {
        JPanel rootPanel = new JPanel();
        rootPanel.setLayout(new BoxLayout(rootPanel, BoxLayout.Y_AXIS));
        rootPanel.setPreferredSize(new Dimension(400, 560));

        JPanel canvas = new JPanel();
        canvas.setPreferredSize(new Dimension(400, 440));
        rootPanel.add(canvas, BorderLayout.CENTER);

        ControlPanel controls = new ControlPanel();
        controls.setPreferredSize(new Dimension(400, 120));
        rootPanel.add(controls, BorderLayout.SOUTH);

        JFrame mainFrame = new JFrame();
        mainFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        mainFrame.setContentPane(rootPanel);
        mainFrame.pack();
        mainFrame.setLocationRelativeTo(null);
        mainFrame.setVisible(true);

        Scanner scanner = null;
        try {
            scanner = new Scanner(new File("field1.dat"));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        Model model = Model.restore(scanner);

        View view = new View();
        view.setGraphics(new SwingGraphicsAdapter(mainFrame, (Graphics2D) canvas.getGraphics()));

        Controller controller = new Controller(model, view);

        controls.addUpButtonListener(e -> controller.moveUp());
        controls.addRightButtonListener(e -> controller.moveRight());
        controls.addDownButtonListener(e -> controller.moveDown());
        controls.addLeftButtonListener(e -> controller.moveLeft());

        canvas.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent event) {
                switch (event.getKeyCode()) {
                    case KeyEvent.VK_LEFT:
                    case KeyEvent.VK_A:
                        controller.moveLeft();
                        break;

                    case KeyEvent.VK_RIGHT:
                    case KeyEvent.VK_D:
                        controller.moveRight();
                        break;

                    case KeyEvent.VK_UP:
                    case KeyEvent.VK_W:
                        controller.moveUp();
                        break;

                    case KeyEvent.VK_DOWN:
                    case KeyEvent.VK_S:
                        controller.moveDown();
                        break;
                }
            }
        });

        Timer timer = new Timer(50, e -> {
            controller.viewUpdated();
            canvas.requestFocus();
        });
        timer.setRepeats(true);
        timer.start();
    }
}
