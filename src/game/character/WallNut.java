package game.character;

import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;

public class WallNut extends Plant implements GameObject  {

    static WallNut instantiate(int posX, int posY){
        return new WallNut(posX,posY);
    }
    
    private WallNut(int posX, int posY) {
        super(posX, posY, 1600, 50, 8000);
    }

    @Override
    public String toString() {
        return "WallNut";
    }
}
