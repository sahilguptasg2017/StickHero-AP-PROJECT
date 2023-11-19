package StickManHero;

import javafx.application.Application;
import javafx.stage.Stage;

public class Scene1 extends Application {
    private boolean isCollision;
    Hero hero;

    public boolean getIsCollision() {
        return isCollision;
    }

    public void setIsCollision(boolean isCollision) {
        this.isCollision = isCollision;
    }

    public static void main(String[] args) {
        launch();
    }

    @Override
    public void start(Stage stage) throws Exception {

    }
}
