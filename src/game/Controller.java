package game;

import javafx.animation.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ProgressBar;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.shape.Line;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.util.Timer;

public class Controller {

    private void animate(ImageView image, int duration, boolean loop, int endpointX, int endpointY){
        PathTransition path = new PathTransition();
        path.setDuration(Duration.millis(duration));
        path.setNode(image);
        Line line = new Line();
        line.setStartX(0);
        line.setStartY(0);
        line.setEndX(endpointX);
        line.setEndY(endpointY);
        path.setPath(line);
        if (loop)
            path.setCycleCount(Animation.INDEFINITE);
        else
            path.setCycleCount(0);
        path.play();
    }

}
