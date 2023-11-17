package StickManHero;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class Hero extends Application implements MainHero {
    private static final int WIDTH = 980;
    private static final int HEIGHT = 780;
    private int score = 0;                  // score when hero jumps from one tower to another
    private int speed = 10;                 // speed at which hero runs
    private int highScore = 0;

    public int getHighScore() {
        return highScore;
    }

    public void setHighScore(int highScore) {
        this.highScore = highScore;
    }
    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    @Override
    public void start(Stage stage) throws Exception {
        Pane root = new Pane();
        Scene scene = new Scene(root, WIDTH, HEIGHT);

        Image image = new Image("hero_style1.png");
        ImageView imageView = new ImageView(image);
        imageView.setX(400);
        imageView.setY(400);
        imageView.setFitWidth(100);
        imageView.setFitHeight(100);
        root.getChildren().add(imageView);
        stage.setScene(scene);
        stage.show();
    }

    public void makeStick(){
        //code
    }

    public void run(int distance){          // distance = stickLength
        // code
    }

    public static void main(String[] args) {
        launch();
    }
}

