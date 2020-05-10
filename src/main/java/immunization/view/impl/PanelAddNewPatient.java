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

public class PanelAddNewPatient extends JPanel {

	private static final long serialVersionUID = 5781726775422075379L;

	private JPanel panelAddNewPatient;
	private Long patientEntityID;
	private Integer patientAge;
	private JTextField mothersNameField, childsNameField, numDeliveriesField, patientWeightField, textFieldVitA,
			textFieldBCG, textFieldPolio, textFieldYellowF, textFieldMeasles, textFieldPenta, textFieldCSM;

	/**
	 * Create and initialize the contents of the panel.
	 */
	public PanelAddNewPatient(final JFrame frame, Long pId, Integer pAge) {

		// Initializing controllers
		final ImmRegisterController immReg = new ImmRegisterController();
		final PatientSearchController patSearch = new PatientSearchController();

		patientEntityID = pId;
		patientAge = pAge;
		// 800 x 620
		frame.setSize(new Dimension(800, 620));
		frame.setLocationRelativeTo(null);
		setPanelAddNewPatient(new JPanel(null));

		JLabel lblTitle = new JLabel("Adding a New Patient");
		lblTitle.setHorizontalAlignment(SwingConstants.CENTER);
		lblTitle.setFont(new Font("Tahoma", Font.BOLD, 17));
		lblTitle.setBounds(300, 15, 199, 30);
		getPanelAddNewPatient().add(lblTitle);

		JSeparator separator = new JSeparator();
		separator.setBounds(10, 59, 783, 2);
		getPanelAddNewPatient().add(separator);

		// Initial questions
		JLabel lblMothersName = new JLabel("<html> Mother's Name: <br> (first and last)</html>");
		lblMothersName.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblMothersName.setBounds(20, 70, 136, 36);
		getPanelAddNewPatient().add(lblMothersName);

		mothersNameField = new JTextField();
		mothersNameField.setBounds(125, 75, 218, 25);
		mothersNameField.addKeyListener(new KeyAdapter() {
			public void keyTyped(KeyEvent e) {
				char c = e.getKeyChar();
				if (!((c >= 'A') && (c <= 'z') || (c == KeyEvent.VK_SPACE))) {
					e.consume();
				}
			}
		});
		getPanelAddNewPatient().add(mothersNameField);

		JLabel lblChildsName = new JLabel("<html> Child's Name: <br> (first and last)</html>");
		lblChildsName.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblChildsName.setBounds(365, 70, 136, 41);
		getPanelAddNewPatient().add(lblChildsName);

		childsNameField = new JTextField();
		childsNameField.setBounds(465, 75, 199, 25);
		childsNameField.addKeyListener(new KeyAdapter() {
			public void keyTyped(KeyEvent e) {
				char c = e.getKeyChar();
				if (!((c >= 'A') && (c <= 'z') || (c == KeyEvent.VK_SPACE))) {
					e.consume();
				}
			}
		});
		getPanelAddNewPatient().add(childsNameField);

		JLabel lblNumDeliveries = new JLabel(
				"<html> Number of deliveries mother has had: <br> (include stillbirths)</html>");
		lblNumDeliveries.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblNumDeliveries.setBounds(20, 120, 229, 41);
		getPanelAddNewPatient().add(lblNumDeliveries);

		numDeliveriesField = new JTextField();
		numDeliveriesField.setBounds(260, 125, 60, 25);
		numDeliveriesField.addKeyListener(new KeyAdapter() {
			public void keyTyped(KeyEvent e) {
				char c = e.getKeyChar();
				if (!((c >= '0') && (c <= '9'))) {
					e.consume();
				}
			}
		});
		getPanelAddNewPatient().add(numDeliveriesField);

		JLabel lblPatientsWeight = new JLabel("<html> Patient's Weight: <br> (kg) </html>");
		lblPatientsWeight.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblPatientsWeight.setBounds(20, 171, 115, 41);
		getPanelAddNewPatient().add(lblPatientsWeight);

		patientWeightField = new JTextField();
		patientWeightField.setBounds(135, 177, 60, 25);
		patientWeightField.addKeyListener(new KeyAdapter() {
			public void keyTyped(KeyEvent e) {
				char c = e.getKeyChar();
				if (!((c >= '0') && (c <= '9') || (c == '.'))) {
					e.consume();
				}
			}
		});
		getPanelAddNewPatient().add(patientWeightField);

		JSeparator separator2 = new JSeparator();
		separator2.setBounds(10, 213, 783, 2);
		getPanelAddNewPatient().add(separator2);

		// Vaccines
		JLabel lblVac = new JLabel("Vaccines Recieved Today and Dosage:");
		lblVac.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblVac.setBounds(20, 226, 262, 19);
		getPanelAddNewPatient().add(lblVac);

		final JCheckBox chckbxVitaminA = new JCheckBox(" Vitamin A");
		chckbxVitaminA.setFont(new Font("Tahoma", Font.BOLD, 12));
		chckbxVitaminA.setBounds(20, 270, 94, 21);
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
		getPanelAddNewPatient().add(chckbxVitaminA);

		textFieldVitA = new JTextField();
		textFieldVitA.setToolTipText("");
		textFieldVitA.setEnabled(false);
		textFieldVitA.setBounds(43, 295, 65, 25);
		getPanelAddNewPatient().add(textFieldVitA);

		final JCheckBox chckbxBcg = new JCheckBox(" BCG");
		chckbxBcg.setFont(new Font("Tahoma", Font.BOLD, 12));
		chckbxBcg.setBounds(140, 270, 94, 21);
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
		getPanelAddNewPatient().add(chckbxBcg);

		textFieldBCG = new JTextField();
		textFieldBCG.setEnabled(false);
		textFieldBCG.setBounds(163, 295, 65, 25);
		getPanelAddNewPatient().add(textFieldBCG);

		final JCheckBox chckbxPolio = new JCheckBox(" Polio");
		chckbxPolio.setFont(new Font("Tahoma", Font.BOLD, 12));
		chckbxPolio.setBounds(260, 270, 94, 21);
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
		getPanelAddNewPatient().add(chckbxPolio);

		textFieldPolio = new JTextField();
		textFieldPolio.setEnabled(false);
		textFieldPolio.setBounds(283, 295, 65, 25);
		getPanelAddNewPatient().add(textFieldPolio);

		final JCheckBox chckbxMeasles = new JCheckBox(" Measles");
		chckbxMeasles.setFont(new Font("Tahoma", Font.BOLD, 12));
		chckbxMeasles.setBounds(140, 350, 94, 21);
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
		getPanelAddNewPatient().add(chckbxMeasles);

		textFieldMeasles = new JTextField();
		textFieldMeasles.setEnabled(false);
		textFieldMeasles.setBounds(163, 375, 65, 25);
		getPanelAddNewPatient().add(textFieldMeasles);

		final JCheckBox chckbxPenta = new JCheckBox(" Penta");
		chckbxPenta.setFont(new Font("Tahoma", Font.BOLD, 12));
		chckbxPenta.setBounds(20, 350, 94, 21);
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
		getPanelAddNewPatient().add(chckbxPenta);

		textFieldPenta = new JTextField();
		textFieldPenta.setEnabled(false);
		textFieldPenta.setBounds(43, 375, 65, 25);
		getPanelAddNewPatient().add(textFieldPenta);

		final JCheckBox chckbxYellowFever = new JCheckBox(" Yellow F");
		chckbxYellowFever.setFont(new Font("Tahoma", Font.BOLD, 12));
		chckbxYellowFever.setBounds(260, 350, 94, 21);
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
		getPanelAddNewPatient().add(chckbxYellowFever);

		textFieldYellowF = new JTextField();
		textFieldYellowF.setEnabled(false);
		textFieldYellowF.setBounds(283, 375, 65, 25);
		getPanelAddNewPatient().add(textFieldYellowF);

		final JCheckBox chckbxCsm = new JCheckBox(" CSM");
		chckbxCsm.setFont(new Font("Tahoma", Font.BOLD, 12));
		chckbxCsm.setBounds(20, 430, 94, 21);
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
		getPanelAddNewPatient().add(chckbxCsm);

		textFieldCSM = new JTextField();
		textFieldCSM.setEnabled(false);
		textFieldCSM.setBounds(43, 455, 65, 25);
		getPanelAddNewPatient().add(textFieldCSM);

		// Additional questions and remarks
		JLabel lblDoesThePatient = new JLabel("Does the patient use an ITN?");
		lblDoesThePatient.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblDoesThePatient.setBounds(475, 270, 188, 20);
		getPanelAddNewPatient().add(lblDoesThePatient);

		final JComboBox<String> comboBoxITN = new JComboBox<String>();
		comboBoxITN.setModel(new DefaultComboBoxModel<String>(new String[] { "Yes", "No" }));
		comboBoxITN.setBounds(671, 271, 52, 21);
		getPanelAddNewPatient().add(comboBoxITN);

		JLabel lblAdverseEffect = new JLabel(
				"<html> Has the patient experienced <br> an adverse effect following vaccination? </html>");
		lblAdverseEffect.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblAdverseEffect.setBounds(475, 319, 188, 63);
		getPanelAddNewPatient().add(lblAdverseEffect);

		final JComboBox<String> comboBoxAdEffect = new JComboBox<String>();
		comboBoxAdEffect.setModel(new DefaultComboBoxModel<String>(new String[] { "No", "Yes" }));
		comboBoxAdEffect.setBounds(671, 328, 52, 21);
		getPanelAddNewPatient().add(comboBoxAdEffect);

		JLabel lblAdditionalRemarks = new JLabel("Additional Remarks:");
		lblAdditionalRemarks.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblAdditionalRemarks.setBounds(475, 398, 136, 20);
		getPanelAddNewPatient().add(lblAdditionalRemarks);

		final JTextArea textAreaAddRemarks = new JTextArea();
		textAreaAddRemarks.setBounds(475, 422, 248, 58);
		getPanelAddNewPatient().add(textAreaAddRemarks);

		final JScrollPane scrollRemarks = new JScrollPane(textAreaAddRemarks);
		scrollRemarks.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scrollRemarks.setBounds(475, 422, 248, 58);
		getPanelAddNewPatient().add(scrollRemarks);

		// Buttons
		JButton btnBack = new JButton("Back");
		btnBack.setFont(new Font("Tahoma", Font.PLAIN, 12));
		btnBack.setBounds(200, 530, 100, 29);
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				getPanelAddNewPatient().setVisible(false);
				patSearch.setPatientSearchPanelWithType(frame, "NEW_P");
			}
		});
		getPanelAddNewPatient().add(btnBack);

		JButton btnSubmit = new JButton("Submit");
		btnSubmit.setFont(new Font("Tahoma", Font.PLAIN, 12));
		btnSubmit.setBounds(500, 530, 100, 29);
		getPanelAddNewPatient().add(btnSubmit);
		btnSubmit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				boolean failed = false;
				String errorMessage = "";
				// ID
				Long ID = patientEntityID;
				// Mothers name
				String mName = mothersNameField.getText();
				// Childs name
				String cName = childsNameField.getText();
				// Parity
				Long parity = -1L;
				try {
					parity = Long.parseLong(numDeliveriesField.getText());
				} catch (Exception excep) {
					errorMessage += "<html> Invalid input for parity. <br>";
					failed = true;
				}
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
					temp.getDoses().clear();
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
					temp.getDoses().clear();
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
					temp.getDoses().clear();
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
					temp.getDoses().clear();
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
					temp.getDoses().clear();
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
					temp.getDoses().clear();
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
					temp.getDoses().clear();
					temp.getDoses().add(dose);
					vacList.add(temp);
				}
				if (failed) {
					JOptionPane.showMessageDialog(frame, errorMessage);
				} else {
					immReg.addNewPatient(ID, mName, cName, parity, age, weight, vacList, adverse, itn, remarks);
					JOptionPane.showMessageDialog(frame, "New immunization record has been created.");
					getPanelAddNewPatient().setVisible(false);
					immReg.setImmRecordPanel(frame);
				}
			}
		});
	}

	public JPanel getPanelAddNewPatient() {
		return panelAddNewPatient;
	}

	public void setPanelAddNewPatient(JPanel panelAddNewPatient) {
		this.panelAddNewPatient = panelAddNewPatient;
	}
}