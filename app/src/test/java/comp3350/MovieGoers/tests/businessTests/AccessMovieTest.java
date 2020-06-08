package comp3350.MovieGoers.tests.businessTests;

import junit.framework.TestCase;

import java.util.ArrayList;
import java.util.List;
import comp3350.MovieGoers.business.AccessMovies;
import comp3350.MovieGoers.business.DataManager;
import comp3350.MovieGoers.objects.Movie;
import comp3350.MovieGoers.tests.persistenceTests.DatabaseStub;


public class AccessMovieTest extends TestCase {

    boolean setUp = false;
    boolean test1 = false;
    boolean test2 = false;

    public AccessMovieTest(String arg0) {
        super(arg0);
    }
    protected void setUp() throws Exception {
        if (!setUp) {
            DataManager.setSession(new DatabaseStub());
        }
        // do the setup
        setUp = true;

    }

    public void testGetMovies()
    {
        System.out.println("\nStarting getMovie test in testAccessMovie");
        List<Movie> movies = new ArrayList<>();
        AccessMovies.getMovies(movies);

        assertTrue(0<movies.size());

        Movie movie;
        int id = 1;
        //Movie id = 1
        movie = movies.get(id-1);
        assertTrue(id==movie.getID());
        assertTrue("Star Wars".equals(movie.getTitle()));
        assertTrue("Science Fiction".equals(movie.getGenre()));
        id++;
        //Movie id = 2
        movie = movies.get(id-1);
        assertTrue(id==movie.getID());
        assertTrue("About Time".equals(movie.getTitle()));
        assertTrue("Romance".equals(movie.getGenre()));
        id++;
        //Movie id = 3
        movie = movies.get(id-1);
        assertTrue(id==movie.getID());
        assertTrue("Happy Deathday".equals(movie.getTitle()));
        assertTrue("Horror".equals(movie.getGenre()));
        id++;
        //Movie id = 4
        movie = movies.get(id-1);
        assertTrue(id==movie.getID());
        assertTrue("Jumanji".equals(movie.getTitle()));
        assertTrue("Fantasy".equals(movie.getGenre()));
        id++;
        //Movie id = 5
        movie = movies.get(id-1);
        assertTrue(id==movie.getID());
        assertTrue("Food Inc.".equals(movie.getTitle()));
        assertTrue("Documentary".equals(movie.getGenre()));
        id++;
        //Movie id = 6
        movie = movies.get(id-1);
        assertTrue(id==movie.getID());
        assertTrue("The Hobbit".equals(movie.getTitle()));
        assertTrue("Fantasy".equals(movie.getGenre()));

        System.out.println("Finished getMovie test in testAccessMovie");
        test1 = true;
    }

    public void testGetMovieById()
    {
        System.out.println("\nStarting testGetMovieById test in testAccessMovie");
        Movie movie;
        int id = 1;
        //Movie id = 1
        movie = AccessMovies.getMovie(id);
        assertTrue(id==movie.getID());
        assertTrue("Star Wars".equals(movie.getTitle()));
        assertTrue("Science Fiction".equals(movie.getGenre()));
        id++;
        //Movie id = 2
        movie = AccessMovies.getMovie(id);
        assertTrue(id==movie.getID());
        assertTrue("About Time".equals(movie.getTitle()));
        assertTrue("Romance".equals(movie.getGenre()));
        id++;
        //Movie id = 3
        movie = AccessMovies.getMovie(id);
        assertTrue(id==movie.getID());
        assertTrue("Happy Deathday".equals(movie.getTitle()));
        assertTrue("Horror".equals(movie.getGenre()));
        id++;
        //Movie id = 4
        movie = AccessMovies.getMovie(id);
        assertTrue(id==movie.getID());
        assertTrue("Jumanji".equals(movie.getTitle()));
        assertTrue("Fantasy".equals(movie.getGenre()));
        id++;
        //Movie id = 5
        movie = AccessMovies.getMovie(id);
        assertTrue(id==movie.getID());
        assertTrue("Food Inc.".equals(movie.getTitle()));
        assertTrue("Documentary".equals(movie.getGenre()));
        id++;
        //Movie id = 6
        movie = AccessMovies.getMovie(id);
        assertTrue(id==movie.getID());
        assertTrue("The Hobbit".equals(movie.getTitle()));
        assertTrue("Fantasy".equals(movie.getGenre()));
        id++;

        //out of index
        movie = AccessMovies.getMovie(id);
        assertTrue(null == movie);

        //negative index
        movie = AccessMovies.getMovie(-1);
        assertTrue(null == movie);



        System.out.println("Finished testGetMovieById test in testAccessMoive");
        test2 = true;

    }
    protected void tearDown() throws Exception {
        if(test1&&test2){
            DataManager.closeSession();
        }
    }



}

  