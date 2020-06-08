package comp3350.MovieGoers;

import android.test.ActivityInstrumentationTestCase2;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import com.robotium.solo.Solo;
import comp3350.MovieGoers.presentation.ConcessionActivity;
import comp3350.MovieGoers.presentation.EditProfileImageActivity;
import comp3350.MovieGoers.presentation.EventActivity;
import comp3350.MovieGoers.presentation.LoginActivity;
import comp3350.MovieGoers.presentation.MainActivity;
import comp3350.MovieGoers.presentation.OrderActivity;
import comp3350.MovieGoers.presentation.PickScreenActivity;
import comp3350.MovieGoers.presentation.ProfileActivity;
import comp3350.MovieGoers.presentation.PurchaseActivity;
import comp3350.MovieGoers.presentation.RegisterActivity;
import comp3350.MovieGoers.presentation.SplashIntro;
import comp3350.MovieGoers.presentation.TheatreActivity;

public class RunAcceptanceTest extends ActivityInstrumentationTestCase2<SplashIntro> {
    private Solo solo;

    public RunAcceptanceTest() {
        super(SplashIntro.class);
    }

    public void setUp() throws Exception {
        solo = new Solo(getInstrumentation(), getActivity());
    }

    @Override
    public void tearDown() throws Exception {
        solo.finishOpenedActivities();
    }



    public void testARegisterLogin() throws Exception {
    //Register as New Customer and Login to check profile
        solo.waitForActivity(LoginActivity.class);
        solo.assertCurrentActivity("Expected Login Activity", LoginActivity.class);
        solo.clickOnView(solo.getView(R.id.login_registerButton));
        solo.waitForActivity(RegisterActivity.class);
        solo.assertCurrentActivity("Expected Register Activity", RegisterActivity.class);
        solo.enterText((EditText)solo.getView(R.id.register_usernameBox),"user00");
        solo.enterText((EditText)solo.getView(R.id.register_fNameBox),"Mike");
        solo.enterText((EditText)solo.getView(R.id.register_lNameBox),"Smith");
        solo.enterText((EditText)solo.getView(R.id.register_passwordBox),"1234");
        solo.enterText((EditText)solo.getView(R.id.register_cPasswordBox),"1234");
        solo.clickOnView(solo.getView(R.id.register_submitButton));
        solo.waitForActivity(LoginActivity.class);
        solo.assertCurrentActivity("Expected Login Activity", LoginActivity.class);
        solo.enterText((EditText)solo.getView(R.id.login_usernameBox),"user00");
        solo.enterText((EditText)solo.getView(R.id.login_passwordBox),"1234");
        solo.clickOnView(solo.getView(R.id.login_loginButton));
        solo.waitForActivity(PickScreenActivity.class);
        solo.assertCurrentActivity("Expected PickScreen Activity", PickScreenActivity.class);
        solo.clickOnView(solo.getView(R.id.split_profileButton));
    }

    public void testBBuyTicket() throws Exception {
    //Buy tickets
        solo.waitForActivity(LoginActivity.class);
        solo.assertCurrentActivity("Expected Login Activity", LoginActivity.class);
        solo.enterText((EditText)solo.getView(R.id.login_usernameBox),"user00");
        solo.enterText((EditText)solo.getView(R.id.login_passwordBox),"1234");
        solo.clickOnView(solo.getView(R.id.login_loginButton));
        solo.waitForActivity(PickScreenActivity.class);
        solo.assertCurrentActivity("Expected PickScreen Activity", PickScreenActivity.class);
        solo.clickOnView(solo.getView(R.id.split_moviesButton));
        solo.waitForActivity(MainActivity.class);
        solo.assertCurrentActivity("Expected Main Activity", MainActivity.class);
        solo.searchText("Star");
        ListView ListView=(ListView)solo.getView(R.id.movieList);
        View view=ListView.getChildAt(0);
        Button button=(Button)view.findViewById(R.id.movie_bookbtn);
        solo.clickOnView(button);
        solo.waitForActivity(TheatreActivity.class);
        solo.assertCurrentActivity("Expected Theatre Activity", TheatreActivity.class);
        solo.searchText("Polo Park");
        ListView=(ListView)solo.getView(R.id.theatreList);
        view=ListView.getChildAt(0);
        button=(Button)view.findViewById(R.id.theatreBtn);
        solo.clickOnView(button);
        solo.waitForActivity(EventActivity.class);
        solo.assertCurrentActivity("Expected Event Activity", EventActivity.class);
        solo.searchText("Screen");
        ListView=(ListView)solo.getView(R.id.eventList);
        view=ListView.getChildAt(1);
        button=(Button)view.findViewById(R.id.screenBtn);
        solo.clickOnView(button);
        solo.waitForActivity(ConcessionActivity.class);
        solo.assertCurrentActivity("Expected Concessions Activity", ConcessionActivity.class);
        ListView=(ListView)solo.getView(R.id.listView);
        view=ListView.getChildAt(1);
        button=(Button)view.findViewById(R.id.toggleButton);
        solo.clickOnView(button);
        view=ListView.getChildAt(3);
        button=(Button)view.findViewById(R.id.toggleButton);
        solo.clickOnView(button);
        solo.searchText("Subtotal");
        solo.clickOnView(solo.getView(R.id.button3));
        solo.waitForActivity(OrderActivity.class);
        solo.assertCurrentActivity("Expected Order Activity", OrderActivity.class);
        solo.enterText((EditText)solo.getView(R.id.ccNumBox),"1234123412341234");
        solo.enterText((EditText)solo.getView(R.id.cvvBox),"123");
        solo.searchText("Subtotal");
        solo.clickOnView(solo.getView(R.id.submitButton));
        solo.waitForActivity(PurchaseActivity.class);
        solo.assertCurrentActivity("Expected Purchase Activity", PurchaseActivity.class);
        solo.searchText("Booking");
        solo.clickOnView(solo.getView(R.id.button2));
        solo.waitForActivity(MainActivity.class);
        solo.assertCurrentActivity("Expected Purchase Activity", MainActivity.class);
    }

    public void testCProfileCheckAndEditPicture(){
    //login and edit profile picture, can also check booking history
        solo.waitForActivity(LoginActivity.class);
        solo.assertCurrentActivity("Expected Login Activity", LoginActivity.class);
        solo.enterText((EditText)solo.getView(R.id.login_usernameBox),"user00");
        solo.enterText((EditText)solo.getView(R.id.login_passwordBox),"1234");
        solo.clickOnView(solo.getView(R.id.login_loginButton));
        solo.waitForActivity(PickScreenActivity.class);
        solo.assertCurrentActivity("Expected PickScreen Activity", PickScreenActivity.class);
        solo.clickOnView(solo.getView(R.id.split_profileButton));
        solo.waitForActivity(ProfileActivity.class);
        solo.assertCurrentActivity("Expected Profile Activity", ProfileActivity.class);
        solo.clickOnView(solo.getView(R.id.profile_edit_image_button));
        solo.waitForActivity(EditProfileImageActivity.class);
        solo.assertCurrentActivity("Expected EditProfile Activity", EditProfileImageActivity.class);
        solo.searchText("back");
        solo.clickOnView(solo.getView(R.id.editProfileImage_button3));
        solo.waitForActivity(ProfileActivity.class);
        solo.assertCurrentActivity("Expected Profile Activity", ProfileActivity.class);
        solo.clickOnView(solo.getView(R.id.profile_edit_image_button));
        solo.waitForActivity(EditProfileImageActivity.class);
        solo.assertCurrentActivity("Expected EditProfile Activity", EditProfileImageActivity.class);
        solo.clickOnView(solo.getView(R.id.editProfileImage_button2));
        solo.waitForActivity(ProfileActivity.class);
        solo.assertCurrentActivity("Expected Profile Activity", ProfileActivity.class);
        solo.clickOnView(solo.getView(R.id.profile_backButton));
        solo.waitForActivity(PickScreenActivity.class);
        solo.assertCurrentActivity("Expected PickScreen Activity", PickScreenActivity.class);
        solo.clickOnView(solo.getView(R.id.split_profileButton));
        solo.waitForActivity(ProfileActivity.class);
        solo.assertCurrentActivity("Expected Profile Activity", ProfileActivity.class);
        solo.searchText("back");


    }

}