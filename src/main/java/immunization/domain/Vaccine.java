package immunization.domain;

import java.io.Serializable;
import java.util.ArrayList;

public class Vaccine implements Serializable {

	private static final long serialVersionUID = 7526472295622776146L;
	private static Long VCOUNTER = 1L;

	// params
	private static long vId;
	String name; // Name of this vaccine. EX: VIT. A, BCG, POLIO, etc.
	ArrayList<Double> doses = new ArrayList<Double>(); // Records the doses given to the patient, ranges from 1 to 4 in
														// length based on number of doses

	// Constructors
	/**
	 * Vaccine constructor without an argument makes a default valued vaccine
	 */
	public Vaccine() {
		vId = generateId();
		name = "default";
		doses = new ArrayList<Double>();
	}

	/**
	 * Vaccine constructor with only a name argument
	 * 
	 * @param n The name of the vaccine specified by the user
	 */
	public Vaccine(String n) {
		vId = generateId();
		name = n;
		doses = new ArrayList<Double>();

	}

	/**
	 * Vaccine constructor with arguments name and list of doses specified
	 * 
	 * @param n The name of the vaccine specified by the user
	 * @param d Any doses already associated with vaccine
	 */
	public Vaccine(String n, ArrayList<Double> d) {
		vId = generateId();
		name = n;
		doses = d;
	}

	// Setters
	public void setVId(long i) {
		vId = i;
	}

	public void setName(String n) {
		name = n;
	}

	public void setDoses(ArrayList<Double> d) {
		doses = d;
	}

	// Getters
	public long getVId() {
		return vId;
	}

	public String getName() {
		return name;
	}

	public ArrayList<Double> getDoses() {
		return doses;
	}

	// Other

	private long generateId() {
		return VCOUNTER++;
	}
}
