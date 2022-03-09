package plants;

import java.awt.Graphics;

import game.Game;
import sceneAdventure.BulletManage;

public class BulletIce extends Bullet {

    public BulletIce(int x,int y) {
        this.atk=1;
        this.X=x;
        this.Y=y;
        this.speed=0.5;
        this.buff=1;
    }
    @Override
    public void draw(Graphics g) {
        // TODO Auto-generated method stub
        g.drawImage(BulletManage.bulletImgs[1].getImage(), (int)X-BulletManage.bulletImgs[0].getIconWidth()/2, (int)Y-BulletManage.bulletImgs[0].getIconHeight()/2, BulletManage.bulletImgs[0].getImageObserver());
        g.fillOval((int)X-5, (int)Y-5, 10, 10);
        if (Game.debugMode) {
            g.drawRect((int)X-BulletManage.bulletImgs[0].getIconWidth()/2, (int)Y-BulletManage.bulletImgs[0].getIconHeight()/2, BulletManage.bulletImgs[0].getIconHeight(), BulletManage.bulletImgs[0].getIconWidth());
        }
    }

}