package comp3350.MovieGoers.presentation;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.RatingBar;
import comp3350.MovieGoers.R;


import java.util.ArrayList;

import comp3350.MovieGoers.business.AccessMovies;
import comp3350.MovieGoers.objects.Ticket;
import comp3350.MovieGoers.objects.Movie;

class ProfileTicketAdapter extends ArrayAdapter<Ticket> {

public ProfileTicketAdapter(Context context, ArrayList<Ticket> tickets) { super(context, 0, tickets); }

@Override
public View getView(int position, View convertView, ViewGroup parent) {

        // Get the data item for this position
            Ticket ticket = getItem(position);
            //String username = CustomerState.getUsername();
            Movie movie = AccessMovies.getMovie(ticket.getEvent().getMovieID());
        // Check if an existing view is being reused, otherwise inflate the view
            if (convertView == null) {
                convertView = LayoutInflater.from(getContext()).inflate(R.layout.profile_ticket_item, parent, false);
            }
        // Lookup view for data population
            ImageView moviePoster = (ImageView) convertView.findViewById(R.id.profileItem_movie_poster);
            TextView movieTitle = (TextView) convertView.findViewById(R.id.profileItem_movie_title);
            TextView movieDes = (TextView) convertView.findViewById(R.id.profileItem_movie_des);

            RatingBar ratingBar = (RatingBar) convertView.findViewById(R.id.profileItem_rating_bar);

            // Populate the data into the template view using the data object
            moviePoster.setImageResource(getContext().getResources().getIdentifier(movie.getPoster(), "drawable", getContext().getPackageName()));
            movieTitle.setText(movie.getTitle());
            movieDes.setText(movie.getDirector()+"\n"+movie.getGenre()+"\n"+movie.getDuration()+" min");
            //movieBtn.setTag(position);
            ratingBar.setRating(movie.getRating());

        // Return the completed view to render on screen
            return convertView;
        }



}

