package StickManHero;

import com.almasb.fxgl.entity.action.Action;
import javafx.animation.*;
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
import javafx.scene.transform.Rotate;
import javafx.scene.transform.Translate;
import javafx.stage.Stage;
import javafx.util.Duration;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Objects;
import java.util.Random;

public class StickHeroController implements Controller {
    @FXML
    private static Label welcomeText;
    private static Label exitText;

    private static Stage stage;
    private static Scene scene;
    private static Parent root;
    private static int INT_MAX = 2000;

    private static Parent newSceneRoot;
    private static ArrayList<Rectangle> rectangles;
    private static int current_rectangle = 0;
    private static Rectangle stick;
    private static boolean isMousePressed = false;

    private   Timeline timeline ;
    public ArrayList<Towers> arr;
    public Group G1;
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


    public Timeline getTimeline() {
        return timeline;
    }

    public void setTimeline(Timeline timeline) {
        this.timeline = timeline;
    }

    @FXML
    public void clickmouse(javafx.scene.input.MouseEvent event) {

        isMousePressed = true;
        // Call a method to increase the size of the stick
        increaseStickSize();
    }

    @FXML
    public void unclickmouse(javafx.scene.input.MouseEvent event) {
        isMousePressed = false;
        // Stop the timeline to prevent further growth
        if (timeline != null) {
            timeline.stop();
        }
        if (arr == null){
            game_maker();
        }
        // Call a method to make the stick horizontal
        makeStickHorizontal();
        translateTowers();
        resetStick();
    }
    private void translateTowers() {
        Towers t;
        for (int i = 0; i < arr.size(); ++i) {
            t = arr.get(i);
            if (i == 0) {
                translateTower(t, -290);
            } else {
                translateTower(t, -300);
            }
        }
    }

    private void translateTower(Towers tower, double deltaX) {
        TranslateTransition translate = new TranslateTransition(Duration.seconds(5), tower);
        translate.setByX(deltaX);
        translate.play();
    }
    private void resetStick() {
        stick.setWidth(3);
        stick.setHeight(0);
        stick.setY(456);
        stick.setX(56 + arr.get(0).getWidth() / 2);
    }

    private void increaseStickSize() {
        // Set up a timeline to increase the stick size continuously
        timeline = new Timeline(new KeyFrame(Duration.millis(16), event -> {
            double newHeight = stick.getHeight() + 2; // Adjust the rate of growth as needed
            stick.setHeight(newHeight);

            double newY = stick.getY() - 2; // Adjust the rate of movement as needed
            stick.setY(newY);
        }));
        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.play();
    }




    private void makeStickHorizontal() {
        // Add animation to make the stick horizontal
        Rotate r1 = new Rotate(0, stick.getX(), stick.getY() + stick.getHeight());
        stick.getTransforms().clear();
        stick.getTransforms().add(r1);
        KeyValue k1 = new KeyValue(r1.angleProperty(), 90);
        KeyFrame k2 = new KeyFrame(Duration.millis(300), k1);
        Timeline t1 = new Timeline(k2);
        t1.play();
    }


    @FXML
    public void onStartButtonClick(ActionEvent event) throws IOException {
        String path = "AP-Project\\src\\main\\java\\StickManHero\\game_sound.mp3";

        // Instantiating Media class
        Media media = new Media(new File(path).toURI().toString());

        // Instantiating MediaPlayer class
        MediaPlayer mediaPlayer = new MediaPlayer(media);

        // by setting this property to true, the audio will be played
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

        game_maker();

        // Set separate event handlers for mouse pressed and released

    }

    public void game_maker() {
        rectangles = new ArrayList<>();
        arr = new ArrayList<>();
        Towers t;
        G1 = new Group();
        for (int i = 0; i < getINT_MAX(); i++) {
            Random random = new Random();
            int width = 40 + random.nextInt(100);
            if (i == 0) {
//                Rectangle r = new Rectangle(100, 200, Color.BLACK);
                t = new Towers(100, 200);
                t.setX(10);
                t.setY(456);
                arr.add(t);
//                rectangles.add(r);
                G1.getChildren().add(t);
            } else {
//                Rectangle r = new Rectangle(width, 200, Color.BLACK);
                t = new Towers(width, 200);
                t.setX(i * 300);
                t.setY(456);
//                rectangles.add(r);
                arr.add(t);
                G1.getChildren().add(t);
            }
        }

        Hero h1 = new Hero();
        h1.setFitWidth(40);
        h1.setFitHeight(50);
        h1.setY(406);
//        h1.setX(15 + rectangles.get(0).getWidth() / 2);
        h1.setX(15 + arr.get(0).getWidth() / 2);
        G1.getChildren().add(h1);
        stick = new Rectangle();

        stick.setWidth(3);
        stick.setHeight(100);
        stick.setY(356);
//        stick.setX(56 + rectangles.get(0).getWidth() / 2);
        stick.setX(56 + arr.get(0).getWidth() / 2);

        G1.getChildren().add(stick);
        ((Pane) newSceneRoot).getChildren().add(G1);
        ((Pane) newSceneRoot).getChildren().add(G1);
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
