package StickManHero;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.stage.Stage;

public class GameOverController extends StickHeroController{
    @FXML
    public Label gameOverScoreLabel;
    @FXML
    public Label gameHighScoreLabel;
    @FXML
    public Label CherryScoreLabel;
    @FXML
    public Label reviveMessageLabel;

    public StickHeroController parentController;

    public void setParentController(StickHeroController parentController){
        System.out.println("Parent controller set");
        this.parentController = parentController;
    }
    public void setScore(int score) {
        gameOverScoreLabel.setText("" + score);
    }
    public void setHighScore(int score){
        gameHighScoreLabel.setText("" + score);
    }
    public void setCherryScore(int score){
        CherryScoreLabel.setText("Cherry : " + score);
    }
    public void setReviveMessage(int score){
        if (score < reviveCherries) {
            if (score == 1) {
                reviveMessageLabel.setText("Oops!! Insufficient cherry");
            } else {
                reviveMessageLabel.setText("Oops!! Insufficient cherries");
            }
        }
    }

    public void updateCherryScoreInParent(int newCherryScore){
        if (parentController != null){
            parentController.updateCherryScore(newCherryScore);
        }
    }


}
