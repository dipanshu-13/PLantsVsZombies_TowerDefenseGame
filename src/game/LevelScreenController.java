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

public class LevelScreenController {

    @FXML
    public Button BackButton, Level1, Level2, Level3;

    public void loadLevel(ActionEvent event) throws Exception{
        Button game = (Button) event.getSource();
        int level = Integer.parseInt(game.getId().substring(5));
        if (Main.previousSave.getCurrentLevel() >= level) {
            Main.previousSave.setPlayLevel(level);
            Parent root = FXMLLoader.load(getClass().getResource("GameScreen.fxml"));
            Stage stage = (Stage) game.getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.show();
        }
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

}
