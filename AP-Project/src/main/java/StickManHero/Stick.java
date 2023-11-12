package StickManHero;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import javafx.util.Duration;

public class Stick extends Application {
    private static final int WIDTH = 772;
    private static final int HEIGHT = 772;
    private static final int RECTANGLE_WIDTH = 3;
    private static final int RECTANGLE_HEIGHT = 100;
    private static final int INCREMENT = 5; // Width increment on each step

    private Rectangle rectangle;
    private Timeline timeline;

    public void start(Stage primaryStage) {
        primaryStage.setTitle("Stick Increase");

        Pane root = new Pane();
        Scene scene = new Scene(root, WIDTH, HEIGHT);

        rectangle = new Rectangle(RECTANGLE_WIDTH, RECTANGLE_HEIGHT, Color.BLACK);
        root.getChildren().add(rectangle);
        rectangle.relocate(WIDTH/2,HEIGHT/2);
//        rectangle.relocate(HEIGHT/2,0);

        // Set up a timeline to continuously increase width when the mouse is pressed
        timeline = new Timeline(new KeyFrame(Duration.millis(50), event -> increaseHeight()));
        timeline.setCycleCount(Timeline.INDEFINITE); // Run indefinitely

        // Start the timeline on mouse press
        scene.setOnMousePressed(event -> timeline.play());

        // Stop the timeline on mouse release
        scene.setOnMouseReleased(event -> timeline.pause());

        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void increaseHeight() {
        double newHeight = rectangle.getHeight() + INCREMENT;
        rectangle.setHeight(newHeight);

        double newY = rectangle.getY() - INCREMENT ;
        rectangle.setY(newY);

    }

    public static void main(String[] args) {
        launch(args);
    }
}
