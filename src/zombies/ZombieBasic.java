package zombies;

import java.awt.Graphics;

import animation.Animation;
import animation.AnimationAsset;
import game.Game;
import game.GamePanel;
import game.Vector2D;
import sceneAdventure.Floor;
import sceneAdventure.ZombieManage;

public class ZombieBasic extends Zombie {
    private static AnimationAsset aAsset_Run;
    private static AnimationAsset aAsset_Eat;

    private static AnimationAsset aAsset_DieBody;
    private static AnimationAsset aAsset_DieBHead;

    private static AnimationAsset aAsset_DieBoom;

    private Animation an_Run = new Animation(aAsset_Run);
    private Animation an_Eat = new Animation(aAsset_Eat);
    private Animation an_DieBody = new Animation(aAsset_DieBody);
    private Animation an_DieBHead = new Animation(aAsset_DieBHead);
    private Animation an_DieBoom = new Animation(aAsset_DieBoom);

    private Vector2D eatPlant;

    static {
        aAsset_Run = new AnimationAsset("biology/zombie/Zombie1", 22);
        aAsset_Eat = new AnimationAsset("biology/zombie/ZombieAttack", 20);
        aAsset_DieBody = new AnimationAsset("biology/zombie/ZombieDie", 14);
        aAsset_DieBHead = new AnimationAsset("biology/zombie/ZombieHead", 11);
        aAsset_DieBoom = new AnimationAsset("biology/zombie/ZombieBoom", 20);
    }

    public ZombieBasic(int rowIndex,ZombieManage manage) {
        super(manage);
        x = Floor.checkerBoardX + Floor.boxWidth * 9-(Math.random()*20);
        y = Floor.checkerBoardY + Floor.boxHeight * (rowIndex);
        an_Run.X = (int) x;
        an_Run.Y = (int) y;
    }

    @Override
    public void draw(Graphics g) {
        // TODO Auto-generated method stub
        if (state==0) {
            if (buffIce) {
                an_Run.setPlaySpeed(90);
                x-=spendIce*(GamePanel.frameTime/(double)1000);
                if (System.currentTimeMillis()-this.buffIceBegin>=this.buffIceTime) {
                    buffIce=false;
                }
            }else {
                an_Run.setPlaySpeed(60);
                x-=speed*(GamePanel.frameTime/(double)1000);
            }


            an_Run.X = (int) x-75;
            an_Run.Y = (int) y-60;
            an_Run.draw(g);
            Vector2D temp=manage.manage.getCheckerBoard((int)this.x,(int)this.y);
            if (temp!=null) {
                if (manage.manage.hasPlant(temp)) {
                    eatPlant=temp;
                    state=1;
                }
            }else {
                System.out.println("僵尸吃掉了你的脑子");
            }

        }else if(state==1){
            an_Eat.X = (int) x-75;
            an_Eat.Y = (int) y-60;
            an_Eat.draw(g);
            if (manage.manage.hasPlant(eatPlant)) {
                manage.manage.injuredPlant(eatPlant, this.atk*(GamePanel.frameTime/1000));
            }else {
                state=0;
            }
        }else if(state==2) {
            an_DieBHead.X = (int) x-75;
            an_DieBHead.Y = (int) y-60;
            an_DieBHead.draw(g);
            an_DieBody.X = (int) x-75;
            an_DieBody.Y = (int) y-60;
            an_DieBody.draw(g);
            if (an_DieBody.isPlayEnd()) {
                state=4;
            }
        }else if(state==3) {
            an_DieBoom.X = (int) x-75;
            an_DieBoom.Y = (int) y-60;
            an_DieBoom.draw(g);;
            if (an_DieBoom.isPlayEnd()) {
                state=4;
            }
        }

        g.fillOval((int) x + 10, (int) y + 65, 70, 20);
        // debug
        if (Game.debugMode) {
            g.drawRect((int) x, (int) y, Floor.boxWidth, Floor.boxHeight);

        }

    }

    @Override
    public void setDie(int stateDie) {
        // TODO Auto-generated method stub
        if (stateDie==0) {
            state=2;
            an_DieBHead.play();
            an_DieBody.play();
        }else if (stateDie==1) {
            state=3;
            an_DieBoom.play();
        }

    }

}