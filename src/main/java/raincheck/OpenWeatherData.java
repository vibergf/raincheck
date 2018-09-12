package raincheck;

import com.google.api.client.util.Key;

/**
 * Created by Vibergf on 12/09/2018.
 *
 * Data class to represent the required information from the JSON response from OpenWeather.
 */
public class OpenWeatherData implements IWeatherData{

    @Key
    private Weather[] weather;
    @Key
    private Main main;
    @Key
    private Wind wind;

    public String getDescription(){
        return weather[0].description;
    }

    public String getIconUrl(){
        return "https://openweathermap.org/img/w/" + weather[0].icon + ".png";
    }

    public String getTemperature(){
        return "" + main.temperature;
    }

    public String getWindSpeed(){
        return "" + wind.windSpeed;
    }

    public static class Weather {

        @Key
        private String description;
        @Key
        private String icon;

    }

    public static class Main {

        @Key("temp")
        private int temperature;
    }

    public static class Wind {

        @Key("speed")
        private int windSpeed;
    }
}
