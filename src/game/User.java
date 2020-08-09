package game;

import java.io.Serializable;
import java.util.ArrayList;

class User implements Serializable {

    private int currentLevel;
    private int playLevel;
    private ArrayList<String> plants;

    User(){
        currentLevel = 1;
        playLevel = 1;
        plants = new ArrayList<String>();
        plants.add("PeaShooter");
    }

    public void setCurrentLevel(int currentLevel) {
        this.currentLevel = currentLevel;
    }

    public int getCurrentLevel() {
        return currentLevel;
    }

    public ArrayList<String> getPlants() {
        return plants;
    }

    public void setPlayLevel(int playLevel) {
        this.playLevel = playLevel;
    }

    public int getPlayLevel() {
        return playLevel;
    }

    public void levelUp(Level level){
        if (playLevel == currentLevel){
            currentLevel += 1;
            playLevel += 1;
            plants.add(level.getPlantPrize());
        }
        else {
            playLevel += 1;
        }
    }
}
