package comp3350.MovieGoers.objects;


public class Customer{

    private String username;
    private String password;
    private String fname;
    private String lname;
    private String cardNumber;
    private String cvv;

    public Customer(String username, String password, String fname, String lname, String cardNumber, String cvv) {
        this.username = username;
        this.password = password;
        this.fname = fname;
        this.lname = lname;
        this.cardNumber = cardNumber;
        this.cvv = cvv;
    }

    public String getUsername() { return username; }
    public String getPassword() { return password; }
    public String getfName() { return fname; }
    public String getlName() { return lname; }
    public String getCardNumber() { return cardNumber; }
    public String getCvv() { return cvv; }

    public void setCvv(String cvv) { this.cvv = cvv;}
    public void setCardNumber(String num) { cardNumber = num; }


}

