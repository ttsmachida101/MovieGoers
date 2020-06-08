package comp3350.MovieGoers.persistence;

import java.sql.Statement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;


import comp3350.MovieGoers.objects.Concessions;
import comp3350.MovieGoers.objects.Customer;
import comp3350.MovieGoers.objects.Event;
import comp3350.MovieGoers.objects.Movie;
import comp3350.MovieGoers.objects.Theatre;
import comp3350.MovieGoers.objects.Ticket;

public class Database implements DataAccessor {

    private String dbName;
    private String dbType;
    private Connection session;

    public Database(String dbName, String dbPath) throws SQLException {
        this.dbName = dbName;
        this.dbType = "HSQL";
        try {
            Class.forName("org.hsqldb.jdbcDriver").newInstance();
            session = DriverManager.getConnection("jdbc:hsqldb:file:" + dbPath, "SA", "");
            System.out.println(String.format("Opened %s database %s", dbType, dbPath));
        } catch (Exception e) {
            processSQLError(e);
        }
    }

    public Database() throws SQLException {
        this.dbType = "HSQL";
        try {
            Class.forName("org.hsqldb.jdbcDriver").newInstance();
            session = DriverManager.getConnection("jdbc:hsqldb:file:./app/src/main/assets/db/db;", "SA", "");
            System.out.println(String.format("Opened %s database", dbType));
        } catch (Exception e) {
            processSQLError(e);
        }
    }

    public boolean close() {
        boolean error = false;
        try {
            Statement query = session.createStatement();
            query.executeQuery("SHUTDOWN COMPACT");
            query.close();
            session.close();
            System.out.println(String.format("Closed %s database %s", dbType, dbName));
        } catch (SQLException e) {
            error = processSQLError(e);
        }
        return error;
    }

    // movie queries

    private Movie toMovie(ResultSet result) throws SQLException {
        return new Movie(result.getInt("mID"),
                result.getString("title"),
                result.getString("genre"),
                result.getString("director"),
                result.getInt("duration"),
                result.getFloat("rating"),
                result.getString("posterID"));
    }

    public boolean getMovies(List<Movie> list) {
        return getMovies(list, 0);
    }

    public boolean getMovies(List<Movie> list, int theatreID) {
        boolean error = false;
        try {
            //System.out.println(session != null ? "NOT NULL" : "ITS NULL");
            Statement query = session.createStatement();
            String queryString;

            if (theatreID > 0) {
                queryString = "SELECT * FROM Movies WHERE mID IN (SELECT mID FROM Events WHERE tID = " + theatreID;
            } else {
                queryString = "SELECT * FROM Movies";
            }
            queryString += " ORDER BY mID ASC";

            ResultSet result = query.executeQuery(queryString);
            while (result.next())
                list.add(toMovie(result));
            query.close();
        } catch (SQLException e) {
            error = processSQLError(e);
        }
        return error;
    }

    public Movie getMovie(int id) {
        Movie m = null;
        try {
            Statement query = session.createStatement();
            ResultSet result = query.executeQuery("SELECT * FROM Movies WHERE mID = " + id);
            if (result.next())
                m = toMovie(result);
            query.close();
        } catch (SQLException e) {
            processSQLError(e);
        }
        return m;
    }

    // theatre queries

    private Theatre toTheatre(ResultSet result) throws SQLException {
        return new Theatre(result.getInt("tID"),
                result.getString("name"),
                result.getString("location").replaceAll("\\\\n", "\n"),
                result.getString("logoID"));
    }

    public boolean getTheatres(List<Theatre> list) {
        return getTheatres(list, 0);
    }

    public boolean getTheatres(List<Theatre> list, int movieID) {
        boolean error = false;
        try {
            Statement query = session.createStatement();
            String queryString;

            if (movieID > 0) {
                queryString = "SELECT * FROM Theatres WHERE tID IN (SELECT tID FROM Events WHERE mID = " + movieID + ")";
            } else {
                queryString = "SELECT * FROM Theatres";
            }
            queryString += " ORDER BY tID ASC";

            ResultSet result = query.executeQuery(queryString);
            while (result.next())
                list.add(toTheatre(result));
            query.close();
        } catch (SQLException e) {
            error = processSQLError(e);
        }
        return error;
    }

    public Theatre getTheatre(int id) {
        Theatre t = null;
        try {
            Statement query = session.createStatement();
            ResultSet result = query.executeQuery("SELECT * FROM Theatres WHERE tID = " + id);
            if (result.next())
                t = toTheatre(result);
            query.close();
        } catch (SQLException e) {
            processSQLError(e);
        }
        return t;
    }

    // event queries

//    private int pair(int x, int y) {
//        return (x + y)*(x + y + 1)/2 + y;
//    }

    private Event toEvent(ResultSet result) throws SQLException {
        return new Event(result.getInt("eID"),
                toTheatre(result),
                toMovie(result),
                result.getInt("screen"),
                result.getInt("time"),
                result.getInt("capacity"),
                result.getInt("taken"),
                result.getFloat("price"));
    }

    public boolean getEvents(List<Event> list) {
        return getEvents(list, 0, 0);
    }

    public boolean getEvents(List<Event> list, int movieID, int theatreID) {
        boolean error = false;
        try {
            Statement query = session.createStatement();
            String queryString = "SELECT * FROM Events INNER JOIN Movies ON Events.mID = Movies.mID " +
                    "INNER JOIN Theatres ON Events.tID = Theatres.tID";

            if (movieID != 0 && theatreID != 0)
                queryString += String.format(" WHERE mID = %s AND tID = %s", movieID, theatreID);
            else if (movieID != 0)
                queryString += " WHERE mID = " + movieID;
            else if (theatreID != 0)
                queryString += " WHERE tID = " + theatreID;
            queryString += " ORDER BY eID ASC";

            ResultSet result = query.executeQuery(queryString);
            while (result.next())
                list.add(toEvent(result));
            query.close();
        } catch (SQLException e) {
            error = processSQLError(e);
        }
        return error;
    }

    public Event getEvent(int id) {
        Event booking = null;
        try {
            Statement query = session.createStatement();
            ResultSet result = query.executeQuery(
                    "SELECT * FROM Events INNER JOIN Movies ON Events.mID = Movies.mID " +
                            "INNER JOIN Theatres ON Events.tID = Theatres.tID " +
                            "WHERE eID = " + id);
            if (result.next())
                booking = toEvent(result);
            query.close();
        } catch (SQLException e) {
            processSQLError(e);
        }
        return booking;
    }

    public boolean bookEvent(int id) {
        boolean error = false;
        try {
            Statement query = session.createStatement();
            String queryString = String.format(
                    "UPDATE Events SET taken = taken+1 WHERE eID = %s " +
                            "AND capacity > taken", id);

            error = query.executeUpdate(queryString) != 1;
            query.close();
        } catch (SQLException e) {
            error = processSQLError(e);
        }
        return error;
    }

    public boolean cancelEvent(int id) {
        boolean error = false;
        try {
            Statement query = session.createStatement();
            String queryString = String.format(
                    "UPDATE Events SET taken = taken-1 WHERE eID = %s " +
                            "AND taken > 0", id);
            query.executeQuery(queryString);
            query.close();
        } catch (SQLException e) {
            error = processSQLError(e);
        }
        return error;
    }

    private boolean processSQLError(Exception e) {
        String result = "*** SQL Error: " + e.getMessage();
        e.printStackTrace();

        return true;
    }


    //concessions queries

    private Concessions toConcessions(ResultSet result) throws SQLException {
        return new Concessions(result.getInt("coID"),
                               result.getInt("tID"),
                               result.getString("coName"),
                               result.getFloat("coPrice"),
                               result.getString("coDesc"));
    }

    public boolean getConcessions(List<Concessions> list) {
        return getConcessions(list, 0);
    }

    public boolean getConcessions(List<Concessions> list, int theatreID) {
        boolean error = false;
        try {
            Statement query = session.createStatement();
            String queryString;

            if (theatreID > 0) {
                queryString = "SELECT * FROM Concessions WHERE tID = " + theatreID;
            } else {
                queryString = "SELECT * FROM Concessions";
            }
            queryString += " ORDER BY coID ASC";

            ResultSet result = query.executeQuery(queryString);
            while (result.next())
                list.add(toConcessions(result));
            query.close();
        } catch (SQLException e) {
            error = processSQLError(e);
        }
        return error;
    }

    public Concessions getConcession(int id) {
        Concessions c = null;
        try {
            Statement query = session.createStatement();
            ResultSet result = query.executeQuery("SELECT * FROM Concessions WHERE coID =" + id);
            if (result.next())
                c = toConcessions(result);
            query.close();
        } catch (SQLException e) {
            processSQLError(e);
        }
        return c;
    }

    public boolean addCustomer(Customer customer) throws DuplicateException {
        boolean error = false;
        //INSERT INTO Customers ('customer.getUsername()', 'customer.getfName()', 'customer.getlName()', 'customer.getCardNumber()', 'customer.getcvv')
        try {
            Statement query = session.createStatement();
            query.executeQuery("INSERT INTO Customers VALUES('" + customer.getUsername() + "', '" + customer.getPassword() + "', '" + customer.getfName() +
                "', '" + customer.getlName() + "', '" + customer.getCardNumber() + "', '" + customer.getCvv() + "');");
            query.close();
            error = true;
        } catch (SQLException e) {
            error = processSQLError(e);
            throw new DuplicateException("username");
        }
        return error;
    }


    public boolean deleteCustomer(String username) {
        boolean error = false;
        //INSERT INTO Customers ('customer.getUsername()', 'customer.getfName()', 'customer.getlName()', 'customer.getCardNumber()', 'customer.getcvv')
        if(searchCustomers(username)){
            try {
                Statement query = session.createStatement();
                query.executeQuery("DELETE FROM Customers WHERE username='"+username+"';");
                query.close();
                error = true;
            } catch (SQLException e) {
                error = processSQLError(e);
            }
        }

        return error;
    }

    private Customer toCustomer(ResultSet result) throws SQLException {
        return new Customer(result.getString("username"),
                result.getString("password"),
                result.getString("fname"),
                result.getString("lname"),
                result.getString("cardNumber"),
                result.getString("cvv"));
    }


    public Customer getCustomer(String username) {
        Customer customer = null;
        try {
            Statement query = session.createStatement();
            ResultSet result = query.executeQuery("SELECT * FROM Customers WHERE username = '" + username + "';");
            if(result.next()) {
                customer = toCustomer(result);
            }
            query.close();
        } catch (SQLException e) {
            processSQLError(e);
        }
        return customer;
    }

    public boolean searchCustomers(String username) {
        boolean found = false;

        try {
            Statement query = session.createStatement();
            ResultSet result = query.executeQuery("SELECT username FROM Customers WHERE username = '" + username + "';");
            if(result.next()) {
               found = true;
            }
            query.close();
        } catch (SQLException e) {
            processSQLError(e);
        }
        
        return found;
    }

    //UPDATE Customers SET Table = 'Name' WHERE CustomerID = 1
    public boolean setCvv (String username, String val) {
        boolean found = false;

        try {
            Statement query = session.createStatement();
            ResultSet result = query.executeQuery("UPDATE Customers SET cvv = '" + val + "' WHERE username = '" + username + "';");
            if(result.next()) {
                found = true;
            }
            query.close();
        } catch (SQLException e) {
            processSQLError(e);
        }
        System.out.println(found);
        return found;
    }

    public boolean setCardNumber (String username, String val) {
        boolean found = false;

        try {
            Statement query = session.createStatement();
            ResultSet result = query.executeQuery("UPDATE Customers SET cardNumber = '"  + val + "' WHERE username = '" + username + "';");
            if(result.next()) {
                found = true;
            }
            query.close();
        } catch (SQLException e) {
            processSQLError(e);
        }
        System.out.println(found);
        return found;
    }

    // tickets + concessions

    private Ticket toTicket(ResultSet rs) throws SQLException {
        return new Ticket(rs.getInt("tiID"),
                toCustomer(rs),
                toEvent(rs),
                rs.getString("timestamp"));
    }

    // database automatically generates a unique ticket ID; see script
    public boolean createTicket(String customerID, int eventID, String time) {
        boolean error = false;
        try {
            Statement query = session.createStatement();
            query.executeQuery(String.format(
                                    "INSERT INTO Tickets(cID, eID, timestamp)" +
                                    "VALUES('%s', %d, '%s')", customerID, eventID, time));
            query.close();
        } catch(SQLException e) {
            error = processSQLError(e);
        }

        return error;
    }


    public boolean getTicketsByCustomer(List<Ticket> list, String cID) {
        boolean error = false;
        try {
            Statement query = session.createStatement();
            ResultSet rs = query.executeQuery(String.format(
                    "SELECT * FROM Tickets " +
                            "INNER JOIN Customers ON Tickets.cID = Customers.username " +
                            "INNER JOIN Events ON Tickets.eID = Events.eID " +
                            "INNER JOIN Movies ON Events.mID = Movies.mID " +
                            "INNER JOIN Theatres ON Events.tID = Theatres.tID " +
                            "WHERE Tickets.cID = '%s'" +
                            "ORDER BY timestamp DESC", cID));
            while(rs.next()) {
                list.add(toTicket(rs));
            }
            query.close();
        } catch(SQLException e) {
            error = processSQLError(e);
        }
        return error;
    }


    //public Concessions(int ID, int theatreID, String item, float price, String description)
    private Concessions toConcession(ResultSet rs) throws SQLException {
        return new Concessions(rs.getInt("coID"),
                               rs.getInt("tID"),
                               rs.getString("coName"),
                               rs.getFloat("coPrice"),
                               rs.getString("coDesc"));
    }

    public boolean addExtrasToTicket(List<Integer> concessions, int ticketID) {
        boolean error = false;
        try {
            Statement query = session.createStatement();
            for(int i = 0; i < concessions.size(); i++)
                query.executeQuery(String.format("INSERT INTO Extras VALUES(%d, %d)", ticketID, concessions.get(i)));
            query.close();
        } catch(SQLException e) {
            error = processSQLError(e);
        }
        return error;
    }

    public boolean getExtrasByTicket(List<Concessions> concessions, int ticketID) {
        boolean error = false;
        try {
            Statement query = session.createStatement();
            ResultSet rs = query.executeQuery("SELECT coID FROM Tickets WHERE tiID = "+ticketID);
            while(rs.next())
                concessions.add(toConcession(rs));
        } catch(SQLException e) {
            error = processSQLError(e);
        }
        return error;
    }

    public static class DuplicateException extends Exception {

        public DuplicateException(String message){
            super(message);
        }

    }


}


