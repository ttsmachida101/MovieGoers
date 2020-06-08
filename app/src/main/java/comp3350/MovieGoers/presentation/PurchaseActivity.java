package comp3350.MovieGoers.presentation;

import java.util.ArrayList;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.ImageView;


import comp3350.MovieGoers.R;
import comp3350.MovieGoers.business.AccessEvents;
import comp3350.MovieGoers.objects.Event;

public class PurchaseActivity extends AppCompatActivity {
    private Event event;
    private int eventID;
    private float subtotal = 0;

    //boolean isGuest;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_purchase);

        Intent intent = getIntent();
        eventID = intent.getIntExtra("eventID", 0);
        event = AccessEvents.getEvent(eventID);
        AccessEvents.bookEvent(eventID);

        //isGuest = intent.getBooleanExtra("isGuest", true);

        subtotal = intent.getFloatExtra("subtotal", 0);

        setTitle("Booking Summary");
        ImageView theatreImage = (ImageView)findViewById(R.id.imageView3);
        theatreImage.setImageResource(getResources().getIdentifier(event.getTheatre().getLogo(), "drawable", getPackageName()));
        ImageView movieImage = (ImageView)findViewById(R.id.imageView5);
        movieImage.setImageResource(getResources().getIdentifier(event.getMovie().getPoster(), "drawable", getPackageName()));
        TextView textView = (TextView)findViewById(R.id.textView4);
        textView.setText("Booking complete. Theatre reservation details:\n\n"+
                event.getTheatre().getName() +
                ", " + event.getTheatre().getLocation()+"\n\n"
                + event.getMovie().getTitle() + " by " + event.getMovie().getDirector()+"\n\n" +
                "Screen # " + event.getScreen() +
                ", Seat "+event.getSeatsTaken() + "\n\n" +
                "Start Time: " + event.displayTime() +
                ", Duration: " + event.getMovie().getDuration() + " mins.");
    }

    public void clickMe3(View view){
        Intent mainActivity = new Intent(this, MainActivity.class);
        //mainActivity.putExtra("isGuest", isGuest);
        mainActivity.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        this.startActivity(mainActivity);
    }
}
