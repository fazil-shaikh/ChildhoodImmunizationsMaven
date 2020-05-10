package immunization.view.impl;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.SwingConstants;

import immunization.controller.TopController;
import immunization.domain.MonthlyReturn;

public class PanelMonReturnsOutput extends MainFrame {

	private JPanel panelMonReturnsOutput;
	MonthlyReturn monthlyReturn;

	private String Clinic;
	private String Region;
	private String Metro;
	private LocalDate Month;

	private double bcgGiven[];
	private double pentaGiven[][];
	private double polioGiven[][];
	private double measlesGiven[];
	private double yfGiven[];
	private double csmGiven[];
	private double vitAGiven[];

	private double bcgCount[];
	private double pentaCount[];
	private double polioCount[];
	private double measlesCount[];
	private double yfCount[];
	private double csmCount[];
	private double vitACount[];

	private double wastage[];

	private double AEFICount;

	private double SBsCount;
	private double SBsDisposed[];

	/**
	 * Create and initialize the contents of the panel.
	 */
	public PanelMonReturnsOutput(final JFrame frame, MonthlyReturn mr) {

		// Initializing controllers
		final TopController top = new TopController();

		// 800 x 620
		frame.setSize(new Dimension(800, 620));
		frame.setLocationRelativeTo(null);
		setPanelMonReturnsOutput(new JPanel(null));

		monthlyReturn = mr;

		JLabel lblTitle = new JLabel("Monthly Report");
		lblTitle.setHorizontalAlignment(SwingConstants.CENTER);
		lblTitle.setFont(new Font("Tahoma", Font.BOLD, 17));
		lblTitle.setBounds(300, 15, 199, 30);
		getPanelMonReturnsOutput().add(lblTitle);

		JSeparator separator = new JSeparator();
		separator.setBounds(10, 59, 783, 2);
		getPanelMonReturnsOutput().add(separator);

		// Initialize data to print
		bcgGiven = monthlyReturn.getBCGGiven();
		pentaGiven = monthlyReturn.getPentaGiven();
		polioGiven = monthlyReturn.getPolioGiven();
		measlesGiven = monthlyReturn.getMeaslesGiven();
		yfGiven = monthlyReturn.getYFGiven();
		csmGiven = monthlyReturn.getCSMGiven();
		vitAGiven = monthlyReturn.getVitAGiven();

		bcgCount = monthlyReturn.getBCGUsed();
		pentaCount = monthlyReturn.getPentaUsed();
		polioCount = monthlyReturn.getPolioUsed();
		measlesCount = monthlyReturn.getMeaslesUsed();
		yfCount = monthlyReturn.getYFUsed();
		csmCount = monthlyReturn.getCSMUsed();
		vitACount = monthlyReturn.getVitAUsed();

		wastage = monthlyReturn.getWastageRate();

		AEFICount = monthlyReturn.getAEFI();

		SBsCount = monthlyReturn.getSafetyBoxes();
		SBsDisposed = monthlyReturn.getSafetyBoxesDisposed();

		// Clinic, Metro, and Region
		String clinicText = "<html><p>Clinic:</p></html>";
		JLabel lblclinicSection = new JLabel(clinicText);
		lblclinicSection.setFont(new Font("Tahoma", Font.BOLD, 10));
		lblclinicSection.setBounds(10, 59, 42, 21);
		getPanelMonReturnsOutput().add(lblclinicSection);

		String clinicName = monthlyReturn.getImmCenter();
		JLabel lblclinicName = new JLabel(clinicName);
		lblclinicName.setFont(new Font("Tahoma", Font.PLAIN, 10));
		lblclinicName.setBounds(53, 59, 124, 21);
		getPanelMonReturnsOutput().add(lblclinicName);

		String regionText = "<html><p>Region:</p></html>";
		JLabel lblregionSection = new JLabel(regionText);
		lblregionSection.setFont(new Font("Tahoma", Font.BOLD, 10));
		lblregionSection.setBounds(10, 80, 42, 21);
		getPanelMonReturnsOutput().add(lblregionSection);

		String regionName = monthlyReturn.getRegion();
		JLabel lblregionName = new JLabel(regionName);
		lblregionName.setFont(new Font("Tahoma", Font.PLAIN, 10));
		lblregionName.setBounds(63, 80, 115, 21);
		getPanelMonReturnsOutput().add(lblregionName);

		String metroText = "<html><p>Metro:</p></html>";
		JLabel lblmetroSection = new JLabel(metroText);
		lblmetroSection.setFont(new Font("Tahoma", Font.BOLD, 10));
		lblmetroSection.setBounds(10, 103, 42, 21);
		getPanelMonReturnsOutput().add(lblmetroSection);

		String metroName = monthlyReturn.getMetro();
		JLabel lblmetroName = new JLabel(metroName);
		lblmetroName.setFont(new Font("Tahoma", Font.PLAIN, 10));
		lblmetroName.setBounds(53, 103, 115, 21);
		getPanelMonReturnsOutput().add(lblmetroName);

		// Report Date
		String dateText = "<html><p>Date:</p></html>";
		JLabel lbldateSection = new JLabel(dateText);
		lbldateSection.setFont(new Font("Tahoma", Font.BOLD, 10));
		lbldateSection.setBounds(645, 59, 42, 21);
		getPanelMonReturnsOutput().add(lbldateSection);

		String reportDate = monthlyReturn.getMonth();
		JLabel lblreportDate = new JLabel(reportDate);
		lblreportDate.setFont(new Font("Tahoma", Font.PLAIN, 10));
		lblreportDate.setBounds(686, 59, 135, 21);
		getPanelMonReturnsOutput().add(lblreportDate);

		// Number of Vaccines Administered
		JLabel lblnumVaccinesGiven = new JLabel("Number of Vaccines Administered");
		lblnumVaccinesGiven.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblnumVaccinesGiven.setBounds(293, 71, 213, 36);
		getPanelMonReturnsOutput().add(lblnumVaccinesGiven);

		JSeparator separator_1 = new JSeparator();
		separator_1.setBounds(273, 105, 250, 2);
		getPanelMonReturnsOutput().add(separator_1);

		String bcgText = "<html><p>Number of BCG Vaccines Given:</p></html>";
		JLabel lblbcgGivenSection = new JLabel(bcgText);
		lblbcgGivenSection.setFont(new Font("Tahoma", Font.BOLD, 10));
		lblbcgGivenSection.setBounds(303, 117, 191, 30);
		getPanelMonReturnsOutput().add(lblbcgGivenSection);

		String bcgStats = "<html><p>0-11 months: " + bcgGiven[0] + "<br>" + "12-23 months: " + bcgGiven[1] + "<br>"
				+ "24+ months: " + bcgGiven[2] + "<br>" + "TOTAL: " + bcgGiven[3] + "</p></html>";
		JLabel lblbcgGivenStats = new JLabel(bcgStats);
		lblbcgGivenStats.setFont(new Font("Tahoma", Font.PLAIN, 10));
		lblbcgGivenStats.setBounds(354, 139, 124, 61);
		getPanelMonReturnsOutput().add(lblbcgGivenStats);

		String pentaText = "<html><p>Number of PENTA Vaccines Given:</p></html>";
		JLabel lblpentaGivenSection = new JLabel(pentaText);
		lblpentaGivenSection.setFont(new Font("Tahoma", Font.BOLD, 10));
		lblpentaGivenSection.setBounds(304, 222, 209, 21);
		getPanelMonReturnsOutput().add(lblpentaGivenSection);

		String pentaStats = "<html><p><pre>1st Dose:<br>    0-11 months: " + pentaGiven[1][0] + "<br>"
				+ "    12-23 months: " + pentaGiven[1][1] + "<br>" + "    24+ months: " + pentaGiven[1][2] + "<br>"
				+ "    1ST DOSE TOTAL: " + pentaGiven[1][3] + "<br>" + "2nd Dose:<br>    0-11 months: "
				+ pentaGiven[2][0] + "<br>" + "    12-23 months: " + pentaGiven[2][1] + "<br>" + "    24+ months: "
				+ pentaGiven[2][2] + "<br>" + "    2ND DOSE TOTAL: " + pentaGiven[2][3] + "<br>"
				+ "3rd Dose:<br>    0-11 months: " + pentaGiven[3][0] + "<br>" + "    12-23 months: " + pentaGiven[3][1]
				+ "<br>" + "    24+ months: " + pentaGiven[3][2] + "<br>" + "    3RD DOSE TOTAL: " + pentaGiven[3][3]
				+ "<br>" + "GRAND TOTAL: " + (pentaGiven[1][3] + pentaGiven[2][3] + pentaGiven[3][3])
				+ "</pre></p></html>";

		JLabel lblpentaGivenStats = new JLabel(pentaStats);
		lblpentaGivenStats.setFont(new Font("Tahoma", Font.PLAIN, 10));
		lblpentaGivenStats.setBounds(325, 243, 170, 221);
		getPanelMonReturnsOutput().add(lblpentaGivenStats);

		// Buttons
		JButton btnBack = new JButton("Main Menu");
		lblTitle.setHorizontalAlignment(SwingConstants.CENTER);
		btnBack.setFont(new Font("Tahoma", Font.PLAIN, 12));
		btnBack.setBounds(334, 542, 124, 29);
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				getPanelMonReturnsOutput().setVisible(false);
				top.setMainMenuPanel(frame);
			}
		});
		getPanelMonReturnsOutput().add(btnBack);

		JSeparator separator_1_1 = new JSeparator();
		separator_1_1.setBounds(273, 210, 250, 2);
		getPanelMonReturnsOutput().add(separator_1_1);

	}

	public JPanel getPanelMonReturnsOutput() {
		return panelMonReturnsOutput;
	}

	public void setPanelMonReturnsOutput(JPanel panelMonReturnsOutput) {
		this.panelMonReturnsOutput = panelMonReturnsOutput;
	}
}
