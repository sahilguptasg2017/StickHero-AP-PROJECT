package StickManHero;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class GameOverController extends StickHeroController{
    @FXML
    public Label gameOverScoreLabel;

    public void setScore(int score) {
        gameOverScoreLabel.setText("" + score);
    }
}
