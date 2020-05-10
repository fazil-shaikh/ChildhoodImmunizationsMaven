package immunization.dao.domain;

import java.util.List;

import java.util.Map;

import com.google.gson.reflect.TypeToken;
import java.lang.reflect.Type;

import immunization.dao.GenericDao;
import immunization.dao.JsonDao;
import immunization.domain.Vaccine;

/**
 * 
 * A Data Access Object specifically for Vaccine entities
 * 
 */
public class VaccineDao {
	private GenericDao<Long, Vaccine> VaccineDao;

	/**
	 * Default constructor creates Json file called ImmunizationRegister.json.
	 * TypeToken allows the GSON parser to map to/from JSON to objects
	 */
	public VaccineDao() {
		Type t = new TypeToken<Map<Long, Vaccine>>() {
		}.getType();
		VaccineDao = new JsonDao<>("Vaccine.json", t);
	}

	/**
	 * Constructor where the filename is provided
	 * 
	 * @param fileName file's name
	 */
	public VaccineDao(String fileName) {
		Type t = new TypeToken<Map<Long, Vaccine>>() {
		}.getType();
		VaccineDao = new JsonDao<>(fileName, t);
	}

	/**
	 * Support for other DAOs is provided
	 * 
	 * @param dao a Data Access Object class that implements GenericDao
	 */
	public VaccineDao(GenericDao<Long, Vaccine> dao) {
		VaccineDao = dao;
	}

	/**
	 * Returns the DAO used in the class
	 * 
	 * @return a DAO that implements GenericDao
	 */
	public GenericDao<Long, Vaccine> getImmunizationDao() {
		return VaccineDao;
	}

	/**
	 * Add a ImmunizationRegister to the DAO repository
	 * 
	 * @param ImmRegister any ImmunizationRegister object
	 */
	public void add(Vaccine vac) {
		VaccineDao.add(vac.getVId(), vac);
	}

	/**
	 * Update a ImmunizationRegister in the DAO repository
	 * 
	 * @param entity any ImmunizationRegister object
	 */
	public void update(Vaccine entity) {
		VaccineDao.update(entity.getVId(), entity);
	}

	/**
	 * Remove a ImmunizationRegister in the DAO repository
	 * 
	 * @param id id of the ImmunizationRegister object to remove
	 */

	public void remove(Long id) {
		VaccineDao.remove(id);
	}

	/**
	 * Find a ImmunizationRegister in the DAO repository
	 * 
	 * @param key id of the ImmunizationRegister object to locate
	 * @return ImmunizationRegister with id field equal to key
	 */
	public Vaccine find(Long key) {
		return VaccineDao.find(key);
	}

	/**
	 * Generate a list of ImmunizationRegister in the DAO repository
	 * 
	 * @return List of ImmunizationRegister
	 */

	public List<Vaccine> list() {
		return VaccineDao.list();
	}

}