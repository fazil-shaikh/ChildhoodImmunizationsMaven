package immunization.dao.domain;

import static org.junit.Assert.*;

import java.io.IOException;
import java.nio.file.DirectoryNotEmptyException;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Path;
import java.util.Date;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import immunization.dao.GenericDao;
import immunization.dao.ObjectStreamDao;
import immunization.domain.ImmRegister;

public class TestImmRegisterDao {

	ImmRegisterDao dao; 
	
/** 
 * Create a clean DAO before each test
 */
	@Before
	public void setupData() {
		   dao = new ImmRegisterDao("_ImmRegisterTest.ser");
	}
	
	@Test
    public void testSaveandFind1() {
        Long id; 
        ImmRegister retrievedItem;
        ImmRegister oneItem = new ImmRegister((long) (Math.random()*100000)); 
        
        // get PK of first address
        id = oneItem.getId();
        
        dao.add(oneItem);

        retrievedItem = dao.find(id);
        
        assertNotNull("Dao returns a null item.", retrievedItem);
        assertEquals("Stored Id and original Id are not equal ", retrievedItem.getId() , oneItem.getId());
//        assertEquals("Stored date and original date are not equal ", retrievedItem.getDate(), oneItem.getDate());
        // add more tests
	}

	@Test
    public void testSaveandRemove1() {
        Long id; 
        ImmRegister retrievedItem;
        
    	
        ImmRegister oneItem = new ImmRegister((long) (Math.random()*100000)); 
        ImmRegister twoItem = new ImmRegister((long) (Math.random()*100000)); 
        ImmRegister threeItem = new ImmRegister((long) (Math.random()*100000)); 
        
        // get PK of first address
        id = oneItem.getId();        
        dao.add(oneItem);
        id = twoItem.getId();        
        dao.add(twoItem);
        id = threeItem.getId();        
        dao.add(threeItem);

        dao.remove(twoItem.getId());
        
        retrievedItem = dao.find(twoItem.getId());
        
        assertNull("Dao returns a null item.", retrievedItem);
        
	}

	@Test
    public void testSaveandUpdate1() {
        Long id; 
        ImmRegister retrievedItem;
        
    	
        ImmRegister oneItem = new ImmRegister((long) (Math.random()*100000)); 
        ImmRegister twoItem = new ImmRegister((long) (Math.random()*100000)); 
        ImmRegister threeItem = new ImmRegister((long) (Math.random()*100000)); 
        
        // get PK of first address
        id = oneItem.getId();        
        dao.add(oneItem);
        id = twoItem.getId();        
        dao.add(twoItem);
        id = threeItem.getId();        
        dao.add(threeItem);

        // CHeck one of the three items to make sure it was stored correctly
        retrievedItem = dao.find(twoItem.getId());
        
        assertNotNull("Dao returns a null item.", retrievedItem);
        assertEquals("Stored Id and original Id are not equal ", retrievedItem.getId() , twoItem.getId());
//        assertEquals("Stored date and original date are not equal ", retrievedItem.getDate() , twoItem.getDate());
        assertEquals("Stored parity and original parity are not equal ", retrievedItem.getParity() , twoItem.getParity());

//        twoItem.setDate(new Date());
        twoItem.setParity(1);
        dao.update(twoItem);
        retrievedItem = (ImmRegister) dao.find(twoItem.getId());

        // Check that the modified elements was properly stored
        assertNotNull("Dao returns a null item.", retrievedItem);
        assertEquals("Stored Id and original Id are not equal ", retrievedItem.getId() , twoItem.getId());
//        assertEquals("Stored date and original date are not equal ", retrievedItem.getDate() , twoItem.getDate());
        assertEquals("Stored parity and original parity are not equal ", retrievedItem.getParity() , twoItem.getParity());

        retrievedItem = dao.find(threeItem.getId());
        
        // check one of the other elements to make sure they are ok 
        assertNotNull("Dao returns a null item.", retrievedItem);
        assertEquals("Stored Id and original Id are not equal ", retrievedItem.getId() , threeItem.getId());
//        assertEquals("Stored date and original date are not equal ", retrievedItem.getDate() , threeItem.getDate());
        assertEquals("Stored parity and original parity are not equal ", retrievedItem.getParity() , threeItem.getParity());
        
	}

	@Test
    public void testUpdateAdd() {
        Long id; 
        ImmRegister retrievedItem;  
    	
        ImmRegister oneItem = new ImmRegister((long) (Math.random()*100000)); 
        ImmRegister twoItem = new ImmRegister((long) (Math.random()*100000)); 
        ImmRegister threeItem = new ImmRegister((long) (Math.random()*100000)); 
        
        // get PK of first address
        id = oneItem.getId();        
        dao.add(oneItem);
        id = twoItem.getId();        
        dao.add(twoItem);

        // CHeck one of the three items to make sure it was stored correctly
        retrievedItem = dao.find(twoItem.getId());
        
        assertNotNull("Dao returns a null item.", retrievedItem);
        assertEquals("Stored Id and original Id are not equal ", retrievedItem.getId() , twoItem.getId());
//        assertEquals("Stored date and original date are not equal ", retrievedItem.getDate() , twoItem.getDate());
        assertEquals("Stored parity and original parity are not equal ", retrievedItem.getParity() , twoItem.getParity());

//        twoItem.setDate(new Date());
        twoItem.setParity(1);
        dao.update(twoItem);
        retrievedItem = (ImmRegister) dao.find(twoItem.getId());

        // Check that the modified elements was properly stored
        assertNotNull("Dao returns a null item.", retrievedItem);
        assertEquals("Stored Id and original Id are not equal ", retrievedItem.getId() , twoItem.getId());
//        assertEquals("Stored date and original date are not equal ", retrievedItem.getDate() , twoItem.getDate());
        assertEquals("Stored parity and original parity are not equal ", retrievedItem.getParity() , twoItem.getParity());
        
        id = threeItem.getId();        
        dao.add(threeItem);
        
        retrievedItem = dao.find(threeItem.getId());
        
        // check one of the other elements to make sure they are ok 
        assertNotNull("Dao returns a null item.", retrievedItem);
        assertEquals("Stored Id and original Id are not equal ", retrievedItem.getId() , threeItem.getId());
//        assertEquals("Stored date and original date are not equal ", retrievedItem.getDate() , threeItem.getDate());
        assertEquals("Stored parity and original parity are not equal ", retrievedItem.getParity() , threeItem.getParity());

	}


		
	/** 
	 * Need to delete the file for next test
	 */
	@After
	public void tearDown()
	{

		Path path1 = FileSystems.getDefault().getPath(".", "_ImmRegisterTest.ser");
		try {
		    Files.delete(path1);
		} catch (NoSuchFileException x) {
		    System.err.format("%s: no such" + " file or directory%n", path1);
		} catch (DirectoryNotEmptyException x) {
		    System.err.format("%s not empty%n", path1);
		} catch (IOException x) {
		    // File permission problems are caught here.
		    System.err.println(x);
		}
	}
	
}
