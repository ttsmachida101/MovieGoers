package comp3350.MovieGoers.objects;

public class Concessions {

    private int ID;
    private int theatreID;
    private String item;
    //private Sizes[] sizes;
    private float price;

    private String description;

    public Concessions(int ID, int theatreID, String item, float price, String description) {//Sizes[] sizes) {
        this.ID = ID;
        this.theatreID = theatreID;
        this.item = item;
        this.price = price;
        //this.sizes = sizes;
        this.description = description;
    }

    public int getID() {return ID; }
    public int getTheatreID() { return theatreID;}
    public String getItem() { return item; }
    public float getItemPrice() { return price; }
    public String getDesription() { return description; }
}





