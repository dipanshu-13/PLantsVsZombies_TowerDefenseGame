package game.character;

import game.exception.DestroyException;
import game.exception.ZombieKillException;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.util.Duration;
import javax.security.auth.DestroyFailedException;
import java.io.Serializable;
import java.util.ArrayList;

public class Pea extends Character implements GameObject, Serializable {

    private final int damage;
    private final int speed;
    private boolean isUsed;

    public Pea(int posX, int posY){
        super(25);
        this.posX = posX+251;
        this.posY = posY+61;
        this.damage = 20;
        this.speed = 10;
        this.isUsed = false;
    }

    public boolean isUsed() {
        return isUsed;
    }

    public void shoot(ArrayList< ArrayList<Zombie> > zombies){
        Timeline shot = new Timeline();
        shot.setCycleCount(150);
        KeyFrame keyFrame = new KeyFrame(
            Duration.millis(50),
            e -> {
                boolean hit = false;
                for (ArrayList<Zombie> i: zombies) {
                    for (Zombie zombie: i) {
                        if(getImageView().getBoundsInParent().intersects(zombie.getImageView().getBoundsInParent())){
                            hit = true;
                            System.out.println("Zombie got hit.");
                            AnchorPane pane = (AnchorPane)getImageView().getParent();
                            pane.getChildren().remove(this.getImageView());
                            if (zombie.getHit(getDamage())){
                                System.out.println("Zombie died.");
                                pane.getChildren().remove(zombie.getImageView());
                                i.remove(zombie);
                            }
                            this.isUsed = true;
                            shot.stop();
                        }
                        if (!hit)
                            break;
                    }
                }
                if (!hit){
                    getImageView().setTranslateX(getImageView().getTranslateX()+speed);
                }
            });
        shot.getKeyFrames().add(keyFrame);
        shot.play();
    }

    @Override
    public String toString() {
        return "Pea";
    }

    public int getDamage() {
        return damage;
    }

}
