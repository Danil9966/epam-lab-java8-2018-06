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

    public static void main(String[] args) throws FileNotFoundException {
        JFrame mainFrame = new JFrame();
        mainFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        JPanel rootPanel = new JPanel();
        rootPanel.setLayout(new BoxLayout(rootPanel, BoxLayout.Y_AXIS));
        rootPanel.setPreferredSize(new Dimension(400, 560));

        JPanel canvas = new JPanel();
        canvas.setPreferredSize(new Dimension(400, 440));

        rootPanel.add(canvas, BorderLayout.CENTER);

        JPanel controls = new JPanel(null);
        controls.setPreferredSize(new Dimension(400, 120));

        JButton up = new JButton("↑");
        up.setBounds(190, 10, 30, 30);
        up.setFocusPainted(false);
        up.setBorder(BorderFactory.createEmptyBorder(0,0,0,0));
        controls.add(up);

        JButton right = new JButton("→");
        right.setBounds(220, 40, 30, 30);
        right.setFocusPainted(false);
        right.setBorder(BorderFactory.createEmptyBorder(0,0,0,0));
        controls.add(right);

        JButton down = new JButton("↓");
        down.setBounds(190, 70, 30, 30);
        down.setFocusPainted(false);
        down.setBorder(BorderFactory.createEmptyBorder(0,0,0,0));
        controls.add(down);

        JButton left = new JButton("←");
        left.setBounds(160, 40, 30, 30);
        left.setFocusPainted(false);
        left.setBorder(BorderFactory.createEmptyBorder(0,0,0,0));
        controls.add(left);

        rootPanel.add(controls, BorderLayout.SOUTH);

        mainFrame.setContentPane(rootPanel);
        mainFrame.pack();
        mainFrame.setLocationRelativeTo(null);
        mainFrame.setVisible(true);

        Scanner scanner = new Scanner(new File("field1.dat"));
        Model model = Model.restore(scanner);

        View view = new View();
        view.setGraphics(new SwingGraphicsAdapter(mainFrame, (Graphics2D) canvas.getGraphics()));

        Controller controller = new Controller(model, view);

        up.addActionListener(e -> controller.moveUp());
        down.addActionListener(e -> controller.moveDown());
        left.addActionListener(e -> controller.moveLeft());
        right.addActionListener(e -> controller.moveRight());

        canvas.setFocusable(true);
        canvas.grabFocus();
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
