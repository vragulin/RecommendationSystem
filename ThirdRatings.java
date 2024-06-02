
/**
 * ThirdRatings class contains methods to get average ratins with/without filters.
 * 
 * @author V. Ragulin 
 * @version 30-May-2024
 */

import java.util.*;

public class ThirdRatings {
    private ArrayList<Rater> myRaters;
    
    public ThirdRatings() {
        // default constructor
        this("ratings");
    }
    
    public ThirdRatings (String ratingsfile) {
        FirstRatings firstRatings = new FirstRatings();
        myRaters = firstRatings.loadRaters(ratingsfile);
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
        ArrayList<String> movies = MovieDatabase.filterBy(new TrueFilter());
        ArrayList<Rating> averageRatings = new ArrayList<Rating> ();
        
        for (String movieID : movies) {
            double average = getAverageByID(movieID, minRaters);
            if (average != 0.0) {
                Rating rating = new Rating(movieID, average);
                averageRatings.add(rating);
            }
        }
        
        return averageRatings;
    }   
    
    public ArrayList<Rating> getAverageRatingByFilter (int minimalRaters, Filter filterCriteria) {
        ArrayList<Rating> averageRatings = new ArrayList<Rating> ();
        ArrayList<String> filteredMovies = MovieDatabase.filterBy(filterCriteria);
        
        for (String movieID : filteredMovies ) {
            double average = getAverageByID(movieID, minimalRaters);
            if (average != 0.0) {
                Rating rating = new Rating(movieID, average);
                averageRatings.add(rating);
            }
        }
        return averageRatings;
    }
}
