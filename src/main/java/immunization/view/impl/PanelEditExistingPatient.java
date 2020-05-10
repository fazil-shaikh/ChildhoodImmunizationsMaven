package immunization.view.impl;

import javax.swing.JPanel;
import javax.swing.JSeparator;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;

import javax.swing.JTextField;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JTextArea;
import javax.swing.ScrollPaneConstants;
import javax.swing.JScrollPane;
import java.util.ArrayList;

import immunization.controller.ImmRegisterController;
import immunization.controller.PatientSearchController;
import immunization.domain.Vaccine;

public class PanelEditExistingPatient extends JPanel {

	private static final long serialVersionUID = 3648589851507223694L;

	private JPanel panelEditExistingPatient;
	private Long patientEntityID;
	private Integer patientAge;
	private JTextField patientWeightField, textFieldVitA, textFieldBCG, textFieldPolio, textFieldYellowF,
			textFieldMeasles, textFieldPenta, textFieldCSM;

	/**
	 * Create and initialize the contents of the panel.
	 */
	public PanelEditExistingPatient(final JFrame frame, long pId, Integer pAge) {

		// Initializing controllers
		final ImmRegisterController immReg = new ImmRegisterController();
		final PatientSearchController patSearch = new PatientSearchController();

		patientEntityID = pId;
		patientAge = pAge;
		// 800 x 520
		frame.setSize(new Dimension(800, 520));
		frame.setLocationRelativeTo(null);
		setPanelEditExistingPatient(new JPanel(null));

		JLabel lblTitle = new JLabel("Editing an Existing Patient");
		lblTitle.setHorizontalAlignment(SwingConstants.CENTER);
		lblTitle.setFont(new Font("Tahoma", Font.BOLD, 17));
		lblTitle.setBounds(280, 15, 239, 30);
		getPanelEditExistingPatient().add(lblTitle);

		JSeparator separator = new JSeparator();
		separator.setBounds(10, 59, 783, 2);
		getPanelEditExistingPatient().add(separator);

		JLabel lblPatientsWeight = new JLabel("<html> Patient's Weight: <br> (kg) </html>");
		lblPatientsWeight.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblPatientsWeight.setBounds(20, 69, 136, 41);
		getPanelEditExistingPatient().add(lblPatientsWeight);

		patientWeightField = new JTextField();
		patientWeightField.setBounds(270, 74, 59, 25);
		patientWeightField.addKeyListener(new KeyAdapter() {
			public void keyTyped(KeyEvent e) {
				char c = e.getKeyChar();
				if (!((c >= '0') && (c <= '9') || (c == '.'))) {
					e.consume();
				}
			}
		});
		getPanelEditExistingPatient().add(patientWeightField);

		JSeparator separator2 = new JSeparator();
		separator2.setBounds(10, 111, 783, 2);
		getPanelEditExistingPatient().add(separator2);

		// Vaccines
		JLabel lblVac = new JLabel("Vaccines Recieved Today and Dosage:");
		lblVac.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblVac.setBounds(20, 124, 262, 19);
		getPanelEditExistingPatient().add(lblVac);

		final JCheckBox chckbxVitaminA = new JCheckBox(" Vitamin A");
		chckbxVitaminA.setFont(new Font("Tahoma", Font.BOLD, 12));
		chckbxVitaminA.setBounds(20, 168, 94, 21);
		chckbxVitaminA.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				if (e.getStateChange() == ItemEvent.SELECTED) {
					textFieldVitA.setEnabled(true);
				} else if (e.getStateChange() == ItemEvent.DESELECTED) {
					textFieldVitA.setEnabled(false);
				}
				validate();
				repaint();
			}
		});
		getPanelEditExistingPatient().add(chckbxVitaminA);

		textFieldVitA = new JTextField();
		textFieldVitA.setToolTipText("");
		textFieldVitA.setEnabled(false);
		textFieldVitA.setBounds(43, 193, 65, 25);
		getPanelEditExistingPatient().add(textFieldVitA);

		final JCheckBox chckbxBcg = new JCheckBox(" BCG");
		chckbxBcg.setFont(new Font("Tahoma", Font.BOLD, 12));
		chckbxBcg.setBounds(140, 168, 94, 21);
		chckbxBcg.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				if (e.getStateChange() == ItemEvent.SELECTED) {
					textFieldBCG.setEnabled(true);
				} else if (e.getStateChange() == ItemEvent.DESELECTED) {
					textFieldBCG.setEnabled(false);
				}
				validate();
				repaint();
			}
		});
		getPanelEditExistingPatient().add(chckbxBcg);

		textFieldBCG = new JTextField();
		textFieldBCG.setEnabled(false);
		textFieldBCG.setBounds(163, 193, 65, 25);
		getPanelEditExistingPatient().add(textFieldBCG);

		final JCheckBox chckbxPolio = new JCheckBox(" Polio");
		chckbxPolio.setFont(new Font("Tahoma", Font.BOLD, 12));
		chckbxPolio.setBounds(260, 168, 94, 21);
		chckbxPolio.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				if (e.getStateChange() == ItemEvent.SELECTED) {
					textFieldPolio.setEnabled(true);
				} else if (e.getStateChange() == ItemEvent.DESELECTED) {
					textFieldPolio.setEnabled(false);
				}
				validate();
				repaint();
			}
		});
		getPanelEditExistingPatient().add(chckbxPolio);

		textFieldPolio = new JTextField();
		textFieldPolio.setEnabled(false);
		textFieldPolio.setBounds(283, 193, 65, 25);
		getPanelEditExistingPatient().add(textFieldPolio);

		final JCheckBox chckbxMeasles = new JCheckBox(" Measles");
		chckbxMeasles.setFont(new Font("Tahoma", Font.BOLD, 12));
		chckbxMeasles.setBounds(140, 248, 94, 21);
		chckbxMeasles.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				if (e.getStateChange() == ItemEvent.SELECTED) {
					textFieldMeasles.setEnabled(true);
				} else if (e.getStateChange() == ItemEvent.DESELECTED) {
					textFieldMeasles.setEnabled(false);
				}
				validate();
				repaint();
			}
		});
		getPanelEditExistingPatient().add(chckbxMeasles);

		textFieldMeasles = new JTextField();
		textFieldMeasles.setEnabled(false);
		textFieldMeasles.setBounds(163, 273, 65, 25);
		getPanelEditExistingPatient().add(textFieldMeasles);

		final JCheckBox chckbxPenta = new JCheckBox(" Penta");
		chckbxPenta.setFont(new Font("Tahoma", Font.BOLD, 12));
		chckbxPenta.setBounds(20, 248, 94, 21);
		chckbxPenta.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				if (e.getStateChange() == ItemEvent.SELECTED) {
					textFieldPenta.setEnabled(true);
				} else if (e.getStateChange() == ItemEvent.DESELECTED) {
					textFieldPenta.setEnabled(false);
				}
				validate();
				repaint();
			}
		});
		getPanelEditExistingPatient().add(chckbxPenta);

		textFieldPenta = new JTextField();
		textFieldPenta.setEnabled(false);
		textFieldPenta.setBounds(43, 273, 65, 25);
		getPanelEditExistingPatient().add(textFieldPenta);

		final JCheckBox chckbxYellowFever = new JCheckBox(" Yellow F");
		chckbxYellowFever.setFont(new Font("Tahoma", Font.BOLD, 12));
		chckbxYellowFever.setBounds(260, 248, 94, 21);
		chckbxYellowFever.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				if (e.getStateChange() == ItemEvent.SELECTED) {
					textFieldYellowF.setEnabled(true);
				} else if (e.getStateChange() == ItemEvent.DESELECTED) {
					textFieldYellowF.setEnabled(false);
				}
				validate();
				repaint();
			}
		});
		getPanelEditExistingPatient().add(chckbxYellowFever);

		textFieldYellowF = new JTextField();
		textFieldYellowF.setEnabled(false);
		textFieldYellowF.setBounds(283, 273, 65, 25);
		getPanelEditExistingPatient().add(textFieldYellowF);

		final JCheckBox chckbxCsm = new JCheckBox(" CSM");
		chckbxCsm.setFont(new Font("Tahoma", Font.BOLD, 12));
		chckbxCsm.setBounds(20, 328, 94, 21);
		chckbxCsm.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				if (e.getStateChange() == ItemEvent.SELECTED) {
					textFieldCSM.setEnabled(true);
				} else if (e.getStateChange() == ItemEvent.DESELECTED) {
					textFieldCSM.setEnabled(false);
				}
				validate();
				repaint();
			}
		});
		getPanelEditExistingPatient().add(chckbxCsm);

		textFieldCSM = new JTextField();
		textFieldCSM.setEnabled(false);
		textFieldCSM.setBounds(43, 353, 65, 25);
		getPanelEditExistingPatient().add(textFieldCSM);

		// Additional questions and remarks
		JLabel lblDoesThePatient = new JLabel("Does the patient use an ITN?");
		lblDoesThePatient.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblDoesThePatient.setBounds(475, 168, 188, 20);
		getPanelEditExistingPatient().add(lblDoesThePatient);

		final JComboBox<String> comboBoxITN = new JComboBox<String>();
		comboBoxITN.setModel(new DefaultComboBoxModel<String>(new String[] { "Yes", "No" }));
		comboBoxITN.setBounds(671, 169, 52, 21);
		getPanelEditExistingPatient().add(comboBoxITN);

		JLabel lblAdverseEffect = new JLabel(
				"<html> Has the patient experienced <br> an adverse effect following vaccination? </html>");
		lblAdverseEffect.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblAdverseEffect.setBounds(475, 217, 188, 63);
		getPanelEditExistingPatient().add(lblAdverseEffect);

		final JComboBox<String> comboBoxAdEffect = new JComboBox<String>();
		comboBoxAdEffect.setModel(new DefaultComboBoxModel<String>(new String[] { "No", "Yes" }));
		comboBoxAdEffect.setBounds(671, 226, 52, 21);
		getPanelEditExistingPatient().add(comboBoxAdEffect);

		JLabel lblAdditionalRemarks = new JLabel("Additional Remarks:");
		lblAdditionalRemarks.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblAdditionalRemarks.setBounds(475, 296, 136, 20);
		getPanelEditExistingPatient().add(lblAdditionalRemarks);

		final JTextArea textAreaAddRemarks = new JTextArea();
		textAreaAddRemarks.setBounds(475, 422, 248, 58);
		getPanelEditExistingPatient().add(textAreaAddRemarks);

		JScrollPane scrollRemarks = new JScrollPane(textAreaAddRemarks);
		scrollRemarks.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scrollRemarks.setBounds(475, 320, 248, 58);
		getPanelEditExistingPatient().add(scrollRemarks);

		// Navigation buttons
		JButton btnBack = new JButton("Back");
		btnBack.setFont(new Font("Tahoma", Font.PLAIN, 12));
		btnBack.setBounds(200, 428, 100, 29);
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				getPanelEditExistingPatient().setVisible(false);
				patSearch.setPatientSearchPanelWithType(frame, "EXIST_P");
			}
		});
		getPanelEditExistingPatient().add(btnBack);

		JButton btnSubmit = new JButton("Submit");
		btnSubmit.setFont(new Font("Tahoma", Font.PLAIN, 12));
		btnSubmit.setBounds(500, 428, 100, 29);
		getPanelEditExistingPatient().add(btnSubmit);

		btnSubmit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				boolean failed = false;
				String errorMessage = "";
				// ID
				Long ID = patientEntityID;
				// Age
				Integer age = patientAge;
				// Weight
				int weight = -1;
				try {
					weight = Integer.parseInt(patientWeightField.getText());
				} catch (Exception excep) {
					errorMessage += "<html> Invalid input for weight. <br>";
					failed = true;
				}
				// Adverse
				boolean adverse = false;
				if (comboBoxAdEffect.getSelectedItem() == "Yes") {
					adverse = true;
				} else if (comboBoxAdEffect.getSelectedItem() == "No") {
					adverse = false;
				}
				// itn
				boolean itn = false;
				if (comboBoxITN.getSelectedItem() == "Yes") {
					itn = true;
				}
				if (comboBoxITN.getSelectedItem() == "No") {
					itn = false;
				}
				// remarks
				String remarks = textAreaAddRemarks.getText();

				// Vaccine List
				ArrayList<Vaccine> vacList = new ArrayList<Vaccine>();
				// VitA
				if (chckbxVitaminA.isSelected()) {
					Vaccine temp = new Vaccine();
					double dose = -1;
					temp.setName("VitA");
					try {
						dose = Double.parseDouble(textFieldVitA.getText());
					} catch (Exception excep) {
						errorMessage += "<html> Invalid input for Vitamin A dosage. <br>";
						failed = true;
					}
					temp.getDoses().add(dose);
					vacList.add(temp);
				}

				// BCG
				if (chckbxBcg.isSelected()) {
					Vaccine temp = new Vaccine();
					double dose = -1;
					temp.setName("BCG");
					try {
						dose = Double.parseDouble(textFieldBCG.getText());
					} catch (Exception excep) {
						errorMessage += "<html> Invalid input for BCG dosage. <br>";
						failed = true;
					}
					temp.getDoses().add(dose);
					vacList.add(temp);
				}

				// Polio
				if (chckbxPolio.isSelected()) {
					Vaccine temp = new Vaccine();
					double dose = -1;
					temp.setName("Polio");
					try {
						dose = Double.parseDouble(textFieldPolio.getText());
					} catch (Exception excep) {
						errorMessage += "<html> Invalid input for Polio dosage. <br>";
						failed = true;
					}
					temp.getDoses().add(dose);
					vacList.add(temp);
				}

				// Measles
				if (chckbxMeasles.isSelected()) {
					Vaccine temp = new Vaccine();
					double dose = -1;
					temp.setName("Measles");
					try {
						dose = Double.parseDouble(textFieldMeasles.getText());
					} catch (Exception excep) {
						errorMessage += "<html> Invalid input for Measles dosage. <br>";
						failed = true;
					}
					temp.getDoses().add(dose);
					vacList.add(temp);
				}

				// Penta
				if (chckbxPenta.isSelected()) {
					Vaccine temp = new Vaccine();
					double dose = -1;
					temp.setName("Penta");
					try {
						dose = Double.parseDouble(textFieldPenta.getText());
					} catch (Exception excep) {
						errorMessage += "<html> Invalid input for Penta dosage. <br>";
						failed = true;
					}
					temp.getDoses().add(dose);
					vacList.add(temp);
				}

				// YellowFever
				if (chckbxYellowFever.isSelected()) {
					Vaccine temp = new Vaccine();
					double dose = -1;
					temp.setName("YF");
					try {
						dose = Double.parseDouble(textFieldYellowF.getText());
					} catch (Exception excep) {
						errorMessage += "<html> Invalid input for YellowFever dosage. <br>";
						failed = true;
					}
					temp.getDoses().add(dose);
					vacList.add(temp);
				}

				// CSM
				if (chckbxCsm.isSelected()) {
					Vaccine temp = new Vaccine();
					double dose = -1;
					temp.setName("CSM");
					try {
						dose = Double.parseDouble(textFieldCSM.getText());
					} catch (Exception excep) {
						errorMessage += "<html> Invalid input for CSM dosage. <br>";
						failed = true;
					}
					temp.getDoses().add(dose);
					vacList.add(temp);
				}
				if (failed) {
					JOptionPane.showMessageDialog(frame, errorMessage);
				} else {
					immReg.updateExitingPatient(ID, age, weight, vacList, adverse, itn, remarks);
					JOptionPane.showMessageDialog(frame, "Immunization Record has been updated");
					getPanelEditExistingPatient().setVisible(false);
					immReg.setImmRecordPanel(frame);
				}
			}
		});
	}

	public JPanel getPanelEditExistingPatient() {
		return panelEditExistingPatient;
	}

	public void setPanelEditExistingPatient(JPanel panelEditExistingPatient) {
		this.panelEditExistingPatient = panelEditExistingPatient;
	}

}