package StickManHero;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class GameOverController extends StickHeroController{
    @FXML
    public Label gameOverScoreLabel;
    @FXML
    public Label gameHighScoreLabel;
    public void setScore(int score) {
        gameOverScoreLabel.setText("" + score);
    }
    public void setHighScore(int score){
        gameHighScoreLabel.setText("" + score);
    }
}
