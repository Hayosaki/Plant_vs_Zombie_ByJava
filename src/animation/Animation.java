package animation;

import game.Game;

import java.awt.*;

public class Animation {
    public int X = 0;
    public int Y = 0;

    private AnimationAsset asset;
    private int nowID=0;
    private int playSpeed =70;//播放速度初始值为70
    private int state=0;//0:循环播放;1:正在播放中;2:播放完毕

    private Color color=Color.white;

    private long time;

    public Animation(AnimationAsset asset) {
        super();
        this.asset = asset;
        this.time=System.currentTimeMillis();
    }

    public void draw(Graphics g) {
//		g.drawImage(asset.img.get(nowID).getImage(), X, Y, Color.white, asset.img.get(nowID).getImageObserver());
        g.drawImage(asset.img.get(nowID).getImage(), X, Y, asset.img.get(nowID).getImageObserver());
        if (System.currentTimeMillis()-time>= playSpeed) {
            if (state!=2){
                nowID++;
            }

            time=System.currentTimeMillis();
        }
        if (nowID>=asset.frameAmount) {
            if (state==0) {
                nowID=0;
            }else if (state==1) {
                state=2;
                nowID=asset.frameAmount-1;
            }

        }
        //debug
        if (Game.debugMode) {
            g.drawRect(X, Y, asset.img.get(nowID).getIconWidth(), asset.img.get(nowID).getIconHeight());
        }
    }
    /**
     * 开始播放并且指定为单次播放
     */
    public void play() {
        state=1;
        nowID=0;
    }
    /**
     * 查询是否播放完毕
     * @return
     */
    public boolean isPlayEnd() {
        if (state==2) {
            return true;
        }else {
            return false;
        }
    }

    /**
     * 重新播放动画
     */
    public void rePlay() {
        nowID=0;
    }
    /**
     * 指定每帧的停留时间
     * @param time 每帧的停留时间
     */
    public void setPlaySpeed(int time) {
        playSpeed =time;
    }
    /**
     * 将绘图的位置移动到(x,y)处
     * @param x
     * @param y
     */
    public void moveTo(int x, int y) {
        // TODO Auto-generated method stub
        X = x;
        Y = y;
    }
    /**
     *将绘图的位置移动(x,y)个单位
     * @param x
     * @param y
     */
    public void move(int x, int y) {
        // TODO Auto-generated method stub
        X += x;
        Y += y;
    }
}