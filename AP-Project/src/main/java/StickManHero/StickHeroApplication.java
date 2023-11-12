package StickManHero;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;

public class StickHeroApplication extends Application {
    private int x;
//    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(StickHeroApplication.class.getResource("StickHero.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 772, 772);
        stage.setTitle("StickHero Game");

        // Added Icon for the Application
        Image icon = new Image("hero.png");
        stage.getIcons().add(icon);
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();                             // launch() method is a static method
    }
}