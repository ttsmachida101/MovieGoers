package comp3350.MovieGoers.objects;

public class Movie {
    private static int nextID = 1;
    private int ID;

    private String title;
    private String genre; // may have separate class later
    private String director; // may have separate class later
    //private String[] actors; 
    private int duration;
    private float rating;
    private String poster;

    public Movie(int ID) {
        this.ID = ID;
    }

    public Movie(int ID, String title, String genre, String director, 
                 int duration, float rating, String poster) {
        this.ID = ID;
        this.title = title;
        this.genre = genre;
        this.director = director;
        this.duration = duration;
        this.rating = rating;
        this.poster = poster;
    }

    public int getID() { return ID; }
    public String getTitle() { return title; }
    public String getGenre() { return genre; }
    public float getRating() { return rating; }
    public String getDirector() { return director; }
    public int getDuration() { return duration; }
    public String getPoster() { return poster; }
    public String toString() { return title; }
}

