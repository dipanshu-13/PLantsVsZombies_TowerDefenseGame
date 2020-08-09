package game.character;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.util.Duration;
import java.io.Serializable;
import java.util.ArrayList;

public class LawnMower extends Character implements GameObject, Serializable {

    private int speed;

    public LawnMower(int posX, int posY){
        super(70);
        this.posX = posX;
        this.posY = posY;
        this.speed = 4;
    }

    public void init(ArrayList<Zombie> laneZombies){
        Timeline checker = new Timeline();
        KeyFrame keyFrame = new KeyFrame(
            Duration.millis(50),
            e ->{
                for (int j  = laneZombies.size()-1; j >= 0; j--){
                    if (getImageView().getBoundsInParent().intersects(laneZombies.get(j).getImageView().getBoundsInParent())){
                        System.out.println("LawnMower collision");
                        this.killingSpree(laneZombies);
                        checker.stop();
                    }
                }
            }
        );
        checker.getKeyFrames().add(keyFrame);
        checker.setCycleCount(Animation.INDEFINITE);
        checker.play();
    }

    private void killingSpree(ArrayList<Zombie> laneZombies){
        Timeline runOver = new Timeline();
        runOver.setCycleCount(220);
        KeyFrame keyFrame = new KeyFrame(
                Duration.millis(50),
                e -> {
                    for (Zombie zombie: laneZombies) {
                        if(getImageView().getBoundsInParent().intersects(zombie.getImageView().getBoundsInParent())){
                            System.out.println("Zombie died.");
                            AnchorPane pane = (AnchorPane)getImageView().getParent();
                            pane.getChildren().remove(zombie.getImageView());
                            laneZombies.remove(zombie);
                        }
                    }
                    getImageView().setTranslateX(getImageView().getTranslateX()+speed);

                });
        runOver.getKeyFrames().add(keyFrame);
        runOver.play();
    }

    @Override
    public String toString() {
        return "LawnMower";
    }
}
