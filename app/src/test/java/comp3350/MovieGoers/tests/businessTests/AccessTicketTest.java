package comp3350.MovieGoers.tests.businessTests;

import junit.framework.TestCase;

import java.util.ArrayList;
import java.util.List;

import comp3350.MovieGoers.business.AccessTickets;
import comp3350.MovieGoers.business.DataManager;
import comp3350.MovieGoers.objects.Ticket;
import comp3350.MovieGoers.tests.persistenceTests.DatabaseStub;


public class AccessTicketTest extends TestCase {

    public AccessTicketTest(String arg0) {
        super(arg0);
    }
    protected void setUp() throws Exception {
        DataManager.setSession(new DatabaseStub());
    }

    public void testGetTicketsByCustomer()
    {
        System.out.println("\nStarting getMovie test in testGetTicketsByCustomer");
        List<Ticket> tickets = new ArrayList<>();
        AccessTickets.getTicketsByCustomer(tickets,"0");
        assertTrue(0==tickets.size());
        System.out.println("Finished testGetTicketsByCustomer");

    }

    protected void tearDown() throws Exception {
        DataManager.closeSession();
    }



}

  