package immunization;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import immunization.dao.TestJsonDao;
import immunization.dao.TestObjectStreamDao;
import immunization.dao.domain.TestImmRegisterDao;
import immunization.service.impl.IntegrationTestImmRegister1Service;

@RunWith(Suite.class)
@SuiteClasses({  TestObjectStreamDao.class, IntegrationTestImmRegister1Service.class, TestImmRegisterDao.class, TestJsonDao.class })
public class AllTests {

}
