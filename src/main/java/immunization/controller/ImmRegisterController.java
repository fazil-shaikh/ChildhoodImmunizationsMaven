package immunization.controller;

import edu.usm.cos420.consultingregister.domain.Patient; // allows for Patient objects in the code
import edu.usm.cos420.consultingregister.service.search.PatientSearch; // gives access search method
import immunization.domain.Vaccine;
import immunization.service.ImmRegisterService;
import immunization.service.impl.ImmRegister1Service;
import immunization.view.impl.PanelAddNewPatient;
import immunization.view.impl.PanelEditExistingPatient;
import immunization.view.impl.PanelImmRecords;

import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;

/**
 * A controller class that handles calling the ImmRegisterView for displaying
 * and prompting for info, as well as calling the service class to handle basic
 * ImmReg-related services.
 */
public class ImmRegisterController {

	ImmRegisterService immService = new ImmRegister1Service();

	/**
	 * Constructor for creating a new immunization record controller
	 */
	public ImmRegisterController() {
	}

	/**
	 * Creates and sets the imm Record panel to display
	 * 
	 * @param frame Current frame
	 */
	public void setImmRecordPanel(JFrame frame) {
		PanelImmRecords immRec = new PanelImmRecords(frame);
		frame.getContentPane().removeAll();
		frame.getContentPane().add(immRec.getPanelImmRecords());
	}

	/**
	 * Creates the add new patient panel
	 * 
	 * @param frame     Frame to add new patient panel to
	 * @param pEntityID Patient ID
	 * @param pAge      Patient Age
	 * 
	 */
	public void setAddNewPatientPanel(JFrame frame, Long pEntityID, Integer pAge) {
		PanelAddNewPatient newPatient = new PanelAddNewPatient(frame, pEntityID, pAge);
		frame.getContentPane().removeAll();
		frame.getContentPane().add(newPatient.getPanelAddNewPatient());
	}

	/**
	 * Creates the edit existing patient panel
	 * 
	 * @param frame      Frame to add existing patient panel to
	 * @param pEntityID  Patient ID
	 * @param pAge       Patient Age
	 */
	public void setEditExistingPatientPanel(JFrame frame, Long pEntityID, Integer pAge) {
		PanelEditExistingPatient existPatient = new PanelEditExistingPatient(frame, pEntityID, pAge);
		frame.getContentPane().removeAll();
		frame.getContentPane().add(existPatient.getPanelEditExistingPatient());
	}

	/**
	 * Calls the add patient service
	 * 
	 * @param ID      Patient ID
	 * @param mName   Patient's mother's name
	 * @param cName   Patient's name
	 * @param par     Parity of the mother
	 * @param age     Age of the child
	 * @param weight  Weight of the child
	 * @param vacList List of vaccines
	 * @param aefi    If patient has had adverse event following immunization
	 * @param itn     If patient uses ITN
	 * @param remarks Additional comments on patient
	 *
	 */
	public void addNewPatient(Long ID, String mName, String cName, Long par, Integer age, Integer weight,
			ArrayList<Vaccine> vacList, Boolean aefi, Boolean itn, String remarks) {
		immService.addACIRecord(ID, mName, cName, par, age, weight, vacList, aefi, itn, remarks);
	}

	/**
	 * Calls the update patient service
	 * 
	 * @param ID      Patient ID
	 * @param age     Age of the patient
	 * @param weight  Weight of the patient
	 * @param vacList List of vaccines
	 * @param aefi    If patient has had adverse event following immunization
	 * @param itn     If patient uses ITN
	 * @param remarks Additional comments on patient
	 * 
	 */
	public void updateExitingPatient(Long ID, Integer age, Integer weight, ArrayList<Vaccine> vacList, boolean aefi,
			boolean itn, String remarks) {
		immService.updateACIRecord(ID, age, weight, vacList, aefi, itn, remarks);
	}

	/**
	 * Checks if the patient exists by their ID
	 * 
	 * @param patientEntityID The patients ID number
	 * 
	 * @return Boolean for if patient exists
	 */
	public boolean checkPatientExists(Long patientEntityID) {
		return immService.checkPatientExists(patientEntityID);
	}
}
