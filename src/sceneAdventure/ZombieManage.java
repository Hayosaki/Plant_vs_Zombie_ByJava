package sceneAdventure;

import java.awt.Graphics;
import java.util.ArrayList;
import java.util.LinkedList;

import game.Vector2D;
import zombies.Zombie;
import zombies.ZombieBasic;

public class ZombieManage {
    public Floor manage;
    public LinkedList<ArrayList<Zombie>> zombieList = new LinkedList<ArrayList<Zombie>>();

    private long beginTime;

    private boolean autoCreator=false;
    private long autoCreatorTime;
    private int autoCreateSpeed = 3000;

    private int groupSum=5;
    private int groupSpeed=40000;
    private long groupTime;

    public ZombieManage(Floor floor) {
        // TODO Auto-generated constructor stub
        super();
        this.manage = floor;
        this.beginTime=System.currentTimeMillis();
        this.groupTime = System.currentTimeMillis();
        zombieList.add(new ArrayList<Zombie>());
        zombieList.add(new ArrayList<Zombie>());
        zombieList.add(new ArrayList<Zombie>());
        zombieList.add(new ArrayList<Zombie>());
        zombieList.add(new ArrayList<Zombie>());
    }

    public void draw(Graphics g) {
        ArrayList<Zombie> zombies;
        for (int i = 0; i < zombieList.size(); i++) {
            zombies = zombieList.get(i);
            for (int j = 0; j < zombies.size(); j++) {
                //是否死亡
                if (zombies.get(j).getState() == 4) {
                    zombies.remove(j);
                }else {
                    zombies.get(j).draw(g);
                }
            }
        }
        if (!autoCreator) {
            if (System.currentTimeMillis() - beginTime>10000) {
                openAutoCreator();
            }
        }


        if (autoCreator) {
            if (System.currentTimeMillis() - groupTime >= groupSpeed) {
                for (int i = 0; i < groupSum; i++) {
                    randomZombie();
                }
                groupSum++;
                groupSpeed--;
                groupTime = System.currentTimeMillis();
            }

            if (System.currentTimeMillis() - autoCreatorTime >= autoCreateSpeed) {
                if (Math.random() > 0.5) {
                    randomZombie();
                }
                autoCreatorTime = System.currentTimeMillis();
            }
        }
    }

    public void openAutoCreator() {
        autoCreator=true;
        autoCreatorTime=System.currentTimeMillis();
    }


    public void randomZombie() {
        int row = (int) (Math.random() * 5);
        int state = (int) (Math.random() * 2);
        switch (state) {
            case 0:
                zombieList.get(row).add(new ZombieBasic(row, this));
                break;
            case 1:
//			zombieList.get(row).add(new ZombieBasic1(row, this));
                break;
            case 2:
//			zombieList.get(row).add(new ZombieBasic2(row, this));
                break;
            default:
                zombieList.get(row).add(new ZombieBasic(row, this));
                break;
        }
    }

    public void injuredZombie(int rowIndex, Zombie zombie, int size) {
        zombie.blood -= size;
        if (zombie.blood <= 0) {
            zombie.setDie(0);
        }
    }

    /*
     * 判断是否存在僵尸，存在返回僵尸对象，不存在返回null
     *
     * @param rowIndex要判断的行
     * @param x要判断的x位置
     * @param y要判断的y位置
     * @return
     */
    public Zombie hasZombie(int rowIndex, double x, double y) {
        ArrayList<Zombie> zombies = zombieList.get(rowIndex);
        for (int i = 0; i < zombies.size(); i++) {
            if (zombies.get(i).isHit(x, y)) {
                if (zombies.get(i).blood > 0) {
                    return zombies.get(i);
                }
            }
        }
        return null;
    }

    /*
     * 判断在指定的圆形区域是否存在僵尸，返回僵尸list
     *
     * @param radius半径
     * @param 圆心x位置
     * @param 圆心y位置
     * @return
     */
    public ArrayList<Zombie> hasZombie( int rowIndex,Vector2D center,int radius) {
        ArrayList<Zombie> reArrayList=new ArrayList<Zombie>();
        ArrayList<Zombie> zombies;

        int i=rowIndex-1;
        int n=i+3;
        Vector2D temp;
        if (rowIndex==0) {
            i=rowIndex;
            n=i+2;
        }else if(rowIndex==4) {
            i=rowIndex-1;
            n=i+2;
        }
        for (; i < n; i++) {
            zombies=zombieList.get(i);
            for (int j = 0; j < zombies.size(); j++) {
                temp=new Vector2D(zombies.get(j).x, zombies.get(j).y);
                if (temp.minusVec(center).getNorm()<=radius) {
                    reArrayList.add(zombies.get(j));
                    zombies.get(j).setDie(1);
                }
            }
        }
        return reArrayList;
    }

    public boolean hasZombieRow(int index) {
        return !zombieList.get(index).isEmpty();
    }

}