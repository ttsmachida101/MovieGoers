package comp3350.MovieGoers.presentation;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import comp3350.MovieGoers.R;
import comp3350.MovieGoers.objects.Movie;
import comp3350.MovieGoers.objects.Theatre;

class TheaterAdapter extends ArrayAdapter<Theatre> {


    public TheaterAdapter(Context context, ArrayList<Theatre> theatres) {
        super(context, 0, theatres);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Get the data item for this position
        Theatre theatre = getItem(position);
        // Check if an existing view is being reused, otherwise inflate the view
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.theatre_item, parent, false);
        }
        // Lookup view for data population
        ImageView logo = (ImageView) convertView.findViewById(R.id.theaterLogo);

        TextView address = (TextView) convertView.findViewById(R.id.theaterAddress);
        Button btn = (Button)  convertView.findViewById(R.id.theatreBtn);
        // Populate the data into the template view using the data object
        logo.setImageResource(getContext().getResources().getIdentifier(theatre.getLogo(), "drawable", getContext().getPackageName()));
        System.out.println(theatre.getLocation());
        address.setText(theatre.getLocation());

        btn.setTag(position);

        // Return the completed view to render on screen
        return convertView;
    }



}