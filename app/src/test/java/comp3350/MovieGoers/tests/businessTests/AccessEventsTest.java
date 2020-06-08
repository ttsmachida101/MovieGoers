package comp3350.MovieGoers.tests.businessTests;

import junit.framework.TestCase;

import java.util.ArrayList;
import java.util.List;

import comp3350.MovieGoers.business.AccessEvents;
import comp3350.MovieGoers.business.DataManager;
import comp3350.MovieGoers.objects.Event;
import comp3350.MovieGoers.tests.persistenceTests.DatabaseStub;


public class AccessEventsTest extends TestCase {

    boolean setUp = false;
    boolean test1 = false;
    boolean test2 = false;

    public AccessEventsTest(String arg0) {
        super(arg0);
    }
    protected void setUp() throws Exception {
        if (!setUp) {
            DataManager.setSession(new DatabaseStub());
        }
        // do the setup
        setUp = true;

    }

    public void testGetEvent()
    {
        System.out.println("\nStarting getEvent test in testAccessEvent");

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
        test1 = true;
    }

    public void testGetEventById()
    {
        System.out.println("\nStarting testGetEventById test in testAccessEvent");
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

        System.out.println("Finished testGetEventById test in testAccessEvent");
        test2 = true;

    }
    protected void tearDown() throws Exception {
        if(test1&&test2){
            DataManager.closeSession();
        }
    }



}

  