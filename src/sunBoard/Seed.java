package sunBoard;

import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import javax.swing.ImageIcon;

import game.GameComponent;
import game.MyTool;
import game.Vector2D;
import sceneAdventure.Floor;

public class Seed extends GameComponent {
    public static final int sunLength = 117;

    public int sunSum = 50;
    public int sunTime = 5000;
    private Floor floor;
    private long createTime = System.currentTimeMillis();
    private int selectCardID = -1;

    public ArrayList<Sun> suns = new ArrayList<Sun>();
    private Card[] cards = new Card[6];
    public int x = 170;
    public int y = 0;
    public int cardX = x + 75;
    public int cardY = y + 5;
    // 图片资源
    private static ImageIcon bgImageIcon = new ImageIcon(MyTool.toAbsolutePath("ui/SeedBank.png"));
    private static ImageIcon shovelBoxImg = new ImageIcon(MyTool.toAbsolutePath("ui/ShovelBank.png"));
    private static ImageIcon shovelImg = new ImageIcon(MyTool.toAbsolutePath("ui/Shovel.png"));

    public Seed(Floor floor) {
        super();
        this.floor=floor;
        setCardsID(new Card(2000, 2000, 50, 0,this), 0);
        setCardsID(new Card(2500, 2500, 100, 1,this), 1);
        setCardsID(new Card(5000, 5000, 50, 2,this), 2);
        setCardsID(new Card(15000, 15000, 150, 3,this), 3);
        setCardsID(new Card(8000, 8000, 175, 4,this), 4);
        setCardsID(new Card(10000, 10000, 200, 5,this), 5);
    }


    public void setCards(Card[] cards) {
        this.cards = cards;
    }

    public void setCardsID(Card card, int id) {
        this.cards[id] = card;
    }
    public void createSun(int x,int y) {
        suns.add(new Sun(new Vector2D(x, y), new Vector2D(x, y), this));
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        // TODO Auto-generated method stub

    }

    @Override
    public void mousePressed(MouseEvent e) {
        // TODO Auto-generated method stub
        int CardID;
        if (e.getY() > 0 && e.getY() <= Card.CARD_HEIGHT) {
            if (e.getX() > cardX && e.getX() < cardX + Card.CARD_WIATH * 6) {
                CardID = (e.getX() - cardX) / Card.CARD_WIATH;
                if (cards[CardID]!=null) {
                    if (cards[CardID].nowCd<=0) {
                        if (sunSum>=cards[CardID].price) {
                            floor.setPointPlant(cards[CardID].plantID);
                            selectCardID=CardID;
                            return;
                        }
                    }
                }
            }
        }
        //进行放置植物
        if (selectCardID!=-1) {
            Vector2D temp=floor.getCheckerBoard(e.getX(), e.getY());
            if (temp!=null) {
                floor.setPlant(temp,cards[selectCardID].plantID);
                sunSum-=cards[selectCardID].price;
                cards[selectCardID].reCD();
                selectCardID=-1;
            }
        }
        floor.setPointPlant(-1);
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        // TODO Auto-generated method stub
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        // TODO Auto-generated method stub

    }

    @Override
    public void mouseExited(MouseEvent e) {
        // TODO Auto-generated method stub

    }

    @Override
    public void mouseDragged(MouseEvent e) {
        // TODO Auto-generated method stub

    }

    @Override
    public void mouseMoved(MouseEvent e) {
        // TODO Auto-generated method stub
        int x = e.getX();
        int y = e.getY();
        for (Sun sun : suns) {
            if (!sun.isCatch) {
                if (x > sun.position.x && x < sun.position.x + sunLength) {
                    if (y > sun.position.y && y < sun.position.y + sunLength) {
                        sun.catchSun();
                    }
                }
            }
        }
    }

    @Override
    public void draw(Graphics g) {
        // TODO Auto-generated method stub
        // 渲染背景

        g.drawImage(bgImageIcon.getImage(), x, y, bgImageIcon.getImageObserver());
        g.drawImage(shovelBoxImg.getImage(), x+bgImageIcon.getIconWidth(), y, shovelBoxImg.getImageObserver());
        g.drawImage(shovelImg.getImage(), x+bgImageIcon.getIconWidth(), y, shovelImg.getImageObserver());
        // 渲染卡片
        for (int i = 0; i < cards.length; i++) {
            if (cards[i] != null) {
                cards[i].draw(cardX + (i * Card.CARD_WIATH), cardY, g);
            }
        }
        // 渲染阳光数量
        g.drawString(sunSum + "", x + 20, 75);
        // 渲染阳光
        for (int i = 0; i < suns.size(); i++) {
            suns.get(i).draw(g);
        }
        // 生成阳光
        if (System.currentTimeMillis() - createTime >= sunTime) {
            Vector2D position = new Vector2D((int) (Math.random() * 600 + Floor.checkerBoardX), 0);
            Vector2D target = new Vector2D(0, (int) (Math.random() * 400 + 100));
            suns.add(new Sun(position, target, this));
            createTime = System.currentTimeMillis();
        }

    }

}