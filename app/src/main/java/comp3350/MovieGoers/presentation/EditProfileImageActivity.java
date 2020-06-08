package comp3350.MovieGoers.presentation;


import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import comp3350.MovieGoers.R;
import comp3350.MovieGoers.business.CustomerState;

public class EditProfileImageActivity extends AppCompatActivity {

    ImageView profileImage;
    Intent next;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile_image);
        setTitle("Edit Image");


    }





    public void backButton(View view) {
        next = new Intent(this, ProfileActivity.class);
        this.startActivity(next);
    }
    Intent imageSet;
    public void editImageButton1(View view) {
        next = new Intent(this, ProfileActivity.class);
        next.putExtra("movie_icon", R.drawable.movie_icon1);
        CustomerState.profilePic = 0;
//        profileImage = findViewById(comp3350.MovieGoers.R.id.profile_image);
//        profileImage.setImageResource(comp3350.MovieGoers.R.drawable.movie_icon1);
        this.startActivity(next);
    }
    public void editImageButton2(View view) {
        Intent next = new Intent(this, ProfileActivity.class);
        next.putExtra("movie_icon", R.drawable.movie_icon2);
        CustomerState.profilePic = 1;
//        profileImage = findViewById(comp3350.MovieGoers.R.id.profile_image);
//        profileImage.setImageResource(comp3350.MovieGoers.R.drawable.movie_icon2);
        this.startActivity(next);
    }
    public void editImageButton3(View view) {
        next = new Intent(this, ProfileActivity.class);
        next.putExtra("movie_icon", R.drawable.movie_icon3);
        CustomerState.profilePic = 2;
//        profileImage = findViewById(comp3350.MovieGoers.R.id.profile_image);
//        profileImage.setImageResource(comp3350.MovieGoers.R.drawable.movie_icon3);
        this.startActivity(next);
    }
    public void editImageButton4(View view) {
        next = new Intent(this, ProfileActivity.class);
        next.putExtra("movie_icon", R.drawable.movie_icon4);
        CustomerState.profilePic = 3;
//        profileImage = findViewById(comp3350.MovieGoers.R.id.profile_image);
//        profileImage.setImageResource(comp3350.MovieGoers.R.drawable.movie_icon4);
        this.startActivity(next);
    }
}


