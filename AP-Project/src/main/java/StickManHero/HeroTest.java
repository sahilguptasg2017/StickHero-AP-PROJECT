package StickManHero;

import org.junit.Test;

import static org.junit.Assert.assertEquals ;
import static org.junit.Assert.assertNotEquals;

public class HeroTest{

    @Test
    public void heroscoretest(){
        assertNotEquals(1,Hero.getHero_score());

    }
    @Test
    public void cherryscoretest(){
        assertNotEquals(1,Cherry.getCherry_score());

    }

    @Test
    public void sticklength(){
        assertEquals(1,Stick.getLength()) ;
    }
    @Test
    public void stickWidth(){
        assertEquals(3,Stick.getWidth()) ;
    }

}