package comp3350.MovieGoers.presentation;


import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import comp3350.MovieGoers.R;
import comp3350.MovieGoers.business.CustomerState;

public class PickScreenActivity extends AppCompatActivity{

    Intent next;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pick_screen);



    }

    public void moviesButton(View view) {
        next = new Intent(this, MainActivity.class);
        //next.putExtra("isGuest", isGuest);
        this.startActivity(next);

    }

    public void profileButton(View view) {
        if(!CustomerState.getIsGuest()) {
            next = new Intent(this, ProfileActivity.class);

            this.startActivity(next);
        } else {
            InputHelper.toastMaker(this, "Not available to guest users");
        }
    }
}
