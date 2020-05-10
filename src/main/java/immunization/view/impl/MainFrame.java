package immunization.view.impl;

import javax.swing.JFrame;

import immunization.controller.TopController;

import java.awt.CardLayout;
import java.awt.Dimension;

public class MainFrame {

	protected JFrame frame;

	/**
	 * Create the application.
	 */
	public MainFrame() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {

		frame = new JFrame();
		// default window size
		frame.setSize(new Dimension(500, 400));
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(new CardLayout(0, 0));

		TopController top = new TopController();
		top.setMainMenuPanel(frame);
	}
}
