package immunization.dao.domain;

import java.util.List;

import java.util.Map;

import com.google.gson.reflect.TypeToken;
import java.lang.reflect.Type;

import immunization.dao.GenericDao;
import immunization.dao.JsonDao;
import immunization.domain.ImmRegister;

/**
 * 
 * A Data Access Object specifically for ImmunizationRegister entities
 * 
 */
public class ImmRegisterDao {
	private GenericDao<Long, ImmRegister> ImmunizationDao;

	/**
	 * Default constructor creates Json file called ImmunizationRegister.json.
	 * TypeToken allows the GSON parser to map to/from JSON to objects
	 */
	public ImmRegisterDao() {
		Type t = new TypeToken<Map<Long, ImmRegister>>() {
		}.getType();
		ImmunizationDao = new JsonDao<>("ImmunizationRegister.json", t);
	}

	/**
	 * Constructor where the filename is provided
	 * 
	 * @param fileName file's name
	 */
	public ImmRegisterDao(String fileName) {
		Type t = new TypeToken<Map<Long, ImmRegister>>() {
		}.getType();
		ImmunizationDao = new JsonDao<>(fileName, t);
	}

	/**
	 * Support for other DAOs is provided
	 * 
	 * @param dao a Data Access Object class that implements GenericDao
	 */
	public ImmRegisterDao(GenericDao<Long, ImmRegister> dao) {
		ImmunizationDao = dao;
	}

	/**
	 * Returns the DAO used in the class
	 * 
	 * @return a DAO that implements GenericDao
	 */
	public GenericDao<Long, ImmRegister> getImmunizationDao() {
		return ImmunizationDao;
	}

	/**
	 * Add a ImmunizationRegister to the DAO repository
	 * 
	 * @param ImmRegister any ImmunizationRegister object
	 */
	public void add(ImmRegister register) {
		ImmunizationDao.add(register.getId(), register);
	}

	/**
	 * Update a ImmunizationRegister in the DAO repository
	 * 
	 * @param entity any ImmunizationRegister object
	 */
	public void update(ImmRegister entity) {
		ImmunizationDao.update(entity.getId(), entity);
	}

	/**
	 * Remove a ImmunizationRegister in the DAO repository
	 * 
	 * @param id id of the ImmunizationRegister object to remove
	 */

	public void remove(Long id) {
		ImmunizationDao.remove(id);
	}

	/**
	 * Find a ImmunizationRegister in the DAO repository
	 * 
	 * @param key id of the ImmunizationRegister object to locate
	 * @return ImmunizationRegister with id field equal to key
	 */
	public ImmRegister find(Long key) {
		return ImmunizationDao.find(key);
	}

	/**
	 * Generate a list of ImmunizationRegister in the DAO repository
	 * 
	 * @return List of ImmunizationRegister
	 */

	public List<ImmRegister> list() {
		return ImmunizationDao.list();
	}

}
