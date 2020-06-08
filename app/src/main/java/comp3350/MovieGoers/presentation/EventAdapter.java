package comp3350.MovieGoers.presentation;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;

import android.widget.TextView;

import java.util.ArrayList;

import comp3350.MovieGoers.R;
import comp3350.MovieGoers.objects.Event;


public class EventAdapter extends ArrayAdapter<Event> {


    public EventAdapter(Context context, ArrayList<Event> events) {
        super(context, 0, events);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Get the data item for this position
        Event event = getItem(position);
        // Check if an existing view is being reused, otherwise inflate the view
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.event_item, parent, false);
        }
        // Lookup view for data population

        TextView screenNum = (TextView) convertView.findViewById(R.id.screenNum);
        Button btn = (Button)  convertView.findViewById(R.id.screenBtn);
        // Populate the data into the template view using the data object

        screenNum.setText("Screen # "+event.getScreen()+
                          ":\nSeat Availability: "+event.getSeatsRemaining()+"/"+event.getSeatCapacity()+
                          "\nTicket Price: "+event.getPrice());
        btn.setText(event.displayTime());

        btn.setTag(position);

        // Return the completed view to render on screen
        return convertView;
    }
}