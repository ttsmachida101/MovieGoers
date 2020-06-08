package comp3350.MovieGoers.tests.IntergrationTest;

import junit.framework.TestCase;
import comp3350.MovieGoers.business.DataManager;
import comp3350.MovieGoers.persistence.Database;
import comp3350.MovieGoers.tests.persistenceTests.DataAccessTest;


public class DataAccessHSQLDBTest extends TestCase {



    public DataAccessHSQLDBTest(String arg0) {
        super(arg0);
    }

    public void testDataAccess() throws Database.DuplicateException {
        String dbName= "dbTest";
        System.out.println("\nStarting DatabaseBasicTest test in DatabaseTest");
        DataManager.startSession(dbName);
        DataAccessTest.dataAccessTest(DataManager.getSession());
        DataManager.closeSession();
        System.out.println("\nfinish DatabaseBasicTest test in DatabaseTest");
    }


}

  