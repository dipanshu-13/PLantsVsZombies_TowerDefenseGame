package game.character;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;

import java.io.Serializable;

public abstract class Character implements GameObject, Serializable {

    protected int posX;
    protected int posY;
    protected transient Image image;
    protected transient ImageView img;

    Character(int size){
        image = new Image(getClass().getResource("imgcharacter/"+this.toString()+".png").toExternalForm(), size, size, true, true);
        img = new ImageView(image);
    }

    public int getPosX() {
        return posX;
    }

    public int getPosY() {
        return posY;
    }

    public void setPosX() {
        this.posX = (int)(img.getBoundsInParent()).getCenterX();
    }

    public void setPosY() {
        this.posY = (int)(img.getBoundsInParent()).getCenterY();
    }

    @Override
    public void loadGraphics(int size){
        String file = "imgcharacter/"+this.toString()+".png";
        this.image = new Image(getClass().getResource(file).toExternalForm(), size, size, true, true);
    }

    @Override
    public void saveGraphics(){
        setPosX();
        setPosY();
    }

    @Override
    public ImageView getImageView(){
        return img;
    }

    @Override
    public void addOnScene(AnchorPane root) {
        ImageView imageView = getImageView();
        root.getChildren().add(imageView);
        imageView.setX(getPosX());
        imageView.setY(getPosY());
    }

}
