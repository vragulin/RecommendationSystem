
/**
 * Runner for the final project.  getItemsToRate method randomly generates 20 MovieDatabase entries.
 * This info is used to compare user's ratings with ratings provided by other users and to make 
 * personalized recommendations.
 * 
 * printRecommendationsFor method prints a table with recommended movies based on user's ratings.
 * 
 * @author V. Ragulin
 * @version 2-Jun-2024
 */
import java.util.*;

public class RecommendationRunner implements Recommender {
   public ArrayList<String> getItemsToRate (){
       MovieDatabase.initialize("ratedmoviesfull");

       ArrayList<String> itemsToRate = new ArrayList<String> ();
       ArrayList<String> movies = MovieDatabase.filterBy(new TrueFilter());
       
       for (int k=0; k<20; k++) {
           Random rand = new Random();
           int random = rand.nextInt(movies.size());
           if( ! itemsToRate.contains(movies.get(random)) )
               itemsToRate.add(movies.get(random));
       }
       return itemsToRate;
   }
   
    public void printRecommendationsFor (String webRaterID) {
        FourthRatings fourthRatings = new FourthRatings ();
        MovieDatabase.initialize("ratedmoviesfull");
        RaterDatabase.initialize("ratings");
        
        print("<p>Read data for " + RaterDatabase.size() + " raters</p>");
        print("<p>Read data for " + MovieDatabase.size() + " movies</p>");
        
        int numSimilarRaters = 20; //variable
        int minNRatings =  5;
        ArrayList<Rating> similarRatings = fourthRatings.getSimilarRatings(webRaterID, numSimilarRaters, minNRatings);
        
        if (similarRatings.size() == 0)
            print("No matching movies found.");
        else {
            String header = ("<table> <tr> <th>Movie Title</th> <th>Rating Value</th> <Genres></th> </tr>");
            String body = "";
            for (Rating rating : similarRatings) 
                body += "<tr> <td>" + MovieDatabase.getTitle(rating.getItem()) + "</td><td>"
                        + rating.getValue() + "</td><td>" + MovieDatabase.getGenres(rating.getItem())
                        + "</td></tr> ";
            print(header + body + "</table>");
        }
    }
    
    // Helper function - abberviation for System.out.println
    private <T> void print(T t) {
        System.out.println(t);
    }
}
