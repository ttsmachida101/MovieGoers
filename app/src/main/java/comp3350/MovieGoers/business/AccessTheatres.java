package comp3350.MovieGoers.business;

import java.util.List;

import comp3350.MovieGoers.objects.Theatre;
import comp3350.MovieGoers.persistence.DataAccessor;

public class AccessTheatres {

    private static DataAccessor session = DataManager.getSession();

//  TOBE ADDED
//    public static void getTheatres(List<Theatre> theatres) {
//        theatres.clear();
//        session.getTheatres(theatres);
//    }

    public static void getTheatres(List<Theatre> theatres, int id) {
        theatres.clear();
        session.getTheatres(theatres, id);
    }

    public static Theatre getTheatre(int id) { 
        return session.getTheatre(id);
    }
}
