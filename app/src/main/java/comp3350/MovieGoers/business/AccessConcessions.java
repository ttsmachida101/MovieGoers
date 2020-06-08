package comp3350.MovieGoers.business;

import java.util.List;

import comp3350.MovieGoers.objects.Concessions;
import comp3350.MovieGoers.persistence.DataAccessor;



public class AccessConcessions {

    private static DataAccessor session = DataManager.getSession();

//    TOBE ADDED
//    public static void getConcessions(List<Concessions> concessions) {
//        concessions.clear();
//        session.getConcessions(concessions);
//    }

    public static void getConcessions(List<Concessions> concessions, int id) {
        concessions.clear();
        session.getConcessions(concessions, id);
    }

    public static Concessions getConcession(int id) {return session.getConcession(id);}
}
