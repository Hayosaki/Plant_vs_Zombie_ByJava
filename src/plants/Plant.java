package plants;
import java.awt.Graphics;

import sceneAdventure.Floor;

public abstract class Plant {
    public int x;
    public int y;
    public double fireRate;
    public double nowBlood;
    public boolean isFire = false;
    public Floor manage;
    private int gridX;
    private int gridY;

    public Plant(Floor floor, int gridX, int gridY) {
        super();
        // TODO Auto-generated constructor stub
        this.manage = floor;
        this.gridX = gridX;
        this.gridY = gridY;
    }

    public int getGridX() {
        return gridX;
    }

    public void setGridX(int gridX) {
        this.gridX = gridX;
    }

    public int getGridY() {
        return gridY;
    }

    public void setGridY(int gridY) {
        this.gridY = gridY;
    }

    public abstract void draw(Graphics g);

}
