package StickManHero;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class Cherry extends ImageView {
    public Cherry(){
        // Smoothing of cherry image is implemented
        Image cherry = new Image("cherry.png",30,30,true,true) ;
        setImage(cherry);
    }
    private int cherry_score ;

    public static int getCherry_score() {
        return 0;
    }


}
