package game.character;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import java.io.Serializable;


public abstract class Plant extends Character implements Serializable, GameObject {

    protected int health;
    protected final int cost;
    protected final long reloadTime;

    Plant(int posX, int posY, int health, int cost, long reloadTime){
        super(70);
        this.posX = posX;
        this.posY = posY;
        this.health = health;
        this.cost = cost;
        this.reloadTime = reloadTime;
    }

    public static Image pickImage(String name){
        String file = "imgcharacter/"+name+".png";
        return new Image(Plant.class.getResource(file).toExternalForm(), 70, 70, true, true);
    }

    public static Plant createPlant(String name, int x, int y) {
        Plant plant = null;
        if (name.equals("PeaShooter")) {
            plant = PeaShooter.instantiate(x, y);
        }
        else if (name.equals("Sunflower")){
            plant = Sunflower.instantiate(x,y);
        }
        else if (name.equals("WallNut")){
            plant = WallNut.instantiate(x,y);
        }
        else if (name.equals("Repeater")){
            plant = Repeater.instantiate(x,y);
        }
        else if (name.equals("CherryBomb")){
            plant = CherryBomb.instantiate(x,y);
        }
        else if (name.equals("Squasher")){
            plant =  Squasher.instantiate(x,y);
        }
        return plant;
    }

    public static int tryToBuy(String name) {
        switch (name) {
            case "PeaShooter":
                return 100;
            case "WallNut":
            case "Sunflower":
            case "Squasher":
                return 50;
            case "Repeater":
                return 200;
            case "CherryBomb":
                return 150;
        }
        return 10000;
    }

    public int getCost() {
        return cost;
    }

    public Image getImage() {
        return image;
    }

    public long getReloadTime() {
        return reloadTime;
    }

    public boolean getEaten(int damage) {
        this.health -= damage;
        return health <= 0;
    }

}
