package StickManHero;

import com.almasb.fxgl.entity.action.Action;
import javafx.animation.FadeTransition;
import javafx.animation.SequentialTransition;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import javafx.util.Duration;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Objects;
import java.util.Random;

public class StickHeroController implements Controller {
    @FXML
    private static Label welcomeText;
    private static Label exitText ;


    private static Stage stage ;

    private static Scene scene ;

    private static Parent root ;

    private static int INT_MAX = 2000 ;

    public int getINT_MAX() {
        return INT_MAX;
    }

    public void setExitText(Label exitText) {
        this.exitText = exitText;
    }

    public Label getExitText() {
        return exitText;
    }

    public void setINT_MAX(int INT_MAX) {
        this.INT_MAX = INT_MAX;
    }

    public Label getWelcomeText() {
        return welcomeText;
    }

    public void setWelcomeText(Label welcomeText) {
        this.welcomeText = welcomeText;
    }

    public Parent getRoot() {
        return root;
    }

    public void setRoot(Parent root) {
        this.root = root;
    }

    public Scene getScene() {
        return scene;
    }

    public void setScene(Scene scene) {
        this.scene = scene;
    }

    public Stage getStage() {
        return stage;
    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }
    private static  Parent newSceneRoot ;


    private static  ArrayList<Rectangle> rectangles ;

    private static int current_rectangle = 0 ;


    @FXML
    public void onStartButtonClick(ActionEvent event) throws IOException  {
        String path = "AP-Project\\src\\main\\java\\StickManHero\\game_sound.mp3";

        //Instantiating Media class
        Media media = new Media(new File(path).toURI().toString());

        //Instantiating MediaPlayer class
        MediaPlayer mediaPlayer = new MediaPlayer(media);

        //by setting this property to true, the audio will be played
        mediaPlayer.setAutoPlay(true);

        mediaPlayer.setCycleCount(MediaPlayer.INDEFINITE);

        // Load the new scene
        newSceneRoot = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("Scene-1.fxml")));

        // Get the current stage
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

        // Create a new scene for fade-out transition
        Scene oldScene = stage.getScene();

        // Set up a fade-out transition for the old scene
        FadeTransition fadeOutTransition = new FadeTransition(Duration.millis(500), oldScene.getRoot());
        fadeOutTransition.setFromValue(1.0);
        fadeOutTransition.setToValue(0.0);

        // Set up a fade-in transition for the new scene
        FadeTransition fadeInTransition = new FadeTransition(Duration.millis(500), newSceneRoot);
        fadeInTransition.setFromValue(0.0);
        fadeInTransition.setToValue(1.0);

        // Combine fade-out and fade-in transitions
        SequentialTransition sequentialTransition = new SequentialTransition(fadeOutTransition, fadeInTransition);

        // Set the new scene with a fade-in transition
        sequentialTransition.setOnFinished(e -> {
            stage.setScene(new Scene(newSceneRoot));
            stage.show();
        });

        // Start the combined fade-out and fade-in transition
        sequentialTransition.play();


//        Rectangle r1 = new Rectangle(100,200, Color.BLACK) ;
//
//        Group G1  = new Group() ;
//
//        G1.getChildren().add(r1) ;
//
//        ((Pane)newSceneRoot).getChildren().add(G1) ;



        game_maker();




    }

    public void game_maker(){

        rectangles = new ArrayList<Rectangle>() ;

        Group G1 = new Group() ;
        for(int i=0;i<getINT_MAX();i++){
            Random random = new Random() ;
            int width = 40+random.nextInt(100) ;
            Rectangle r = new Rectangle(width,200,Color.BLACK) ;
            if(i == 0){
                r.setX(10);

                r.setY(456);
            }
            else {
                r.setX(i * 300);
                r.setY(456);
            }
            rectangles.add(r) ;
            G1.getChildren().add(r) ;
        }

        Hero h1 = new Hero() ;
        h1.setFitWidth(40);
        h1.setFitHeight(50);
        h1.setY(406);
        h1.setX(10) ;
        G1.getChildren().add(h1) ;

        Rectangle stick = new Rectangle() ;

        stick.setWidth(10);
        stick.setHeight(100);
        stick.setY(453) ;
        stick.setX(40);


        G1.getChildren().add(stick) ;



        ((Pane)newSceneRoot).getChildren().add(G1) ;


    }


    @FXML
    public void showExitConfirmationDialog() {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Exit Confirmation");
        alert.setHeaderText(null);
        alert.setContentText("Are you sure you want to exit?");

        // Customize the buttons
        ButtonType yesButton = new ButtonType("Yes");
        ButtonType noButton = new ButtonType("No");

        alert.getButtonTypes().setAll(yesButton, noButton);

        // Show the dialog and wait for a result
        alert.showAndWait().ifPresent(buttonType -> {
            if (buttonType == yesButton) {
                // User clicked "Yes"
                System.out.println("Thank-you for playing StickHero");
                System.exit(0); // Exit the application
            } else if (buttonType == noButton) {
                // User clicked "No" or closed the dialog
                alert.close();
            }
        });
    }

    @Override
    public void onStartButtonClick() throws IOException {

    }


}