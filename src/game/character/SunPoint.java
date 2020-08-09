package game.character;

import game.Game;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.util.Duration;

import java.io.Serializable;

public class SunPoint extends Character implements GameObject,Serializable {

    private int value;

    public SunPoint(int posX, int posY, Label sunPoint, Game game){
        super(80);
        this.posX = posX;
        this.posY = posY;
        this.value = 50;
        img.setOnMouseClicked( e -> {
            Integer newValue = Integer.parseInt(sunPoint.getText())+50;
            sunPoint.setText(newValue.toString());
            AnchorPane pane = (AnchorPane)img.getParent();
            pane.getChildren().remove(img);
            game.setSunPoints(newValue);
        });
    }

    public void drop(){
        this.getImageView().setTranslateY(-50);

        Timeline down = new Timeline(new KeyFrame(
                Duration.millis(50),
                e -> {
                    this.getImageView().setTranslateY(this.getImageView().getTranslateY()+3);
                }
        ));
        down.setCycleCount(160);
        down.play();
    }

    @Override
    public String toString() {
        return "SunPoint";
    }
}
