package game.character;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class PeaShooter extends Plant{

    static PeaShooter instantiate(int posX, int posY){
        return new PeaShooter(posX,posY);
    }

    private PeaShooter(int posX, int posY){
        super(posX,posY,120, 100, 3000);
        image = new Image(getClass().getResource("imgcharacter/PeaShooterShooting.gif").toExternalForm(), 70, 70, true, true);
        img = new ImageView(image);
    }

    public Pea shoot(){
        return new Pea(this.posX, this.posY);
    }

    @Override
    public String toString(){
        return "PeaShooter";
    }

    @Override
    public void loadGraphics(int size) {
        image = new Image(getClass().getResource("imgcharacter/PeaShooter_Shooting.gif").toExternalForm(), size, size, true, true);
        img = new ImageView(image);
    }

}
