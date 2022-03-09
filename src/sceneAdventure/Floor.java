package sceneAdventure;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseEvent;

import javax.swing.ImageIcon;

import game.Game;
import game.GameComponent;
import game.MyTool;
import game.Vector2D;
import plants.P_CherryBomb;
import plants.P_Peashooter;
import plants.P_Repeater;
import plants.P_SnowPea;
import plants.Plant;
import plants.P_SunPlant;
import plants.P_WallNut;
import sunBoard.Seed;

public class  Floor extends GameComponent {

    int mouseX = 0;
    int mouseY = 0;

    public static final int boxHeight = 99;
    public static final int boxWidth = 81;
    public static final int checkerBoardX = 50;
    public static final int checkerBoardY = 80;
    public static final int checkerBoard_HEIGHT = boxHeight * 5;
    public static final int checkerBoard_WIDTH = boxWidth * 9;

    public int state=0;
    Plant[][] plantS = new Plant[9][5];
    public BulletManage bulletManage = new BulletManage(this);
    public ZombieManage zombieManage = new ZombieManage(this);
    public Seed seedSun = new Seed(this);

    public long beginTime;

    private int pointPlantsID = -1;



    public Floor() {
        super();
        // TODO Auto-generated constructor stub
        beginTime=System.currentTimeMillis();
    }

    /**
     * 半透明的影子图片
     */
    private static ImageIcon[] plantShadow = new ImageIcon[6];
    ImageIcon bgImageIcon = new ImageIcon(MyTool.toAbsolutePath("img/background.jpg"));
    static {
        plantShadow[0] = new ImageIcon(MyTool.toAbsolutePath("img/Blurs/SunFlower.png"));
        plantShadow[1] = new ImageIcon(MyTool.toAbsolutePath("img/Blurs/Peashooter.png"));
        plantShadow[2] = new ImageIcon(MyTool.toAbsolutePath("img/Blurs/WallNut.png"));
        plantShadow[3] = new ImageIcon(MyTool.toAbsolutePath("img/Blurs/CherryBomb.png"));
        plantShadow[4] = new ImageIcon(MyTool.toAbsolutePath("img/Blurs/SnowPea.png"));
        plantShadow[5] = new ImageIcon(MyTool.toAbsolutePath("img/Blurs/Repeater.png"));
    }

    /**
     * 放置植物
     *
     * @param positionVec 放置植物的网格纵坐标
     * @param PlantID    放置植物的类型
     * @return 是否放置成功
     */
    public boolean setPlant(Vector2D positionVec, int PlantID) {
        // 放置植物类型
        switch (PlantID) {
            case 0:
                plantS[(int) positionVec.x][(int) positionVec.y] = new P_SunPlant(this, (int) positionVec.x,
                        (int) positionVec.y);
                break;
            case 1:
                plantS[(int) positionVec.x][(int) positionVec.y] = new P_Peashooter(this, (int) positionVec.x,
                        (int) positionVec.y);
                break;
            case 2:
                plantS[(int) positionVec.x][(int) positionVec.y] = new P_WallNut(this, (int) positionVec.x,
                        (int) positionVec.y);
                break;
            case 3:
                plantS[(int) positionVec.x][(int) positionVec.y] = new P_CherryBomb(this, (int) positionVec.x,
                        (int) positionVec.y);
                break;
            case 4:
                plantS[(int) positionVec.x][(int) positionVec.y] = new P_SnowPea(this, (int) positionVec.x,
                        (int) positionVec.y);
                break;
            case 5:
                plantS[(int) positionVec.x][(int) positionVec.y] = new P_Repeater(this, (int) positionVec.x,
                        (int) positionVec.y);
                break;
            default:
                return false;
        }
        Vector2D temp = getPlantPosition(positionVec);
        plantS[(int) positionVec.x][(int) positionVec.y].x = (int) temp.x;
        plantS[(int) positionVec.x][(int) positionVec.y].y = (int) temp.y;
        return true;
    }

    /**
     * 用网格的位置获取绘制图像的位置
     */
    private Vector2D getPlantPosition(int x, int y) {
        return new Vector2D(checkerBoardX + x * boxWidth, checkerBoardY + y * boxHeight);
    }

    /**
     * 用网格的位置获取绘制图像的位置
     */
    private Vector2D getPlantPosition(Vector2D vec) {
        return getPlantPosition((int) vec.x, (int) vec.y);
    }

    /**
     * 将鼠标的位置转换为植物网格位置，如果超出返回一个null
     */
    public Vector2D getCheckerBoard(int X, int Y) {
        if (X < checkerBoardX || Y < checkerBoardY) {
            return null;
        }
        if (X > checkerBoardX + checkerBoard_WIDTH || Y > checkerBoardY + checkerBoard_HEIGHT) {
            return null;
        }
        return new Vector2D((X - checkerBoardX) / boxWidth, (Y - checkerBoardY) / boxHeight);
    }

    /**
     * 设置当前鼠标选择的植物，如果没有设置为-1;
     *
     * @param ID
     */
    public void setPointPlant(int ID) {
        pointPlantsID = ID;
    }

    public void reMovePlant(int x,int y) {
        plantS[x][y]=null;
    }

    /**
     * 查询植物网格中是否存在植物
     *
     * @param vec
     * @return
     */
    public boolean hasPlant(Vector2D vec) {
        if (vec.x<9&&vec.y<5) {
            if (vec.x>=0&&vec.y>=0) {
                if (plantS[(int) vec.x][(int) vec.y] == null) {
                    return false;
                } else {
                    return true;
                }
            }
        }
        return false;
    }
    /**
     * 获取网格中的植物
     *
     * @param vec
     * @return
     */
    public Plant getPlant(Vector2D vec) {
        return plantS[(int) vec.x][(int) vec.y];
    }
    public void injuredPlant(Vector2D vec,double size) {
        plantS[(int) vec.x][(int) vec.y].nowBlood -=size;
        if (plantS[(int) vec.x][(int) vec.y].nowBlood <=0) {
            plantS[(int) vec.x][(int) vec.y]=null;
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        // TODO Auto-generated method stub

    }

    @Override
    public void mousePressed(MouseEvent e) {
        // TODO Auto-generated method stub
        seedSun.mousePressed(e);
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
        mouseX = e.getX();
        mouseY = e.getY();
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        // TODO Auto-generated method stub
        seedSun.mouseMoved(e);
        mouseX = e.getX();
        mouseY = e.getY();
    }

    @Override
    public void draw(Graphics g) {
        // TODO Auto-generated method stub
        Graphics2D g2 = (Graphics2D) g;
        if (state==0) {
            g2.drawImage(bgImageIcon.getImage(), -200, 0, bgImageIcon.getImageObserver());
            // plant
            for (int i = 0; i < plantS.length; i++) {
                for (int j = 0; j < plantS[0].length; j++) {
                    if (plantS[i][j] != null) {
                        if (zombieManage.hasZombieRow(j)) {
                            plantS[i][j].isFire = true;
                        } else {
                            plantS[i][j].isFire = false;
                        }
                        plantS[i][j].draw(g);
                    }
                    // g.drawRect(boxX+j*81, boxY+i*99, boxWidth, boxHeight);
                }
            }

            bulletManage.draw(g);
            zombieManage.draw(g);
            seedSun.draw(g);
            // Draw pointPlant
            if (pointPlantsID != -1) {

                g.drawImage(plantShadow[pointPlantsID].getImage(), mouseX - 15, mouseY - 15,
                        plantShadow[pointPlantsID].getImageObserver());
                Vector2D temp = getCheckerBoard(mouseX, mouseY);
                if (temp != null) {
                    if (!hasPlant(temp)) {
                        temp = getPlantPosition(temp);
                        g.drawImage(plantShadow[pointPlantsID].getImage(), (int) temp.x, (int) temp.y,
                                plantShadow[pointPlantsID].getImageObserver());
                    }
                }
            }
        }



        // debug
        if (Game.debugMode) {
            for (int i = 0; i < 5; i++) {
                for (int j = 0; j < 9; j++) {
                    g.drawRect(checkerBoardX + j * 81, checkerBoardY + i * 99, boxWidth, boxHeight);
                }
            }
            if (mouseX > checkerBoardX && mouseX < checkerBoardX + boxWidth * 9) {
                if (mouseY > checkerBoardY && mouseY < checkerBoardY + boxHeight * 5) {
                }
            }

        }
    }

}