
/**
 * MovieRunnerWithFilters - tests for the ThirRatings Class
 * 
 * @author V. Ragulin
 * @version 30-May-24
 */

import java.util.*; 

public class MovieRunnerWithFilters {
    public void printAverageRatings() {
        ThirdRatings thirdRatings = new ThirdRatings ("ratings");
        MovieDatabase.initialize("ratedmoviesfull");
        //MovieDatabase.initialize("ratedmovies_short");
        
        print("Read data for " + thirdRatings.getRaterSize() + " raters");
        print("Read data for " + MovieDatabase.size() + " movies");
        
        int minNumOfRatings = 35; //variable
        ArrayList<Rating> averageRatings = thirdRatings.getAverageRatings(minNumOfRatings);
        print("There are " + averageRatings.size() + " movies with " + minNumOfRatings + " or more rating(s)");

        Collections.sort(averageRatings);
        for (Rating rating : averageRatings) {
            print(rating.getValue() + " " + MovieDatabase.getTitle(rating.getItem()));
        }
    }

    public void printAverageRatingByYear() {
        ThirdRatings thirdRatings = new ThirdRatings("ratings");
        MovieDatabase.initialize("ratedmoviesfull");
        
        print("Read data for " + thirdRatings.getRaterSize() + " raters");
        print("Read data for " + MovieDatabase.size() + " movies");

        int year = 2000; //variable;
        YearAfterFilter yaf = new YearAfterFilter(year);

        int minNumOfRatings = 20; //variable
        ArrayList<Rating> averageRatings = thirdRatings.getAverageRatingByFilter(minNumOfRatings, yaf);
        print("There are " + averageRatings.size() + " movies released after " 
                + year + " with " + minNumOfRatings + " or more rating(s)");

        Collections.sort(averageRatings);
        for (Rating rating : averageRatings) {
            print(rating.getValue() + "\t" + MovieDatabase.getYear(rating.getItem())
                    + "\t" + MovieDatabase.getTitle(rating.getItem()));
        }
    }
    
    public void printAverageRatingByGenre() {
        ThirdRatings thirdRatings = new ThirdRatings("ratings");
        MovieDatabase.initialize("ratedmoviesfull");
        
        print("Read data for " + thirdRatings.getRaterSize() + " raters");
        print("Read data for " + MovieDatabase.size() + " movies");

        String genre = "Comedy"; //variable;
        GenreFilter gf = new GenreFilter(genre);

        int minNumOfRatings = 20; //variable
        ArrayList<Rating> averageRatings = thirdRatings.getAverageRatingByFilter(minNumOfRatings, gf);
        print("There are " + averageRatings.size() + " movies in genre of \"" 
                + genre + "\" with " + minNumOfRatings + " or more rating(s)");

        Collections.sort(averageRatings);
        for (Rating rating : averageRatings) {
            print(rating.getValue() + "\t" + MovieDatabase.getTitle(rating.getItem()) + "\t"
                + MovieDatabase.getGenres(rating.getItem()));
        }
    }

    public void printAverageRatingByMinutes() {
        ThirdRatings thirdRatings = new ThirdRatings("ratings");
        MovieDatabase.initialize("ratedmoviesfull");
        
        print("Read data for " + thirdRatings.getRaterSize() + " raters");
        print("Read data for " + MovieDatabase.size() + " movies");

        int minMinutes = 105; //variable
        int maxMinutes = 135; //variable
        int minNumOfRatings = 5; //variable
        MinutesFilter mf = new MinutesFilter(minMinutes, maxMinutes);
      
        ArrayList<Rating> averageRatings = thirdRatings.getAverageRatingByFilter(minNumOfRatings, mf);
        print("There are " + averageRatings.size() + " movies that are between "  + minMinutes + 
            " and " + maxMinutes + " length with " + minNumOfRatings + " or more rating(s)");

        Collections.sort(averageRatings);
        for (Rating rating : averageRatings) {
            String item = rating.getItem();
            print(rating.getValue() + "\t" + " Time " + MovieDatabase.getMinutes(item)
                + " " + MovieDatabase.getTitle(item));
        }
    }        
    
    public void printAverageRatingByDirectors() {
        ThirdRatings thirdRatings = new ThirdRatings("ratings");
        MovieDatabase.initialize("ratedmoviesfull");
        
        print("Read data for " + thirdRatings.getRaterSize() + " raters");
        print("Read data for " + MovieDatabase.size() + " movies");

        int minNumOfRatings = 4; //variable
        String directorsList =  "Clint Eastwood,Joel Coen,Martin Scorsese,Roman Polanski,Nora Ephron,Ridley Scott,Sydney Pollack";
        DirectorsFilter df = new DirectorsFilter(directorsList);
        
        ArrayList<Rating> averageRatings = thirdRatings.getAverageRatingByFilter(minNumOfRatings, df);
        print("There are " + averageRatings.size() + " movies that were directed by either of "
            + directorsList + " with " + minNumOfRatings + " or more rating(s)");

        Collections.sort(averageRatings);
        for (Rating rating : averageRatings) {
            String item = rating.getItem();
            print(rating.getValue() + "\t" + MovieDatabase.getTitle(item) 
                + "\tdirected by : " + MovieDatabase.getDirector(item));
        }
    }        
    
        public void printAverageRatingByYearAfterAndGenre() {
        ThirdRatings thirdRatings = new ThirdRatings("ratings");
        MovieDatabase.initialize("ratedmoviesfull");
        
        print("Read data for " + thirdRatings.getRaterSize() + " raters");
        print("Read data for " + MovieDatabase.size() + " movies");

        int year = 1990; //variable
        YearAfterFilter yaf = new YearAfterFilter(year);
        
        String genre = "Drama"; //variable
        GenreFilter gf = new GenreFilter(genre);

        AllFilters af = new AllFilters();
        af.addFilter(yaf);
        af.addFilter(gf);
        
        int minNumOfRatings = 8; //variable
        ArrayList<Rating> averageRatings = thirdRatings.getAverageRatingByFilter(minNumOfRatings, af);
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

    public void printAverageRatingByDirectorsAndMinutes() {
        ThirdRatings thirdRatings = new ThirdRatings("ratings");
        MovieDatabase.initialize("ratedmoviesfull");
        
        print("Read data for " + thirdRatings.getRaterSize() + " raters");
        print("Read data for " + MovieDatabase.size() + " movies");

        String directorsList =  "Clint Eastwood,Joel Coen,Tim Burton,Ron Howard,Nora Ephron,Sydney Pollack";
        DirectorsFilter df = new DirectorsFilter(directorsList);

        int minMinutes = 90; //variable
        int maxMinutes = 180; //variable
        MinutesFilter mf = new MinutesFilter(minMinutes, maxMinutes);

        AllFilters af = new AllFilters();
        af.addFilter(df);
        af.addFilter(mf);
        
        int minNumOfRatings = 3; //variable
        ArrayList<Rating> averageRatings = thirdRatings.getAverageRatingByFilter(minNumOfRatings, af);
        print("There are " + averageRatings.size() + " movie(s) that were filmed by either one of " 
                + directorsList + "; and between " + minMinutes + " and " + maxMinutes + "in length " + 
                + minNumOfRatings + " or more rating(s)");

        Collections.sort(averageRatings);
        for (Rating rating : averageRatings) {
            String item = rating.getItem();
            print(rating.getValue() +"\tTime: "+ MovieDatabase.getMinutes(item) +"\t"
                + MovieDatabase.getTitle(item) +"\tdirected by: "+ MovieDatabase.getDirector(item));
        }
    }

        // Helper function - abberviation for System.out.println
        private <T> void print(T t) {
            System.out.println(t);
        }
}
