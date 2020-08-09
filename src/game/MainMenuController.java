package game;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class MainMenuController {


    @FXML
    public Button StartGameButton, LoadGameButton, LevelButton, ExitButton;

    public void startGame(ActionEvent event) throws Exception{
        Main.previousSave.setPlayLevel(Main.previousSave.getCurrentLevel());
        Parent root = FXMLLoader.load(getClass().getResource("GameScreen.fxml"));
        Stage stage = (Stage) StartGameButton.getScene().getWindow();
        stage.setScene(new Scene(root));
        stage.show();
    }

    public void loadSave(ActionEvent event) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("loadgamescreen.fxml"));
        Stage stage = (Stage) StartGameButton.getScene().getWindow();
        stage.setScene(new Scene(root));
        stage.show();
    }

    public void levelMenu(ActionEvent event) throws Exception{
        Stage stage = (Stage)LevelButton.getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getResource("LevelScreen.fxml"));
        stage.setScene(new Scene(root));
        stage.show();
    }

    public void quitGame(ActionEvent event) throws Exception{
        Stage stage = (Stage) ExitButton.getScene().getWindow();
        stage.close();
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
}
