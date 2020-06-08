package comp3350.MovieGoers.tests.persistenceTests;

import junit.framework.TestCase;

import java.util.ArrayList;
import java.util.List;
import comp3350.MovieGoers.objects.Concessions;
import comp3350.MovieGoers.objects.Customer;
import comp3350.MovieGoers.objects.Event;
import comp3350.MovieGoers.objects.Movie;
import comp3350.MovieGoers.objects.Theatre;
import comp3350.MovieGoers.objects.Ticket;
import comp3350.MovieGoers.persistence.DataAccessor;
import comp3350.MovieGoers.persistence.Database;


public class DataAccessTest extends TestCase
{
	private DataAccessor dataAccess;

	public DataAccessTest(String arg0)
	{
		super(arg0);
	}

	public void setUp() {
		System.out.println("\nStarting Persistence test DataAccess (using stub)");

		// Use the following statements to run with the stub database:
		dataAccess = new DatabaseStub();
		// or switch to the real database:
		// dataAccess = new DataAccessObject(Main.dbName);
		// dataAccess.open(Main.getDBPathName());
	}

	public void tearDown() {
		dataAccess.close();
		System.out.println("Finished Persistence test DataAccess (using stub)");
	}

	public static void dataAccessTest(DataAccessor dataAccess) throws Database.DuplicateException {
		DataAccessTest dataAccessTest = new DataAccessTest("");
		dataAccessTest.dataAccess = dataAccess;
		dataAccessTest.test1();
	}

	public void test1() throws Database.DuplicateException {
		ArrayList<Movie> movies;
		ArrayList<Theatre> theatres;
		ArrayList<Concessions> concessions;
        ArrayList<Ticket> tickets;

        Movie movie;

		int id = 1;
		movies = new ArrayList<>();
		dataAccess.getMovies(movies);
		assertNotNull(movies);
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


		id = 1;
		//Movie id = 1
		movie = dataAccess.getMovie(id);
		assertTrue(id==movie.getID());
		assertTrue("Star Wars".equals(movie.getTitle()));
		assertTrue("Science Fiction".equals(movie.getGenre()));
		id++;
		//Movie id = 2
		movie = dataAccess.getMovie(id);
		assertTrue(id==movie.getID());
		assertTrue("About Time".equals(movie.getTitle()));
		assertTrue("Romance".equals(movie.getGenre()));
		id++;
		//Movie id = 3
		movie = dataAccess.getMovie(id);
		assertTrue(id==movie.getID());
		assertTrue("Happy Deathday".equals(movie.getTitle()));
		assertTrue("Horror".equals(movie.getGenre()));
		id++;
		//Movie id = 4
		movie = dataAccess.getMovie(id);
		assertTrue(id==movie.getID());
		assertTrue("Jumanji".equals(movie.getTitle()));
		assertTrue("Fantasy".equals(movie.getGenre()));
		id++;
		//Movie id = 5
		movie = dataAccess.getMovie(id);
		assertTrue(id==movie.getID());
		assertTrue("Food Inc.".equals(movie.getTitle()));
		assertTrue("Documentary".equals(movie.getGenre()));
		id++;
		//Movie id = 6
		movie = dataAccess.getMovie(id);
		assertTrue(id==movie.getID());
		assertTrue("The Hobbit".equals(movie.getTitle()));
		assertTrue("Fantasy".equals(movie.getGenre()));
		id++;

		//out of index
		movie = dataAccess.getMovie(id);
		assertTrue(null == movie);

		//negative index
		movie = dataAccess.getMovie(-1);
		assertTrue(null == movie);

		//get theatre that has the movie id
		theatres = new ArrayList<>();
		dataAccess.getTheatres(theatres,1);
		assertTrue(2==theatres.size());

		//get theatre that has the movie id
		theatres.clear();
		dataAccess.getTheatres(theatres,2);
		assertTrue(1==theatres.size());
		assertTrue(2==theatres.get(0).getID());

		theatres.clear();
		dataAccess.getTheatres(theatres,3);
		assertTrue(2==theatres.size());

		theatres.clear();
		dataAccess.getTheatres(theatres,4);
		assertTrue(2==theatres.size());

		theatres.clear();
		dataAccess.getTheatres(theatres,5);
		assertTrue(2==theatres.size());

		theatres.clear();
		dataAccess.getTheatres(theatres,6);
		assertTrue(2==theatres.size());

		//wrong index
		theatres.clear();
		dataAccess.getTheatres(theatres,10);
		assertTrue(0==theatres.size());

		//negative index(if<0, get all)
		theatres.clear();
		dataAccess.getTheatres(theatres,-1);
		assertTrue(2==theatres.size());

		Theatre theatre;
		id = 1;
		//Movie id = 1
		theatre = dataAccess.getTheatre(id);
		assertTrue(id==theatre.getID());
		assertTrue("Silver City".equals(theatre.getName()));
		id++;
		//Movie id = 2
		theatre = dataAccess.getTheatre(id);
		assertTrue(id==theatre.getID());
		assertTrue("Cineplex".equals(theatre.getName()));
		id++;

		//out of index
		theatre = dataAccess.getTheatre(id);
		assertTrue(null == theatre);

		//negative of index
		theatre = dataAccess.getTheatre(-id);
		assertTrue(null == theatre);

		//get theatre that has the movie id
		List<Event> events = new ArrayList<>();
		dataAccess.getEvents(events,1,1);
		assertTrue(3==events.size());

		events.clear();
		dataAccess.getEvents(events,1,2);
		assertTrue(3==events.size());

		events.clear();
		dataAccess.getEvents(events,2,1);
		assertTrue(0==events.size());

		events.clear();
		dataAccess.getEvents(events,2,2);
		assertTrue(3==events.size());

		events.clear();
		dataAccess.getEvents(events,3,1);
		assertTrue(3==events.size());

		events.clear();
		dataAccess.getEvents(events,3,2);
		assertTrue(3==events.size());

		events.clear();
		dataAccess.getEvents(events,4,1);
		assertTrue(3==events.size());

		events.clear();
		dataAccess.getEvents(events,4,2);
		assertTrue(3==events.size());

		events.clear();
		dataAccess.getEvents(events,5,1);
		assertTrue(3==events.size());

		events.clear();
		dataAccess.getEvents(events,5,2);
		assertTrue(3==events.size());

		events.clear();
		dataAccess.getEvents(events,6,1);
		assertTrue(2==events.size());

		events.clear();
		dataAccess.getEvents(events,6,2);
		assertTrue(2==events.size());

		//out of index
		events.clear();
		dataAccess.getEvents(events,60,20);
		assertTrue(0==events.size());

		events.clear();
		dataAccess.getEvents(events,1,20);
		assertTrue(0==events.size());

		events.clear();
		dataAccess.getEvents(events,60,2);
		assertTrue(0==events.size());

		events.clear();
		dataAccess.getEvents(events,0,20);
		assertTrue(0==events.size());

		events.clear();
		dataAccess.getEvents(events,60,0);
		assertTrue(0==events.size());

		//index<1 get all events
		events.clear();
		dataAccess.getEvents(events,0,0);
		assertTrue(31==events.size());
		//get all events of theatre 2
		events.clear();
		dataAccess.getEvents(events,0,2);
		assertTrue(17==events.size());
		//get event of movie1 form all theatre
		events.clear();
		dataAccess.getEvents(events,1,0);
		assertTrue(6==events.size());

		//access by id
		Event event;
		id = 1;
		//Movie id = 1
		event = dataAccess.getEvent(id);
		assertTrue(id==event.getID());
		id += 5;

		//Movie id = 6
		event = dataAccess.getEvent(id);
		assertTrue(id==event.getID());
		id += 5;

		//Movie id = 11
		event = dataAccess.getEvent(id);
		assertTrue(id==event.getID());
		id += 5;

		//Movie id = 16
		event = dataAccess.getEvent(id);
		assertTrue(id==event.getID());

		//negative index
		event = dataAccess.getEvent(-id);
		assertTrue(null == event);

		//out of index
		event = dataAccess.getEvent(9999);
		assertTrue(null == event);


		Customer customer = new Customer("userName","","fName","lName","00","00");
		assertTrue(dataAccess.addCustomer(customer));
		assertTrue(dataAccess.searchCustomers("userName"));
		assertTrue(!dataAccess.searchCustomers("hi"));
		assertTrue(dataAccess.deleteCustomer("userName"));

		customer = new Customer("","","fName","lName","00","00");
		assertTrue(dataAccess.addCustomer(customer));
		assertTrue(dataAccess.searchCustomers(""));
		assertTrue(!dataAccess.searchCustomers("hi"));
		assertTrue(dataAccess.deleteCustomer(""));

		customer = new Customer("userName","","fName","lName","00","00");
		assertTrue(dataAccess.addCustomer(customer));

		try {
			dataAccess.addCustomer(customer);
			fail("Expected exception");
		} catch (Database.DuplicateException expect) {
			//Pass!
		}

		assertTrue(dataAccess.deleteCustomer("userName"));


		Concessions concession;
		id = 1;
		concession = dataAccess.getConcession(id);
		assertTrue(id==concession.getID());
		assertTrue("Combo #1".equals(concession.getItem()));
		assertTrue("popcorn, a drink and candy".equals(concession.getDesription()));
		id++;
		concession = dataAccess.getConcession(id);
		assertTrue(id==concession.getID());
		assertTrue("Combo #2".equals(concession.getItem()));
		assertTrue("popcorn and a drink".equals(concession.getDesription()));
		id++;
		concession = dataAccess.getConcession(id);
		assertTrue(id==concession.getID());
		assertTrue("Combo #1".equals(concession.getItem()));
		assertTrue("popcorn and a drink".equals(concession.getDesription()));
		id++;
		concession = dataAccess.getConcession(id);
		assertTrue(id==concession.getID());
		assertTrue("Combo #2".equals(concession.getItem()));
		assertTrue("popcorn, a drink and candy".equals(concession.getDesription()));
		id++;
		concession = dataAccess.getConcession(id);
		assertTrue(id==concession.getID());
		assertTrue("Combo #3".equals(concession.getItem()));
		assertTrue("nachos and a drink".equals(concession.getDesription()));
		id++;
		concession = dataAccess.getConcession(id);
		assertTrue(id==concession.getID());
		assertTrue("Candy".equals(concession.getItem()));
		assertTrue("smarties".equals(concession.getDesription()));
		id++;
		concession = dataAccess.getConcession(id);
		assertTrue(id==concession.getID());
		assertTrue("Candy".equals(concession.getItem()));
		assertTrue("skittles".equals(concession.getDesription()));
		id++;
		concession = dataAccess.getConcession(id);
		assertTrue(id==concession.getID());
		assertTrue("Chips".equals(concession.getItem()));
		assertTrue("doritos".equals(concession.getDesription()));
		id++;
		concession = dataAccess.getConcession(id);
		assertTrue(id==concession.getID());
		assertTrue("Chips".equals(concession.getItem()));
		assertTrue("cheetos".equals(concession.getDesription()));
		id++;

		//out of index
		concession = dataAccess.getConcession(id);
		assertTrue(null == concession);

		//negative index
		concession = dataAccess.getConcession(id);
		assertTrue(null == concession);

		tickets = new ArrayList<>();
		dataAccess.getTicketsByCustomer(tickets,"0");
		assertTrue(0==tickets.size());


	}
}
