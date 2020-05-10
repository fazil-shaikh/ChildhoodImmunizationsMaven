package immunization.view.impl;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Vector;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import immunization.controller.MonReturnController;
import immunization.controller.TopController;
import immunization.domain.ImmRegister;
import immunization.domain.MonthlyReturn;
import immunization.domain.Vaccine;
import immunization.service.impl.ImmRegister1Service;

public class PanelMonReturns extends MainFrame {

	private JPanel panelMonReturns;

	private JTextField textFieldClinic, textFieldRegion, textFieldMetro, textFieldVitAUsed, textFieldBCGUsed,
			textFieldPolioUsed, textFieldYellowFUsed, textFieldMeaslesUsed, textFieldPentaUsed, textFieldCSMUsed,
			textFieldWastage, textFieldAEFIUsed, textFieldSafetyBoxesUsed, textFieldSBsDisposedI, textFieldSBsDisposedP;

	private JComboBox<String> monthComboBox;
	private JComboBox<Integer> yearComboBox;

	/**
	 * Create and initialize the contents of the panel.
	 */
	public PanelMonReturns(final JFrame frame) {

		// Initializing controllers
		final TopController top = new TopController();
		final MonReturnController monRet = new MonReturnController();

		// 800 x 620
		frame.setSize(new Dimension(800, 620));
		frame.setLocationRelativeTo(null);
		setPanelMonReturns(new JPanel(null));

		JLabel lblTitle = new JLabel("Monthly Report");
		lblTitle.setHorizontalAlignment(SwingConstants.CENTER);
		lblTitle.setFont(new Font("Tahoma", Font.BOLD, 17));
		lblTitle.setBounds(300, 15, 199, 30);
		getPanelMonReturns().add(lblTitle);

		String date = "<html><p>Enter the Numerical Representation of the Month and Year for which you want to Generate a Report</p></html>";
		JLabel lbldate = new JLabel(date);
		lbldate.setFont(new Font("Tahoma", Font.PLAIN, 10));
		lbldate.setBounds(20, 20, 1000, 100);
		getPanelMonReturns().add(lbldate);

		JLabel lbldateMonth = new JLabel("Month");
		lbldateMonth.setFont(new Font("Tahoma", Font.BOLD, 12));
		lbldateMonth.setBounds(20, 100, 76, 36);
		getPanelMonReturns().add(lbldateMonth);

		monthComboBox = new JComboBox<String>();
		monthComboBox.setModel(new DefaultComboBoxModel<String>(new String[] { "January", "February", "March", "April",
				"May", "June", "July", "August", "September", "October", "November", "December" }));
		monthComboBox.setBounds(70, 105, 90, 25);
		getPanelMonReturns().add(monthComboBox);

		JLabel lbldateYear = new JLabel("Year");
		lbldateYear.setFont(new Font("Tahoma", Font.BOLD, 12));
		lbldateYear.setBounds(220, 100, 76, 36);
		getPanelMonReturns().add(lbldateYear);

		yearComboBox = new JComboBox<Integer>();
		Calendar now = Calendar.getInstance();
		int year = now.get(Calendar.YEAR);
		Vector<Integer> v = new Vector<Integer>();
		for (int i = 1970; i <= year; i++) {
			v.add(i);
		}
		yearComboBox.setModel(new DefaultComboBoxModel<Integer>(v));
		yearComboBox.setSelectedItem(year);

		yearComboBox.setBounds(260, 105, 55, 25);
		getPanelMonReturns().add(yearComboBox);

		JSeparator separator = new JSeparator();

		String text = "<html><p>Enter the Clinic, Region, and Metro</p></html>";
		JLabel lbldirections = new JLabel(text);
		lbldirections.setFont(new Font("Tahoma", Font.PLAIN, 10));
		lbldirections.setBounds(20, 143, 1000, 77);
		getPanelMonReturns().add(lbldirections);

		separator.setBounds(10, 59, 783, 2);
		getPanelMonReturns().add(separator);

		// Initial questions
		JLabel lblClinic = new JLabel("Clinic");
		lblClinic.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblClinic.setBounds(20, 200, 76, 36);
		getPanelMonReturns().add(lblClinic);

		textFieldClinic = new JTextField();
		textFieldClinic.setBounds(97, 205, 125, 25);
		textFieldClinic.addKeyListener(new KeyAdapter() {
			public void keyTyped(KeyEvent e) {
				char c = e.getKeyChar();
				if (!((c >= 'A') && (c <= 'z') || (c == KeyEvent.VK_SPACE))) {
					e.consume();
				}
			}
		});
		getPanelMonReturns().add(textFieldClinic);

		JLabel lblRegion = new JLabel("Region");
		lblRegion.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblRegion.setBounds(286, 200, 76, 36);
		getPanelMonReturns().add(lblRegion);

		textFieldRegion = new JTextField();
		textFieldRegion.setBounds(350, 205, 125, 25);
		textFieldRegion.addKeyListener(new KeyAdapter() {
			public void keyTyped(KeyEvent e) {
				char c = e.getKeyChar();
				if (!((c >= 'A') && (c <= 'z') || (c == KeyEvent.VK_SPACE))) {
					e.consume();
				}
			}
		});
		getPanelMonReturns().add(textFieldRegion);

		JLabel lblMetro = new JLabel("Metro");
		lblMetro.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblMetro.setBounds(550, 200, 76, 36);
		getPanelMonReturns().add(lblMetro);

		textFieldMetro = new JTextField();
		textFieldMetro.setBounds(620, 205, 125, 25);
		textFieldMetro.addKeyListener(new KeyAdapter() {
			public void keyTyped(KeyEvent e) {
				char c = e.getKeyChar();
				if (!((c >= 'A') && (c <= 'z') || (c == KeyEvent.VK_SPACE))) {
					e.consume();
				}
			}
		});
		getPanelMonReturns().add(textFieldMetro);

		separator.setBounds(10, 59, 783, 2);
		getPanelMonReturns().add(separator);

		String text2 = "<html><p>Enter the Total Number of Doses Used for each Vaccine</p></html>";
		JLabel lbldirection = new JLabel(text2);
		lbldirection.setFont(new Font("Tahoma", Font.PLAIN, 10));
		lbldirection.setBounds(20, 220, 1000, 100);
		getPanelMonReturns().add(lbldirection);

		separator.setBounds(10, 59, 783, 2);
		getPanelMonReturns().add(separator);

		// Initial questions
		JLabel lblVitA = new JLabel("Vit A");
		lblVitA.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblVitA.setBounds(20, 300, 76, 36);
		getPanelMonReturns().add(lblVitA);

		textFieldVitAUsed = new JTextField();
		textFieldVitAUsed.setBounds(97, 305, 125, 25);
		textFieldVitAUsed.addKeyListener(new KeyAdapter() {
			public void keyTyped(KeyEvent e) {
				char c = e.getKeyChar();
				if (!((c >= '0') && (c <= '9'))) {
					e.consume();
				}
			}
		});
		getPanelMonReturns().add(textFieldVitAUsed);

		JLabel lblPolio = new JLabel("Polio");
		lblPolio.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblPolio.setBounds(300, 300, 76, 36);
		getPanelMonReturns().add(lblPolio);

		textFieldPolioUsed = new JTextField();
		textFieldPolioUsed.setBounds(350, 305, 125, 25);
		textFieldPolioUsed.addKeyListener(new KeyAdapter() {
			public void keyTyped(KeyEvent e) {
				char c = e.getKeyChar();
				if (!((c >= '0') && (c <= '9'))) {
					e.consume();
				}
			}
		});
		getPanelMonReturns().add(textFieldPolioUsed);

		JLabel lblPenta = new JLabel("Penta");
		lblPenta.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblPenta.setBounds(550, 300, 76, 36);
		getPanelMonReturns().add(lblPenta);

		textFieldPentaUsed = new JTextField();
		textFieldPentaUsed.setBounds(620, 305, 125, 25);
		textFieldPentaUsed.addKeyListener(new KeyAdapter() {
			public void keyTyped(KeyEvent e) {
				char c = e.getKeyChar();
				if (!((c >= '0') && (c <= '9'))) {
					e.consume();
				}
			}
		});
		getPanelMonReturns().add(textFieldPentaUsed);

		JLabel lblBCG = new JLabel("BCG");
		lblBCG.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblBCG.setBounds(20, 340, 76, 36);
		getPanelMonReturns().add(lblBCG);

		textFieldBCGUsed = new JTextField();
		textFieldBCGUsed.setBounds(97, 347, 125, 25);
		textFieldBCGUsed.addKeyListener(new KeyAdapter() {
			public void keyTyped(KeyEvent e) {
				char c = e.getKeyChar();
				if (!((c >= '0') && (c <= '9'))) {
					e.consume();
				}
			}
		});
		getPanelMonReturns().add(textFieldBCGUsed);

		JLabel lblYF = new JLabel("YF");
		lblYF.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblYF.setBounds(300, 340, 76, 36);
		getPanelMonReturns().add(lblYF);

		textFieldYellowFUsed = new JTextField();
		textFieldYellowFUsed.setBounds(350, 347, 125, 25);
		textFieldYellowFUsed.addKeyListener(new KeyAdapter() {
			public void keyTyped(KeyEvent e) {
				char c = e.getKeyChar();
				if (!((c >= '0') && (c <= '9'))) {
					e.consume();
				}
			}
		});
		getPanelMonReturns().add(textFieldYellowFUsed);

		JLabel lblMeasles = new JLabel("Measles");
		lblMeasles.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblMeasles.setBounds(550, 340, 76, 36);
		getPanelMonReturns().add(lblMeasles);

		textFieldMeaslesUsed = new JTextField();
		textFieldMeaslesUsed.setBounds(620, 347, 125, 25);
		textFieldMeaslesUsed.addKeyListener(new KeyAdapter() {
			public void keyTyped(KeyEvent e) {
				char c = e.getKeyChar();
				if (!((c >= '0') && (c <= '9'))) {
					e.consume();
				}
			}
		});
		getPanelMonReturns().add(textFieldMeaslesUsed);

		JLabel lblCSM = new JLabel("CSM");
		lblCSM.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblCSM.setBounds(20, 390, 76, 36);
		getPanelMonReturns().add(lblCSM);

		textFieldCSMUsed = new JTextField();
		textFieldCSMUsed.setBounds(97, 395, 125, 25);
		textFieldCSMUsed.addKeyListener(new KeyAdapter() {
			public void keyTyped(KeyEvent e) {
				char c = e.getKeyChar();
				if (!((c >= '0') && (c <= '9'))) {
					e.consume();
				}
			}
		});
		getPanelMonReturns().add(textFieldCSMUsed);

		JLabel lblAEFI = new JLabel("AFEI");
		lblAEFI.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblAEFI.setBounds(300, 390, 76, 36);
		getPanelMonReturns().add(lblAEFI);

		textFieldAEFIUsed = new JTextField();
		textFieldAEFIUsed.setBounds(350, 395, 125, 25);
		textFieldAEFIUsed.addKeyListener(new KeyAdapter() {
			public void keyTyped(KeyEvent e) {
				char c = e.getKeyChar();
				if (!((c >= '0') && (c <= '9'))) {
					e.consume();
				}
			}
		});
		getPanelMonReturns().add(textFieldAEFIUsed);

		JLabel lblWastage = new JLabel("Wastage");
		lblWastage.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblWastage.setBounds(550, 390, 76, 36);
		getPanelMonReturns().add(lblWastage);

		textFieldWastage = new JTextField();
		textFieldWastage.setBounds(620, 395, 125, 25);
		textFieldWastage.addKeyListener(new KeyAdapter() {
			public void keyTyped(KeyEvent e) {
				char c = e.getKeyChar();
				if (!((c >= '0') && (c <= '9'))) {
					e.consume();
				}
			}
		});
		getPanelMonReturns().add(textFieldWastage);

		String text3 = "<html><p>Enter the Number of Safety Boxes Used, Disposed of by Pit, and Disposed of by Incineration</p></html>";
		JLabel lbldirectionsSB = new JLabel(text3);
		lbldirectionsSB.setFont(new Font("Tahoma", Font.PLAIN, 10));
		lbldirectionsSB.setBounds(20, 400, 1000, 100);
		getPanelMonReturns().add(lbldirectionsSB);

		separator.setBounds(10, 59, 783, 2);
		getPanelMonReturns().add(separator);

		JLabel lblSBs = new JLabel("<html>Safety<br>Boxes</html>");
		lblSBs.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblSBs.setBounds(20, 472, 76, 47);
		getPanelMonReturns().add(lblSBs);

		textFieldSafetyBoxesUsed = new JTextField();
		textFieldSafetyBoxesUsed.setBounds(100, 485, 125, 25);
		textFieldSafetyBoxesUsed.addKeyListener(new KeyAdapter() {
			public void keyTyped(KeyEvent e) {
				char c = e.getKeyChar();
				if (!((c >= '0') && (c <= '9'))) {
					e.consume();
				}
			}
		});
		getPanelMonReturns().add(textFieldSafetyBoxesUsed);

		JLabel lblSBsDP = new JLabel("<html>Disposed<br>of by Pit</html>");
		lblSBsDP.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblSBsDP.setBounds(274, 472, 76, 47);
		getPanelMonReturns().add(lblSBsDP);

		textFieldSBsDisposedP = new JTextField();
		textFieldSBsDisposedP.setBounds(350, 485, 125, 25);
		textFieldSBsDisposedP.addKeyListener(new KeyAdapter() {
			public void keyTyped(KeyEvent e) {
				char c = e.getKeyChar();
				if (!((c >= '0') && (c <= '9'))) {
					e.consume();
				}
			}
		});
		getPanelMonReturns().add(textFieldSBsDisposedP);

		JLabel lblSBsDI = new JLabel("<html>Disposed of<br>by Incineration</html>");
		lblSBsDI.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblSBsDI.setBounds(503, 472, 109, 47);
		getPanelMonReturns().add(lblSBsDI);

		textFieldSBsDisposedI = new JTextField();
		textFieldSBsDisposedI.setBounds(620, 485, 125, 25);
		textFieldSBsDisposedI.addKeyListener(new KeyAdapter() {
			public void keyTyped(KeyEvent e) {
				char c = e.getKeyChar();
				if (!((c >= '0') && (c <= '9'))) {
					e.consume();
				}
			}
		});
		getPanelMonReturns().add(textFieldSBsDisposedI);

		// Buttons
		JButton btnBack = new JButton("Cancel");
		btnBack.setFont(new Font("Tahoma", Font.PLAIN, 12));
		btnBack.setBounds(200, 530, 100, 29);
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				getPanelMonReturns().setVisible(false);
				top.setMainMenuPanel(frame);
			}
		});
		getPanelMonReturns().add(btnBack);
		JButton btnGenerate = new JButton("Generate");
		btnGenerate.setFont(new Font("Tahoma", Font.PLAIN, 12));
		btnGenerate.setBounds(530, 530, 100, 29);
		btnGenerate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// Set up arrays to hold data
				double bcgGiven[] = new double[4];
				double pentaGiven[][] = new double[4][4];
				double polioGiven[][] = new double[4][4];
				double measlesGiven[] = new double[4];
				double yfGiven[] = new double[4];
				double csmGiven[] = new double[4];
				double vitAGiven[] = new double[2];

				// Get the desired report date (month, year) and set the boundaries of
				// the search for records within the specified month
				String yearString = yearComboBox.getSelectedItem().toString();
				String monthString = "";

				int month = monthComboBox.getSelectedIndex() + 1;

				if (month >= 10) {
					monthString = String.valueOf(month);
				} else {
					monthString = "0" + String.valueOf(month);
				}

				String dateString = yearString + "-" + monthString + "-" + "01";

				DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

				LocalDate reportStartDate = LocalDate.parse(dateString, formatter);
				LocalDate reportEndDate = reportStartDate.plusMonths(1L);

				// Get a list of all of the records
				ImmRegister1Service immService = new ImmRegister1Service();
				List<ImmRegister> allrecords = immService.getAll();

				// Search through all of the records and get stats from records that fall
				// within the valid month range
				for (int i = 0; i < allrecords.size(); i++) {

					// If date is within month range
					if (allrecords.get(i).getLastVisitDate().isAfter(reportStartDate)
							&& allrecords.get(i).getLastVisitDate().isBefore(reportEndDate)) {

						// Check age of record holder and set age bracket index accordingly
						int age = allrecords.get(i).getAge();
						int ageBracketIndex = -1;

						if (age < 1) {
							ageBracketIndex = 0;
						} else if (age >= 1 && age < 2) {
							ageBracketIndex = 1;
						} else if (age >= 2) {
							ageBracketIndex = 2;
						}

						int vitAAgeBracketIndex = -1;

						if (age < 1) {
							vitAAgeBracketIndex = 0;
						} else if (age >= 1) {
							vitAAgeBracketIndex = 1;
						}

						// Get the dosage arrays for each vaccine in the patient's record
						// And Increment the number of doses given in the correct age group
						Vaccine bcg = allrecords.get(i).vacSearch("BCG");
						if (bcg != null) {
							ArrayList<Double> bcgDoses = bcg.getDoses();
							for (int j = 0; j < bcgDoses.size(); j++) {
								if (bcgDoses.get(j) != 0.0) {
									bcgGiven[ageBracketIndex]++;
									bcgGiven[3]++;
								}
							}

						}

						Vaccine penta = allrecords.get(i).vacSearch("PENTA");
						if (penta != null) {
							ArrayList<Double> pentaDoses = penta.getDoses();
							for (int j = 0; j < pentaDoses.size(); j++) {
								if (pentaDoses.get(j) != 0.0) {
									pentaGiven[j][ageBracketIndex]++;
									pentaGiven[j][3]++;
								}
							}
						}

						Vaccine polio = allrecords.get(i).vacSearch("POLIO");
						if (polio != null) {
							ArrayList<Double> polioDoses = polio.getDoses();
							for (int j = 0; j < polioDoses.size(); j++) {
								if (polioDoses.get(j) != 0.0) {
									polioGiven[j][ageBracketIndex]++;
									polioGiven[j][3]++;
								}
							}
						}

						Vaccine measles = allrecords.get(i).vacSearch("MEASLES");
						if (measles != null) {
							ArrayList<Double> measlesDoses = measles.getDoses();
							for (int j = 0; j < measlesDoses.size(); j++) {
								if (measlesDoses.get(j) != 0.0) {
									measlesGiven[ageBracketIndex]++;
									measlesGiven[3]++;
								}
							}
						}

						Vaccine yf = allrecords.get(i).vacSearch("YF");
						if (yf != null) {
							ArrayList<Double> yfDoses = yf.getDoses();
							for (int j = 0; j < yfDoses.size(); j++) {
								if (yfDoses.get(j) != 0.0) {
									yfGiven[ageBracketIndex]++;
									yfGiven[3]++;
								}
							}
						}

						Vaccine csm = allrecords.get(i).vacSearch("CSM");
						if (csm != null) {
							ArrayList<Double> csmDoses = csm.getDoses();
							for (int j = 0; j < csmDoses.size(); j++) {
								if (csmDoses.get(j) != 0.0) {
									csmGiven[ageBracketIndex]++;
									csmGiven[3]++;
								}
							}
						}

						Vaccine vitA = allrecords.get(i).vacSearch("VIT.A");
						if (vitA != null) {
							ArrayList<Double> vitADoses = vitA.getDoses();
							for (int j = 0; j < vitADoses.size(); j++) {
								if (vitADoses.get(j) != 0.0) {
									vitAGiven[vitAAgeBracketIndex]++;
								}
							}
						}
					}
				}

				// Adding more user interaction
				double[] bcgCount = new double[2];
				double[] pentaCount = new double[2];
				double[] polioCount = new double[2];
				double[] measlesCount = new double[2];
				double[] yfCount = new double[3];
				double[] csmCount = new double[2];
				double[] vitACount = new double[3];
				double[] wastage = new double[6];
				double[] SBsdisposed = new double[3];
				double AEFICount = 0;
				double SBsCount = 0;

				String errorMessage = "";
				boolean failed = false;

				try {
					bcgCount[1] = Integer.parseInt(textFieldBCGUsed.getText());
					// Total 20 dose blocks given total / 20
					bcgCount[0] = bcgCount[1] / 20;
				} catch (Exception excep) {
					errorMessage += "<html> Invalid input for BCG. <br>";
					failed = true;
				}
				try {
					pentaCount[1] = Integer.parseInt(textFieldPentaUsed.getText());
					// Total 2 dose blocks given total / 2
					pentaCount[0] = pentaCount[1] / 2;
				} catch (Exception excep) {
					errorMessage += "<html> Invalid input for Penta. <br>";
					failed = true;
				}
				try {
					polioCount[1] = Integer.parseInt(textFieldPolioUsed.getText());
					// Total 20 dose blocks given total / 20
					polioCount[0] = polioCount[1] / 20;
				} catch (Exception excep) {
					errorMessage += "<html> Invalid input for Polio. <br>";
					failed = true;
				}
				try {
					measlesCount[1] = Integer.parseInt(textFieldMeaslesUsed.getText());
					// Total 10 dose blocks given total / 10
					measlesCount[0] = measlesCount[1] / 10;
				} catch (Exception excep) {
					errorMessage += "<html> Invalid input for Measles. <br>";
					failed = true;
				}
				try {
					yfCount[2] = Integer.parseInt(textFieldYellowFUsed.getText());
					// Total 5 dose blocks given total / 5
					yfCount[0] = yfCount[2] / 5;
					// Total 10 dose blocks given total / 10
					yfCount[1] = yfCount[2] / 10;
				} catch (Exception excep) {
					errorMessage += "<html> Invalid input for YF. <br>";
					failed = true;
				}
				try {
					csmCount[1] = Integer.parseInt(textFieldCSMUsed.getText());
					// Total 20 dose blocks given total / 20
					csmCount[0] = csmCount[1] / 20;
				} catch (Exception excep) {
					errorMessage += "<html> Invalid input for CSM. <br>";
					failed = true;
				}

				try {
					vitACount[2] = Integer.parseInt(textFieldVitAUsed.getText());
					// Amount in IU = Doses * Dosage
					vitACount[0] = vitAGiven[0] * 100000;
					vitACount[1] = vitAGiven[1] * 200000;

				} catch (Exception excep) {
					errorMessage += "<html> Invalid input for Vit A. <br>";
					failed = true;
				}
				try {
					AEFICount = Integer.parseInt(textFieldVitAUsed.getText());

				} catch (Exception excep) {
					errorMessage += "<html> Invalid input for AEFI. <br>";
					failed = true;
				}
				try {
					SBsCount = Integer.parseInt(textFieldSafetyBoxesUsed.getText());

				} catch (Exception excep) {
					errorMessage += "<html> Invalid input for SBs. <br>";
					failed = true;
				}
				try {
					SBsdisposed[1] = Integer.parseInt(textFieldSBsDisposedP.getText());
					SBsdisposed[2] = Integer.parseInt(textFieldSBsDisposedI.getText());

					// Total number disposed
					SBsdisposed[0] = SBsdisposed[1] + SBsdisposed[2];

				} catch (Exception excep) {
					errorMessage += "<html> Invalid input for SBs Disposed. <br>";
					failed = true;
				}

				// only show the output if all the fields have been inputted
				if (failed) {
					JOptionPane.showMessageDialog(frame, errorMessage);
				} else {

					wastage[0] = bcgCount[1] - bcgGiven[3];
					wastage[1] = pentaCount[1] - (pentaGiven[1][3] + pentaGiven[2][3] + pentaGiven[3][3]);
					wastage[1] = polioCount[1]
							- (polioGiven[0][3] + polioGiven[1][3] + polioGiven[2][3] + polioGiven[3][3]);
					wastage[3] = measlesCount[1] - measlesGiven[3];
					wastage[4] = yfCount[2] - yfGiven[3];
					wastage[5] = csmCount[1] - csmGiven[3];

					// generate object
					MonthlyReturn mr = new MonthlyReturn(0L, textFieldClinic.getText(), textFieldRegion.getText(),
							textFieldMetro.getText(), reportStartDate, bcgGiven, pentaGiven, polioGiven, measlesGiven,
							yfGiven, csmGiven, vitAGiven, bcgCount, pentaCount, polioCount, measlesCount, yfCount,
							csmCount, vitACount, wastage, AEFICount, SBsCount, SBsdisposed);

					getPanelMonReturns().setVisible(false);
					monRet.setMonReturnsOutputPanel(frame, mr);
				}
			}
		});
		getPanelMonReturns().add(btnGenerate);

	}

	public JPanel getPanelMonReturns() {
		return panelMonReturns;
	}

	public void setPanelMonReturns(JPanel panelMonReturns) {
		this.panelMonReturns = panelMonReturns;
	}

}
