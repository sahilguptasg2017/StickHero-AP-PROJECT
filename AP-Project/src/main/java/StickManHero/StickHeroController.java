package StickManHero;

import com.almasb.fxgl.entity.action.Action;
import javafx.animation.*;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.transform.Rotate;
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
    @FXML
    public Label Score;
    @FXML
    public Label myScore;


    private static Stage stage;
    private static Scene scene;
    private static Parent root;
    private static int INT_MAX = 2000;
    private static Parent newSceneRoot;
    private static ArrayList<Rectangle> rectangles;
    private static Rectangle stick;
    private static boolean isMousePressed = false;
    private   Timeline timeline ;
    static int heroScore = 0;
    static int highScore = 0;
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
    private boolean isKeyPressed = false;
    @FXML
    public void clickmouse(javafx.scene.input.MouseEvent event) {

        isMousePressed = true;
        // Call a method to increase the size of the stick
        increaseStickSize();
    }
    private static Group G1 ;
    private static Hero h1 ;

    private static int curr_rectangle  = 0;

    private Media media ;

    private MediaPlayer mediaPlayer ;



    private Media media_1 ;
    private MediaPlayer mediaPlayer_1 ;
    @FXML
    public void unclickmouse(javafx.scene.input.MouseEvent event) {
        isMousePressed = false;
        // Stop the timeline to prevent further growth
        if (timeline != null) {
            timeline.stop();
        }
        // Call a method to make the stick horizontal
        makeStickHorizontal();

        curr_rectangle ++ ;
        double x1 = rectangles.get(curr_rectangle - 1).getX();
        double w1 = rectangles.get(curr_rectangle - 1).getWidth();
        double x2 = rectangles.get(curr_rectangle).getX() ;
        double w2 = rectangles.get(curr_rectangle).getWidth();
        double l = stick.getHeight();
        if (x2 > x1+w1+(l-3) || x2 + w2 < x1+w1+(l-3)){
            double heronewX = l + 20;
            TranslateTransition move_hero = new TranslateTransition(Duration.millis(2000),h1) ;
            move_hero.setByX(heronewX);
            move_hero.setOnFinished(endEvent->GameOver());
            move_hero.play();
            Score.setText("Score: ");
            // game-over
//            System.exit(0);
        }else{
            double heronewX = 300 + rectangles.get(curr_rectangle).getWidth() - rectangles.get(curr_rectangle - 1).getWidth();
            TranslateTransition move_hero = new TranslateTransition(Duration.millis(1000),h1) ;
            // System.out.println("sw");
            move_hero.setByX(heronewX);
            move_hero.setOnFinished(event1->transitions());
            move_hero.play();
            heroScore++;
            Score.setText("Score :" + heroScore);
        }
    }
    public void GameOver(){
        TranslateTransition translate = new TranslateTransition(Duration.millis(1000));
        translate.setToY(300f);
        translate.setAutoReverse(true);
        RotateTransition rotate = new RotateTransition(Duration.millis(1000));
        rotate.setByAngle(360f);
        PauseTransition pause = new PauseTransition(Duration.millis(5000));
        ParallelTransition seqT = new ParallelTransition (h1, translate, rotate, pause);
        seqT.play();
        if (heroScore > highScore) highScore = heroScore;

        try{
            Score.setText("Score :");
            endScene();
        }catch(Exception e){
            e.printStackTrace();
        }


    }
    @FXML
    public void endScene() throws IOException{
        FXMLLoader loader2 = new FXMLLoader(getClass().getResource("GameOverScene.fxml"));
        Parent rootOver = loader2.load();
        GameOverController newController = loader2.getController();
        newController.setScore(heroScore);
        newController.setHighScore(highScore);
        // Create new stage
        Stage gameOverStage = new Stage();
        Scene scene2 = new Scene(rootOver);
        gameOverStage.setTitle("Game Over");
        Image icon = new Image("icon.png");
        gameOverStage.getIcons().add(icon);
        gameOverStage.setScene(scene2);

        // Show the stage
        gameOverStage.show();

    }
    @FXML
    public void PlayAgain(ActionEvent event) throws IOException{
        // clear all nodes from previous scene
        rectangles.clear();
        G1.getChildren().clear();
        // re-start the game
        heroScore = 0;
        curr_rectangle = 0;
        game_maker();

        // To Remove the window where myScore label is there
        ((Stage) myScore.getScene().getWindow()).close();
    }
    private void transitions(){
        for (Rectangle rectangle : rectangles) {
            double newX = rectangle.getTranslateX() - 300 + rectangles.get(curr_rectangle - 1 ).getWidth() - rectangles.get(curr_rectangle).getWidth() ;
            TranslateTransition transition = new TranslateTransition(Duration.millis(1000),rectangle) ;
            transition.setToX(newX);
            transition.play();
        }

        double heronew1X = h1.getTranslateX() - 300 + rectangles.get(curr_rectangle -1).getWidth()  - rectangles.get(curr_rectangle).getWidth() ;
        TranslateTransition transition_2 = new TranslateTransition(Duration.millis(1000),h1) ;
        transition_2.setToX(heronew1X);
        transition_2.play();

        double sticknewX = stick.getTranslateX() - 300+ rectangles.get(curr_rectangle - 1).getWidth() - rectangles.get(curr_rectangle).getWidth() ;
        TranslateTransition transition_1 = new TranslateTransition(Duration.millis(1000),stick) ;
        transition_1.setToX(sticknewX);
        transition_1.play();

        Rectangle new_stick = new Rectangle() ;

        new_stick.setWidth(3);
        new_stick.setHeight(1);
        new_stick.setY(455);
        new_stick.setX(46 + rectangles.get(0).getWidth() / 2);

        G1.getChildren().add(new_stick) ;

        stick = new_stick ;
        String path_1 = "AP-Project\\src\\main\\java\\StickManHero\\success_sound.mp3";

        media_1 = new Media(new File(path_1).toURI().toString());

        // Instantiating MediaPlayer class
        mediaPlayer_1 = new MediaPlayer(media_1);

        // by setting this property to true, the audio will be played
        mediaPlayer_1.setAutoPlay(true);
    }

    private void increaseStickSize() {
        // Set up a timeline to increase the stick size continuously
        timeline = new Timeline(new KeyFrame(Duration.millis(8), event -> {
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
        String path = "AP-Project\\src\\main\\java\\StickManHero\\sound_1.mp3";

        // Instantiating Media class
        media = new Media(new File(path).toURI().toString());

        // Instantiating MediaPlayer class
        mediaPlayer = new MediaPlayer(media);

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
        G1 = new Group();
        for (int i = 0; i < getINT_MAX(); i++) {
            Random random = new Random();
            int width = 40 + random.nextInt(100);
            if (i == 0) {
                Rectangle r = new Rectangle(100, 200, Color.BLACK);
                r.setX(10);
                r.setY(456);
                rectangles.add(r);
                G1.getChildren().add(r);
            } else {
                Rectangle r = new Rectangle(width, 200, Color.BLACK);
                r.setX(i * 300);
                r.setY(456);
                rectangles.add(r);
                G1.getChildren().add(r);
            }
        }

        h1 = new Hero();
        h1.setFitWidth(40);
        h1.setFitHeight(50);
        h1.setY(406);
        h1.setX(9 + rectangles.get(0).getWidth() / 2);
        G1.getChildren().add(h1);

        stick = new Rectangle();

        stick.setWidth(3);
        stick.setHeight(1);
        stick.setY(455);
        stick.setX(58 + rectangles.get(0).getWidth() / 2);
        System.out.println("Initial x:" + stick.getX());
        System.out.println("Initial y:" + stick.getY());
        G1.getChildren().add(stick);
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
    @FXML
    private void goToHome(ActionEvent event) throws IOException {
        rectangles.clear();
        G1.getChildren().clear();
        // re-start the game
        heroScore = 0;
        curr_rectangle = 0;
        for(Stage stage: StickHeroApplication.openStages){
            stage.close();
        }
        StickHeroApplication.openStages.clear();

        // Close the current stage
//        currentStage.close();

        // Load the initial scene
        FXMLLoader loader = new FXMLLoader(getClass().getResource("StickHero.fxml"));
        Parent root = loader.load();

        // Get the current stage
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        StickHeroApplication.openStages.add(stage);
        // Create a new scene for fade-out transition
        Scene oldScene = stage.getScene();

        // Set up a fade-out transition for the old scene
        FadeTransition fadeOutTransition = new FadeTransition(Duration.millis(500), oldScene.getRoot());
        fadeOutTransition.setFromValue(1.0);
        fadeOutTransition.setToValue(0.0);

        // Set up a fade-in transition for the new scene
        FadeTransition fadeInTransition = new FadeTransition(Duration.millis(500), root);
        fadeInTransition.setFromValue(0.0);
        fadeInTransition.setToValue(1.0);

        // Combine fade-out and fade-in transitions
        SequentialTransition sequentialTransition = new SequentialTransition(fadeOutTransition, fadeInTransition);

        // Set the new scene with a fade-in transition
        sequentialTransition.setOnFinished(e -> {
            stage.setScene(new Scene(root));
            stage.show();
        });

        // Start the combined fade-out and fade-in transition
        sequentialTransition.play();
    }

    @Override
    public void onStartButtonClick() throws IOException {
    }
}
