import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * Created by Vibergf on 12/09/2018.
 */
public class Main extends Application{

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("mainView.fxml"));
        Scene scene = new Scene(root);

        stage.setTitle("Rain Check");
        stage.setScene(scene);
        stage.show();
    }
}
