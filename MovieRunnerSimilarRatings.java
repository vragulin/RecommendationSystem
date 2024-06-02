
/**
 * MovieRunnerSimilarRatings contains various tests for the methods from FourthRatings
 * 
 * @author Vlad Ragulin
 * @version 31-May-2024
 */

import java.util.*; 

public class MovieRunnerSimilarRatings {
    public void printAverageRatings() {
        FourthRatings fourthRatings = new FourthRatings ("ratings");
        MovieDatabase.initialize("ratedmoviesfull");
        //MovieDatabase.initialize("ratedmovies_short");
        
        print("Read data for " + RaterDatabase.size() + " raters");
        print("Read data for " + MovieDatabase.size() + " movies");
        
        int minNumOfRatings = 35; //variable
        ArrayList<Rating> averageRatings = fourthRatings.getAverageRatings(minNumOfRatings);
        print("There are " + averageRatings.size() + " movies with " + minNumOfRatings + " or more rating(s)");

        Collections.sort(averageRatings);
        for (Rating rating : averageRatings) {
            print(rating.getValue() + " " + MovieDatabase.getTitle(rating.getItem()));
        }
    }
    
    public void printAverageRatingByYearAfterAndGenre() {
        FourthRatings fourthRatings = new FourthRatings("ratings");
        MovieDatabase.initialize("ratedmoviesfull");
        
        print("Read data for " + RaterDatabase.size() + " raters");
        print("Read data for " + MovieDatabase.size() + " movies");

        int year = 1990; //variable
        YearAfterFilter yaf = new YearAfterFilter(year);
        
        String genre = "Drama"; //variable
        GenreFilter gf = new GenreFilter(genre);

        AllFilters af = new AllFilters();
        af.addFilter(yaf);
        af.addFilter(gf);
        
        int minNumOfRatings = 8; //variable
        ArrayList<Rating> averageRatings = fourthRatings.getAverageRatingByFilter(minNumOfRatings, af);
        print("There are " + averageRatings.size() + " movies in genre of \"" 
                + genre + "\" that were directed after " + year + " with " 
                + minNumOfRatings + " or more rating(s)");

        Collections.sort(averageRatings);
        for (Rating rating : averageRatings) {
            String item = rating.getItem();
            print(rating.getValue() +"\t"+ MovieDatabase.getYear(item) +"\t"
                + MovieDatabase.getTitle(item) +"\t"+ MovieDatabase.getGenres(item));
        }
    }

    public void printSimilarRatings () {
        FourthRatings fourthRatings = new FourthRatings("ratings");
        MovieDatabase.initialize("ratedmoviesfull");

        print("Read data for " + RaterDatabase.size() + " raters");
        print("Read data for " + MovieDatabase.size() + " movies");

        String id = "71"; //variable
        int numSimilarRaters = 20; //variable
        int minRaters = 5; //variable
        
        ArrayList<Rating> similarRatings = fourthRatings.getSimilarRatings(id, numSimilarRaters, minRaters);
        print("There are " + similarRatings.size() + " movies that are recommended for the rater with ID "
            + id + " with " + minRaters + " or more ratings. " + numSimilarRaters + " closest raters were considered.");

        for (Rating rating : similarRatings)
            print(rating.getValue() + " " + MovieDatabase.getTitle(rating.getItem()));
    }
    
    public void printSimilarRatingsByGenre () {
        FourthRatings fourthRatings = new FourthRatings("ratings");
        MovieDatabase.initialize("ratedmoviesfull");

        print("Read data for " + RaterDatabase.size() + " raters");
        print("Read data for " + MovieDatabase.size() + " movies");

        String genre = "Mystery"; //variable
        GenreFilter gf = new GenreFilter(genre);
        
        String id = "964"; //variable
        int numSimilarRaters = 20; //variable
        int minRaters = 5; //variable
        
        ArrayList<Rating> similarRatings = fourthRatings.getSimilarRatingsByFilter(
                        id, numSimilarRaters, minRaters, gf);
        print("There are " + similarRatings.size() + " movies that are recommended for the rater with ID "
            + id + " with " + minRaters + " or more ratings, in \"" + genre + "\" genre. " 
            + numSimilarRaters + " closest raters were considered.");

        for (Rating rating : similarRatings)
            print(rating.getValue() + " " + MovieDatabase.getTitle(rating.getItem()));
    }
    
    public void printSimilarRatingsByDirector () {
        FourthRatings fourthRatings = new FourthRatings("ratings");
        MovieDatabase.initialize("ratedmoviesfull");

        print("Read data for " + RaterDatabase.size() + " raters");
        print("Read data for " + MovieDatabase.size() + " movies");

        String directors ="Clint Eastwood,J.J. Abrams,Alfred Hitchcock,Sydney Pollack,David Cronenberg,Oliver Stone,Mike Leigh"; //variable
        DirectorsFilter df = new DirectorsFilter(directors);
        
        String id = "120"; //variable
        int numSimilarRaters = 10; //variable
        int minRaters = 2; //variable
        
        ArrayList<Rating> similarRatings = fourthRatings.getSimilarRatingsByFilter(
                        id, numSimilarRaters, minRaters, df);
        print("There are " + similarRatings.size() + " movies that are recommended for the rater with ID "
            + id + " with " + minRaters + " or more ratings, that were directed by either of teh following directors: " 
            + directors + ". " + numSimilarRaters + " closest raters were considered.");

        for (Rating rating : similarRatings)
            print(rating.getValue() + " " + MovieDatabase.getTitle(rating.getItem()) + ", " + 
                MovieDatabase.getDirector(rating.getItem()));
    }

    public void printSimilarRatingsByGenreAndMinutes () {
        FourthRatings fourthRatings = new FourthRatings("ratings");
        MovieDatabase.initialize("ratedmoviesfull");

        print("Read data for " + RaterDatabase.size() + " raters");
        print("Read data for " + MovieDatabase.size() + " movies");

        String genre = "Drama"; //variable
        GenreFilter gf = new GenreFilter(genre);
        
        int minMin = 80; //variable
        int maxMin = 160; //variable
        MinutesFilter mf = new MinutesFilter(minMin, maxMin);
        
        AllFilters af = new AllFilters();
        af.addFilter(gf);
        af.addFilter(mf);
        
        String id = "168"; //variable
        int numSimilarRaters = 10; //variable
        int minRaters = 3; //variable
        
        ArrayList<Rating> similarRatings = fourthRatings.getSimilarRatingsByFilter(
                        id, numSimilarRaters, minRaters, af);
        print("There are " + similarRatings.size() + " movies that are recommended for the rater with ID "
            + id + " with " + minRaters + " or more ratings, in \"" + genre + "\" genre, that are between " 
            + minMin + " and " + maxMin + " minutes in length. " + numSimilarRaters + " closest raters were considered.");

        for (Rating rating : similarRatings){
            String item = rating.getItem();
            print(rating.getValue() + " " + MovieDatabase.getTitle(item) + ", " + " Time: " + MovieDatabase.getMinutes(item)
                + ", Genre: " + MovieDatabase.getGenres(item));
        }
    }

    public void printSimilarRatingsByYearAndMinutes () {
        FourthRatings fourthRatings = new FourthRatings("ratings");
        MovieDatabase.initialize("ratedmoviesfull");

        print("Read data for " + RaterDatabase.size() + " raters");
        print("Read data for " + MovieDatabase.size() + " movies");

        int year = 1975; //variable
        YearAfterFilter yaf = new YearAfterFilter(year);
        
        int minMin = 70; //variable
        int maxMin = 200; //variable
        MinutesFilter mf = new MinutesFilter(minMin, maxMin);
        
        AllFilters af = new AllFilters();
        af.addFilter(yaf);
        af.addFilter(mf);
        
        String id = "314"; //variable
        int numSimilarRaters = 10; //variable
        int minRaters = 5; //variable
        
        ArrayList<Rating> similarRatings = fourthRatings.getSimilarRatingsByFilter(
                        id, numSimilarRaters, minRaters, af);
        print("There are " + similarRatings.size() + " movies that are recommended for the rater with ID "
            + id + " with " + minRaters + " or more ratings, releasted after" + year + " year, that are between " 
            + minMin + " and " + maxMin + " minutes in length. " + numSimilarRaters + " closest raters were considered.");

        for (Rating rating : similarRatings){
            String item = rating.getItem();
            print(rating.getValue() + " " + MovieDatabase.getTitle(item) + ", " + " Time: " + MovieDatabase.getMinutes(item)
                + ", Year: " + MovieDatabase.getYear(item));
        }
    }

    // Helper function - abberviation for System.out.println
    private <T> void print(T t) {
        System.out.println(t);
    }
}
