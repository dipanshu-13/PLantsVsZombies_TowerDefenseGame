package game;

import game.Level;
import javafx.util.Pair;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.TreeMap;

class LevelLoader {

    public static void main(String[] args) {
        String plant = "Repeater";
        TreeMap <Integer, Pair<Integer, String> > a = new TreeMap<Integer, Pair<Integer, String>>();
        a.put(25, new Pair<Integer, String>(3, "NormalZombie"));
        a.put(32, new Pair<Integer, String>(3, "NormalZombie"));
        a.put(35, new Pair<Integer, String>(2, "NormalZombie"));
        a.put(42, new Pair<Integer, String>(2, "NormalZombie"));
        a.put(43, new Pair<Integer, String>(3, "ConeZombie"));
        a.put(50, new Pair<Integer, String>(2, "ConeZombie"));
        a.put(53, new Pair<Integer, String>(4, "NormalZombie"));
        a.put(55, new Pair<Integer, String>(3, "NormalZombie"));
        a.put(61, new Pair<Integer, String>(4, "ConeZombie"));
        a.put(62, new Pair<Integer, String>(5, "NormalZombie"));
        a.put(65, new Pair<Integer, String>(2, "NormalZombie"));
        a.put(70, new Pair<Integer, String>(1, "NormalZombie"));
        a.put(74,new Pair<Integer, String>(1,"ConeZombie"));
        Level level = new Level(plant, a);
        try{
            ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream("level5.dat"));
            out.writeObject(level);
            System.out.println("Level serialized.");
        }
        catch (IOException e){
            System.out.println("Level not serialized.");
            e.printStackTrace();

        }
    }

}
