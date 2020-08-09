package game.character;

import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;

public class Squasher extends Plant implements GameObject  {

    static Squasher instantiate(int posX, int posY){
        return new Squasher(posX,posY);
    }

    private Squasher(int posX, int posY) {
        super(posX, posY, 120,50, 8000);
    }

    @Override
    public String toString() {
        return "Squasher";
    }
}
