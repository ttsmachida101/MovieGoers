package comp3350.MovieGoers.tests.objectTests;

import junit.framework.TestCase;
import comp3350.MovieGoers.objects.Movie;

public class MovieTest extends TestCase
{
    public MovieTest(String arg0) {
        super(arg0);
    }

    public void testMovie() {
        Movie movie;

        System.out.println("\nStarting testMovie");
        
        movie = new Movie(1,"A movie", "Comedy", "John Smith", 120, 4.5f, "poster");
        
        assertNotNull(movie);
        assertTrue(1 == movie.getID());
        assertTrue("A movie".equals(movie.getTitle()));
        assertTrue("Comedy".equals(movie.getGenre()));
        assertTrue("John Smith".equals(movie.getDirector()));
        assertTrue(120 == movie.getDuration());
        assertTrue((Math.abs(4.5f - movie.getRating()) < 0.0000001));
        //suppose to be Math.abs(x -y) < epsilon, where epsilon is a really small value for comparing floats
        assertTrue("poster".equals(movie.getPoster()));
        assertTrue("A movie".equals(movie.toString()));

        
        System.out.println("Finished testMovie");
        
    }
    
}