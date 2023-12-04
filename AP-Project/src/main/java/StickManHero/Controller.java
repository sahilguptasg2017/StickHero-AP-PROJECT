package StickManHero;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public interface Controller {
    void initialize(URL url, ResourceBundle resourceBundle);

    public void showExitConfirmationDialog() ;
    public void onStartButtonClick() throws IOException;

}
