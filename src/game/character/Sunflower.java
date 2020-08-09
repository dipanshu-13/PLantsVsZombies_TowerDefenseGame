package game.character;

import game.Game;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;

public class Sunflower extends Plant implements GameObject {

    static Sunflower instantiate(int posX, int posY){
        return new Sunflower(posX,posY);
    }

    private Sunflower(int posX, int posY) {
        super(posX, posY, 120, 50, 3000);
        image = new Image(getClass().getResource("imgcharacter/Sunflower.gif").toExternalForm(), 70, 70, true, true);
        img = new ImageView(image);
    }

    @Override
    public String toString() {
        return "Sunflower";
    }

    public SunPoint produce(Label sunPoint, Game game){
        return new SunPoint(getPosX()+231,getPosY()+61,sunPoint, game);
    }

    @Override
    public void loadGraphics(int size) {
        image = new Image(getClass().getResource("imgcharacter/Sunflower.gif").toExternalForm(), size, size, true, true);
        img = new ImageView(image);
    }
}
