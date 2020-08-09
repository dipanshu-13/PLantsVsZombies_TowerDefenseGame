package game;

import game.character.Plant;
import javafx.util.Pair;

import java.io.*;
import java.util.ArrayList;
import java.util.TreeMap;

public class Level implements Serializable {

    private String plantPrize;
    private TreeMap<Integer, Pair<Integer, String> > attackers;

    public Level(int level){
        Level loader;
        String file = "level"+level+".dat";
        System.out.println(file);
        try{
            ObjectInputStream in = new ObjectInputStream(new FileInputStream(file));
            loader = (Level) in.readObject();
            plantPrize = loader.getPlantPrize();
            attackers = loader.getAttackers();
            System.out.println(attackers);
        }
        catch (ClassNotFoundException | IOException e){
            System.out.println("No level file found. Cannot play!");
            e.printStackTrace(System.out);
        }
    }

    public Level(String plant, TreeMap<Integer, Pair<Integer, String> > attacker){
        plantPrize = plant;
        attackers = attacker;
    }

    public String getPlantPrize() {
        return plantPrize;
    }

    public TreeMap<Integer, Pair<Integer, String>> getAttackers() {
        return attackers;
    }


}
