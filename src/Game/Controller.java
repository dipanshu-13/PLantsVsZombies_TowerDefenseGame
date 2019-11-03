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
    @FXML
    public Button StartGameButton, LoadGameButton, LevelButton, ExitButton;
    @FXML
    public Button SaveGameButton, BackButton, PlayPrompt;
    @FXML
    public ImageView Zombie1, Zombie2, Pea, Sun;
    @FXML
    public ProgressBar gameProgress;

    public void startPlaying(ActionEvent event) throws Exception{
        PlayPrompt.setVisible(false);
        animate(Pea, 1200, true, 800, 0);
        animate(Sun, 5000, false, 0, 400);
        animate(Zombie1, 18000, false, -500, 0);
        animate(Zombie2, 18000, false, -500, 0);
        gameProgress.setProgress(0.05);
        Timeline timeline = new Timeline();
        KeyValue keyValue = new KeyValue(gameProgress.progressProperty(),0.9);
        KeyFrame keyFrame = new KeyFrame(new Duration(60000), keyValue);
        timeline.getKeyFrames().add(keyFrame);
        timeline.play();
    }

    public void startGame(ActionEvent event) throws Exception{
        Stage stage = (Stage) StartGameButton.getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getResource("GameScreen.fxml"));
        stage.setScene(new Scene(root));
        stage.show();
    }

    public void levelMenu(ActionEvent event) throws Exception{
        Stage stage = (Stage)LevelButton.getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getResource("LevelScreen.fxml"));
        stage.setScene(new Scene(root));
        stage.show();
    }

    public void backToMainMenu(ActionEvent event) throws Exception{
        Stage stage = (Stage) BackButton.getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getResource("MainMenu.fxml"));
        stage.setScene(new Scene(root));
        stage.show();
    }

    public void buttonPress(MouseEvent event) throws Exception{
        Button button = (Button)event.getSource();
        String imgPath = "img/"+button.getId()+"OnClick.png";
        ImageView image = new ImageView(new Image(getClass().getResourceAsStream(imgPath)));
        button.setGraphic(image);
    }

    public void buttonRelease(MouseEvent event) throws Exception{
        Button button = (Button)event.getSource();
        String imgPath = "img/"+button.getId()+".png";
        ImageView image = new ImageView(new Image(getClass().getResourceAsStream(imgPath)));
        button.setGraphic(image);
    }

    public void selectPlantSeed(MouseEvent event) throws Exception{
        System.out.println(event.getButton().getClass());
        GridPane grid = new GridPane();

    }

    public void quitGame(ActionEvent event) throws Exception{
        Stage stage = (Stage) ExitButton.getScene().getWindow();
        stage.close();
    }

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
