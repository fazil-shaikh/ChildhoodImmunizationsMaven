/*
 * MonthlyReturn.java
 *
 * Created on March 28th, 2020
 */

package immunization.domain;

import java.io.Serializable;
import java.util.Date;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;

/**
 * The model for representing the Monthly Returns
 * 
 * @author Marie Hartung
 * @author John Hofacker
 */
public class MonthlyReturn implements Serializable {

	private static final long serialVersionUID = 7526472295622776146L;
	private Long id;
	private static Long COUNTER = 1L;
	private String immunizationCenter;
	private String region;
	private String metro;
	private String month;

	private double bcgGiven[];
	private double pentaGiven[][];
	private double polioGiven[][];
	private double measlesGiven[];
	private double yfGiven[];
	private double csmGiven[];
	private double vitAGiven[];

	private double numBCGUsed[];
	private double numPentaUsed[];
	private double numPolioUsed[];
	private double numMeaslesUsed[];
	private double numYFUsed[];
	private double numCSMUsed[];
	private double numVitAUsed[];
	private double wastageRate[];

	private double numAEFI;
	private double numSafetyBoxes;
	private double numSafetyBoxesDisposed[];

	/**
	 * Default Constructor with an argument containing an ID
	 */
	public MonthlyReturn(Long ID) {
		immunizationCenter = "";
		region = "";
		metro = "";
		month = LocalDate.of(2000, 1, 1).toString();

		bcgGiven = new double[4];
		pentaGiven = new double[4][4];
		polioGiven = new double[4][4];
		measlesGiven = new double[4];
		yfGiven = new double[4];
		csmGiven = new double[4];
		vitAGiven = new double[2];

		numBCGUsed = new double[2];
		numPentaUsed = new double[2];
		numPolioUsed = new double[2];
		numMeaslesUsed = new double[2];
		numYFUsed = new double[3];
		numCSMUsed = new double[2];
		numVitAUsed = new double[3];

		wastageRate = new double[6];

		numAEFI = 0;
		numSafetyBoxes = 0;
		numSafetyBoxesDisposed = new double[2];
	}

	/**
	 * Constructor that takes in the information for the month
	 * 
	 * @param ID             ID number for the particular month/year/location/etc.
	 * @param ImmCenter      Immunization Center information is from
	 * @param Region         Region Immunization Center is located
	 * @param Metro          Metro Immunization Center is located
	 * @param Month          Current month
	 * @param BCGGiven       List of BCG vaccines given in a month
	 * @param PentaGiven     List of Penta vaccines given in a month
	 * @param PolioGiven     List of Polio vaccines given in a month
	 * @param MeaslesGiven   List of Measles vaccines given in a month
	 * @param YFGiven        List of YF vaccines given in a month
	 * @param CSMGiven       List of CSM vaccines given in a month
	 * @param VitAGiven      List of Vitamin A supplements given in a month
	 * @param NumBCG         Number of BCG vaccines given in a month
	 * @param NumPenta       Number of Penta vaccines given in a month
	 * @param NumPolio       Number of Polio vaccines given in a month
	 * @param NumMeasles     Number of Measles vaccines given in a month
	 * @param NumYF          Number of YF vaccines given in a month
	 * @param NumCSM         Number of CSM vaccines given in a month
	 * @param NumVitA        Number of Vitamin A supplements given in a month
	 * @param Wastage        List of wasted vaccine shots
	 * @param NumAEFI        Number of adverse effects
	 * @param NumSBs         Number of safety boxes used in a month
	 * @param NumSBsDisposed Number of safety boxes that were disposed of/burnt in a
	 *                       month
	 */
	public MonthlyReturn(Long ID, String ImmCenter, String Region, String Metro, LocalDate Month, double BCGGiven[],
			double PentaGiven[][], double PolioGiven[][], double MeaslesGiven[], double YFGiven[], double CSMGiven[],
			double VitAGiven[], double NumBCG[], double NumPenta[], double NumPolio[], double NumMeasles[],
			double NumYF[], double NumCSM[], double NumVitA[], double Wastage[], double NumAEFI, double NumSBs,
			double NumSBsDisposed[]) {
		id = ID;
		immunizationCenter = ImmCenter;
		region = Region;
		metro = Metro;

		// Format the date
		String formattedDate = Month.format(DateTimeFormatter.ofLocalizedDate(FormatStyle.LONG));
		month = formattedDate;

		// Copy over parameter arrays for the "Number Given by Age Group"
		bcgGiven = new double[BCGGiven.length];
		for (int i = 0; i < BCGGiven.length; i++) {
			bcgGiven[i] = Double.valueOf(BCGGiven[i]);
		}

		pentaGiven = new double[PentaGiven.length][PentaGiven[0].length];
		for (int i = 0; i < PentaGiven.length; i++) {
			for (int j = 0; j < PentaGiven[i].length; j++) {
				pentaGiven[i][j] = Double.valueOf(PentaGiven[i][j]);
			}
		}

		polioGiven = new double[PolioGiven.length][PolioGiven[0].length];
		for (int i = 0; i < PolioGiven.length; i++) {
			for (int j = 0; j < PolioGiven[i].length; j++) {
				polioGiven[i][j] = Double.valueOf(PolioGiven[i][j]);
			}
		}

		measlesGiven = new double[MeaslesGiven.length];
		for (int i = 0; i < MeaslesGiven.length; i++) {
			measlesGiven[i] = Double.valueOf(MeaslesGiven[i]);
		}

		yfGiven = new double[YFGiven.length];
		for (int i = 0; i < YFGiven.length; i++) {
			yfGiven[i] = Double.valueOf(YFGiven[i]);
		}

		csmGiven = new double[CSMGiven.length];
		for (int i = 0; i < CSMGiven.length; i++) {
			csmGiven[i] = Double.valueOf(CSMGiven[i]);
		}

		vitAGiven = new double[VitAGiven.length];
		for (int i = 0; i < VitAGiven.length; i++) {
			vitAGiven[i] = Double.valueOf(VitAGiven[i]);
		}

		// Copy over parameter arrays for the "Number of Doses Used"
		numBCGUsed = new double[NumBCG.length];
		for (int i = 0; i < NumBCG.length; i++) {
			numBCGUsed[i] = Double.valueOf(NumBCG[i]);
		}

		numPentaUsed = new double[NumPenta.length];
		for (int i = 0; i < NumPenta.length; i++) {
			numPentaUsed[i] = Double.valueOf(NumPenta[i]);
		}

		numPolioUsed = new double[NumPolio.length];
		for (int i = 0; i < NumPolio.length; i++) {
			numPolioUsed[i] = Double.valueOf(NumPolio[i]);
		}

		numMeaslesUsed = new double[NumMeasles.length];
		for (int i = 0; i < NumMeasles.length; i++) {
			numMeaslesUsed[i] = Double.valueOf(NumMeasles[i]);
		}

		numYFUsed = new double[NumYF.length];
		for (int i = 0; i < NumYF.length; i++) {
			numYFUsed[i] = Double.valueOf(NumYF[i]);
		}

		numCSMUsed = new double[NumCSM.length];
		for (int i = 0; i < NumCSM.length; i++) {
			numCSMUsed[i] = Double.valueOf(NumCSM[i]);
		}

		numVitAUsed = new double[NumVitA.length];
		for (int i = 0; i < NumVitA.length; i++) {
			numVitAUsed[i] = Double.valueOf(NumVitA[i]);
		}

		// Copy over parameter array for wastage rate (for all vaccines)
		wastageRate = new double[Wastage.length];
		for (int i = 0; i < Wastage.length; i++) {
			wastageRate[i] = Double.valueOf(Wastage[i]);
		}

		// Copy over number of adverse effects following immunization
		numAEFI = Double.valueOf(NumAEFI);

		// Copy over safety box statistics
		numSafetyBoxes = Double.valueOf(NumSBs);

		numSafetyBoxesDisposed = new double[NumSBsDisposed.length];
		for (int i = 0; i < NumSBsDisposed.length; i++) {
			numSafetyBoxesDisposed[i] = Double.valueOf(NumSBsDisposed[i]);
		}
	}

	// Getter and setter for ID
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	// Getters and setters for clinic info
	public String getImmCenter() {
		return immunizationCenter;
	}

	public void setImmCenter(String immunizationCenter) {
		this.immunizationCenter = immunizationCenter;
	}

	public String getRegion() {
		return region;
	}

	public void setRegion(String region) {
		this.region = region;
	}

	public String getMetro() {
		return metro;
	}

	public void setmetro(String metro) {
		this.metro = metro;
	}

	// Getter and setter for the month
	public String getMonth() {
		return month;
	}

	public void setMonth(LocalDate month) {
		String formattedDate = month.format(DateTimeFormatter.ofLocalizedDate(FormatStyle.LONG));
		this.month = formattedDate;
	}

	// Getters and setters for "Number of Doses Used"
	public double[] getBCGGiven() {
		return bcgGiven;
	}

	public void setBCGGiven(int bcgGiven[]) {
		for (int i = 0; i < bcgGiven.length; i++) {
			this.bcgGiven[i] = Double.valueOf(bcgGiven[i]);
		}
	}

	public double[][] getPentaGiven() {
		return pentaGiven;
	}

	public void setPentaGiven(int pentaGiven[][]) {
		for (int i = 0; i < pentaGiven.length; i++) {
			for (int j = 0; j < pentaGiven[i].length; j++) {
				this.pentaGiven[i][j] = Double.valueOf(pentaGiven[i][j]);
			}
		}
	}

	public double[][] getPolioGiven() {
		return polioGiven;
	}

	public void setPolioGiven(int polioGiven[][]) {
		for (int i = 0; i < polioGiven.length; i++) {
			for (int j = 0; j < polioGiven[i].length; j++) {
				this.polioGiven[i][j] = Double.valueOf(polioGiven[i][j]);
			}
		}
	}

	public double[] getMeaslesGiven() {
		return measlesGiven;
	}

	public void setMeaslesGiven(int measlesGiven[]) {
		for (int i = 0; i < measlesGiven.length; i++) {
			this.measlesGiven[i] = Double.valueOf(measlesGiven[i]);
		}
	}

	public double[] getYFGiven() {
		return yfGiven;
	}

	public void setYFGiven(int yfGiven[]) {
		for (int i = 0; i < yfGiven.length; i++) {
			this.yfGiven[i] = Double.valueOf(yfGiven[i]);
		}
	}

	public double[] getCSMGiven() {
		return csmGiven;
	}

	public void setCSMGiven(int csmGiven[]) {
		for (int i = 0; i < csmGiven.length; i++) {
			this.csmGiven[i] = Double.valueOf(csmGiven[i]);
		}
	}

	public double[] getVitAGiven() {
		return vitAGiven;
	}

	public void setVitAGiven(int vitAGiven[]) {
		for (int i = 0; i < vitAGiven.length; i++) {
			this.vitAGiven[i] = Double.valueOf(vitAGiven[i]);
		}
	}

	// Getters and setters for the "Number of Doses Used"
	public double[] getBCGUsed() {
		return numBCGUsed;
	}

	public void setBCGUsed(int numBCGUsed[]) {
		for (int i = 0; i < numBCGUsed.length; i++) {
			this.numBCGUsed[i] = Double.valueOf(numBCGUsed[i]);
		}
	}

	public double[] getPentaUsed() {
		return numPentaUsed;
	}

	public void setPentaUsed(int numPentaUsed[]) {
		for (int i = 0; i < numPentaUsed.length; i++) {
			this.numPentaUsed[i] = Double.valueOf(numPentaUsed[i]);
		}
	}

	public double[] getPolioUsed() {
		return numPolioUsed;
	}

	public void setPolioUsed(int numPolioUsed[]) {
		for (int i = 0; i < numPolioUsed.length; i++) {
			this.numPolioUsed[i] = Double.valueOf(numPolioUsed[i]);
		}
	}

	public double[] getMeaslesUsed() {
		return numMeaslesUsed;
	}

	public void setMeaslesUsed(int numMeaslesUsed[]) {
		for (int i = 0; i < numMeaslesUsed.length; i++) {
			this.numMeaslesUsed[i] = Double.valueOf(numMeaslesUsed[i]);
		}
	}

	public double[] getYFUsed() {
		return numYFUsed;
	}

	public void setYFUsed(int numYFUsed[]) {
		for (int i = 0; i < numYFUsed.length; i++) {
			this.numYFUsed[i] = Double.valueOf(numYFUsed[i]);
		}
	}

	public double[] getCSMUsed() {
		return numCSMUsed;
	}

	public void setCSMUsed(int numCSMUsed[]) {
		for (int i = 0; i < numCSMUsed.length; i++) {
			this.numCSMUsed[i] = Double.valueOf(numCSMUsed[i]);
		}
	}

	public double[] getVitAUsed() {
		return numVitAUsed;
	}

	public void setVitAUsed(int numVitAUsed[]) {
		for (int i = 0; i < numVitAUsed.length; i++) {
			this.numVitAUsed[i] = Double.valueOf(numVitAUsed[i]);
		}
	}

	// Getter and setter for the wastage rate
	public double[] getWastageRate() {
		return wastageRate;
	}

	public void setWastageRate(int wastageRate[]) {
		for (int i = 0; i < wastageRate.length; i++) {
			this.wastageRate[i] = Double.valueOf(wastageRate[i]);
		}
	}

	// Getter and setter for AEFI
	public double getAEFI() {
		return numAEFI;
	}

	public void setAEFI(int numAEFI) {
		this.numAEFI = Double.valueOf(numAEFI);
	}

	// Getters and setters for the safety box statistics
	public double getSafetyBoxes() {
		return numSafetyBoxes;
	}

	public void setSafetyBoxes(int numSafetyBoxes) {
		this.numSafetyBoxes = Double.valueOf(numSafetyBoxes);
	}

	public double[] getSafetyBoxesDisposed() {
		return wastageRate;
	}

	public void setSafetyBoxesDisposed(int numSafetyBoxesDisposed[]) {
		for (int i = 0; i < numSafetyBoxesDisposed.length; i++) {
			this.numSafetyBoxesDisposed[i] = Double.valueOf(numSafetyBoxesDisposed[i]);
		}
	}

	// ToString method
	public String toString() {
		return String.format("Monthly Return");
	}

}
