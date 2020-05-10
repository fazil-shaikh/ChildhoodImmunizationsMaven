package immunization.view.impl;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import immunization.controller.PatientSearchController;
import immunization.controller.TopController;

public class PanelImmRecords {

	private JPanel panelImmRecords;
	private String patientType;

	/**
	 * Create and initialize the contents of the panel.
	 */
	public PanelImmRecords(final JFrame frame) {

		// Initializing controllers
		final TopController top = new TopController();
		final PatientSearchController patSearch = new PatientSearchController();

		// Immunization records panel
		frame.setSize(new Dimension(500, 400));
		frame.setLocationRelativeTo(null);

		setPanelImmRecords(new JPanel(null));

		// Immunization records panel
		JLabel lblQuestion = new JLabel("Are you entering a new or existing patient?");
		lblQuestion.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblQuestion.setBounds(100, 30, 332, 19);
		getPanelImmRecords().add(lblQuestion);

		// Existing patient button
		JButton btnExistPatient = new JButton("Existing Patient");
		btnExistPatient.setFont(new Font("Tahoma", Font.PLAIN, 12));
		btnExistPatient.setBounds(168, 90, 160, 55);
		btnExistPatient.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				patientType = "EXIST_P";
				getPanelImmRecords().setVisible(false);
				patSearch.setPatientSearchPanelWithType(frame, patientType);
			}
		});
		getPanelImmRecords().add(btnExistPatient);

		// New patient button
		JButton btnNewPatient = new JButton("New Patient");
		btnNewPatient.setFont(new Font("Tahoma", Font.PLAIN, 12));
		btnNewPatient.setBounds(168, 170, 160, 55);
		btnNewPatient.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				patientType = "NEW_P";
				getPanelImmRecords().setVisible(false);
				patSearch.setPatientSearchPanelWithType(frame, patientType);
			}
		});
		getPanelImmRecords().add(btnNewPatient);

		// Return to main menu
		JButton btnReturnToMenu = new JButton("Return to menu");
		btnReturnToMenu.setBounds(188, 275, 128, 21);
		getPanelImmRecords().add(btnReturnToMenu);
		btnReturnToMenu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				getPanelImmRecords().setVisible(false);
				top.setMainMenuPanel(frame);
			}
		});
	}

	public JPanel getPanelImmRecords() {
		return panelImmRecords;
	}

	public void setPanelImmRecords(JPanel panelImmRecords) {
		this.panelImmRecords = panelImmRecords;
	}
}
