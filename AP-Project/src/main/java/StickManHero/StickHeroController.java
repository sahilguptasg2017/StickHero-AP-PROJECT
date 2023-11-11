package StickManHero;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;

public class StickHeroController {
    @FXML
    private Label welcomeText;
    private Label exitText ;

    @FXML
    protected void onHelloButtonClick() {
        welcomeText.setText("Welcome to JavaFX Application!");
    }
    @FXML
    protected void onStartButtonClick(){




    }
    @FXML
    private void showExitConfirmationDialog() {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Exit Confirmation");
        alert.setHeaderText(null);
        alert.setContentText("Are you sure you want to exit?");

        // Customize the buttons
        ButtonType yesButton = new ButtonType("Yes");
        ButtonType noButton = new ButtonType("No");

        alert.getButtonTypes().setAll(yesButton, noButton);

        // Show the dialog and wait for a result
        alert.showAndWait().ifPresent(buttonType -> {
            if (buttonType == yesButton) {
                // User clicked "Yes"
                System.out.println("Thank-you for playing StickHero");
                System.exit(0); // Exit the application
            } else if (buttonType == noButton) {
                // User clicked "No" or closed the dialog
                alert.close();
            }
        });
    }


}