package game;

import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import javax.swing.JPanel;

import sceneStart.Start;

public class GamePanel extends JPanel implements MouseListener, MouseMotionListener {
    /**
     *
     */
    private static final long serialVersionUID = 1L;

    //当前游戏内容:初始值设为 开始界面
    public static GameComponent nowMold = new Start();

    //显示游戏FPS
    private static int fps = 0;
    private static int fpsCount = 0;
    private static long beginFrame = System.currentTimeMillis();
    //帧像周期初始为1
    public static double frameTime = 1;
    private long nowTime = System.currentTimeMillis();

    public GamePanel() {
        this.addMouseListener(this);
        this.addMouseMotionListener(this);
    }

    public void paint(Graphics g) {
        nowTime = System.currentTimeMillis();
        nowMold.draw(g);
        try {
            Thread.sleep(1);
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        if (Game.debugMode) {
            // FPS绘制
            fpsCount++;
            if (System.currentTimeMillis() - beginFrame >= 1000) {
                fps = fpsCount;
                fpsCount = 0;
                beginFrame = System.currentTimeMillis();
            }
            g.drawString("FPS:" + fps, 15, 15);
        }
        frameTime = System.currentTimeMillis() - nowTime;
        g.drawString("" + frameTime, 65, 15);
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        // TODO Auto-generated method stub
        nowMold.mouseDragged(e);
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        // TODO Auto-generated method stub
        nowMold.mouseMoved(e);
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        // TODO Auto-generated method stub
        nowMold.mouseClicked(e);
    }

    @Override
    public void mousePressed(MouseEvent e) {
        // TODO Auto-generated method stub
        nowMold.mousePressed(e);
    }
    @Override
    public void mouseReleased(MouseEvent e) {
        // TODO Auto-generated method stub
        nowMold.mouseReleased(e);
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        // TODO Auto-generated method stub
        nowMold.mouseEntered(e);
    }

    @Override
    public void mouseExited(MouseEvent e) {
        // TODO Auto-generated method stub
        nowMold.mouseExited(e);
    }
}