package comp3350.MovieGoers.business;

import java.util.List;
import java.util.ArrayList;

import comp3350.MovieGoers.objects.Event;
import comp3350.MovieGoers.persistence.DataAccessor;

public class AccessEvents {

    private static DataAccessor session = DataManager.getSession();

    public static void getEvents(List<Event> list, int movieID, int theatreID) {
        list.clear();
        session.getEvents(list, movieID, theatreID);
    }

    public static Event getEvent(int id) {
        return session.getEvent(id);
    }

    public static boolean bookEvent(int id) {
        return session.bookEvent(id);
    }
}
