package plants;

import java.awt.Graphics;

import animation.Animation;
import animation.AnimationAsset;
import game.MyTool;
import sceneAdventure.Floor;

public class P_Repeater extends Plant {

    private static AnimationAsset aAsset;
    Animation plant=new Animation(aAsset);
    long fireTime=System.currentTimeMillis();
    static {
        aAsset=new AnimationAsset(MyTool.toAbsolutePath("biology/botany/Repeater"), "Repeater", 15);
    }
    public P_Repeater(Floor floor,int gridX,int gridY) {
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
        if (isFire) {
            if (System.currentTimeMillis()-fireTime>=2000) {
                manage.bulletManage.addBullet( super.getGridY(),x+25,y+25,0);;
                manage.bulletManage.addBullet( super.getGridY(),x-25,y+25,0);;
                fireTime=System.currentTimeMillis();
            }
        }
    }
}