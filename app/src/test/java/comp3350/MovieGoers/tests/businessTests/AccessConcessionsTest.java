package comp3350.MovieGoers.tests.businessTests;

import junit.framework.TestCase;
import comp3350.MovieGoers.business.AccessConcessions;
import comp3350.MovieGoers.business.DataManager;
import comp3350.MovieGoers.objects.Concessions;
import comp3350.MovieGoers.tests.persistenceTests.DatabaseStub;


public class AccessConcessionsTest extends TestCase {


    public AccessConcessionsTest(String arg0) {
        super(arg0);
    }
    protected void setUp() throws Exception {
        DataManager.setSession(new DatabaseStub());
    }


    public void testGetConcessionById()
    {
        System.out.println("\nStarting testGetConcessionById test in testAccessConcession");
        Concessions concession;
        int id = 1;
        concession = AccessConcessions.getConcession(id);
        assertTrue(id==concession.getID());
        assertTrue("Combo #1".equals(concession.getItem()));
        assertTrue("popcorn, a drink and candy".equals(concession.getDesription()));
        id++;
        concession = AccessConcessions.getConcession(id);
        assertTrue(id==concession.getID());
        assertTrue("Combo #2".equals(concession.getItem()));
        assertTrue("popcorn and a drink".equals(concession.getDesription()));
        id++;
        concession = AccessConcessions.getConcession(id);
        assertTrue(id==concession.getID());
        assertTrue("Combo #1".equals(concession.getItem()));
        assertTrue("popcorn and a drink".equals(concession.getDesription()));
        id++;
        concession = AccessConcessions.getConcession(id);
        assertTrue(id==concession.getID());
        assertTrue("Combo #2".equals(concession.getItem()));
        assertTrue("popcorn, a drink and candy".equals(concession.getDesription()));
        id++;
        concession = AccessConcessions.getConcession(id);
        assertTrue(id==concession.getID());
        assertTrue("Combo #3".equals(concession.getItem()));
        assertTrue("nachos and a drink".equals(concession.getDesription()));
        id++;
        concession = AccessConcessions.getConcession(id);
        assertTrue(id==concession.getID());
        assertTrue("Candy".equals(concession.getItem()));
        assertTrue("smarties".equals(concession.getDesription()));
        id++;
        concession = AccessConcessions.getConcession(id);
        assertTrue(id==concession.getID());
        assertTrue("Candy".equals(concession.getItem()));
        assertTrue("skittles".equals(concession.getDesription()));
        id++;
        concession = AccessConcessions.getConcession(id);
        assertTrue(id==concession.getID());
        assertTrue("Chips".equals(concession.getItem()));
        assertTrue("doritos".equals(concession.getDesription()));
        id++;
        concession = AccessConcessions.getConcession(id);
        assertTrue(id==concession.getID());
        assertTrue("Chips".equals(concession.getItem()));
        assertTrue("cheetos".equals(concession.getDesription()));
        id++;

        //out of index
        concession = AccessConcessions.getConcession(id);
        assertTrue(null == concession);

        //negative index
        concession = AccessConcessions.getConcession(id);
        assertTrue(null == concession);

        System.out.println("Finished testGetConcessionById test in testAccessConcession");

    }
    protected void tearDown() throws Exception {
        DataManager.closeSession();
    }



}

  