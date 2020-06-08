package comp3350.MovieGoers.presentation;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import java.util.ArrayList;
import comp3350.MovieGoers.R;
import comp3350.MovieGoers.business.AccessMovies;
import comp3350.MovieGoers.business.AccessTheatres;
import comp3350.MovieGoers.objects.Theatre;

public class TheatreActivity extends AppCompatActivity {
    private ArrayList<Theatre> theatreList;
    private TheaterAdapter adapter;
    private int movieID;
    //boolean isGuest;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_theatre);
        movieID = Integer.parseInt(getIntent().getStringExtra("movieID"));
        setTitle("Please select theatre for: \""+AccessMovies.getMovie(movieID).getTitle()+"\"");
        //Intent intent = getIntent();
        //isGuest = intent.getBooleanExtra("isGuest", true);

        theatreList = new ArrayList<>();
        AccessTheatres.getTheatres(theatreList, movieID);


        // Create the adapter to convert the array to views
        adapter = new TheaterAdapter(this, theatreList);
        // Attach the adapter to a ListView
        ListView listView = (ListView) findViewById(R.id.theatreList);
        listView.setAdapter(adapter);
    }
    public void clickMe1(View view){
        int theatreID = theatreList.get((Integer) view.getTag()).getID();
        Intent eventActivity = new Intent(this, EventActivity.class);
        eventActivity.putExtra("movieID", movieID+"");
        eventActivity.putExtra("theatreID", theatreID+"");
        //eventActivity.putExtra("isGuest", isGuest);
        this.startActivity(eventActivity);
    }
}
