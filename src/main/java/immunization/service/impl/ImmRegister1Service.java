package immunization.service.impl;

import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;
import java.util.ArrayList;

import immunization.service.ImmRegisterService;
import immunization.dao.domain.ImmRegisterDao;
import immunization.dao.domain.VaccineDao;
import immunization.domain.ImmRegister;
import immunization.domain.Vaccine;

/**
 * 
 * Uses methods for functions such as adding CI Records, getting a list of
 * CI Records, getting and displaying a list of CI Records, and getting the max ID
 * needed for the CI Record creation.
 *
 */
public class ImmRegister1Service implements ImmRegisterService {

	ImmRegisterDao dao;
	VaccineDao vDao;

	/**
	 * Default Constructor creates a default ImmunizationDao object
	 */
	public ImmRegister1Service() {
		this.dao = new ImmRegisterDao();
		this.vDao = new VaccineDao();
	}

	/**
	 * Constructor with the DAO provided
	 * 
	 * @param dao Data Access Object to use in the service
	 */
	public ImmRegister1Service(ImmRegisterDao dao) {
		this.dao = dao;
	}
	
	public ImmRegister1Service(ImmRegisterDao dao, VaccineDao vDao) {
		this.dao = dao;
		this.vDao = vDao;
	}

	/**
	 * Add an ImmunizationRegister element with user specified information.
	 * See parameter details at
	 * {@link immunization.domain.ImmRegister#ImmRegister(Long, String, Long, Integer, ArrayList<Vaccine>, Boolean, Boolean, String)}
	 */
	
	public void addACIRecord(Long ID, String mName, String cName, Long par, Integer age, Integer weight, ArrayList<Vaccine> vacList,Boolean aefi, Boolean itn, String remarks) {
		ImmRegister IR = new ImmRegister(ID,mName, cName, par,age,weight,vacList,aefi,itn,remarks);
		dao.add(IR);
	}
	/**
	 * Update an ImmunizationRegister element with user specified information.
	 * See parameter details at
	 * {@link immunization.domain.ImmRegister#ImmRegister((Long, Integer, ArrayList<Vaccine>, boolean, boolean, String)}
	 */
	
	public void updateACIRecord(Long ID, Integer age, Integer weight, ArrayList<Vaccine> vacList, boolean aefi, boolean itn, String remarks) {
		ImmRegister immReg = dao.find(ID);
		immReg.setAge(age);
		immReg.setWeight(weight);
		immReg.addDate(new Date());
		for(Vaccine v : vacList) {
			immReg.editVac(v.getName(),v.getDoses());
		}
		immReg.setITN(itn); 
		immReg.setRemarks(remarks);
		dao.update(immReg);
	}

	/**
	 * Get list of CI Records
	 * 
	 * @return list of CI Records
	 */
	public List<ImmRegister> getAll() {
		return dao.list();
	}
	
	/**
	 * Check if the patient exists in CI Records
	 *
	 * @param key id of the entity to find
	 * @return true if found false if not found
	 */
	public boolean checkPatientExists(Long id) {
		return dao.find(id) == null ? false : true;
	}
	
	/**
	 * Calculate the maximum ID value of elements in the repository
	 * 
	 * @return the maximum id of a CI Record in the repository
	 */
	public Long maxCIRecordId() {
		List<ImmRegister> clist = dao.list();
		Long max = 0L;
		if (clist.isEmpty())
			return max;
		else {
			// else list not empty
			Iterator<ImmRegister> iter = clist.iterator();
			max = iter.next().getId();
			while (iter.hasNext()) {
				ImmRegister anItem = iter.next();
				if (anItem.getId() > max)
					max = anItem.getId();
			}
			return max;
		}
	}

	/**
	 * Lists all CI Records
	 */
	public void displayCIRecords() {
		List<ImmRegister> it = getAll();
		if (it.size() <= 0) {
			System.out.println("No Patients found.");
		} else {
			for (ImmRegister cust : it) {
				System.out.println();
			}
		}
	}
}