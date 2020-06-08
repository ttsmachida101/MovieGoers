package comp3350.MovieGoers.tests.objectTests;

import junit.framework.TestCase;

import java.util.ArrayList;
import java.util.List;
import comp3350.MovieGoers.objects.Customer;
import comp3350.MovieGoers.objects.Event;
import comp3350.MovieGoers.objects.Ticket;

public class TicketTest extends TestCase
{
    public TicketTest(String arg0) {
        super(arg0);
    }

    public void testTicket() {
        System.out.println("\nStarting testTicket");

        Ticket ticket;
        Event evt;
        evt = new Event(1, null, null, 10, 59, 200, 0, 10.99f);
        Customer customer = new Customer("username","password","fname",null,"cardnum000","000");
        ticket = new Ticket(1,customer, evt, "1234");

        List<Integer> list = new ArrayList<>();

        assertNotNull(ticket);
        assertTrue(1 == ticket.getID());

        //get empty list
        ticket.getConcessions(list);
        assertTrue(null != list);
        assertTrue(0 == list.size());

        //add 3 concession
        list.add(0);
        list.add(1);
        list.add(2);
        ticket.addConcessions(list);
        list.clear();

        //check element added to list;
        ticket.getConcessions(list);
        assertTrue(null != list);
        assertTrue(3 == list.size());
        assertTrue(list.contains(0));
        assertTrue(list.contains(1));
        assertTrue(list.contains(2));

        assertTrue("username".equals(ticket.getCustomer().getUsername()));
        assertTrue(1 == ticket.getEvent().getID());
        assertTrue("1234".equals(ticket.getTimestamp()));

        System.out.println("Finished testTicket");
        
    }
    
}