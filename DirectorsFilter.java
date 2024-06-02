/**
 * DirectorsFilter can be used to extract movies with length in between minimum and maximum
 * specified minutes
 */

import java.util.*;

public class DirectorsFilter implements Filter {
    String directorsList;
    
    public DirectorsFilter(String directors) {
        directorsList = directors;
    }
    
    @Override
    public boolean satisfies(String id) {
        String [] directorsSplit = directorsList.split(",");
        for (String director : directorsSplit)
            if (MovieDatabase.getDirector(id).indexOf(director) != -1)
                return true;
        return false;
    }

}
