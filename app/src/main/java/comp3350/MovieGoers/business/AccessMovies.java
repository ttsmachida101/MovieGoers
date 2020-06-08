package comp3350.MovieGoers.business;

import java.util.List;

import comp3350.MovieGoers.objects.Movie;
import comp3350.MovieGoers.persistence.DataAccessor;

public class AccessMovies {

    private static DataAccessor session = DataManager.getSession();

    public static void getMovies(List<Movie> list){
        list.clear();
        session.getMovies(list);
    }

    public static Movie getMovie(int id) {
        return session.getMovie(id);
    }

    //getMovieByName
    //getMovieByGenre
}
