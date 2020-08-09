package game.character;

import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.shape.Circle;

public class CherryBomb extends Plant implements GameObject {

    static CherryBomb instantiate(int posX, int posY){
        return new CherryBomb(posX,posY);
    }

    private CherryBomb(int posX, int posY) {
        super(posX, posY,100, 150, 10000);
    }

    @Override
    public String toString() {
        return "CherryBomb";
    }

    public Circle explode(){
        Circle range = new Circle(getPosX(),getPosY(),100);
        return range;
    }

}
