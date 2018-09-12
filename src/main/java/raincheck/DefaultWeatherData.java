package raincheck;

/**
 * Created by Vibergf on 12/09/2018.
 *
 */
public class DefaultWeatherData implements IWeatherData{
    @Override
    public String getDescription() {
        return "--";
    }

    @Override
    public String getIconUrl() {
        return "default.png";
    }

    @Override
    public String getTemperature() {
        return "--";
    }

    @Override
    public String getWindSpeed() {
        return "--";
    }
}
