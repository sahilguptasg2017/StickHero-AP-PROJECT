package StickManHero;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.util.Objects;

// This class implements Singleton Design pattern
public class Hero extends ImageView implements MainHero {

    // The single instance of the Hero
    private static Hero instance;

    // The image to be used for the Hero
    private Image heroImage;

    // Private constructor to prevent instantiation from outside
    private Hero() {
        // Load the image during the first instantiation
        heroImage = new Image("hero_style1.png");
    }

    // Public method to get the single instance of Hero
    public static Hero getInstance() {
        if (instance == null) {
            instance = new Hero();
        }
        return instance;
    }

    // Public method to get the ImageView for the Hero
    public ImageView getImageView() {
        return new ImageView(heroImage);
    }

    @Override
    public void makeStick() {
        System.out.println("Hero is Making stick");
    }

    @Override
    public void run() {
        System.out.println("Hero is Running");
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Hero hero = (Hero) o;

        return Objects.equals(heroImage, hero.heroImage);
    }

    @Override
    public int hashCode() {
        return heroImage != null ? heroImage.hashCode() : 0;
    }
    private int hero_score ;

    public static int getHero_score() {
        return 0 ;
    }


}

