package sceneAdventure;

import java.awt.Graphics;
import java.util.ArrayList;
import java.util.LinkedList;

import javax.swing.ImageIcon;

import game.Game;
import game.GamePanel;
import game.MyTool;
import plants.Bullet;
import plants.BulletBasic;
import plants.BulletIce;
import zombies.Zombie;

public class BulletManage {
    public Floor manage;
    private static final int MAX_DISTANCE = 820;

    public LinkedList<ArrayList<Bullet>> bulletList=new LinkedList<ArrayList<Bullet>>();
    public static ImageIcon[]  bulletImgs = new ImageIcon[2];
    static {
        bulletImgs[0]=new ImageIcon(MyTool.toAbsolutePath("biology\\botany\\bullet/ProjectilePea.png"));
        bulletImgs[1]=new ImageIcon(MyTool.toAbsolutePath("biology\\botany\\bullet//ProjectileSnowPea.png"));

    }

    public ArrayList<Bullet> Bullets=new ArrayList<Bullet>();

    public BulletManage(Floor floor) {
        super();
        this.manage=floor;
        bulletList.add(new ArrayList<Bullet>());
        bulletList.add(new ArrayList<Bullet>());
        bulletList.add(new ArrayList<Bullet>());
        bulletList.add(new ArrayList<Bullet>());
        bulletList.add(new ArrayList<Bullet>());
    }

    public void addBullet(int rowIndex, int x,int y,int index) {
        if (index==0) {
            bulletList.get(rowIndex).add(new BulletBasic(x, y));
        }else if (index==1) {
            bulletList.get(rowIndex).add(new BulletIce(x, y));
        }

    }

    public void draw(Graphics g) {
        ArrayList<Bullet> bullets;
        Zombie hitZombie;
        for (int i = 0; i < bulletList.size(); i++) {
            bullets=bulletList.get(i);
            for (int j = 0; j < bullets.size(); j++) {
                bullets.get(j).X+=bullets.get(j).speed*GamePanel.frameTime;
                hitZombie=manage.zombieManage.hasZombie(i, bullets.get(j).X, bullets.get(j).Y);
                if (hitZombie!=null) {
                    if (bullets.get(j).buff==1) {
                        hitZombie.setIceBuff();
                    }
                    manage.zombieManage.injuredZombie(i, hitZombie, bullets.get(j).atk);
                    bullets.remove(j);
                }else {
                    bullets.get(j).draw(g);
                    if (bullets.get(j).X>MAX_DISTANCE) {
                        bullets.remove(j);
                    }
                }
            }
        }
    }
}