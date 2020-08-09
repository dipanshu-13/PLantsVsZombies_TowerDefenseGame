package game.character;

import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;

public class Repeater extends Plant implements GameObject  {

    static Repeater instantiate(int posX, int posY){
        return new Repeater(posX,posY);
    }

    private Repeater(int posX, int posY) {
        super(posX, posY, 120,200, 5000);
    }

    @Override
    public String toString() {
        return "Repeater";
    }

    public Pea shoot(){
        return new Pea(this.posX, this.posY);
    }
}
