package sunBoard;

import java.awt.Graphics;

import javax.swing.ImageIcon;

import game.GamePanel;
import game.MyTool;

public class Card {
    public static final int CARD_WIATH=55;
    public static final int CARD_HEIGHT=80;

    private long cd;
    private Seed seed;

    public int plantID;
    public double nowCd;
    public int price;

    private static ImageIcon[] cardImg=new ImageIcon[6];
    private static ImageIcon[] cardCDImg=new ImageIcon[6];
    static {
        cardImg[0]=new ImageIcon(MyTool.toAbsolutePath("ui/Cards/SunFlower0.png"));
        cardImg[1]=new ImageIcon(MyTool.toAbsolutePath("ui/Cards/Peashooter0.png"));
        cardImg[2]=new ImageIcon(MyTool.toAbsolutePath("ui/Cards/WallNut0.png"));
        cardImg[3]=new ImageIcon(MyTool.toAbsolutePath("ui/Cards/CherryBomb0.png"));
        cardImg[4]=new ImageIcon(MyTool.toAbsolutePath("ui/Cards/SnowPea0.png"));
        cardImg[5]=new ImageIcon(MyTool.toAbsolutePath("ui/Cards/Repeater0.png"));

        cardCDImg[0]=new ImageIcon(MyTool.toAbsolutePath("ui/Cards/SunFlower1.png"));
        cardCDImg[1]=new ImageIcon(MyTool.toAbsolutePath("ui/Cards/Peashooter1.png"));
        cardCDImg[2]=new ImageIcon(MyTool.toAbsolutePath("ui/Cards/WallNut1.png"));
        cardCDImg[3]=new ImageIcon(MyTool.toAbsolutePath("ui/Cards/CherryBomb1.png"));
        cardCDImg[4]=new ImageIcon(MyTool.toAbsolutePath("ui/Cards/SnowPea1.png"));
        cardCDImg[5]=new ImageIcon(MyTool.toAbsolutePath("ui/Cards/Repeater1.png"));
    }
    public Card(long cd, long nowCd, int price,int cardID,Seed seed) {
        super();
        this.cd = cd;
        this.nowCd = nowCd;
        this.plantID = cardID;
        this.price=price;
        this.seed=seed;
    }
    public void reCD() {
        nowCd=cd;
    }
    public boolean isCDing() {
        if (nowCd<=0) {
            return false;
        }
        return true;
    }
    public void draw(int x,int y,Graphics g) {
        g.drawImage(cardImg[plantID].getImage(), x, y, cardImg[plantID].getImageObserver());
        if (nowCd>0) {
            int h=(int) ((nowCd/cd)*cardCDImg[plantID].getIconHeight());
            g.drawImage(cardCDImg[plantID].getImage(),
                    x, y,
                    x+cardCDImg[plantID].getIconWidth(), y+h,
                    0, 0, cardCDImg[plantID].getIconWidth(), h, cardImg[plantID].getImageObserver());
            nowCd-=GamePanel.frameTime;
        }
        if (seed.sunSum<price) {
            g.drawImage(cardCDImg[plantID].getImage(), x, y, cardImg[plantID].getImageObserver());
        }

    }


}