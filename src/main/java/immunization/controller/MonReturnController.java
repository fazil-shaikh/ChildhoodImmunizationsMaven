package immunization.controller;

import javax.swing.JFrame;

import immunization.domain.MonthlyReturn;
import immunization.view.impl.PanelMonReturns;
import immunization.view.impl.PanelMonReturnsOutput;

/**
 * A controller class that handles calling the ImmRegisterView for displaying
 * and prompting for info, as well as calling the service class to handle basic
 * ImmReg-related services.
 */
public class MonReturnController {

	/**
	 * Constructor for creating a new monthly return controller using a monthly
	 * return view
	 */
	public MonReturnController() {
	}

	/**
	 * Creates the Monthly Returns panel
	 * 
	 * @param frame The Monthly Returns panel
	 */
	public void setMonReturnsPanel(JFrame frame) {
		PanelMonReturns monRet = new PanelMonReturns(frame);
		frame.getContentPane().removeAll();
		frame.getContentPane().add(monRet.getPanelMonReturns());
	}

	/**
	 * Sets the output for the Monthly Returns panel
	 * 
	 * @param frame The frame
	 * @param mr Monthly returns object
	 */
	public void setMonReturnsOutputPanel(JFrame frame, MonthlyReturn mr) {
		PanelMonReturnsOutput monthlyReturnOutput = new PanelMonReturnsOutput(frame, mr);
		frame.getContentPane().removeAll();
		frame.getContentPane().add(monthlyReturnOutput.getPanelMonReturnsOutput());
	}
}
