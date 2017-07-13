package cchapy.cc;

import java.util.Comparator;

/**
 * Created by Hayley on 12/07/2017.
 * A class modelling a city in the system.
 */
public class City {
    private String cityName;
    private int userCount;
    private int leafCount;

    public City(String cityName, int userCount, int leafCount) {
        this.cityName = cityName;
        this.userCount = userCount;
        this.leafCount = leafCount;
    }

    public String getCityName() {
        return cityName;
    }

    public int getUserCount() {
        return userCount;
    }

    public int getLeafCount() {
        return leafCount;
    }


    @Override
    public String toString() {
        return cityName;
    }
}

