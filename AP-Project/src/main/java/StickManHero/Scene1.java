package StickManHero;

import javafx.application.Application;
import javafx.stage.Stage;

public class Scene1 extends Application {
    private int isCollision;

    public int getIsCollision() {
        return isCollision;
    }

    public void setIsCollision(int isCollision) {
        this.isCollision = isCollision;
    }

    public static void main(String[] args) {
        launch();
    }

    @Override
    public void start(Stage stage) throws Exception {

    }
}
