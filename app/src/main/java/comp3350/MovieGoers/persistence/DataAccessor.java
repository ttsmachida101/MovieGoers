package comp3350.MovieGoers.persistence;

import java.util.List;

import comp3350.MovieGoers.objects.Customer;
import comp3350.MovieGoers.objects.Event;
import comp3350.MovieGoers.objects.Movie;
import comp3350.MovieGoers.objects.Concessions;
import comp3350.MovieGoers.objects.Theatre;
import comp3350.MovieGoers.objects.Ticket;

public interface DataAccessor {

    String dbName = "db";

    public boolean close();
    
    public boolean getMovies(List<Movie> list);
    public boolean getMovies(List<Movie> list, int id);
    public Movie getMovie(int id);

    public boolean getTheatres(List<Theatre> list);
    public boolean getTheatres(List<Theatre> list, int id);
    public Theatre getTheatre(int id);

    public boolean getEvents(List<Event> list);
    public boolean getEvents(List<Event> list, int mID, int tID);
    public Event getEvent(int id);
    public boolean bookEvent(int id);

    public boolean getConcessions(List<Concessions> list);
    public boolean getConcessions(List<Concessions> list, int id);
    public Concessions getConcession(int id);

    public boolean addCustomer(Customer customer) throws Database.DuplicateException;
    public boolean deleteCustomer(String username);
    public boolean searchCustomers(String username);
    public Customer getCustomer(String username);
    public boolean setCvv (String val, String username);
    public boolean setCardNumber (String val, String username);

    // make it book the event
    public boolean createTicket(String cID, int eID, String time); // make it book the event
    public boolean getTicketsByCustomer(List<Ticket> list, String cID);
    public boolean addExtrasToTicket(List<Integer> concessions, int ticketID);
    public boolean getExtrasByTicket(List<Concessions> concessions, int ticketID);
}