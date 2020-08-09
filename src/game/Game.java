package game;

import game.character.*;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.util.Duration;
import javafx.util.Pair;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.TreeMap;

public class Game implements Serializable {

    private int sunPoints;
    private Level level;
    private TreeMap<Integer, TreeMap<Integer, Plant> > plants;
    private LawnMower[] lawnmower;
    private ArrayList<Pea> peas;
    private SunPoint sun;
    private ArrayList<ArrayList<Zombie> > zombies;
    private transient AnchorPane root;

    public Game(User user, AnchorPane root, Label sunPoint){
        this.sunPoints = 50;
        this.root = root;
        this.level = new Level(user.getPlayLevel());
        this.plants = new TreeMap<Integer, TreeMap<Integer, Plant> >();
        this.zombies = new ArrayList< ArrayList<Zombie> >();
        for (int i = 0; i < 5; i++) {
            this.zombies.add(new ArrayList<Zombie>());
        }
        createZombieForce(this.plants);
        this.lawnmower = new LawnMower[5];
        for (int i = 0; i < 5; i++) {
            lawnmower[i] = new LawnMower(190-i*3, 90 + 100 * i);
            lawnmower[i].addOnScene(root);
            lawnmower[i].init(zombies.get(i));
        }
        this.peas = new ArrayList<Pea>();
        peaKeeperInit();
        sunGeneratorInit(sunPoint);
        gameWinner(user,level);
    }

    private void gameWinner(User user, Level level){
        Timeline advanceToNext = new Timeline();
        advanceToNext.setCycleCount(0);
        advanceToNext.getKeyFrames().add(new KeyFrame(
                Duration.seconds(5),
                e ->{
                    user.levelUp(level);
                    Button button = new Button("Next Level");
                    root.getChildren().add(button);
                    button.setLayoutX(506);
                    button.setLayoutY(300);
                    button.setOnAction( actionEvent ->{
                        Parent root = null;
                        try {
                            root = FXMLLoader.load(getClass().getResource("GameScreen.fxml"));
                        }
                        catch(IOException a){
                            System.out.println("Level "+user.getCurrentLevel()+" not implemented.");
                        }
                        Stage stage = (Stage) button.getScene().getWindow();
                        stage.setScene(new Scene(root));
                        stage.show();
                        advanceToNext.stop();
                    });
                }
        ));
        Timeline winCheck = new Timeline();
        KeyFrame keyFrame = new KeyFrame(
                Duration.seconds(1),
                e -> {
                    boolean x = true;
                    for (ArrayList<Zombie> i: zombies){
                        if (i.size() > 0){
                            x = false;
                            break;
                        }
                    }
                    if (x){
                        advanceToNext.play();
                        winCheck.stop();
                    }
                }
        );
        winCheck.getKeyFrames().add(keyFrame);
        winCheck.setCycleCount(Animation.INDEFINITE);
        winCheck.play();

    }

    private void peaKeeperInit(){
        Timeline checker = new Timeline(new KeyFrame(
            Duration.millis(50),
            e ->{
                for (int i = peas.size()-1; i >= 0; i--){
                    if (peas.get(i).isUsed()){
                        peas.remove(i);
                    }
                }
            }
        ));
        checker.setCycleCount(Animation.INDEFINITE);
        checker.play();
    }

    private void sunGeneratorInit(Label sunPoints){
        Timeline generator = new Timeline(new KeyFrame(
            Duration.seconds(10),
            e -> {
//                sunOnscreen = sun;
                sun = new SunPoint((int)(Math.random()*700)+250,0, sunPoints,this);
                sun.addOnScene(root);
                System.out.println("Sun created!");
                sun.drop();
            }
        ));
        generator.setCycleCount(12);
        generator.play();
    }

    public void addNewPlant(int x, int y, Plant plant,Label sunPoint){
//        plant.setPosX();
//        plant.setPosY();
        TreeMap<Integer, Plant> lane;
        if (plants.get(y) == null){
             lane = new TreeMap<Integer, Plant>();
             lane.put(x,plant);
             plants.put(y,lane);
        }
        else{
            lane = plants.get(y);
            lane.put(x,plant);
        }
        playPlant(plant,sunPoint);
    }

    public void playPlant(Plant plant, Label sun){
        if (plant.getClass().equals(PeaShooter.class)) {
            Timeline zombieKiller = new Timeline();
            zombieKiller.setCycleCount(Animation.INDEFINITE);
            zombieKiller.getKeyFrames().add(new KeyFrame(
                    Duration.millis(1200),
                    e ->{
                        Pea pea = ((PeaShooter) plant).shoot();
                        peas.add(pea);
                        pea.addOnScene(root);
                        pea.shoot(zombies);
                    }
            ));
            zombieKiller.play();
        }
        else if (plant.getClass().equals(Sunflower.class)){
            Timeline sunProduce = new Timeline();
            sunProduce.setCycleCount(Animation.INDEFINITE);
            sunProduce.getKeyFrames().add(new KeyFrame(
                    Duration.seconds(8),
                    e ->{
                        SunPoint sunPoint = ((Sunflower) plant).produce(sun,this);
                        sunPoint.addOnScene(root);
                    }
            ));
            sunProduce.play();
        }
    }

    private void createZombieForce(TreeMap<Integer, TreeMap<Integer, Plant> > plants){
        TreeMap<Integer, Pair<Integer, String>> enemies = this.level.getAttackers();
        for (Integer i: enemies.keySet()) {
            Pair<Integer, String> newZombieData = enemies.get(i);
            String zombieName = newZombieData.getValue();
            Zombie zombie = Zombie.createZombie(zombieName,newZombieData.getKey());
            this.zombies.get(newZombieData.getKey()-1).add(zombie);
            zombie.addOnScene(root);
            zombie.moveAfter(i);
        }
    }

    public void setSunPoints(int sunPoints) {
        this.sunPoints = sunPoints;
    }

    public String getSunPoints() {
        return Integer.toString(sunPoints);
    }

    public int getSunPointValue(){
        return sunPoints;
    }

}
