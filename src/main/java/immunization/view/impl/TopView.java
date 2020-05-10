package immunization.view.impl;

import java.awt.EventQueue;

/**
 * A class that provides the user with basic console-based UI.
 */
public class TopView {
	
	public void displayMainFrame() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainFrame window = new MainFrame();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
}
