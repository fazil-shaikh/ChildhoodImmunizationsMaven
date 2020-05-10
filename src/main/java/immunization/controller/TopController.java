package immunization.controller;

import immunization.view.impl.PanelMainMenu;
import immunization.view.impl.TopView;

import javax.swing.JFrame;

import immunization.controller.TopController;

public class TopController {

	private TopView tView;

	public TopController() {
	}

	/**
	 * TopController previews tView to display Main Frame
	 * 
	 * @param iView
	 */
	public TopController(TopView iView) {
		tView = iView;
	}

	/**
	 * Initializes the Main Frame
	 */
	public void init() {
		tView.displayMainFrame();
	}

	/**
	 * Creates the main menu panel
	 * 
	 * @param frame Sets main menu to current panel
	 */
	public void setMainMenuPanel(JFrame frame) {
		PanelMainMenu menu = new PanelMainMenu(frame);
		frame.getContentPane().removeAll();
		frame.getContentPane().add(menu.getPanelMenu());
	}
}
