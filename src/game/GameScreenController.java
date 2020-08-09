package game;

import game.character.Pea;
import game.character.Plant;
import game.character.SunPoint;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.util.ArrayList;

public class GameScreenController {

    private Game currentGame;
    private Image currPlant;
    private Button trigger;

    @FXML
    public AnchorPane root;
    @FXML
    public ImageView plantToPut;
    @FXML
    public Button SaveGameButton, BackButton, PlayPrompt;
    @FXML
    public ProgressBar gameProgress;
    @FXML
    public Label SunPoints;
    @FXML
    public Button PeaShooter, Sunflower, WallNut, CherryBomb, Squasher, Repeater;
    @FXML
    public GridPane Lawn;

    @FXML
    public void initialize(){
        currentGame = new Game(Main.previousSave,root, SunPoints);
        SunPoints.setText(currentGame.getSunPoints());
        setCardButtons(Main.previousSave.getPlants());
    }

    private void setCardButtons(ArrayList<String> plants){
        for (String plant : plants) {
            if (plant.equals("PeaShooter"))
                PeaShooter.setVisible(true);
            else if (plant.equals("Sunflower"))
                Sunflower.setVisible(true);
            else if (plant.equals("CherryBomb"))
                CherryBomb.setVisible(true);
            else if (plant.equals("WallNut"))
                WallNut.setVisible(true);
            else if (plant.equals("Squasher"))
                Squasher.setVisible(true);
            else if (plant.equals("Repeater"))
                Repeater.setVisible(true);
            else{
                System.out.println("Unknown plant string found: "+plant);
            }
        }
    }

    public void startPlaying(ActionEvent event) throws Exception{
        PlayPrompt.setVisible(false);
        gameProgress.setProgress(0.05);
        Timeline timeline = new Timeline();
        KeyValue keyValue = new KeyValue(gameProgress.progressProperty(),0.9);
        KeyFrame keyFrame = new KeyFrame(new Duration(60000), keyValue);
        timeline.getKeyFrames().add(keyFrame);
        timeline.play();
    }

    @FXML
    public void pickPlant(MouseEvent event){
        trigger = (Button) event.getSource();
        if (!trigger.isDisabled()){
            currPlant = Plant.pickImage(trigger.getId());
            if (currentGame.getSunPointValue() >= Plant.tryToBuy(trigger.getId())){
                plantToPut.setImage(currPlant);
                Dragboard db = plantToPut.startDragAndDrop(TransferMode.ANY);
                ClipboardContent cb = new ClipboardContent();
                cb.putImage(currPlant);
                db.setContent(cb);
                event.consume();
            }
        }
    }

    @FXML
    public void handlePlantDrag(DragEvent event){
        if(event.getDragboard().hasImage()){
            event.acceptTransferModes(TransferMode.ANY);
        }
    }

    @FXML
    public void handlePlantDrop(DragEvent event){
        ImageView source = (ImageView) event.getSource();
        if (source.getImage() == null) {
            Plant newPlant = Plant.createPlant(trigger.getId(), (int) source.getBoundsInParent().getCenterX(), (int) source.getBoundsInParent().getCenterY());
            trigger.setVisible(false);
            plantCardReload(trigger, newPlant.getReloadTime());
            currentGame.setSunPoints(currentGame.getSunPointValue() - newPlant.getCost());
            SunPoints.setText(currentGame.getSunPoints());
            System.out.println("Plant Bought");
            currentGame.addNewPlant(newPlant.getPosX(), newPlant.getPosY(), newPlant, SunPoints);
            source.setImage(newPlant.getImage());
            trigger = null;
            currPlant = null;
            source.setFitHeight(70);
        }

    }

    private void plantCardReload(Button button, long time ){
        Timeline enable = new Timeline(new KeyFrame(
                Duration.millis(time),
                e -> {
                    button.setVisible(true);
                }
        ));
        enable.setCycleCount(0);
        enable.play();
    }

    @FXML
    public void saveGame(){

    }

    @FXML
    public void backToMainMenu(ActionEvent event) throws Exception{
        currentGame = null;
        root.getChildren().removeAll();
        Stage stage = (Stage) BackButton.getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getResource("MainMenu.fxml"));
        stage.setScene(new Scene(root));
        stage.show();
    }

    @FXML
    public void buttonPress(MouseEvent event) throws Exception{
        Button button = (Button)event.getSource();
        String imgPath = "img/"+button.getId()+"OnClick.png";
        ImageView image = new ImageView(new Image(getClass().getResourceAsStream(imgPath)));
        button.setGraphic(image);
    }

    @FXML
    public void buttonRelease(MouseEvent event) throws Exception{
        Button button = (Button)event.getSource();
        String imgPath = "img/"+button.getId()+".png";
        ImageView image = new ImageView(new Image(getClass().getResourceAsStream(imgPath)));
        button.setGraphic(image);
    }

}
