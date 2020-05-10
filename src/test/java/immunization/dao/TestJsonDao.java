package immunization.dao;

import static org.junit.Assert.*;

import java.io.IOException;
import java.nio.file.DirectoryNotEmptyException;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Path;
import java.util.Date;
import java.util.Map;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import immunization.domain.ImmRegister;

import com.google.gson.reflect.TypeToken;
import java.lang.reflect.Type;


public class TestJsonDao 
{
	JsonDao<Long, ImmRegister> dao1; 
	JsonDao<String, String> dao2; 
	
/** 
 * Create a clean DAO before each test
 */
	@Before
	public void setupData() {
	    Type t1 = new TypeToken<Map<Long, ImmRegister>>(){}.getType(); 
	    Type t2 = new TypeToken<Map<String, String>>(){}.getType(); 

		dao1 = new JsonDao<Long, ImmRegister>("_test1.json",t1);
		dao2 = new JsonDao<String, String>("_test2.json",t2);
	}
	
	@Test
    public void testSaveandFind1() {
        Long id; 
        ImmRegister retrievedItem;
        
        ImmRegister oneItem = new ImmRegister(2L); 
        
        // get PK of first address
        id = oneItem.getId();
        
        dao1.add(id, oneItem);

        retrievedItem = (ImmRegister) dao1.find(id);
        
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
        dao1.add(id, oneItem);
        id = twoItem.getId();        
        dao1.add(id, twoItem);
        id = threeItem.getId();        
        dao1.add(id, threeItem);

        dao1.remove(twoItem.getId());
        
        retrievedItem = (ImmRegister) dao1.find(twoItem.getId());
        
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
        dao1.add(id, oneItem);
        id = twoItem.getId();        
        dao1.add(id, twoItem);
        id = threeItem.getId();        
        dao1.add(id, threeItem);

        // CHeck one of the three items to make sure it was stored correctly
        retrievedItem = (ImmRegister) dao1.find(twoItem.getId());
        
        assertNotNull("Dao returns a null item.", retrievedItem);
        assertEquals("Stored Id and original Id are not equal ", retrievedItem.getId() , twoItem.getId());
//        assertEquals("Stored date and original date are not equal ", retrievedItem.getDate() , twoItem.getDate());
        assertEquals("Stored parity and original parity are not equal ", retrievedItem.getParity() , twoItem.getParity());

//        twoItem.setDate(new Date());
        twoItem.setParity(1);
        dao1.update(twoItem.getId(),twoItem);
        retrievedItem = (ImmRegister) dao1.find(twoItem.getId());

        // Check that the modified elements was properly stored
        assertNotNull("Dao returns a null item.", retrievedItem);
        assertEquals("Stored Id and original Id are not equal ", retrievedItem.getId() , twoItem.getId());
//        assertEquals("Stored date and original date are not equal ", retrievedItem.getDate() , twoItem.getDate());
        assertEquals("Stored parity and original parity are not equal ", retrievedItem.getParity() , twoItem.getParity());

        retrievedItem = (ImmRegister) dao1.find(threeItem.getId());
        
        // check one of the other elements to make sure they are ok 
        assertNotNull("Dao returns a null item.", retrievedItem);
        assertEquals("Stored Id and original Id are not equal ", retrievedItem.getId() , threeItem.getId());
//        assertEquals("Stored date and original date are not equal ", retrievedItem.getDate() , threeItem.getDate());
        assertEquals("Stored parity and original parity are not equal ", retrievedItem.getParity() , threeItem.getParity());

        
	}

	@Test
    public void testSaveandFind2() {

		dao2.add("1", "Test 1");
		dao2.add("2", "Test 1");

        String retrievedItem = (String) dao2.find("1");
        
        assertNotNull("Dao returns a null item.", retrievedItem);
        assertEquals("Stored string and original String are not equal ", retrievedItem, "Test 1");
        
	}

	@Test
    public void testCountObjectsInFile() {
        Long id; 
        
        ImmRegister oneItem = new ImmRegister(1L); 
        ImmRegister twoItem = new ImmRegister(2L); 
        ImmRegister threeItem = new ImmRegister(3L); 
        
        // get PK of first address
        id = oneItem.getId();      
        dao1.add(id, oneItem);
        id = twoItem.getId();      
        dao1.add(id, twoItem);
        id = threeItem.getId();      
        dao1.add(id, threeItem);

        int retrievedCount = dao1.determineNumberOfObjectsInStream();
        
        assertEquals("Stored object counts are not equal ", retrievedCount, 3);
        
	}

	@Test
    public void testPersistenceAcrossTests() {
        Long id; 
	    Type t1 = new TypeToken<Map<Long, ImmRegister>>(){}.getType(); 

 	    JsonDao<Long,ImmRegister> pdao = new JsonDao<Long, ImmRegister>("_ptest.json",t1);
        ImmRegister oneItem = new ImmRegister((long) (Math.random()*100000)); 
        ImmRegister twoItem = new ImmRegister((long) (Math.random()*100000)); 
        ImmRegister threeItem = new ImmRegister((long) (Math.random()*100000));  
        
        int initialCount = pdao.determineNumberOfObjectsInStream();

        // get PK of first address
        id = oneItem.getId();        
        pdao.add(id, oneItem);
        id = twoItem.getId();        
        pdao.add(id, twoItem);
        id = threeItem.getId();        
        pdao.add(id, threeItem);

        int updatedCount = pdao.determineNumberOfObjectsInStream();

        assertEquals("Stored object counts are not equal after updating file", initialCount + 3, updatedCount);
    
		Path path3 = FileSystems.getDefault().getPath(".", "_ptest.json");
		try {
		    Files.delete(path3);
		} catch (NoSuchFileException x) {
		    System.err.format("%s: no such" + " file or directory%n", path3 );
		} catch (DirectoryNotEmptyException x) {
		    System.err.format("%s not empty%n", path3);
		} catch (IOException x) {
		    // File permission problems are caught here.
		    System.err.println(x);
		}

	}
	
	/** 
	 * Need to delete the file for next test
	 */
	@After
	public void tearDown()
	{

/*
 *   This will be run after every test ... overly ambitious
 */

		Path path1 = FileSystems.getDefault().getPath(".", "_test1.json");
		Path path2 = FileSystems.getDefault().getPath(".", "_test2.json");
		try {
		    Files.delete(path1);
		    Files.delete(path2);
		} catch (NoSuchFileException x) {
		} catch (DirectoryNotEmptyException x) {
		} catch (IOException x) {
		}
	}
	
}
