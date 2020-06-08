package comp3350.MovieGoers.tests.IntergrationTest;

import junit.framework.TestCase;

import java.util.ArrayList;
import java.util.List;

import comp3350.MovieGoers.business.AccessConcessions;
import comp3350.MovieGoers.business.AccessCustomers;
import comp3350.MovieGoers.business.AccessEvents;
import comp3350.MovieGoers.business.AccessMovies;
import comp3350.MovieGoers.business.AccessTheatres;
import comp3350.MovieGoers.business.AccessTickets;
import comp3350.MovieGoers.business.DataManager;
import comp3350.MovieGoers.objects.Concessions;
import comp3350.MovieGoers.objects.Customer;
import comp3350.MovieGoers.objects.Event;
import comp3350.MovieGoers.objects.Movie;
import comp3350.MovieGoers.objects.Theatre;
import comp3350.MovieGoers.objects.Ticket;
import comp3350.MovieGoers.persistence.Database;
import comp3350.MovieGoers.tests.persistenceTests.DatabaseStub;

public class BusinessPersistenceSeamTest extends TestCase
{
	public BusinessPersistenceSeamTest(String arg0)
	{
		super(arg0);
	}
	boolean setUp = false;
	boolean test1 = false;
	boolean test2 = false;
	boolean test3 = false;
	boolean test4 = false;
	boolean test5 = false;
	boolean test6 = false;

	protected void setUp() throws Exception {
		if (!setUp) {
			DataManager.setSession(new DatabaseStub());
		}
		// do the setup
		setUp = true;

	}

	public void testAccessMovies()
	{
		System.out.println("\nStarting Integration test of AccessMovies to persistence");
		List<Movie> movies = new ArrayList<>();
		AccessMovies.getMovies(movies);

		assertTrue(0<movies.size());

		//testGetMovies
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


		//testGetMovieById
		id = 1;
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

		System.out.println("Finished Integration test of AccessMovies to persistence");
		test1 = true;
	}

	public void testAccessTheatres()
	{
		System.out.println("\nStarting Integration test of AccessMovies to persistence");

		//Test getTheatre
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

		//Test getTheatreById
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

		System.out.println("Finished Integration test of AccessTheatre to persistence");
		test2 = true;

	}

	public void testAccessCustomer() throws Database.DuplicateException {
		System.out.println("\nStarting Integration test of AccessCustomer to persistence");

		Customer customer = new Customer("userName","","fName","lName","00","00");
		assertTrue(AccessCustomers.addCustomer(customer));
		assertTrue(AccessCustomers.searchCustomer("userName"));
		assertTrue(!AccessCustomers.searchCustomer("hi"));
		assertTrue(AccessCustomers.deleteCustomer("userName"));

		customer = new Customer("","","fName","lName","00","00");
		assertTrue(AccessCustomers.addCustomer(customer));
		assertTrue(AccessCustomers.searchCustomer(""));
		assertTrue(!AccessCustomers.searchCustomer("hi"));
		assertTrue(AccessCustomers.deleteCustomer(""));

		customer = new Customer("userName","","fName","lName","00","00");
		assertTrue(AccessCustomers.addCustomer(customer));

		try {
			AccessCustomers.addCustomer(customer);
			fail("Expected exception");
		} catch (Database.DuplicateException expect) {
			//Pass!
		}
		assertTrue(AccessCustomers.deleteCustomer("userName"));

		System.out.println("Finished Integration test of AccessCustomer to persistence");

		test3 = true;
	}

	public void testAccessEvents()
	{
		System.out.println("\nStarting Integration test of AccessEvents to persistence");

		//Test getEvents
		//get theatre that has the movie id
		List<Event> events = new ArrayList<>();
		AccessEvents.getEvents(events,1,1);
		assertTrue(3==events.size());

		events.clear();
		AccessEvents.getEvents(events,1,2);
		assertTrue(3==events.size());

		events.clear();
		AccessEvents.getEvents(events,2,1);
		assertTrue(0==events.size());

		events.clear();
		AccessEvents.getEvents(events,2,2);
		assertTrue(3==events.size());

		events.clear();
		AccessEvents.getEvents(events,3,1);
		assertTrue(3==events.size());

		events.clear();
		AccessEvents.getEvents(events,3,2);
		assertTrue(3==events.size());

		events.clear();
		AccessEvents.getEvents(events,4,1);
		assertTrue(3==events.size());

		events.clear();
		AccessEvents.getEvents(events,4,2);
		assertTrue(3==events.size());

		events.clear();
		AccessEvents.getEvents(events,5,1);
		assertTrue(3==events.size());

		events.clear();
		AccessEvents.getEvents(events,5,2);
		assertTrue(3==events.size());

		events.clear();
		AccessEvents.getEvents(events,6,1);
		assertTrue(2==events.size());

		events.clear();
		AccessEvents.getEvents(events,6,2);
		assertTrue(2==events.size());

		//out of index
		events.clear();
		AccessEvents.getEvents(events,60,20);
		assertTrue(0==events.size());

		events.clear();
		AccessEvents.getEvents(events,1,20);
		assertTrue(0==events.size());

		events.clear();
		AccessEvents.getEvents(events,60,2);
		assertTrue(0==events.size());

		events.clear();
		AccessEvents.getEvents(events,0,20);
		assertTrue(0==events.size());

		events.clear();
		AccessEvents.getEvents(events,60,0);
		assertTrue(0==events.size());

		//index<1 get all events
		events.clear();
		AccessEvents.getEvents(events,0,0);
		assertTrue(31==events.size());
		//get all events of theatre 2
		events.clear();
		AccessEvents.getEvents(events,0,2);
		assertTrue(17==events.size());
		//get event of movie1 form all theatre
		events.clear();
		AccessEvents.getEvents(events,1,0);
		assertTrue(6==events.size());

		System.out.println("Finished getEvent test in testAccessEvent");
		System.out.println("\nStarting testGetEventById test in testAccessEvent");

		//Test getEventByID
		Event event;
		int id = 1;
		//Movie id = 1
		event = AccessEvents.getEvent(id);
		assertTrue(id==event.getID());
		id += 5;

		//Movie id = 6
		event = AccessEvents.getEvent(id);
		assertTrue(id==event.getID());
		id += 5;

		//Movie id = 11
		event = AccessEvents.getEvent(id);
		assertTrue(id==event.getID());
		id += 5;

		//Movie id = 16
		event = AccessEvents.getEvent(id);
		assertTrue(id==event.getID());

		//negative index
		event = AccessEvents.getEvent(-id);
		assertTrue(null == event);

		//out of index
		event = AccessEvents.getEvent(9999);
		assertTrue(null == event);

		System.out.println("Finished Integration test of AccessEvents to persistence");
		test4 = true;
	}

	public void testAccessConcessions()
	{
		System.out.println("\nStarting Integration test of AccessConcessions to persistence");
		//Test getConcession
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

		//Test tetConcessionById
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

		System.out.println("Finished Integration test of AccessConcessions to persistence");
		test5 = true;
	}

	public void testAccessTickets()
	{
		System.out.println("\nStarting Integration test of AccessTickets to persistence");
		List<Ticket> tickets = new ArrayList<>();
		AccessTickets.getTicketsByCustomer(tickets,"0");
		assertTrue(0==tickets.size());
		System.out.println("Finished Integration test of AccessTickets to persistence");
		test6 = true;
	}


	protected void tearDown() throws Exception {
		if(test1&&test2&&test3&&test4&&test5&&test6){
			DataManager.closeSession();
		}
	}


}