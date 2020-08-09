package game.character;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;

public class ConeZombie extends Zombie implements GameObject {

    public ConeZombie(int X, int Y){
        super(X,Y,200, 30, 1);
        image = new Image(getClass().getResource("imgcharacter/ConeZombie.gif").toExternalForm(),100, 100, true, true);
        img = new ImageView(image);
    }

    @Override
    public String toString() {
        return "ConeZombie";
    }

}
