package comp3350.MovieGoers.presentation;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;
import android.graphics.Color;
import android.view.Gravity;

import java.util.ArrayList;

import comp3350.MovieGoers.R;
import comp3350.MovieGoers.business.AccessConcessions;
import comp3350.MovieGoers.business.AccessEvents;
import comp3350.MovieGoers.objects.Event;
import comp3350.MovieGoers.objects.Concessions;

public class ConcessionActivity extends AppCompatActivity {

    private ArrayList<Concessions> list = new ArrayList<Concessions>();
    private ArrayList<Integer> chosenIDs = new ArrayList<Integer>();
    private float subtotal = 0;
    private int eventID;
    private Event e;
    //boolean isGuest;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_concession);

        eventID = Integer.parseInt(getIntent().getStringExtra("eventID"));
        e = AccessEvents.getEvent(eventID);
        int theatreID = e.getTheatre().getID();

        //Intent intent = getIntent();
        //isGuest = intent.getBooleanExtra("isGuest", isGuest);

        AccessConcessions.getConcessions(list, theatreID);
        System.out.println(list);

        // Create the adapter to convert the array to views
        ConcessionAdapter adapter = new ConcessionAdapter(this, list);
        // Attach the adapter to a ListView
        ListView listView = findViewById(R.id.listView);
        listView.setAdapter(adapter);
        TextView textView = findViewById(R.id.textView);
        textView.setTextColor(Color.BLACK);
        printSubtotal(textView);
    }

    private void printSubtotal(TextView v) {
        if(subtotal < 0.01)
            subtotal = 0;
        v.setText(String.format("Subtotal: $%.2f", subtotal));
    }

    public void onToggle(View view) {
        ToggleButton btn = view.findViewById(R.id.toggleButton);
        final TextView text = findViewById(R.id.textView);
        int coID = list.get((Integer) view.getTag()).getID();
        final Concessions c = AccessConcessions.getConcession(coID);
        if (btn.isChecked()) {
            btn.setBackgroundResource(android.R.drawable.alert_dark_frame);
            btn.setTextColor(Color.WHITE);
            subtotal += c.getItemPrice();
            chosenIDs.add(Integer.valueOf(coID));
            Toast t = Toast.makeText(this, c.getItem() + " added for $" + c.getItemPrice(), Toast.LENGTH_SHORT);
            t.setGravity(Gravity.TOP|Gravity.RIGHT, 0, 40);
            t.show();
        } else {
            btn.setBackgroundResource(android.R.drawable.alert_light_frame);
            btn.setTextColor(Color.BLACK);
            subtotal -= c.getItemPrice();
            chosenIDs.remove(Integer.valueOf(coID));
            Toast t = Toast.makeText(this, c.getItem() + " removed", Toast.LENGTH_SHORT);
            t.setGravity(Gravity.TOP|Gravity.RIGHT, 0, 40);
            t.show();
        }
        printSubtotal(text);
    }

    public void concessionClick(View view) {
        Intent next = new Intent(this, OrderActivity.class);
        next.putIntegerArrayListExtra("coIDs", chosenIDs);
        next.putExtra("eventID", eventID);
        next.putExtra("subtotal", subtotal);
        //next.putExtra("isGuest", isGuest);
        this.startActivity(next);
    }
}