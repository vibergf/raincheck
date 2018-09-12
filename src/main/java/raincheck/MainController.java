package raincheck;

import com.google.gson.Gson;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by Vibergf on 12/09/2018.
 *
 * The JavaFX controller class for mainView.fxml.
 * Since that is our only view, this class contains the bulk of the application.
 */
public class MainController {

    @FXML private ImageView iconImageView;
    @FXML private Label temperatureLabel;
    @FXML private Label descriptionLabel;
    @FXML private Label windSpeedLabel;

    @FXML private ComboBox<CityData> cityComboBox;
    @FXML private Button checkButton;

    private HttpWrapper httpWrapper;

    public MainController(){
        httpWrapper = new HttpWrapper();
    }

    @FXML
    private void initialize() {

        CityData[] cities = readCityFile("target/classes/city.list.json");
        cityComboBox.getItems().addAll(cities);
        cityComboBox.setValue(cityComboBox.getItems().get(0));
        checkButton.setText("Check");

        updateWeatherData(httpWrapper.requestDefaultWeatherData());
    }

    private void updateWeatherData(IWeatherData newData){
        temperatureLabel.setText(newData.getTemperature() + " \u00b0C");
        windSpeedLabel.setText(newData.getWindSpeed() + " m/s");

        try {
            iconImageView.setImage(new Image(newData.getIconUrl()));
        } catch(IllegalArgumentException e) {
            e.printStackTrace();
            //do nothing (keep the old image) if no image was found
        }
        //Capitalize description
        descriptionLabel.setText(newData.getDescription().substring(0, 1).toUpperCase() + newData.getDescription().substring(1));
    }

    @FXML
    private void checkButtonPressed(){
        updateWeatherData(httpWrapper.requestWeatherData(cityComboBox.getValue().getId()));
    }

    private CityData[] readCityFile(String path){
        Gson gson = new Gson();
        String citiesString = "";

        try {
            BufferedReader in = new BufferedReader(
                    new FileReader(path));
            StringBuilder input = new StringBuilder();
            String line;

            while ((line = in.readLine()) != null)
                input.append(line);

            citiesString = input.toString();
            in.close();
        } catch (IOException e) {
            e.printStackTrace();
            //If we can't find the city file, no cities will be added.
        }

        CityData[] cities = gson.fromJson(citiesString, CityData[].class);

        //For this application we are only interested in Swedish cities
        List<CityData> filteredCities = new ArrayList<>();
        for(CityData c : cities)
            if(c.getCountry().equals("SE"))
                filteredCities.add(c);

        //We also want to sort the list
        Collections.sort(filteredCities);

        return filteredCities.toArray(new CityData[0]);
    }
}
