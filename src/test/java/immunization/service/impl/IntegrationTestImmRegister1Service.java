package immunization.service.impl;

import static org.junit.Assert.*;

import java.io.IOException;
import java.nio.file.DirectoryNotEmptyException;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import immunization.dao.GenericDao;
import immunization.dao.ObjectStreamDao;
import immunization.dao.domain.ImmRegisterDao;
import immunization.domain.ImmRegister;
import immunization.domain.Vaccine;
import immunization.service.ImmRegisterService;

public class IntegrationTestImmRegister1Service {
	GenericDao<Long, ImmRegister> dao;
	ImmRegisterDao immRegDao;
    ImmRegisterService testService;
    
	@Before
	public void setupData() {
	   dao = new ObjectStreamDao<Long, ImmRegister>("_test.ser");
	   immRegDao = new ImmRegisterDao(dao);
	   testService = new ImmRegister1Service(immRegDao);
	}
	
	@Test
    public void testaddACIRecord() {
		ImmRegister retrievedItem;
        List<ImmRegister> clist;
        
        testService.addACIRecord(
        		(long) (Math.random()*100000), 
        		"SampleMotherName", 
        		"SampleChildsName",
        		1L, // parity
        		20, // age
        		50, // weight
        		new ArrayList<Vaccine>(), // vaccine list 
        		false, //AEFI
        		true, // ITN
        		"Good condition"); // remarks
		
        clist = immRegDao.list();
        
        retrievedItem = clist.get(0);
        
        assertNotNull("Dao returns a null item.", retrievedItem);
	}

	@Test
    public void testmaxId() {
        Long id, newMax; 
        List<ImmRegister> clist;
                
        id = testService.maxCIRecordId();
        
        ImmRegister oneItem = new ImmRegister(2L); 
        oneItem.setId(id + 5);
        immRegDao.add(oneItem);
        newMax = testService.maxCIRecordId();
        clist = immRegDao.list();
        
        ImmRegister retrievedItem = clist.get(0);
        System.out.println(retrievedItem);
        
        assertEquals("Stored Id and original Id are not equal ", newMax.longValue(), id.longValue() + 5);
	}
	
	/** 
	 * Need to delete the file for next test
	 */
	@After
	public void tearDown()
	{

		Path path = FileSystems.getDefault().getPath(".", "_test.ser");
		try {
		    Files.delete(path);
		} catch (NoSuchFileException x) {
		    System.err.format("%s: no such" + " file or directory%n", path);
		} catch (DirectoryNotEmptyException x) {
		    System.err.format("%s not empty%n", path);
		} catch (IOException x) {
		    // File permission problems are caught here.
		    System.err.println(x);
		}


	}
}

