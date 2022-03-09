package plants;

import java.awt.Graphics;

import animation.Animation;
import animation.AnimationAsset;
import game.MyTool;
import sceneAdventure.Floor;

public class P_SunPlant extends Plant {

    private static AnimationAsset aAsset;
    Animation plant = new Animation(aAsset);
    long fireTime = System.currentTimeMillis();

    static {
        aAsset = new AnimationAsset(MyTool.toAbsolutePath("biology/botany/SunFlower"), "SunFlower", 18);
    }

    public P_SunPlant(Floor floor,int gridX,int gridY) {
        super(floor,gridX,gridY);
        this.nowBlood =5;
        this.fireRate=2000;
    }

    @Override
    public void draw(Graphics g) {
        // TODO Auto-generated method stub
        plant.X=x;
        plant.Y=y;
        plant.draw(g);

        if (System.currentTimeMillis()-fireTime>=10000) {
            manage.seedSun.createSun(x, y);
            fireTime=System.currentTimeMillis();
        }

    }

}
