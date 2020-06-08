package comp3350.MovieGoers.tests.businessTests;

import junit.framework.TestCase;

import java.util.ArrayList;
import java.util.List;
import comp3350.MovieGoers.business.AccessTheatres;
import comp3350.MovieGoers.business.DataManager;
import comp3350.MovieGoers.objects.Theatre;
import comp3350.MovieGoers.tests.persistenceTests.DatabaseStub;


public class AccessTheatresTest extends TestCase {

    boolean setUp = false;
    boolean test1 = false;
    boolean test2 = false;

    public AccessTheatresTest(String arg0) {
        super(arg0);
    }
    protected void setUp() throws Exception {
        if (!setUp) {
            DataManager.setSession(new DatabaseStub());
        }
        // do the setup
        setUp = true;

    }

    public void testGetTheatre()
    {
        System.out.println("\nStarting getTheatre test in testAccessTheatre");

        //get theatre that has the movie id
        List<Theatre> theatres = new ArrayList<>();
        AccessTheatres.getTheatres(theatres,1);
        assertTrue(2==theatres.size());

        //get theatre that has the movie id
        theatres.clear();
        AccessTheatres.getTheatres(theatres,2);
        assertTrue(1==theatres.size());
        assertTrue(2==theatres.get(0).getID());

        theatres.clear();
        AccessTheatres.getTheatres(theatres,3);
        assertTrue(2==theatres.size());

        theatres.clear();
        AccessTheatres.getTheatres(theatres,4);
        assertTrue(2==theatres.size());

        theatres.clear();
        AccessTheatres.getTheatres(theatres,5);
        assertTrue(2==theatres.size());

        theatres.clear();
        AccessTheatres.getTheatres(theatres,6);
        assertTrue(2==theatres.size());

        //wrong index
        theatres.clear();
        AccessTheatres.getTheatres(theatres,10);
        assertTrue(0==theatres.size());

        //negative index(if<0, get all)
        theatres.clear();
        AccessTheatres.getTheatres(theatres,-1);
        assertTrue(2==theatres.size());

        System.out.println("Finished getTheatre test in testAccessTheatre");
        test1 = true;
    }

    public void testGetTheatreById()
    {
        System.out.println("\nStarting testGetTheatreById test in testAccessTheatre");
        Theatre theatre;
        int id = 1;
        //Movie id = 1
        theatre = AccessTheatres.getTheatre(id);
        assertTrue(id==theatre.getID());
        assertTrue("Silver City".equals(theatre.getName()));
        id++;
        //Movie id = 2
        theatre = AccessTheatres.getTheatre(id);
        assertTrue(id==theatre.getID());
        assertTrue("Cineplex".equals(theatre.getName()));
        id++;

        //out of index
        theatre = AccessTheatres.getTheatre(id);
        assertTrue(null == theatre);

        //negative of index
        theatre = AccessTheatres.getTheatre(-id);
        assertTrue(null == theatre);

        System.out.println("Finished testGetTheatreById test in testAccessTheatre");
        test2 = true;

    }
    protected void tearDown() throws Exception {
        if(test1&&test2){
            DataManager.closeSession();
        }
    }



}

  