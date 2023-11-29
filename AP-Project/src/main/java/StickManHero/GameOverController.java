package StickManHero;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class GameOverController extends StickHeroController{
    @FXML
    public Label gameOverScoreLabel;
    @FXML
    public Label gameHighScoreLabel;
    @FXML
    public Label CherryScoreLabel;
    public void setScore(int score) {
        gameOverScoreLabel.setText("" + score);
    }
    public void setHighScore(int score){
        gameHighScoreLabel.setText("" + score);
    }
    public void setCherryScore(int score){
        CherryScoreLabel.setText("Cherry : " + score);
    }
}
