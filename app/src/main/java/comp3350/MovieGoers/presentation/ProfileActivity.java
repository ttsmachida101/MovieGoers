package comp3350.MovieGoers.presentation;


import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

import comp3350.MovieGoers.R;
import comp3350.MovieGoers.business.AccessTickets;
import comp3350.MovieGoers.business.CustomerState;
import comp3350.MovieGoers.objects.Ticket;

public class ProfileActivity extends AppCompatActivity{

    private ArrayList<Ticket> ticketList;
    private ProfileTicketAdapter adapter;
    Intent next;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        setTitle("Profile");

        String username = CustomerState.getUsername();

        ticketList = new ArrayList<>();
        AccessTickets.getTicketsByCustomer(ticketList, username);
        System.out.println(ticketList);

        // Create the adapter to convert the array to views
        adapter = new ProfileTicketAdapter(this, ticketList);
        // Attach the adapter to a ListView
        ListView listView = (ListView) findViewById(R.id.profile_purchaseHistoryList);
        listView.setAdapter(adapter);

        TextView nameView = findViewById(R.id.profile_username);
        nameView.setText(username);

        ImageView profileImage = findViewById(R.id.profile_image);
        if(CustomerState.profilePic==0){
            profileImage.setImageResource(R.drawable.movie_icon1);
        }else if(CustomerState.profilePic==1){
            profileImage.setImageResource(R.drawable.movie_icon2);
        }else if(CustomerState.profilePic==2){
            profileImage.setImageResource(R.drawable.movie_icon3);
        }else if(CustomerState.profilePic==3){
            profileImage.setImageResource(R.drawable.movie_icon4);
        }


    }

    public void backButton(View view) {
        next = new Intent(this, PickScreenActivity.class);
        this.startActivity(next);
    }

    public void editImageButton(View view) {
        next = new Intent(this, EditProfileImageActivity.class);
        this.startActivity(next);
    }
}
