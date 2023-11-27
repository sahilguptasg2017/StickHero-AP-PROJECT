package StickManHero;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class Cherry extends ImageView {

    private int count ;

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public void incCount(int count){

        this.count += count ;
    }


    public void decCount(int count){

        this.count -= count ;
    }

    public Cherry(){
        Image cherry = new Image("cherry.png") ;
        setImage(cherry);
    }


}
