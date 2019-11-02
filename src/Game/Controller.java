package Game;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class Controller {
    @FXML
    public Button startgame;
    public void startgame(ActionEvent event) throws Exception{
        Stage primaryStage = new Stage();
        primaryStage.setTitle("Plants VS Zombies");
        Parent root = FXMLLoader.load(getClass().getResource("GameScreen.fxml"));
        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.show();
        Stage stage = (Stage) startgame.getScene().getWindow();
        stage.close();
    }

}
