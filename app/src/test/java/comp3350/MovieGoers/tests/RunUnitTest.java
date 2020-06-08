package comp3350.MovieGoers.tests;

import junit.framework.Test;
import junit.framework.TestSuite;
import comp3350.MovieGoers.tests.businessTests.AccessConcessionsTest;
import comp3350.MovieGoers.tests.businessTests.AccessCustomerTest;
import comp3350.MovieGoers.tests.businessTests.AccessEventsTest;
import comp3350.MovieGoers.tests.businessTests.AccessMovieTest;
import comp3350.MovieGoers.tests.businessTests.AccessTheatresTest;
import comp3350.MovieGoers.tests.businessTests.AccessTicketTest;
import comp3350.MovieGoers.tests.businessTests.CustomerStateTest;
import comp3350.MovieGoers.tests.objectTests.EventTest;
import comp3350.MovieGoers.tests.objectTests.MovieTest;
import comp3350.MovieGoers.tests.objectTests.TheatreTest;
import comp3350.MovieGoers.tests.objectTests.ConcessionsTest;
import comp3350.MovieGoers.tests.objectTests.CustomerTest;
import comp3350.MovieGoers.tests.objectTests.TicketTest;
import comp3350.MovieGoers.tests.persistenceTests.PersistenceTests;


public class RunUnitTest
{

    public static TestSuite suite;

    public static Test suite()
    {
        suite = new TestSuite("All tests");
        testObjects();
        testBusiness();
        testPersistence();
        return suite;
    }

    private static void testObjects() {
        suite.addTestSuite(EventTest.class);
        suite.addTestSuite(MovieTest.class);
        suite.addTestSuite(TheatreTest.class);
        suite.addTestSuite(ConcessionsTest.class);
        suite.addTestSuite(CustomerTest.class);
        suite.addTestSuite(TicketTest.class);
    }

    private static void testBusiness()
    {
        suite.addTestSuite(AccessMovieTest.class);
        suite.addTestSuite(AccessTheatresTest.class);
        suite.addTestSuite(AccessEventsTest.class);
        suite.addTestSuite(AccessConcessionsTest.class);
        suite.addTestSuite(AccessCustomerTest.class);
        suite.addTestSuite(AccessTicketTest.class);
        suite.addTestSuite(CustomerStateTest.class);
    }

    private static void testPersistence(){
        suite.addTest(PersistenceTests.suite());
    }
}

