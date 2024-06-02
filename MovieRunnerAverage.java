
/**
 *  Tests for the getAverageRatings method from the secondRatings class
 * 
 * @author V. Ragulin 
 * @version 30-May-2024
 */
import java.util.*;

public class MovieRunnerAverage {
    public void printAverageRatings() {
        //SecondRatings secondRatings = new SecondRatings ("ratedmoviesfull", "ratings");
        //SecondRatings secondRatings = new SecondRatings ("ratedmovies_short", "ratings_short");
        SecondRatings secondRatings = new SecondRatings ();
        
        print("Total number of movies : " + secondRatings.getMovieSize());
        print("Total number of raters : " + secondRatings.getRaterSize());
        
        int minNumOfRatings = 12; //variable
        ArrayList<Rating> averageRatings = secondRatings.getAverageRatings(minNumOfRatings);
        Collections.sort(averageRatings);
        for (Rating rating : averageRatings) {
            print(rating.getValue() + " " + secondRatings.getTitle(rating.getItem()));
        }
        print("There are " + averageRatings.size() + " movies with " + 
            minNumOfRatings + " or more ratings");
    }
    
    public void getAverageRatingsOneMovie() {
        //SecondRatings secondRatings = new SecondRatings ("ratedmovies_short", "ratings_short");
        SecondRatings secondRatings = new SecondRatings ();
        String title = "Vacation"; //variable
        int minNumOfRatings = 1; //variable
        
        String movieID = secondRatings.getID(title);
        ArrayList<Rating> averageRatings = secondRatings.getAverageRatings(minNumOfRatings);
        for (Rating rating : averageRatings)
            if (rating.getItem().equals(movieID))
                print("For movie \"" + title + "\" the average rating is "
                    + rating.getValue());
    }
    
        // Helper function - abberviation for System.out.println
        private <T> void print(T t) {
            System.out.println(t);
        }
}
