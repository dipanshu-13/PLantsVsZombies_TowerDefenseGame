package game.character;

import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;

public interface GameObject {
    ImageView getImageView();
    void addOnScene(AnchorPane root);
    void loadGraphics(int size);
    void saveGraphics();
}
