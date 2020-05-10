package immunization.controller;

import edu.usm.cos420.consultingregister.domain.Gender;
import edu.usm.cos420.consultingregister.domain.Patient; // allows for Patient objects in the code
import edu.usm.cos420.consultingregister.service.search.PatientSearch; // gives access search method
import immunization.view.impl.PanelImmRecords;
import immunization.view.impl.PanelPatientSearch;

import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

/**
 * A controller class that handles calling the ImmRegisterView for displaying
 * and prompting for info, as well as calling the service class to handle basic
 * ImmReg-related services.
 */
public class PatientSearchController {

	// an object that gives access to search methods for patients in the data
	// repository
	private PatientSearch searchService = new PatientSearch();

	// a list to hold results of the search
	private List<Patient> matches = new ArrayList<Patient>();
	private JFrame frame;

	/**
	 * Constructor for creating a new immunization record controller
	 */
	public PatientSearchController() {
	}

	/**
	 * Creates the patient search panel and updates patient type
	 * 
	 * @param frame Frame to add search panel to
	 * @param pType The patient type
	 */
	public void setPatientSearchPanelWithType(JFrame frame, String pType) {
		PanelPatientSearch patientSearch = new PanelPatientSearch(frame);
		patientSearch.setPatientType(pType);
		frame.getContentPane().removeAll();
		frame.getContentPane().add(patientSearch.getPanelPatientSearch());
		this.frame = frame;
	}

	/**
	 * Retrieves matches for the search
	 * 
	 * @param fName     Patient First Name
	 * @param lName     Patient Last Name
	 * @param age       Patient Age
	 * @param gender    Patient gender
	 * @param nhisNum   NHIS number
	 * @return          List of search matches
	 */
	public List<Patient> getMatches(String fName, String lName, String age, String gender, String nhisNum,
			String outPatientNum) {

		searchService = new PatientSearch();

		try {
			// set filters
			searchService.setFilterFirstName(fName);
			searchService.setFilterLastName(lName);

			if (!(age.isBlank() || age.isEmpty()))
				searchService.setFilterAge(Integer.parseInt(age));

			if (gender == "Male")
				searchService.setFilterGender(Gender.Male);
			else if (gender == "Female")
				searchService.setFilterGender(Gender.Female);

			if (!(nhisNum.isBlank() || nhisNum.isEmpty()))
				searchService.setFilterNHISNumber(Long.parseLong(nhisNum));

			if (!(outPatientNum.isBlank() || outPatientNum.isEmpty()))
				searchService.setFilterOutPatientNumber(Long.parseLong(outPatientNum));
		} catch (Exception e) {
			JOptionPane.showMessageDialog(frame, "Invalid Search Parameter(s). Try again with valid input");
		}

		// a list of all the patients that match the filters
		return searchService.getMatches();
	}
}
