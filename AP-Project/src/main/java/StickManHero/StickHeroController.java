package StickManHero;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class StickHeroController {
    @FXML
    private Label welcomeText;

    @FXML
    protected void onHelloButtonClick() {
        welcomeText.setText("Welcome to JavaFX Application!");
    }
    @FXML
    protected void onStartButtonClick(){



    }
    @FXML
    protected void onExitButtonClick(){


    }


}