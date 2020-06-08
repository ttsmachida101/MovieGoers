package comp3350.MovieGoers.tests.persistenceTests;

import java.util.ArrayList;
import java.util.List;
import comp3350.MovieGoers.objects.Concessions;
import comp3350.MovieGoers.objects.Customer;
import comp3350.MovieGoers.objects.Movie;
import comp3350.MovieGoers.objects.Event;
import comp3350.MovieGoers.objects.Theatre;
import comp3350.MovieGoers.objects.Ticket;
import comp3350.MovieGoers.persistence.DataAccessor;
import comp3350.MovieGoers.persistence.Database;



public class DatabaseStub implements DataAccessor {

    private static boolean inite = false;

    private static ArrayList<Movie> movies = new ArrayList<>();
    private static ArrayList<Theatre> theatres = new ArrayList<>();
    private static ArrayList<Event> events = new ArrayList<>();
    private static ArrayList<Concessions> concessions  = new ArrayList<>();
    private static ArrayList<Customer> customers = new ArrayList<>();
    private static ArrayList<Ticket> tickets = new ArrayList<>();

    private static int nextTicketID = 1;

    public DatabaseStub(){
        if(!inite) {
            initialize();
        }
        else{
            close();
            initialize();
        }
    }
    public static void initialize() {
        inite = true;
        movies.add(new Movie(1,"Star Wars", "Science Fiction","Rian Johnson", 120, (float)5.0,"p0"));
        movies.add(new Movie( 2,"About Time", "Romance","Richard Curtis", 132, (float)0.5,"p1"));
        movies.add(new Movie(3, "Happy Deathday", "Horror","Christopher B. Landon", 125, (float)3.0, "p2"));
        movies.add(new Movie(4,"Jumanji", "Fantasy","Joe Johnston", 150, (float)4.5, "jumanji"));
        movies.add(new Movie(5,"Food Inc.", "Documentary","Robert Kenner", 120, (float)2.4, "p4"));
        movies.add(new Movie(6 ,"The Hobbit", "Fantasy", "Peter Jackson", 150, (float)4.5, "thehobbit"));

        theatres.add( new Theatre(1,"Silver City", "Polo Park Shopping Center\n817 St James St, Winnipeg, MB R3G 3L3","l0"));
        theatres.add( new Theatre(2, "Cineplex", "Cineplex Odeon McGillivray & VIP Cinemas\n2190 McGillivray Blvd, Winnipeg, MB R3Y 1S6","l1"));

        concessions.add( new Concessions(1,1, "Combo #1", (float)10.99, "popcorn, a drink and candy"));
        concessions.add( new Concessions(2,1, "Combo #2", (float)8.99, "popcorn and a drink"));
        concessions.add( new Concessions(3,2, "Combo #1", (float)8.99, "popcorn and a drink"));
        concessions.add( new Concessions(4,2, "Combo #2", (float)10.99, "popcorn, a drink and candy"));
        concessions.add( new Concessions(5,2, "Combo #3", (float)10.99, "nachos and a drink"));
        concessions.add( new Concessions(6,1, "Candy", (float)1.99, "smarties"));
        concessions.add( new Concessions(7,2, "Candy", (float)0.99, "skittles"));
        concessions.add( new Concessions(8,2, "Chips", (float)2.99, "doritos"));
        concessions.add( new Concessions(9,1, "Chips", (float)3.99, "cheetos"));

////  Star Wars on Screen 0 at 4:30PM, 6:30PM, 8:30PM
////      INSERT INTO Events VALUES(1, 1, 1, 0, 1630, 75, 75);
////      INSERT INTO Events VALUES(2, 1, 1, 0, 1830, 75, 0);
////      INSERT INTO Events VALUES(3, 1, 1, 0, 2030, 75, 0);
        events.add(new Event(1,theatres.get(0), movies.get(0), 0, 1630, 75, 75, 8.99f));
        events.add(new Event(2,theatres.get(0), movies.get(0), 0, 1830, 75, 0, 8.99f));
        events.add(new Event(3,theatres.get(0), movies.get(0), 0, 2030, 75, 0, 8.99f));
//
////  Happy Deathday on Screen 2 at 4:30PM, 6:30PM, 8:30PM
////      INSERT INTO Events VALUES(4, 1, 3, 2, 1630, 150, 0);
////      INSERT INTO Events VALUES(5, 1, 3, 2, 1830, 150, 0);
////      INSERT INTO Events VALUES(6, 1, 3, 2, 2030, 150, 0);
        events.add(new Event(4,theatres.get(0), movies.get(2), 2, 1630, 150, 0, 10.99f));
        events.add(new Event(5,theatres.get(0), movies.get(2), 2, 1830, 150, 0, 10.99f));
        events.add(new Event(6,theatres.get(0), movies.get(2), 2, 2030, 150, 0, 10.99f));
//
////  About Time on Screen 1 at 4:30PM, 6:30PM, 8:30PM
////      INSERT INTO Events VALUES(7, 1, 4, 1, 930, 100, 0);
////      INSERT INTO Events VALUES(8, 1, 4, 1, 1130, 100, 0);
////      INSERT INTO Events VALUES(9, 1, 4, 1, 1430, 100, 0);
        events.add(new Event(7,theatres.get(0), movies.get(3), 1, 930, 100, 0, 9.99f));
        events.add(new Event(8,theatres.get(0), movies.get(3), 1, 1130, 100, 0, 9.99f));
        events.add(new Event(9,theatres.get(0), movies.get(3), 1, 1430, 100, 0, 9.99f));
//
////  Jumanji on Screen 1 at 9:30AM, 11:30AM, 2:30PM
////      INSERT INTO Events VALUES(10, 1, 5, 2, 930, 150, 0, 8.99);
////      INSERT INTO Events VALUES(11, 1, 5, 2, 1130, 150, 0, 8.99);
////      INSERT INTO Events VALUES(12, 1, 5, 2, 1430, 150, 0, 8.99);
        events.add(new Event(10,theatres.get(0), movies.get(4), 2, 930, 150, 0, 10.99f));
        events.add(new Event(11,theatres.get(0), movies.get(4), 2, 1130, 150, 0, 10.99f));
        events.add(new Event(12,theatres.get(0), movies.get(4), 2, 1430, 150, 0, 10.99f));
//
////  The Hobbit on Screen 0 at 10:30PM and 1:30AM
////      INSERT INTO Events VALUES(13, 1, 6, 0, 2230, 75, 0);
////      INSERT INTO Events VALUES(14, 1, 6, 0, 130, 75, 0);
        events.add(new Event(13,theatres.get(0), movies.get(5), 0, 2230, 75, 0, 8.99f));
        events.add(new Event(14,theatres.get(0), movies.get(5), 0, 130, 75, 0, 8.99f));
//
//        // Cineplex is playing...
//
////  Star Wars on Screen 0 at 6:30PM, 8:30PM, 10:30PM
////      INSERT INTO Events VALUES(15, 2, 1, 0, 1830, 80, 0);
////      INSERT INTO Events VALUES(16, 2, 1, 0, 2030, 80, 0);
////      INSERT INTO Events VALUES(17, 2, 1, 0, 2230, 80, 0);
        events.add(new Event(15,theatres.get(1), movies.get(0), 0, 1830, 80, 0, 11.99f));
        events.add(new Event(16,theatres.get(1), movies.get(0), 0, 2030, 80, 0, 11.99f));
        events.add(new Event(17,theatres.get(1), movies.get(0), 0, 2230, 80, 0, 11.99f));
//
////  About Time on Screen 0 at 9:30AM, 11:30AM, 2:30PM
////      INSERT INTO Events VALUES(18, 2, 2, 0, 930, 80, 0);
////      INSERT INTO Events VALUES(19, 2, 2, 0, 1130, 80, 0);
////      INSERT INTO Events VALUES(20, 2, 2, 0, 1430, 80, 0);
        events.add(new Event(18,theatres.get(1), movies.get(1), 0, 930, 80,0, 11.99f));
        events.add(new Event(19,theatres.get(1), movies.get(1), 0, 1130, 80,0, 11.99f));
        events.add(new Event(20,theatres.get(1), movies.get(1), 0, 1430, 80,0, 11.99f));
//
////  Happy Deathday on Screen 2 at 9:30AM, 11:30AM, 2:30PM
////      INSERT INTO Events VALUES(21, 2, 3, 0, 930, 100, 0);
////      INSERT INTO Events VALUES(22, 2, 3, 0, 1130, 100, 0);
////      INSERT INTO Events VALUES(23, 2, 3, 0, 1430, 100, 0);
        events.add(new Event(21,theatres.get(1), movies.get(2), 0, 930, 100, 0, 11.99f));
        events.add(new Event(22,theatres.get(1), movies.get(2), 0, 1130, 100, 0, 11.99f));
        events.add(new Event(23,theatres.get(1), movies.get(2), 0, 1430, 100, 0, 11.99f));
//
////  Jumanji on Screen 1 at 6:30PM, 8:30PM, 10:30PM
////      INSERT INTO Events VALUES(24, 2, 4, 1, 1830, 90, 0);
////      INSERT INTO Events VALUES(25, 2, 4, 1, 2030, 90, 0);
////      INSERT INTO Events VALUES(26, 2, 4, 1, 2230, 90, 0);
        events.add(new Event(24,theatres.get(1), movies.get(3), 1, 1830, 90,0, 7.99f));
        events.add(new Event(25,theatres.get(1), movies.get(3), 1, 2030, 90,0, 7.99f));
        events.add(new Event(26,theatres.get(1), movies.get(3), 1, 2230, 90,0, 7.99f));
//
////  Food Inc. on Screen 2 at 6:30PM, 8:30PM, 10:30PM
////      INSERT INTO Events VALUES(27, 2, 5, 2, 1830, 100, 0);
////      INSERT INTO Events VALUES(28, 2, 5, 2, 2030, 100, 0);
////      INSERT INTO Events VALUES(29, 2, 5, 2, 2230, 100, 0);
        events.add(new Event(27,theatres.get(1), movies.get(4), 2, 1830, 100,0, 12.99f));
        events.add(new Event(28,theatres.get(1), movies.get(4), 2, 2030, 100,0, 12.99f));
        events.add(new Event(29,theatres.get(1), movies.get(4), 2, 2230, 100,0, 12.99f));
//
////  The Hobbit on Screen 3 at 9:30PM and 11:30PM
////      INSERT INTO Events VALUES(30, 2, 6, 3, 2130, 75, 0);
////      INSERT INTO Events VALUES(31, 2, 6, 3, 2330, 75, 0);
        events.add(new Event(30,theatres.get(1), movies.get(5), 3, 2130, 75,0, 7.99f));
        events.add(new Event(31,theatres.get(1), movies.get(5), 3, 2330, 75,0, 7.99f));

    }
    public boolean close(){
        movies.clear();
        theatres.clear();
        events.clear();
        concessions.clear();
        customers.clear();
        return true;
    }
    public boolean getMovies(List<Movie> list){
        list.addAll(movies);
        return true;
    }
    public boolean getMovies(List<Movie> list, int id){return true;}
    public Movie getMovie(int id){
        if(id<1)
            return null;
        if(id>movies.size())
            return null;
        return movies.get(id-1);
    }

    public boolean getTheatres(List<Theatre> list){
        list.addAll(theatres);
        return true;
    }
    public boolean getTheatres(List<Theatre> list, int id){
        int[] temp = new int[theatres.size()];
        if(id<1){
            list.addAll(theatres);
            return true;
        }
        for (int j = 0;j<temp.length;j++)
            temp[j] = 1;
        for(int i=0; i<events.size();i++){
            if(events.get(i).getMovieID()==id){
                if(temp[events.get(i).getTheatreID()-1]==1){
                    list.add(events.get(i).getTheatre());
                    temp[events.get(i).getTheatreID()-1] = 0;
                }
            }
        }
        return true;
    }
    public Theatre getTheatre(int id){
        if(id>theatres.size()){
            return null;
        }
        if(id<1){
            return null;
        }
        return theatres.get(id-1);
    }

    public boolean getEvents(List<Event> list){
        list.addAll(events);
        return true;
    }
    public boolean getEvents(List<Event> list, int mID, int tID){
        if(mID<1 && tID<1){
            list.addAll(events);
        }else if(mID <1){
            for(int i=0;i<events.size();i++){
                if(events.get(i).getTheatreID()==tID){
                    list.add(events.get(i));
                }
            }
        }else if(tID <1){
            for(int i=0;i<events.size();i++){
                if(events.get(i).getMovieID()==mID){
                    list.add(events.get(i));
                }
            }
        }else{
            for(int i=0;i<events.size();i++){
                if(events.get(i).getMovieID()==mID && events.get(i).getTheatreID()==tID){
                    list.add(events.get(i));
                }
            }
        }
        return true;
    }
    public Event getEvent(int id){
        if(id<1){
            return null;
        }
        if(id>events.size()){
            return null;
        }
        return events.get(id-1);
    }
    public boolean bookEvent(int id){
        events.get(id-1).bookSeat();
        return true;
    }

    public boolean getConcessions(List<Concessions> list){
        list.addAll(concessions);
        return true;
    }
    public boolean getConcessions(List<Concessions> list, int id){
        for(int i=0;i<concessions.size();i++){
            if(concessions.get(i).getTheatreID() == id){
                list.add(concessions.get(i));
            }
        }
        return true;
    }
    public Concessions getConcession(int id){
        if(id<1){
            return null;
        }
        if(id>concessions.size()){
            return null;
        }
        return concessions.get(id-1);
    }

    public boolean addCustomer(Customer customer) throws Database.DuplicateException {
        if(customer==null)
            return false;
        if(searchCustomers(customer.getUsername())){
            throw new Database.DuplicateException("username");
        }
        customers.add(customer);
        return true;
    }

    public boolean deleteCustomer(String username){
        boolean del = false;
        for(int i=0;i<customers.size()&&!del;i++){
            if(customers.get(i).getUsername().equals(username)){
                customers.remove(i);
                del = true;
            }
        }
        return true;
    }
    public boolean searchCustomers(String username){
        boolean res = false;
        for(int i=0;i<customers.size()&&!res;i++){
            if(customers.get(i).getUsername().equals(username)){
                res = true;
            }
        }
        return res;
    }


    public Customer getCustomer(String username) {
        Customer customer = null;
        for(int i=0;i<customers.size();i++)
        {
            if(customers.get(i).getUsername().equals(username)){
                customer = customers.get(i);
            }
        }
        return customer;
    }

    public boolean setCvv(String username, String val) {
        getCustomer(username).setCvv(val);
        return false;
    }//TODO fix this

    public boolean setCardNumber(String username, String val) {
        getCustomer(username).setCardNumber(val);
        return false;
    }//TODO fix this

    public boolean createTicket(String cID, int eID, String time) {
        tickets.add(new Ticket(nextTicketID++, getCustomer(cID), getEvent(eID), time));
        return false;
    }

    public boolean getTicketsByCustomer(List<Ticket> list, String cID) {
        for(int i = 0; i < tickets.size(); i++) {
            if(tickets.get(i).getCustomer().getUsername().equals(cID))
                list.add(tickets.get(i));
        }
        return false;
    }


    public Ticket getTicketByID(int id) {
        int i = 0;
        while(i < tickets.size() && tickets.get(i++).getID() != id);
        return i == tickets.size() ? null : tickets.get(i);
    }
    public boolean addExtrasToTicket(List<Integer> concessions, int ticketID) {
        Ticket t = getTicketByID(ticketID);
        t.addConcessions(concessions);
        return false;
    }

    public boolean getExtrasByTicket(List<Concessions> concessions, int ticketID) {
        ArrayList<Integer> ids = new ArrayList<>();
        getTicketByID(ticketID).getConcessions(ids);
        for(int i = 0; i < ids.size(); i++)
            concessions.add(getConcession(ids.get(i)));
        return false;
    }


}
