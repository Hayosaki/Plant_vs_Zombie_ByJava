package sceneStart;

import java.awt.Graphics;

import javax.swing.ImageIcon;

public class Wbutton {
    public ImageIcon aIcon;
    public ImageIcon bIcon;
    public boolean isAction1 = false;
    public int X;
    public int Y;
    public int h;
    public int w;

    public Wbutton(ImageIcon aIcon,ImageIcon bIcon) {
        super();
        this.aIcon = aIcon;
        this.bIcon = bIcon;
        h=aIcon.getIconHeight();
        w=aIcon.getIconWidth();
    }
    public void draw(Graphics g) {
        if (isAction1) {
            g.drawImage(bIcon.getImage(),X, Y, bIcon.getImageObserver());
        }else {
            g.drawImage(aIcon.getImage(),X, Y, bIcon.getImageObserver());
        }
    }
    public void mouseMove(int mouseX,int mouseY) {
        if (mouseX>X&&mouseX<X+w) {
            if (mouseY>Y&&mouseY<Y+h) {
                isAction1=true;
                return;
            }
        }
        isAction1=false;
    }
    public boolean mouseClick(int mouseX,int mouseY) {
        if (mouseX>X&&mouseX<X+w) {
            if (mouseY>Y&&mouseY<Y+h) {
                return true;
            }
        }
        return false;
    }
}
