package comp3350.MovieGoers.presentation;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import java.util.ArrayList;

import comp3350.MovieGoers.R;
import comp3350.MovieGoers.objects.Customer;
import comp3350.MovieGoers.persistence.Database;
import comp3350.MovieGoers.business.AccessCustomers;

public class RegisterActivity extends AppCompatActivity{
    Intent next;


    EditText usernameText;
    EditText fNameText;
    EditText lNameText;
    EditText passwordText;
    EditText cPasswordText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        setTitle("Register");

        TextView titleView = findViewById(R.id.register_titleView);
        TextView usernameView = findViewById(R.id.register_usernameView);
        TextView fNameView = findViewById(R.id.register_fNameView);
        TextView lNameView = findViewById(R.id.register_lNameView);
        TextView passwordView = findViewById(R.id.register_passwordView);
        TextView cPasswordView = findViewById(R.id.register_cPasswordView);

        titleView.setText("REGISTER");
        usernameView.setText("Username");
        fNameView.setText("First Name");
        lNameView.setText("Last Name");
        passwordView.setText("Password");
        cPasswordView.setText("Confirm Password");

        usernameText = findViewById(R.id.register_usernameBox);
        fNameText = findViewById(R.id.register_fNameBox);
        lNameText = findViewById(R.id.register_lNameBox);
        passwordText = findViewById(R.id.register_passwordBox);
        cPasswordText = findViewById(R.id.register_cPasswordBox);

        usernameText.setHint("Username");
        fNameText.setHint("First Name");
        lNameText.setHint("Last Name");
        passwordText.setHint("Password");
        cPasswordText.setHint("Confirm Password");

    }


    public void backButton(View view) throws Database.DuplicateException {
        next = new Intent(this, LoginActivity.class);
        this.startActivity(next);
    }

    public void submitButton(View view) throws Database.DuplicateException {
        ArrayList editTextList = InputHelper.collectInput(usernameText, fNameText, lNameText, passwordText, cPasswordText);

        if(InputHelper.checkAllBoxes(editTextList)) {

            String username = usernameText.getText().toString();
            if(AccessCustomers.searchCustomer(username)) {
                InputHelper.toastMaker(this, "Username already taken");
            }
            else
            {
                String password = passwordText.getText().toString();
                String cPassword = cPasswordText.getText().toString();
                if(password.equals(cPassword)) {
                    Customer customer = new Customer(username, password, fNameText.getText().toString(), lNameText.getText().toString(), "", "");
                    AccessCustomers.addCustomer(customer);

                    InputHelper.toastMaker(this,"Successfully registered");

                    next = new Intent(this, LoginActivity.class);
                    this.startActivity(next);
                } else {
                    cPasswordText.setError("Passwords do not match.");
                }
            }

        } else {
            InputHelper.toastMaker(this, "Please fill in missing fields");
        }
    }

}
