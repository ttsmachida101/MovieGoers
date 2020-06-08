package comp3350.MovieGoers.business;


import android.app.Application;

public class CustomerState extends Application{
    private static String username;
    private static boolean isGuest = false;
    public static int profilePic=0;

    public CustomerState() { this.isGuest = true; }

    public static void setUsername(String customer){ username = customer; }
    public static String getUsername() throws NullPointerException { return username; }

    public static void setIsGuest(boolean val) { isGuest = val; }
    public static boolean getIsGuest() { return isGuest; }



}
