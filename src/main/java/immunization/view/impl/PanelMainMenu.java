package immunization.view.impl;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import immunization.controller.ImmRegisterController;
import immunization.controller.MonReturnController;

public class PanelMainMenu {

	private JPanel panelMenu;

	/**
	 * Create and initialize the contents of the panel.
	 * 
	 */

	public PanelMainMenu(final JFrame frame) {

		// Initializing controllers
		final ImmRegisterController immReg = new ImmRegisterController();
		final MonReturnController monRet = new MonReturnController();

		// Immunization Register main menu panel
		frame.setSize(new Dimension(500, 400));
		frame.setLocationRelativeTo(null);

		setPanelMenu(new JPanel(null));
		getPanelMenu().setVisible(true);

		// Title
		JLabel lblTitle = new JLabel("Immunization Register");
		lblTitle.setHorizontalAlignment(SwingConstants.CENTER);
		lblTitle.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblTitle.setBounds(128, 27, 244, 33);
		getPanelMenu().add(lblTitle);

		// Buttons
		JButton btnImmRecords = new JButton("Immunization Records");
		btnImmRecords.setFont(new Font("Tahoma", Font.PLAIN, 12));
		btnImmRecords.setBounds(168, 90, 160, 55);
		getPanelMenu().add(btnImmRecords);
		btnImmRecords.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				getPanelMenu().setVisible(false);
				immReg.setImmRecordPanel(frame);
			}
		});

		JButton btnMonReturns = new JButton("Monthly Returns");
		btnMonReturns.setFont(new Font("Tahoma", Font.PLAIN, 12));
		btnMonReturns.setBounds(168, 170, 160, 55);
		getPanelMenu().add(btnMonReturns);
		btnMonReturns.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				getPanelMenu().setVisible(false);
				monRet.setMonReturnsPanel(frame);
			}
		});

		// Exit the application
		JButton btnExit = new JButton("Exit");
		btnExit.setBounds(210, 275, 85, 21);
		getPanelMenu().add(btnExit);
		btnExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
			}
		});
	}

	public JPanel getPanelMenu() {
		return panelMenu;
	}

	public void setPanelMenu(JPanel panelMenu) {
		this.panelMenu = panelMenu;
	}
}