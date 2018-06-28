package game.tetris;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class Tetris {

	protected static final Color[] COLORS = { Color.black, Color.yellow,
			Color.blue, Color.green, Color.magenta, Color.cyan};

	public static void main(final String[] args) {

		Model model = new Model();

		final Controller controller = new Controller();

		model.addListener(controller);

		View view = new View();
		controller.setView(view);
		controller.setModel(model);

		JFrame frame = new JFrame("game.tetris.Tetris");

		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		JPanel panel = new JPanel();
		panel.setPreferredSize(new Dimension(400, 700));


		frame.addKeyListener(new KeyAdapter() {

			@Override
			public void keyPressed(final KeyEvent e) {
				switch (e.getKeyCode()) {
				case KeyEvent.VK_LEFT:
					controller.moveLeft();
					break;

				case KeyEvent.VK_RIGHT:
					controller.moveRight();
					break;

				default:
					break;
				}
			}

		});
		
		Thread thread = new Thread(new Runnable() {
			@Override
			public void run() {
				while (true) {
					try {
						Thread.sleep(1000);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					controller.moveDown();
				}
			}
		});
		thread.setDaemon(true);
		thread.start();

		frame.add(panel);

		frame.pack();

		frame.setVisible(true);

		final Graphics2D graphics = (Graphics2D) panel.getGraphics();
		
		view.setGraphics(new Graphics() {
			
			@Override
			public void fillRect(final int x, final int y, final int width,
					final int height, final int colorIndex) {
				graphics.setColor(COLORS[colorIndex]);
				graphics.fillRect(x, y, width, height);
			}
		});
	}

}
