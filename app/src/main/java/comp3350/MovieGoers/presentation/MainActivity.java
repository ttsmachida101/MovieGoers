package comp3350.MovieGoers.presentation;

import java.io.*;

import android.content.res.AssetManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ListView;
import java.util.ArrayList;

import comp3350.MovieGoers.R;
import comp3350.MovieGoers.business.DataManager;
import comp3350.MovieGoers.business.AccessMovies;
import comp3350.MovieGoers.objects.Movie;
import comp3350.MovieGoers.persistence.DataAccessor;


public class MainActivity extends AppCompatActivity {
    private ArrayList<Movie> movieList;
    private MovieAdapter adapter;

    //boolean isGuest;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle("MovieGoers - Titles Now Playing");

        //Intent intent = getIntent();
        //isGuest = intent.getBooleanExtra("isGuest", true);

        movieList = new ArrayList<>();
        AccessMovies.getMovies(movieList);

        // Create the adapter to convert the array to views
        adapter = new MovieAdapter(this, movieList);
        // Attach the adapter to a ListView
        ListView listView = (ListView) findViewById(R.id.movieList);
        listView.setAdapter(adapter);
    }

    public void clickMe(View view) {
        int movieID = movieList.get((Integer)view.getTag()).getID();
        Intent theatreActivity = new Intent(this, TheatreActivity.class);
        theatreActivity.putExtra("movieID", movieID+"");

        this.startActivity(theatreActivity);
    }

}

