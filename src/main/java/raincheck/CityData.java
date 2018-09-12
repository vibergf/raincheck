package raincheck;

/**
 * Created by Vibergf on 12/09/2018.
 *
 * Data class to represent a city read from the list provided by OpenWeather
 */
public class CityData implements Comparable<CityData> {

    private String name;
    private String country;
    private int id;

    public String getName() {
        return name;
    }

    public String getCountry() {
        return country;
    }

    public int getId() {
        return id;
    }

    public String toString(){
        return name;
    }

    @Override
    public int compareTo(CityData o) {
        return name.compareTo(o.getName());
    }
}
