package game;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.*;
import java.util.ArrayList;

public class Main extends Application{

    static User previousSave;

    private static void loadGame(){
        try{
            ObjectInputStream in = new ObjectInputStream(new FileInputStream("UserInit.dat"));
            previousSave = (User) in.readObject();
            System.out.println("User found. Loaded into game.");
            in.close();
        }
        catch (ClassNotFoundException | IOException e){
            previousSave = new User();
            try {
                ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream("UserInit.dat"));
                out.writeObject(previousSave);
                System.out.println("New User Created. File Saved.");
                out.close();
            }
            catch (IOException f){
                System.out.println("New User Created. File not saved.");
            }
        }
    }

    @Override
    public void start(Stage primaryStage) throws Exception{
        loadGame();
        Parent root = FXMLLoader.load(getClass().getResource("MainMenu.fxml"));
        primaryStage.setTitle("Plants VS Zombies");
        primaryStage.setScene(new Scene(root));
        primaryStage.show();
        primaryStage.setOnCloseRequest(e ->{
            try {
                ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream("UserInit.dat"));
                out.writeObject(previousSave);
                System.out.println("New User Created. File Saved.");
                out.close();
            }
            catch (IOException f){
                System.out.println("New User Created. File not saved.");
            }
        });
    }

    public static void main(String[] args) {
        launch(args);
    }
}
