package raincheck;

import com.google.api.client.http.GenericUrl;
import com.google.api.client.http.HttpRequest;
import com.google.api.client.http.HttpRequestFactory;
import com.google.api.client.http.HttpResponse;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.JsonObjectParser;
import com.google.api.client.json.jackson.JacksonFactory;

import java.io.IOException;

/**
 * Created by Vibergf on 12/09/2018.
 */
class HttpWrapper {

    private HttpRequestFactory httpRequestFactory;
    private JsonFactory jsonFactory;

    HttpWrapper(){
        httpRequestFactory = new NetHttpTransport().createRequestFactory();
        jsonFactory = new JacksonFactory();
    }

    IWeatherData requestWeatherData(int cityId) {
        IWeatherData weatherData;

        try {
            HttpRequest httpRequest = httpRequestFactory.buildGetRequest(new GenericUrl(
                    "http://api.openweathermap.org/data/2.5/weather?id=" + cityId + "&units=metric&appid=91d97f95ed84b41f7f57683edb2cf6e0"));
            httpRequest.setParser(new JsonObjectParser(jsonFactory));
            HttpResponse httpResponse = httpRequest.execute();
            try {
                weatherData = httpResponse.parseAs(OpenWeatherData.class);
            } finally {
                httpResponse.disconnect();
            }

        } catch (IOException e) {
            weatherData = new DefaultWeatherData();
        }

        return weatherData;
    }

    IWeatherData requestDefaultWeatherData(){
        return new DefaultWeatherData();
    }
}
