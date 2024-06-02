
/**
 * Rater is an interface for PlainRater and EfficientRater classes.
 * 
 * @author Vlad Ragulin
 * @version 30-May-2024
 */

import java.util.*;
public interface Rater {
    public void addRating(String item, double rating);    
    
    public boolean hasRating(String item);
    
    public String getID();
    
    public double getRating(String item);
    
    public int numRatings();
    
    ArrayList<String> getItemsRated();
}
