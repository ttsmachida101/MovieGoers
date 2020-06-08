package comp3350.MovieGoers.tests.businessTests;

import junit.framework.TestCase;

import comp3350.MovieGoers.business.CustomerState;

public class CustomerStateTest extends TestCase
{
    public CustomerStateTest(String arg0) {
        super(arg0);
    }

    public void testCustomerState() {

        System.out.println("\nStarting testCustomerState");
        assertTrue(!CustomerState.getIsGuest());
        new CustomerState();
        assertTrue(CustomerState.getIsGuest());
        CustomerState.setIsGuest(false);
        assertTrue(!CustomerState.getIsGuest());
        CustomerState.setIsGuest(true);
        assertTrue(CustomerState.getIsGuest());

        CustomerState.setUsername(null);
        assertTrue(null == CustomerState.getUsername());
        CustomerState.setUsername("");
        assertTrue("".equals(CustomerState.getUsername()));
        CustomerState.setUsername("hello");
        assertTrue("hello".equals(CustomerState.getUsername()));
        CustomerState.setUsername("123");
        assertTrue("123".equals(CustomerState.getUsername()));


        System.out.println("Finished testCustomerState");

    }
    
}