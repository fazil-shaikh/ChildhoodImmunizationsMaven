package immunization.service;

import java.util.ArrayList;
import java.util.List;
import immunization.domain.ImmRegister;
import immunization.domain.Vaccine;

/**
 * 
 *  The ImmRegisterService Interface is based on a design pattern
 *      which aims to organize the functionality of the application into logical units 
 *      that are typically layered on top of much of the low level functionality of the 
 *      application. This organization helps support service oriented architectures. 
 *
 */
public interface ImmRegisterService {
	
	/**
	 * Add a randomly generated Childhood Immunization Record element to the repository
	 */
    public void addACIRecord(Long ID, String mName, String cName, Long par, Integer age, Integer weight, ArrayList<Vaccine> vacList, Boolean aefi, Boolean itn, String remarks);
    
    /**
	 * Update a randomly generated Childhood Immunization Record element to the repository
	 */
    public void updateACIRecord(Long ID, Integer age, Integer weight, ArrayList<Vaccine> vacList, boolean aefi, boolean itn, String remarks);
	/**
	 * Return the list of CItems from the repository
	 */
    public List<ImmRegister> getAll();
	/**
	 * Check if the patient exists in CI Records
	 */
	public boolean checkPatientExists(Long id);
    /**
     * Calculate the maximum ID value of elements in the repository     
     * @return the maximum id of a Childhood Immunization Record in the repository
     */
	public Long maxCIRecordId();
	
	/**
	 * Lists all Patient Childhood Immunization Records
	 */
	public void displayCIRecords();
	
}
