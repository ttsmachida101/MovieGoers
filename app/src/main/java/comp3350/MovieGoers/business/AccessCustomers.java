package comp3350.MovieGoers.business;




import comp3350.MovieGoers.objects.Customer;
import comp3350.MovieGoers.persistence.DataAccessor;
import comp3350.MovieGoers.persistence.Database;


public class AccessCustomers {

    private static DataAccessor session = DataManager.getSession();

    public static boolean addCustomer(Customer customer) throws Database.DuplicateException {
        return session.addCustomer(customer);
    }

    public static boolean deleteCustomer(String username) {
        return session.deleteCustomer(username);
    }

    public static boolean searchCustomer(String username) {
        return session.searchCustomers(username);
    }

    public static boolean setCvv(String username, String val) {
        return session.setCvv(username, val);
    }

    public static boolean setCardNumber(String username, String val) {
        return session.setCardNumber(username, val);
    }


    public static Customer getCustomer(String username) {
        return session.getCustomer(username);
    }
}


