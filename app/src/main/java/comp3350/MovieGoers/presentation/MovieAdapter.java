package comp3350.MovieGoers.presentation;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.RatingBar;
import comp3350.MovieGoers.R;


import java.util.ArrayList;

import comp3350.MovieGoers.objects.Movie;

import static android.content.ContentValues.TAG;


class MovieAdapter extends ArrayAdapter<Movie> {

    public MovieAdapter(Context context, ArrayList<Movie> movies) {
        super(context, 0, movies);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Get the data item for this position
        Movie movie = getItem(position);
        // Check if an existing view is being reused, otherwise inflate the view
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.movie_item, parent, false);
        }
        // Lookup view for data population
        ImageView moviePoster = (ImageView) convertView.findViewById(R.id.movie_poster);
        TextView movieTitle = (TextView) convertView.findViewById(R.id.movie_title);
        TextView moviedes = (TextView) convertView.findViewById(R.id.movie_des);
        Button movieBtn = (Button)  convertView.findViewById(R.id.movie_bookbtn);
        RatingBar ratingBar = (RatingBar) convertView.findViewById(R.id.rating_bar);

        // Populate the data into the template view using the data object
        moviePoster.setImageResource(getContext().getResources().getIdentifier(movie.getPoster(), "drawable", getContext().getPackageName()));
        movieTitle.setText(movie.getTitle());
        moviedes.setText(movie.getDirector()+"\n"+movie.getGenre()+"\n"+movie.getDuration()+" min");
        movieBtn.setTag(position);
        ratingBar.setRating(movie.getRating());

        // Return the completed view to render on screen
        return convertView;
    }



}