package comp3350.MovieGoers.presentation;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import java.util.ArrayList;

import comp3350.MovieGoers.R;
import comp3350.MovieGoers.business.AccessCustomers;
import comp3350.MovieGoers.objects.Customer;
import comp3350.MovieGoers.business.CustomerState;


public class LoginActivity extends AppCompatActivity{
    Intent next;

    EditText usernameText;
    EditText passwordText;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_screen);
        setTitle("Login");

        TextView usernameView = findViewById(R.id.login_usernameView);
        TextView passwordView = findViewById(R.id.login_passwordView);
        TextView titleView = findViewById(R.id.login_titleView);

        usernameView.setText("Username/Phone Number");
        passwordView.setText("Password");
        titleView.setText("LOGIN");

        usernameText = findViewById(R.id.login_usernameBox);
        passwordText = findViewById(R.id.login_passwordBox);

        usernameText.setHint("Username");
        passwordText.setHint("Password");
    }



    public void loginButton(View view) {
        ArrayList editTextList = InputHelper.collectInput(usernameText, passwordText);

        if(InputHelper.checkAllBoxes(editTextList)) {

            String username = usernameText.getText().toString();
            if(AccessCustomers.searchCustomer(username)) {

                Customer customer = AccessCustomers.getCustomer(username);
                String inputPassword = passwordText.getText().toString();
                try{

                    if((inputPassword.equals(customer.getPassword()))) {
                        next = new Intent(this, PickScreenActivity.class);
                        CustomerState.setUsername(customer.getUsername());
                        CustomerState.setIsGuest(false);

                        this.startActivity(next);
                    }

                } catch (NullPointerException e) {
                    passwordText.setError("Invalid password");
                }

            } else {
                usernameText.setError("Invalid Username");
                passwordText.setError("Invalid Password");
            }


        }
    }

    public void guestButton(View view) {
        next = new Intent(this, PickScreenActivity.class);

        CustomerState.setIsGuest(true);
        this.startActivity(next);

    }

    public void registerButton(View view)  {
        next = new Intent(this, RegisterActivity.class);
        this.startActivity(next);

    }


}
