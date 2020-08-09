package game.character;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.util.Duration;
import java.io.Serializable;

public abstract class Zombie extends Character implements Serializable,GameObject {

    protected int health;
    protected final int damage;
    protected int speed;

    public Zombie(int posX, int posY, int health, int damage, int speed){
        super(100);
        this.posX = posX;
        this.posY = posY;
        this.health = health;
        this.damage = damage;
        this.speed = speed;
    }

    public static Zombie createZombie(String className, int lane){
        if (className.equals("NormalZombie")){
            return new NormalZombie(890, 60+(lane-1)*100);
        }
        else if(className.equals("ConeZombie")){
            return new ConeZombie(890, 60+(lane-1)*100);
        }
        else
            return null;
    }

    public void setTranslateX(int posX) {
        this.posX = posX;
        this.getImageView().setX(posX);
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public boolean getHit(int damage){
        this.health = this.health-damage;
        return this.health <= 0;
    }

    public void moveAfter(int seconds){
        Timeline walk = new Timeline();
        this.getImageView().setTranslateX(490);
        walk.setDelay(Duration.seconds(seconds));
        walk.setCycleCount(Animation.INDEFINITE);
        KeyFrame keyFrame = new KeyFrame(
                Duration.millis(75),
                e ->{
                    ImageView img = this.getImageView();
                    img.setTranslateX(img.getTranslateX()-speed);
                    this.setTranslateX((int)img.getTranslateX());
        });
        walk.getKeyFrames().add(keyFrame);
        walk.play();
    }

    public Image getImage() {
        return image;
    }

    public int getHealth() {
        return health;
    }

    public int getDamage() {
        return damage;
    }

    public int getSpeed() {
        return speed;
    }
}
