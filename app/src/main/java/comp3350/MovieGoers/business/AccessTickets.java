package comp3350.MovieGoers.business;

import java.util.List;

import comp3350.MovieGoers.objects.Movie;
import comp3350.MovieGoers.objects.Ticket;
import comp3350.MovieGoers.persistence.DataAccessor;

public class AccessTickets {

    private static DataAccessor session = DataManager.getSession();

    public static void createTicket(String customerID, int eventID, String time) {
        session.createTicket(customerID, eventID, time);
    }

    public static boolean getTicketsByCustomer(List<Ticket> list, String cID) {
        return session.getTicketsByCustomer(list, cID);
    }



}
