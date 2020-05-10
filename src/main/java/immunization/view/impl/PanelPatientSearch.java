package immunization.view.impl;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.ScrollPaneConstants;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;

import edu.usm.cos420.consultingregister.domain.Patient;
import immunization.controller.ImmRegisterController;
import immunization.controller.PatientSearchController;

import javax.swing.JTable;
import javax.swing.JScrollPane;

public class PanelPatientSearch {

	private JPanel panelPatientSearch;
	private String patientType;
	// input text fields
	private JTextField firstNameField, lastNameField, ageField, nhisNumField, outPatientNumField;

	private JScrollPane scrollSearchResult;
	private DefaultTableModel tableModel;
	private JTable table;

	// entityId of the patient selected by the user in patient search screen
	private Long patientEntityID;

	// Age of the patient selected by the user in patient search screen
	private Integer patientAge;

	// a list to hold results of the search
	private List<Patient> matches = new ArrayList<Patient>();

	/**
	 * Create and initialize the contents of the panel.
	 */
	public PanelPatientSearch(final JFrame frame) {

		// Initializing controllers
		final ImmRegisterController immReg = new ImmRegisterController();
		final PatientSearchController patSearch = new PatientSearchController();

		// Consulting Register patient search

		// 800 x 620
		frame.setSize(new Dimension(800, 620));
		frame.setLocationRelativeTo(null);
		setPanelPatientSearch(new JPanel(null));

		JLabel lblTitle = new JLabel("Patient Search");
		lblTitle.setHorizontalAlignment(SwingConstants.CENTER);
		lblTitle.setFont(new Font("Tahoma", Font.BOLD, 17));
		lblTitle.setBounds(300, 15, 199, 30);
		getPanelPatientSearch().add(lblTitle);

		JSeparator separator = new JSeparator();
		separator.setBounds(10, 59, 783, 2);
		getPanelPatientSearch().add(separator);

		// Initial questions
		JLabel lblFirstName = new JLabel("First Name:");
		lblFirstName.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblFirstName.setBounds(20, 74, 76, 36);
		getPanelPatientSearch().add(lblFirstName);

		firstNameField = new JTextField();
		firstNameField.setBounds(97, 81, 125, 25);
		firstNameField.addKeyListener(new KeyAdapter() {
			public void keyTyped(KeyEvent e) {
				char c = e.getKeyChar();
				if (!((c >= 'A') && (c <= 'z') || (c == KeyEvent.VK_SPACE))) {
					e.consume();
				}
			}
		});
		getPanelPatientSearch().add(firstNameField);

		JLabel lblLastName = new JLabel("Last Name:");
		lblLastName.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblLastName.setBounds(20, 120, 76, 36);
		getPanelPatientSearch().add(lblLastName);

		lastNameField = new JTextField();
		lastNameField.setBounds(97, 127, 125, 25);
		lastNameField.addKeyListener(new KeyAdapter() {
			public void keyTyped(KeyEvent e) {
				char c = e.getKeyChar();
				if (!((c >= 'A') && (c <= 'z') || (c == KeyEvent.VK_SPACE))) {
					e.consume();
				}
			}
		});
		getPanelPatientSearch().add(lastNameField);

		JLabel lblFirstName_1 = new JLabel("Age:");
		lblFirstName_1.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblFirstName_1.setBounds(284, 74, 36, 36);

		getPanelPatientSearch().add(lblFirstName_1);

		ageField = new JTextField();
		ageField.setBounds(338, 81, 52, 25);
		ageField.addKeyListener(new KeyAdapter() {
			public void keyTyped(KeyEvent e) {
				char c = e.getKeyChar();
				if (!((c >= '0') && (c <= '9'))) {
					e.consume();
				}
			}
		});
		getPanelPatientSearch().add(ageField);

		JLabel lblGender = new JLabel("Gender:");
		lblGender.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblGender.setBounds(284, 120, 56, 36);
		getPanelPatientSearch().add(lblGender);

		final JComboBox<String> comboBoxGender = new JComboBox<String>();
		comboBoxGender.setModel(new DefaultComboBoxModel<String>(new String[] { "", "Male", "Female" }));
		comboBoxGender.setBounds(338, 127, 68, 25);
		getPanelPatientSearch().add(comboBoxGender);

		JLabel lblNhisNumber = new JLabel("NHIS Number:");
		lblNhisNumber.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblNhisNumber.setBounds(450, 74, 113, 36);

		getPanelPatientSearch().add(lblNhisNumber);

		nhisNumField = new JTextField();
		nhisNumField.setBounds(586, 81, 125, 25);
		nhisNumField.addKeyListener(new KeyAdapter() {
			public void keyTyped(KeyEvent e) {
				char c = e.getKeyChar();
				if (!((c >= '0') && (c <= '9'))) {
					e.consume();
				}
			}
		});
		getPanelPatientSearch().add(nhisNumField);

		JLabel lblOutPatientNumber = new JLabel("Out Patient Number:");
		lblOutPatientNumber.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblOutPatientNumber.setBounds(450, 120, 137, 36);
		getPanelPatientSearch().add(lblOutPatientNumber);

		outPatientNumField = new JTextField();
		outPatientNumField.setBounds(586, 127, 125, 25);
		outPatientNumField.addKeyListener(new KeyAdapter() {
			public void keyTyped(KeyEvent e) {
				char c = e.getKeyChar();
				if (!((c >= '0') && (c <= '9'))) {
					e.consume();
				}
			}
		});
		getPanelPatientSearch().add(outPatientNumField);

		// Column Names
		String[] columnNames = { "ID", "First Name", "Last Name", "Age", "Gender", "NHIS #", "Out Patient #" };

		// Initializing the JTable
		tableModel = new DefaultTableModel(null, columnNames);
		table = new JTable(tableModel) {
			private static final long serialVersionUID = 8519516403721771742L;

			public boolean editCellAt(int row, int column, java.util.EventObject e) {
				return false;
			}
		};
		table.getTableHeader().setReorderingAllowed(false); // not allow re-ordering of columns
		table.getSelectionModel().setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		table.setBounds(30, 40, 200, 300);

		scrollSearchResult = new JScrollPane(table);
		scrollSearchResult.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scrollSearchResult.setBounds(20, 227, 747, 273);
		getPanelPatientSearch().add(scrollSearchResult);

		JButton btnSearch = new JButton("Search");
		btnSearch.setFont(new Font("Tahoma", Font.PLAIN, 12));
		btnSearch.setBounds(348, 176, 100, 36);
		btnSearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				tableModel.setRowCount(0);

				// set filters
				String fName = firstNameField.getText();
				String lName = lastNameField.getText();

				String age = ageField.getText();
				String gender = comboBoxGender.getSelectedItem().toString();

				String nhisNum = nhisNumField.getText();
				String outPatientNum = outPatientNumField.getText();

				// call the controller to get a list of all the patients that match the filters
				matches = patSearch.getMatches(fName, lName, age, gender, nhisNum, outPatientNum);

				for (Patient p : matches) {
					Object[] newRow = new Object[] { Long.toString(p.getEntityId()), p.getFirstName(), p.getLastName(),
							p.getAge().toString(), p.getGender().toString(), p.getNHISNumber().toString(),
							p.getOutPatientNumber().toString() };
					tableModel.addRow(newRow);
				}
			}
		});
		getPanelPatientSearch().add(btnSearch);

		// Buttons
		JButton btnCancel = new JButton("Cancel");
		btnCancel.setFont(new Font("Tahoma", Font.PLAIN, 12));
		btnCancel.setBounds(200, 530, 100, 29);
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				getPanelPatientSearch().setVisible(false);
				immReg.setImmRecordPanel(frame);
			}
		});
		getPanelPatientSearch().add(btnCancel);

		JButton btnSubmit = new JButton("Select");
		btnSubmit.setFont(new Font("Tahoma", Font.PLAIN, 12));
		btnSubmit.setBounds(500, 530, 100, 29);
		btnSubmit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (table.getSelectionModel().isSelectionEmpty()) {
					JOptionPane.showMessageDialog(frame, "Please Choose a Patient to Continue");
				}
				if (!table.getSelectionModel().isSelectionEmpty()) {
					int column = 0; // ID
					int ageColumn = 3; // Age
					int row = table.getSelectedRow();
					String selectedPatientID = table.getModel().getValueAt(row, column).toString();
					String selectedPatientAge = table.getModel().getValueAt(row, ageColumn).toString();
					patientEntityID = Long.parseLong(selectedPatientID);
					patientAge = Integer.parseInt(selectedPatientAge);
				}
				if (patientEntityID != null) {
					if (getPatientType().equals("NEW_P")) {
						// ensure patient does not already exist in CI records
						if (!immReg.checkPatientExists(patientEntityID)) {
							getPanelPatientSearch().setVisible(false);
							immReg.setAddNewPatientPanel(frame, patientEntityID, patientAge);
						} else {
							JOptionPane.showMessageDialog(frame, "Patient Selected is Not a New Patient");
						}
					} else {
						// ensure patient already exist in CI records
						if (immReg.checkPatientExists(patientEntityID)) {
							getPanelPatientSearch().setVisible(false);
							immReg.setEditExistingPatientPanel(frame, patientEntityID, patientAge);
						} else {
							JOptionPane.showMessageDialog(frame, "Patient Selected is Not an Existing Patient");
						}
					}
				}
			}
		});
		getPanelPatientSearch().add(btnSubmit);
	}

	public String getPatientType() {
		return patientType;
	}

	public void setPatientType(String patientType) {
		this.patientType = patientType;
	}

	public JPanel getPanelPatientSearch() {
		return panelPatientSearch;
	}

	public void setPanelPatientSearch(JPanel panelPatientSearch) {
		this.panelPatientSearch = panelPatientSearch;
	}
}