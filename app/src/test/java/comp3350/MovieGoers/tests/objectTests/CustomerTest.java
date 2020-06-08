package comp3350.MovieGoers.tests.objectTests;

import junit.framework.TestCase;

import comp3350.MovieGoers.objects.Customer;


public class CustomerTest extends TestCase {


    public CustomerTest(String arg0) {
        super(arg0);

    }

    public void testCustomer()
    {
        System.out.println("\nStarting testCustomerClass");
        Customer customer = new Customer("username","password","fname",null,"cardnum000","000");
        assertNotNull(customer);
        assertTrue("username".equals(customer.getUsername()));
        assertTrue("fname".equals(customer.getfName()));
        assertTrue(null==customer.getlName());
        assertTrue("cardnum000".equals(customer.getCardNumber()));
        assertTrue("000".equals(customer.getCvv()));

        System.out.println("Finished testCustomerClass");
    }
}

