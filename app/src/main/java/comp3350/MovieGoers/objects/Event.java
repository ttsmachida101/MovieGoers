package comp3350.MovieGoers.objects;

public class Event {
    private int ID;
    private Movie movie;
    private Theatre theatre;
    private int screen;
    private int time;
    private int seatCapacity;
    private int seatsTaken;
    private float price;

    public Event(int ID, Theatre theatre, Movie movie, int screen, int time, int seatCapacity, int seatsTaken, float price){
        this.ID = ID;
        this.theatre = theatre;
        this.movie = movie;
        this.screen = screen;
        this.time = time;
        this.seatCapacity = seatCapacity;
        this.seatsTaken = seatsTaken;
        this.price = price;
    }


    public int getID() { return ID; }
    public Theatre getTheatre() { return theatre; }
    public int getTheatreID() { return getTheatre().getID();}
    public Movie getMovie() { return movie; }
    public int getMovieID() {return getMovie().getID();}
    public int getScreen() { return screen; }
    public int getSeatCapacity() { return seatCapacity; }
    public int getSeatsTaken(){ return seatsTaken; }
    public int getSeatsRemaining() { return seatCapacity - seatsTaken; }
    public void cancelSeat() { seatsTaken--; }
    public void bookSeat() {
        if(seatsTaken < seatCapacity) {seatsTaken++;}}

    public float getPrice() { return price; }
    public int getTime(){ return time; }

    public String displayTime(){
        int time = getTime();
        String result;
        int length;
        if(time>=1200)
            if(time>=1300)
                result = (time-1200)+"PM";
            else
                result = time+"PM";
        else
            if(time<100)
                result = (1200+time)+"AM";
            else
                result = time+"AM";

        length = result.length();
        result = result.substring(0,length-4)+":"+result.substring(length-4,length);

        return result;
    }

//    public String toString(){
//        return movie +" "+ theatre +" screen#: "+ screen.getScreenNum() + " time: "+time+" ("+seat+"/"+screen.getNumSeat()+")";
//    }


}
