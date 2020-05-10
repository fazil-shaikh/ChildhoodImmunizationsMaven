/*
 * ImmunizationRegister.java
 *
 * Created on February 11th, 2020
 * Modified on March 6th, 2020
 */

package immunization.domain;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Date;
import java.time.ZoneId;
import java.time.Instant;
import java.util.ArrayList;

/**
 * The model for representing Patient Childhood Immunization Records
 * 
 * @author Calen Cyr
 * @author Alexander Millett
 */
public class ImmRegister implements Serializable {

	private static final long serialVersionUID = 7526472295622776146L;
	private static Long COUNTER = 1L;

	private Long id;
	private ArrayList<Date> dates = new ArrayList<Date>();
	private String mothersName;
	private String childsName;
	private Long parity;
	private Date firstRegDate;
	private Integer Age;
	private Integer Weight[];
	private Boolean AEFI;
	private String ITN[];
	private String Remarks;
	private ArrayList<Vaccine> vaccines;

	/**
	 * Default Constructor:
	 */
	public ImmRegister(Long ID) {
		dates.add(new Date());
		mothersName = "";
		childsName = "";
		parity = new Long(0);
		firstRegDate = new Date();
		Age = new Integer(0);
		Weight = new Integer[4];
		AEFI = false;
		ITN = new String[] { "", "", "", "" };
		Remarks = "";
		id = ID;
		vaccines = new ArrayList<Vaccine>();
	}

	/**
	 * Constructor for New CI Patients:
	 * 
	 * @param ID      Patient's Identification Number (Long)
	 * @param mName   Mother's Name (String)
	 * @param cName   Patient's Name (String)
	 * @param par     Parity - Number of deliveries the mother has had (Long)
	 * @param age     Patient's age (Integer)
	 * @param weight  Patient's weight (Integer)
	 * @param vacList List of vaccines patient has had
	 * @param aefi    Whether or not the patient experienced an "adverse event
	 *                following immunization" (Boolean)
	 * @param itn     Whether or not the patient has been provided with or uses an
	 *                ITN (Boolean)
	 * @param remarks Additional remarks (String)
	 */

	public ImmRegister(Long ID, String mName, String cName, Long par, Integer age, Integer weight,
			ArrayList<Vaccine> vacList, Boolean aefi, Boolean itn, String remarks) {
		dates.add(new Date());
		mothersName = mName;
		childsName = cName;
		parity = par;
		firstRegDate = new Date();
		Age = age;
		Weight = new Integer[4];
		Weight[0] = weight;
		vaccines = vacList;
		Remarks = remarks;
		if (aefi)
			AEFI = true;
		else
			AEFI = false;
		ITN = new String[] { "", "", "", "" };
		if (itn)
			ITN[0] = "YES";
		else
			ITN[0] = "";
		id = ID;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long i) {
		this.id = i;
	}

	public ArrayList<Date> getDates() {
		return dates;
	}

	public void addDate(Date date) {
		this.dates.add(date);
	}

	public LocalDate getLastVisitDate() {
		Instant dateInstant = this.dates.get(dates.size() - 1).toInstant();
		LocalDate newLocalDate = dateInstant.atZone(ZoneId.systemDefault()).toLocalDate();
		return newLocalDate;
	}

	public String getMothersName() {
		return mothersName;
	}

	public void setMothersName(String mothersName) {
		this.mothersName = mothersName;
	}

	public String getChildsName() {
		return childsName;
	}

	public void setChildsName(String childsName) {
		this.childsName = childsName;
	}

	public long getParity() {
		return parity;
	}

	public void setParity(long parity) {
		this.parity = parity;
	}

	public Date getFirstRegDate() {
		return firstRegDate;
	}

	public void setFirstRegDate(Date firstRegDate) {
		this.firstRegDate = firstRegDate;
	}

	public Integer getAge() {
		return Age;
	}

	public void setAge(int age) {
		this.Age = Integer.valueOf(age);
	}

	public Integer[] getWeight() {
		return Weight;
	}

	public void setWeight(Integer weight) {
		this.Weight = getWeight();
		for (int i = 0; i < this.Weight.length; i++) {
			if (this.Weight[i] == null) {
				this.Weight[i] = weight;
				return;
			}
		}
	}

	public Boolean getAEFI() {
		return AEFI;
	}

	public void setAEFI(Boolean aefi) {
		this.AEFI = getAEFI();
		if (aefi == true) {
			this.AEFI = true;
		} else {
			this.AEFI = false;
		}
	}

	public String[] getITN() {
		return ITN;
	}

	public void setITN(Boolean itn) {
		this.ITN = getITN();
		for (int i = 0; i < this.ITN.length; i++) {
			if (this.ITN[i].isEmpty()) {
				this.ITN[i] = itn ? "YES" : "NO";
				return;
			}
		}
	}

	public String getRemarks() {
		return Remarks;
	}

	public void setRemarks(String remarks) {
		this.Remarks = remarks;
	}

	public ArrayList<Vaccine> getVacList() {
		return vaccines;
	}

	/**
	 * Search for vaccine
	 * 
	 * @param vacName Name of vaccine
	 * @return list of vaccines in results
	 */
	public Vaccine vacSearch(String vacName) {
		for (Vaccine v : vaccines) {
			if (v.getName().equals(vacName))
				return v;
		}
		return null;
	}

	/**
	 * Edits an existing vaccine
	 * 
	 * @param vacName Name of vaccine
	 * @param dose    Dosage of vaccine
	 */
	public void editVac(String vacName, ArrayList<Double> dose) {
		Vaccine temp;
		if (vacSearch(vacName) != null) {
			temp = vacSearch(vacName);
			temp.getDoses().addAll(dose);
		} else {
			temp = new Vaccine(vacName);
			temp.getDoses().addAll(dose);
			vaccines.add(temp);
		}
	}

	public void setVacList(ArrayList<Vaccine> vaccines) {
		this.vaccines = vaccines;
	}

	public String toString() {
		return String.format("Immunization Register");
	}
}
