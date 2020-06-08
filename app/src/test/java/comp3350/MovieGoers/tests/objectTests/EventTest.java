package comp3350.MovieGoers.tests.objectTests;

import junit.framework.TestCase;
import comp3350.MovieGoers.objects.Event;
import comp3350.MovieGoers.objects.Movie;
import comp3350.MovieGoers.objects.Theatre;

public class EventTest extends TestCase{
    Event event;
    Theatre theatre;
    Movie movie;

    public EventTest(String arg0){
        super(arg0);



        movie = new Movie(3, "A movie", "Comedy", "John Smith", 120, 4.5f, "poster");
        theatre = new Theatre(2, "Silver City", "100 That Street", "logo");


    }

    public void testEvent() {
        System.out.println("\nStarting testEventClass");
        assertNotNull(movie);
        assertNotNull(theatre);
        event = new Event(1, theatre, movie, 10, 59, 200, 0, 10.99f);


        assertNotNull(event);
        assertTrue(1 == event.getID());
        assertTrue(theatre == event.getTheatre());
        assertTrue(2 == event.getTheatreID());
        assertTrue(movie == event.getMovie());
        assertTrue(3 == event.getMovieID());
        assertTrue(10 == event.getScreen());
        assertTrue(200 == event.getSeatCapacity());
        assertTrue(0 == event.getSeatsTaken());
        assertTrue(200 == event.getSeatsRemaining());
        assertTrue(10.99f == event.getPrice());
        System.out.println("Finished testEventClass");
    }
    public void testBookSeats() {
        System.out.println("\nStarting booking/canceling seats");
        event = new Event(1, theatre, movie, 10, 59, 200, 0, 10.99f);
        //add 2 seats
        event.bookSeat();
        event.bookSeat();
        assertTrue(198 == event.getSeatsRemaining());
        assertTrue(2 == event.getSeatsTaken());

        //cancel 1 seat
        event.cancelSeat();
        assertTrue(199 == event.getSeatsRemaining());
        assertTrue(1 == event.getSeatsTaken());

        //test negative seat taken by cancel two more seats
        event.cancelSeat();
        event.cancelSeat();
        assertTrue(201 == event.getSeatsRemaining());
        assertTrue(-1 == event.getSeatsTaken());
        System.out.println("Finished booking/canceling seats");
    }
    public void testTimeRegular() {
        System.out.println("\nStarting reqular time display");
        //test time regular case
        event = new Event(1, theatre, movie, 10, 59, 200, 0, 9.99f);
        assertTrue("12:59AM".equals(event.displayTime()));

        event = new Event(0, theatre, movie, 0, 159, 0, 0, 10.99f);
        assertTrue("1:59AM".equals(event.displayTime()));

        event = new Event(0, theatre, movie, 0, 100, 0, 0, 11.99f);
        assertTrue("1:00AM".equals(event.displayTime()));

        event = new Event(0, theatre, movie, 0, 1201, 0,0, 7.99f);
        assertTrue("12:01PM".equals(event.displayTime()));

        event = new Event(0, theatre, movie, 0, 0, 0,0, 8.99f);
        assertTrue("12:00AM".equals(event.displayTime()));
        System.out.println("Finished reqular time display");
    }
    public void testTimeMorethan60() {
        System.out.println("\nStarting time display more than 60mins");
        //99 mins?
        event = new Event(0, theatre, movie, 0, 199, 0, 0, 13.99f);
        assertTrue("1:99AM".equals(event.displayTime()));

        event = new Event(0, theatre, movie, 0, 9999, 0, 0, 5.99f);
        assertTrue("87:99PM".equals(event.displayTime()));
        System.out.println("Finished display more than 60mins");
    }

    public void testTimeNegative() {
        System.out.println("\nStarting negative time");
        //negative times
        event = new Event(0, theatre, movie, 0, -10, 0,0, 4.99f);
        assertTrue("11:90AM".equals(event.displayTime()));

        event = new Event(0, theatre, movie, 0, -1300, 0,0, 7.99f);
        assertTrue("-1:00AM".equals(event.displayTime()));


        System.out.println("Finished negative time");

    }
}
