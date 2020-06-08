package comp3350.MovieGoers.presentation;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;
import android.widget.ToggleButton;
import android.widget.CompoundButton;

import java.util.ArrayList;
import android.widget.RemoteViews;

import comp3350.MovieGoers.R;
import comp3350.MovieGoers.objects.Concessions;
import comp3350.MovieGoers.objects.Event;


public class ConcessionAdapter extends ArrayAdapter<Concessions> {

    public ConcessionAdapter(Context context, ArrayList<Concessions> concessions) {
        super(context, 0, concessions);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Get the data item for this position
        Concessions c = getItem(position);
        // Check if an existing view is being reused, otherwise inflate the view
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.concession_item, parent, false);
        }
        // Lookup view for data population
//        final RemoteViews remoteViews = new RemoteViews(super.getContext().getPackageName(), R.layout.activity_concession);
        ToggleButton btn = convertView.findViewById(R.id.toggleButton);
        btn.setBackgroundResource(android.R.drawable.alert_light_frame);
        btn.setTextColor(Color.BLACK);
//        btn.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
//            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
//                if(isChecked) {
//                    remoteViews.setTextViewText(R.id.textView, "TRASH");
//                } else {
//                    remoteViews.setTextViewText(R.id.textView, "Set button text here");
//                }
//            }
//        });
        // Populate the data into the template view using the data object
        String text = c.getItem()+": "+c.getDesription()+"\n$"+c.getItemPrice();
        btn.setText(text);
        btn.setTextOn(text);
        btn.setTextOff(text);
        btn.setTag(position);

        // Return the completed view to render on screen
        return convertView;
    }
}
