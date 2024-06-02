
/**
 * SecondRatings class contains various methods to extract information from the 
 * Array and Rater lists
 * 
 * @author V. Ragulin 
 * @version 30-May-2024
 */

import java.util.*;

public class SecondRatings {
    private ArrayList<Movie> myMovies;
    private ArrayList<Rater> myRaters;
    
    public SecondRatings() {
        // default constructor
        this("ratedmoviesfull", "ratings");
    }
    
    public SecondRatings (String movieFile, String ratingsfile) {
        FirstRatings firstRatings = new FirstRatings();
        myMovies = firstRatings.loadMovies(movieFile);
        myRaters = firstRatings.loadRaters(ratingsfile);
    }
    
    public int getMovieSize () {
        return myMovies.size();
    }

    public int getRaterSize () {
        return myRaters.size();
    }
    
    private double getAverageByID(String id, int minRaters) {
        double sum = 0.0;
        int count = 0;
        
        for (Rater rater : myRaters) {
            if (rater.hasRating(id) ) {
                sum += rater.getRating(id);
                count ++;
            }
        }
        
        if (count >= minRaters) return sum / count;
            else return 0.0;
    }
    
    public ArrayList<Rating> getAverageRatings (int minRaters) {
        ArrayList<Rating> averageRatings = new ArrayList<Rating> ();
        
        for (Movie movie : myMovies) {
            String movieID = movie.getID();
            double average = getAverageByID(movieID, minRaters);
            if (average != 0.0) {
                Rating rating = new Rating(movieID, average);
                averageRatings.add(rating);
            }
        }
        
        return averageRatings;
    }
    
    public String getTitle(String id) {
        String title = null;
        
        for (Movie movie :  myMovies) {
            if (movie.getID().equals(id)) {
                title = movie.getTitle();
            }
        }
        
        if (title != null) return title;
            else return ("No movie with ID: " + id + " found");
    }
    
    public String getID(String title) {
        String movieID = null;
        
        for (Movie movie :  myMovies) {
            if (movie.getTitle().equals(title)) {
                movieID = movie.getID();
            }
        }
        
        if (movieID != null) return movieID;
            else return ("No movie with title: " + title + " found");
    }
}
