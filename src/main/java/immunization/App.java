package immunization;

import immunization.view.impl.TopView;
import immunization.controller.TopController;

public class App {

	/**
	 * Calls {@link immunization.controller.TopController}
	 * 
	 * @param args main program arguments not used
	 */

	public static void main(String[] args) {
		TopView tView = new TopView();
		TopController tController = new TopController(tView);
		tController.init();
	}
}
