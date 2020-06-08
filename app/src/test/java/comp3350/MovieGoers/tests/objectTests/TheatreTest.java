package comp3350.MovieGoers.tests.objectTests;

import junit.framework.TestCase;
import comp3350.MovieGoers.objects.Theatre;

public class TheatreTest extends TestCase
{
    public TheatreTest(String arg0){
        super(arg0);
    }

    public void testTheatre(){
        System.out.println("\nStarting testTheatreClass");

        Theatre theatre;
        theatre = new Theatre(1,"Silver City", "100 That Street", "logo");

        assertNotNull(theatre);
        assertTrue(1 == theatre.getID());
        assertTrue("Silver City".equals(theatre.getName()));
        assertTrue("100 That Street".equals(theatre.getLocation()));
        assertTrue("logo".equals(theatre.getLogo()));

        System.out.println("Finished testTheatreClass");
    }
}
