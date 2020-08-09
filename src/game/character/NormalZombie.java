package game.character;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;

public class NormalZombie extends Zombie {

    public NormalZombie(int X, int Y){
        super(X,Y,200, 30, 1);
    }

    @Override
    public String toString() {
        return "NormalZombie";
    }
}
