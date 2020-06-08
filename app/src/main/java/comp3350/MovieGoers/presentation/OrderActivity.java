package comp3350.MovieGoers.presentation;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import java.util.ArrayList;

import comp3350.MovieGoers.R;
import comp3350.MovieGoers.business.AccessTickets;
import comp3350.MovieGoers.objects.Customer;
import comp3350.MovieGoers.business.CustomerState;
import comp3350.MovieGoers.objects.Event;
import comp3350.MovieGoers.business.AccessCustomers;
import comp3350.MovieGoers.business.AccessConcessions;
import comp3350.MovieGoers.business.AccessEvents;
import comp3350.MovieGoers.persistence.Database;


public class OrderActivity extends AppCompatActivity {
    private Event event;
    private int eventID;
    private float subtotal;
    private float total;

    private final float taxRate = 0.13f;
    private final int ccNumLength = 16;
    private final int cvvLength = 3;


    EditText fNameText;
    EditText lNameText;
    EditText ccNumText;
    EditText cvvText;

    String ccNumStr ;
    String cvvStr;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order);
        setTitle("Please enter your payment details");

        Intent intent = getIntent();
        eventID = intent.getIntExtra("eventID", 0);
        event = AccessEvents.getEvent(eventID);

        ArrayList<Integer> chosenIDs = intent.getIntegerArrayListExtra("coIDs");

        subtotal = intent.getFloatExtra("subtotal", 0) + event.getPrice();
        total = subtotal * (taxRate + 1);



        TextView fNameView = findViewById(R.id.fNameView);
        TextView lNameView = findViewById(R.id.lNameView);
        TextView ccView = findViewById(R.id.ccView);
        TextView cvvView = findViewById(R.id.cvvView);
        TextView title = findViewById(R.id.title);
        TextView subtotalView = findViewById(R.id.subtotalView);
        TextView totalView = findViewById(R.id.totalView);
        TextView summaryView = findViewById(R.id.summaryView);
        TextView summaryView2 = findViewById(R.id.summaryView2);


        fNameView.setText("First Name");
        lNameView.setText("Last Name");
        ccView.setText("Credit Card Number");
        cvvView.setText("CVV");
        title.setText("Payment Information");

        fNameText = findViewById(R.id.fNameBox);
        lNameText = findViewById(R.id.lNameBox);
        ccNumText = findViewById(R.id.ccNumBox);
        cvvText = findViewById(R.id.cvvBox);
        if(!CustomerState.getIsGuest()){
            Customer customer = AccessCustomers.getCustomer(CustomerState.getUsername());
            fNameText.setText(customer.getfName());
            lNameText.setText(customer.getlName());
            ccNumText.setText(customer.getCardNumber());
            cvvText.setText(customer.getCvv());
        }else{
            fNameText.setHint("First Name");
            lNameText.setHint("Last Name");
            ccNumText.setHint("Credit Card Number");
            cvvText.setHint("CVV");
        }



        summaryView.setText("Theatre: \n" +
                "Showing Time: \n" +
                "Showing Duration: \n\n" +
                "Ticket Price: \n" +
                "Concession Price: \n" +
                "Tax: \n");

        summaryView2.setText(
                String.format("%s\n%s\n%d mins.\n\n$%.2f\n$%.2f\n$%.2f",
                        event.getTheatre().getName(),
                        event.displayTime(),
                        event.getMovie().getDuration(),
                        event.getPrice(),
                        intent.getFloatExtra("subtotal", 0),
                        subtotal * taxRate));

        subtotalView.setText(String.format("Subtotal: $%.2f", subtotal));
        totalView.setText(String.format("Total: $%.2f", total));
    }

    protected String concessionListToString(ArrayList<Integer> idList) {
        String result = "";

        for(int id: idList) {
            result += AccessConcessions.getConcession(id).getItem() + "\n";
        }

        return result;
    }

    protected boolean checkCCNumber(EditText textBox, String str) {
        boolean result = true;
        if(!(str.matches("[0-9]+") && str.length() == ccNumLength)) {
            textBox.setError("Invalid Credit Card Number.");
            result = false;
        }

        return result;
    }

    protected boolean checkCVV(EditText textBox, String str) {
        boolean result = true;
        if(!(str.matches("[0-9]+") && str.length() == cvvLength)) {
            textBox.setError("Invalid CVV.");
            result = false;
        }

        return result;

    }

    public void submitButton(View view) throws Database.DuplicateException {
        ArrayList<EditText> customerInfoList = InputHelper.collectInput(fNameText, lNameText, ccNumText, cvvText);
        String username = CustomerState.getUsername();

        boolean valid = false;

        if (InputHelper.checkAllBoxes(customerInfoList)) {

            ccNumStr = ccNumText.getText().toString();
            cvvStr = cvvText.getText().toString();

            if (CustomerState.getIsGuest()) {

                if (checkCCNumber(ccNumText, ccNumStr) & checkCVV(cvvText, cvvStr)) {
                    valid = true;
                }
                else
                {
                    InputHelper.toastMaker(this, "Invalid payment info");
                }
            } else { //registered user

                Customer customer = AccessCustomers.getCustomer(username);
                System.out.println(customer.getCardNumber());
                System.out.println(customer.getCvv());

                if (!customer.getCardNumber().isEmpty() && !customer.getCvv().isEmpty()) {
                        if(customer.getCardNumber().equals(ccNumStr) && customer.getCvv().equals(cvvStr))
                        {
                            valid = true;
                        }
                        else
                        {
                            System.out.println(ccNumStr);
                            System.out.println(cvvStr);
                            if (checkCCNumber(ccNumText, ccNumStr) & checkCVV(cvvText, cvvStr)) {
                                AccessCustomers.setCvv(username, cvvStr);
                                AccessCustomers.setCardNumber(username, ccNumStr);
                                InputHelper.toastMaker(this, "updating info");
                                valid = true;
                            } else {
                                InputHelper.toastMaker(this, "Invalid payment info");
                            }
                        }

                } else {
                    System.out.println(ccNumStr);
                    System.out.println(cvvStr);
                    if (checkCCNumber(ccNumText, ccNumStr) & checkCVV(cvvText, cvvStr)) {
                        AccessCustomers.setCvv(username, cvvStr);
                        AccessCustomers.setCardNumber(username, ccNumStr);
                        InputHelper.toastMaker(this, "saving info");
                        valid = true;
                    } else {
                        InputHelper.toastMaker(this, "Invalid payment info");
                    }

                }
            }
        }
        else
        {
            InputHelper.toastMaker(this, "Fill in all boxes");
        }

        if (valid) {
            Intent next = new Intent(this, PurchaseActivity.class);
            next.putExtra("eventID", eventID);
            next.putExtra("subtotal", total);
            if(!CustomerState.getIsGuest()) {
                //Format layout = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                //String date = "";
                //layout.format(date);
                String date = "today";
                //TODO have an actual string date
                AccessTickets.createTicket(username, eventID, date);
            }
            //next.putExtra("isGuest", isGuest);
            this.startActivity(next);
        }
    }


}
