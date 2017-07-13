package cchapy.cc;

import java.util.Comparator;


/**
 * Created by Hayley on 12/07/2017.
 */

public class CityComparator implements Comparator<City> {

    @Override
    public int compare(City x, City y) {
        Integer count1 = x.getLeafCount();
        Integer count2 = y.getLeafCount();
        return count2.compareTo(count1);
    }
}
