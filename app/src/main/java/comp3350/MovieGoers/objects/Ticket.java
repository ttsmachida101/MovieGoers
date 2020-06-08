package comp3350.MovieGoers.objects;
import java.lang.reflect.Array;
import java.util.List;
import java.util.ArrayList;

import comp3350.MovieGoers.presentation.ConcessionAdapter;

public class Ticket {
    private int ID;
    private Event event;
    private Customer customer;
    private String timestamp;

    private List concessions = new ArrayList<>();

    public Ticket(int ID, Customer customer, Event event, String timestamp) {
        this.ID = ID;
        this.customer = customer;
        this.event = event;
        this.timestamp = timestamp;
        event.bookSeat();
    }

    public int getID() { return ID; }
    public void getConcessions(List<Integer> list) { list.addAll(concessions); }
    public void addConcessions(List<Integer> list) { concessions.addAll(list); }
    public Customer getCustomer() { return customer; }
    public Event getEvent() { return event; }
    public String getTimestamp() { return timestamp; }

}
