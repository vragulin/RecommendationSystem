
/**
 * Ratings analysis for the movie recommendation engine
 * 
 * @author Vlad Ragulin
 * @version 28-May-28
 * -based on: https://github.com/7sam7/Coursera_Duke_Java/blob/master/capstone/StepOneStarterProgram/FirstRatings.java
 * + https://github.com/konstantinkrumin/Java-Programming-Build-a-Recommendation-System/blob/master/Week%201/FirstRatings.java
*/
import edu.duke.*;
import java.util.*;
import org.apache.commons.csv.*;
import java.util.Date;

public class FirstRatings {
    
    public ArrayList<Movie> loadMovies(String filename){
        ArrayList<Movie> movies = new ArrayList<Movie> ();
        FileResource fr = new FileResource("data/" + filename + ".csv");
        CSVParser parser = fr.getCSVParser();
                      
        for (CSVRecord record : parser) {
            String currentID = record.get(0);
            String currentTitle = record.get(1);
            String currentYear = record.get(2);
            String currentCountry = record.get(3);
            String currentGenre = record.get(4);
            String currentDirector = record.get(5);
            int currentMinutes = Integer.parseInt(record.get(6));
            String currentPoster = record.get(7);
            
            Movie currentMovie = new Movie(currentID, currentTitle, currentYear, currentGenre, 
                                            currentDirector, currentCountry, currentPoster, 
                                            currentMinutes);
            movies.add(currentMovie);                             
        }
        return movies;
    }
    
    public ArrayList<Rater> loadRaters(String filename) {
        ArrayList<Rater> ratersData = new ArrayList<Rater> ();
        ArrayList<String> listOfIDs = new ArrayList<String> ();
        
        FileResource fr = new FileResource("data/" + filename + ".csv");
        CSVParser parser = fr.getCSVParser();
        
        for (CSVRecord record : parser) {
            String currentRaterID = record.get(0);
            String currentMovieID = record.get(1);
            double currentMovieRating = Double.parseDouble(record.get(2));
            
            if (! listOfIDs.contains(currentRaterID)) {
                //Rater currentRater = new PlainRater(currentRaterID);
                Rater currentRater = new EfficientRater(currentRaterID);
                ratersData.add(currentRater);
                listOfIDs.add(currentRaterID);
                currentRater.addRating(currentMovieID, currentMovieRating);
            } else
                for (Rater rater : ratersData)
                    if (rater.getID().equals(currentRaterID))
                        rater.addRating(currentMovieID, currentMovieRating);
        }
        return ratersData;
    }
    
        // Helper function to reduce typing
        private <T> void print(T t) {
            System.out.println(t);
        }
        
    // Test methods
    public void testLoadMovies () {
        ArrayList<Movie> movies = loadMovies("ratedmoviesfull"); //("ratedmovies_short");
        
        print("Number of movies: " + movies.size());
        
        String countInGenre = "Comedy"; //variable
        int countComedies = 0;
        
        int minutes = 150; //variable
        int countMinutes = 0;
        
        for (Movie movie : movies) {
            if (movie.getGenres().contains(countInGenre)) countComedies++;
            if (movie.getMinutes() > minutes) countMinutes++;
        }
        
        print("There are " + countComedies + " comedies in the list");
        print("There are " + countMinutes + " movies with more than " + minutes + 
                " minutes in the list");
        
        // A HashMap keeping track of how many movies each particular director filmed
        HashMap<String, Integer> countMoviesByDirector = new HashMap<String,Integer>();
        for (Movie movie : movies) {
            String[] directors = movie.getDirector().split(",");
        
            for (String director : directors ) {
                director = director.trim();
                if (!countMoviesByDirector.containsKey(director)) 
                    countMoviesByDirector.put(director, 1);
                else
                    countMoviesByDirector.put(director, countMoviesByDirector.get(director) + 1);
                
            }
        }
        
        // Count max number of movies directed by a particular director
        int maxNumOfMovies = 0;
        for (String director : countMoviesByDirector.keySet())
            if (countMoviesByDirector.get(director) > maxNumOfMovies)
                maxNumOfMovies = countMoviesByDirector.get(director);
        
        // Create an ArrayList with directors from the list that directed the max num of movies
        ArrayList<String> directorsList = new ArrayList<String> ();
        for (String director : countMoviesByDirector.keySet())
            if (countMoviesByDirector.get(director) == maxNumOfMovies)
                directorsList.add(director);
        print("Max number of movies directed by one director: " + maxNumOfMovies);
        print("Directors who directed the max # of movies: " + directorsList);
    }

    public void testLoadRaters () {
        ArrayList<Rater> raters = loadRaters("ratings"); //_short");
        print("Total number of raters: " + raters.size());
    
        HashMap<String, HashMap<String, Double>> hashmap = new HashMap<String, HashMap<String, Double>>();
        for (Rater rater : raters) {
            HashMap<String, Double> ratings = new HashMap<String, Double> ();
            ArrayList<String> itemsRated = rater.getItemsRated();
            
            for (String movieID : itemsRated) {
                double movieRating = rater.getRating(movieID);
                ratings.put(movieID, movieRating);
            }
            hashmap.put(rater.getID(), ratings);
        }
        
        String raterID = "193"; //rater_id;
        try {
            int ratingsSize = hashmap.get(raterID).size();
            print("Number of ratings for the rater " + raterID + " : " + ratingsSize);
        }
        catch (NullPointerException e) {
            print("Invalid rater ID: " + raterID);
        }
    
        int maxNumOfRatings = 0;
        for (String key : hashmap.keySet()) {
            int currNumOfRatings = hashmap.get(key).size();
            if (currNumOfRatings > maxNumOfRatings) maxNumOfRatings = currNumOfRatings;
        }
        print("Max number of ratings by any rater : " + maxNumOfRatings);
        
        ArrayList<String> raterWithMaxRatings = new ArrayList<String> ();
        for (String key : hashmap.keySet()) {
            int currNumOfRatings = hashmap.get(key).size();
            if (maxNumOfRatings == currNumOfRatings) raterWithMaxRatings.add(key);
        }
        print("Rater(s) with max # of movies rated: " + raterWithMaxRatings);
        
        String movieID = "1798709";
        int numOfRatings = 0;
        for (String key : hashmap.keySet() )
            if (hashmap.get(key).containsKey(movieID)) numOfRatings++;
        print("Number of ratings movie " + movieID + " has : " + numOfRatings);
        
        ArrayList<String> uniqueMovies = new ArrayList<String> ();
        for (String key : hashmap.keySet()) {
            for (String currMovieID : hashmap.get(key).keySet())
                if (! uniqueMovies.contains(currMovieID)) uniqueMovies.add(currMovieID);
        }
        print("Total number of movies that were rated : " + uniqueMovies.size());
    }
}