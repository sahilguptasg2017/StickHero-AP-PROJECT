package StickManHero;

import javafx.application.Application;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import java.util.Random;

public class Towers extends Application {
    private int towerWidth = 0;
    private int towerHeight = 0;

    public int getTowerHeight() {
        return towerHeight;
    }

    public void setTowerHeight(int towerHeight) {
        this.towerHeight = towerHeight;
    }

    public int getTowerWidth() {
        return towerWidth;
    }

    public void setTowerWidth(int towerWidth) {
        this.towerWidth = towerWidth;
    }
    // Randomly selects a distance and generate a tower at a random distance
    public void generateTowers(){

    }
    @Override
    public void start(Stage stage) throws Exception {
        stage.setTitle("Random towers");
        Pane root = new Pane();

    }

    public static void main(String[] args) {
        launch();
    }
}
