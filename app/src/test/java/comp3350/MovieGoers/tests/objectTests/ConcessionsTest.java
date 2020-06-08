package comp3350.MovieGoers.tests.objectTests;

import junit.framework.TestCase;

import comp3350.MovieGoers.objects.Concessions;


public class ConcessionsTest extends TestCase {

    public ConcessionsTest (String arg0) {
        super(arg0);

    }

    public void testConcession()
    {
        System.out.println("\nStarting testConcessionClass");
        Concessions concession = new Concessions(1,2,"item", 9.99f,"itemDesc");
        assertNotNull(concession);
        assertTrue(1 == concession.getID());
        assertTrue(2 == concession.getTheatreID());
        assertTrue("item".equals(concession.getItem()));
        assertTrue((Math.abs(9.99f - concession.getItemPrice()) < 0.0000001));
        assertTrue("itemDesc".equals(concession.getDesription()));
        System.out.println("Finished testConcessionClass");
    }
}

