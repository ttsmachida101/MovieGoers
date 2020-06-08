package comp3350.MovieGoers.objects;

import java.util.ArrayList;

public class Theatre {
    private int ID;

    private String name;
    private String location;
    private String logo;

    public Theatre(int ID, String name, String location, String logo) {
        this.ID = ID;
        this.name = name;
        this.location = location;
        this.logo = logo;
    }

    public int getID() { return ID; }
    public String getName(){
        return name;
    }
    public String getLocation(){ return location; }
    public String getLogo(){ return logo; }
}
