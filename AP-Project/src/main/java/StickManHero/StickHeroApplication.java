package StickManHero;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;

// WE USED THREADS TO CONTROL THE HERO MOTION

public class StickHeroApplication extends Application {
    private int x;
    private static final int HEIGHT = 630;
    private static final int WIDTH = 800;

    public static ArrayList<Stage> openStages = new ArrayList<>();
    //    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(StickHeroApplication.class.getResource("StickHero.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("StickHero Game");
        openStages.add(stage);

        // Added Icon for the Application
        Image icon = new Image("icon.png");
        stage.getIcons().add(icon);
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();                             // launch() method is a static method
    }
}