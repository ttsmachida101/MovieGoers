package comp3350.MovieGoers.tests.businessTests;

import junit.framework.TestCase;
import comp3350.MovieGoers.business.AccessCustomers;
import comp3350.MovieGoers.business.DataManager;
import comp3350.MovieGoers.objects.Customer;
import comp3350.MovieGoers.persistence.Database;
import comp3350.MovieGoers.tests.persistenceTests.DatabaseStub;


public class AccessCustomerTest extends TestCase {

    public AccessCustomerTest(String arg0) {
        super(arg0);
    }
    protected void setUp() throws Exception {
        DataManager.setSession(new DatabaseStub());
    }

    public void testAddCustomer() throws Database.DuplicateException {
        System.out.println("\nStarting getTheatre test in testAccessTheatre");

        Customer customer = new Customer("userName","","fName","lName","00","00");
        assertTrue(AccessCustomers.addCustomer(customer));
        assertTrue(AccessCustomers.searchCustomer("userName"));
        assertTrue(!AccessCustomers.searchCustomer("hi"));
        assertTrue(AccessCustomers.deleteCustomer("userName"));

        customer = new Customer("","","fName","lName","00","00");
        assertTrue(AccessCustomers.addCustomer(customer));
        assertTrue(AccessCustomers.searchCustomer(""));
        assertTrue(!AccessCustomers.searchCustomer("hi"));
        assertTrue(AccessCustomers.deleteCustomer(""));

        customer = new Customer("userName","","fName","lName","00","00");
        assertTrue(AccessCustomers.addCustomer(customer));

        try {
            AccessCustomers.addCustomer(customer);
            fail("Expected exception");
        } catch (Database.DuplicateException expect) {
            //Pass!
        }

        assertTrue(AccessCustomers.deleteCustomer("userName"));


        System.out.println("Finished getTheatre test in testAccessTheatre");
    }

    protected void tearDown() throws Exception {
        DataManager.closeSession();
    }



}

  