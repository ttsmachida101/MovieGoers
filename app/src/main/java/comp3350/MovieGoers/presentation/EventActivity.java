package comp3350.MovieGoers.presentation;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

import comp3350.MovieGoers.R;
import comp3350.MovieGoers.business.AccessMovies;
import comp3350.MovieGoers.business.AccessEvents;
import comp3350.MovieGoers.business.AccessTheatres;
import comp3350.MovieGoers.objects.Event;

public class EventActivity extends AppCompatActivity {
    private ArrayList<Event> eventList;
    private EventAdapter adapter;
    private Event event;
    int movieID;
    int theatreID;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event);
        movieID = Integer.parseInt(getIntent().getStringExtra("movieID"));
        theatreID = Integer.parseInt(getIntent().getStringExtra("theatreID"));
        setTitle("Please book a time for \""+AccessMovies.getMovie(movieID).getTitle()+"\" at "+AccessTheatres.getTheatre(theatreID).getName());

        eventList = new ArrayList<>();
        AccessEvents.getEvents(eventList, movieID, theatreID);
        System.out.println(eventList);

        // Create the adapter to convert the array to views
        adapter = new EventAdapter(this,eventList);
        // Attach the adapter to a ListView
        ListView listView = (ListView) findViewById(R.id.eventList);
        listView.setAdapter(adapter);
    }

    public void clickMe2(View view){
        int eventID = eventList.get((Integer)view.getTag()).getID();
        Event e = AccessEvents.getEvent(eventID);

        if(e.getSeatsRemaining() > 0) {
            Toast.makeText(this, "Select a combo!", Toast.LENGTH_LONG).show();
            Intent next = new Intent(this, ConcessionActivity.class);
            next.putExtra("eventID", eventID + "");
            this.startActivity(next);
        } else {
            Toast.makeText(this, "No seats available.", Toast.LENGTH_LONG).show();
        }
    }
}
