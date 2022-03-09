package sceneStart;

import java.awt.Graphics;
import java.awt.event.MouseEvent;

import javax.swing.ImageIcon;

import game.GameComponent;
import game.GamePanel;
import game.MyTool;
import sceneAdventure.Floor;

public class Start extends GameComponent{
    private int mouseX;
    private int mouseY;

    public ImageIcon bg1=new ImageIcon(MyTool.toAbsolutePath("ui\\start/SelectorScreen_BG.jpg"));
    public ImageIcon bg2=new ImageIcon(MyTool.toAbsolutePath("ui\\start/SelectorScreen_BG_Center.png"));
    public ImageIcon bg3=new ImageIcon(MyTool.toAbsolutePath("ui\\start/SelectorScreen_BG_Left.png"));
    public ImageIcon bg4=new ImageIcon(MyTool.toAbsolutePath("ui\\start/SelectorScreen_BG_Right.png"));
    public ImageIcon bg5=new ImageIcon(MyTool.toAbsolutePath("ui\\start/SelectorScreen_Leaves.png"));
    public ImageIcon bg6=new ImageIcon(MyTool.toAbsolutePath("ui\\start/SelectorScreen_WoodSign1.png"));
    public ImageIcon bg7=new ImageIcon(MyTool.toAbsolutePath("ui\\start/SelectorScreen_WoodSign2.png"));
    public ImageIcon bg7a=new ImageIcon(MyTool.toAbsolutePath("ui\\start/SelectorScreen_WoodSign2_press.png"));

    public ImageIcon bg8=new ImageIcon(MyTool.toAbsolutePath("ui\\start/SelectorScreen_StartAdventure_Button1.png"));
    public ImageIcon bg8a=new ImageIcon(MyTool.toAbsolutePath("ui\\start/SelectorScreen_StartAdventure_Highlight.png"));

    public ImageIcon bg9=new ImageIcon(MyTool.toAbsolutePath("ui\\start/SelectorScreen_Survival_button.png"));
    public ImageIcon bg10=new ImageIcon(MyTool.toAbsolutePath("ui\\start/SelectorScreen_Challenges_button.png"));
    public ImageIcon bg11=new ImageIcon(MyTool.toAbsolutePath("ui\\start/SelectorScreen_Vasebreaker_button.png"));

    public ImageIcon bg12=new ImageIcon(MyTool.toAbsolutePath("ui\\start/SelectorScreen_Options1.png"));
    public ImageIcon bg12a=new ImageIcon(MyTool.toAbsolutePath("ui\\start/SelectorScreen_Options2.png"));

    public ImageIcon bg13=new ImageIcon(MyTool.toAbsolutePath("ui\\start/SelectorScreen_Help1.png"));
    public ImageIcon bg13a=new ImageIcon(MyTool.toAbsolutePath("ui\\start/SelectorScreen_Help2.png"));

    public ImageIcon bg14=new ImageIcon(MyTool.toAbsolutePath("ui\\start/SelectorScreen_Quit1.png"));
    public ImageIcon bg14a=new ImageIcon(MyTool.toAbsolutePath("ui\\start/SelectorScreen_Quit2.png"));
    public ImageIcon bg111=new ImageIcon(MyTool.toAbsolutePath("ui\\start/SelectorScreen_Cloud4.png"));

    public Wbutton bu1=new Wbutton(bg8, bg8a);
    public Wbutton bu2=new Wbutton(bg12, bg12a);
    public Wbutton bu3=new Wbutton(bg13, bg13a);
    public Wbutton bu4=new Wbutton(bg14, bg14a);
    public Wbutton bu5=new Wbutton(bg7, bg7a);


    public Start() {
        bu1.X=410;
        bu1.Y=65;
        bu2.X=570;
        bu2.Y=490;
        bu3.X=655;
        bu3.Y=529;
        bu4.X=724;
        bu4.Y=515;
        bu5.X=23;
        bu5.Y=130;



    }


    @Override
    public void mouseDragged(MouseEvent e) {
        // TODO Auto-generated method stub

    }

    @Override
    public void mouseMoved(MouseEvent e) {
        // TODO Auto-generated method stub
        mouseX=e.getX();
        mouseY=e.getY();
        bu1.mouseMove(mouseX, mouseY);
        bu2.mouseMove(mouseX, mouseY);
        bu3.mouseMove(mouseX, mouseY);
        bu4.mouseMove(mouseX, mouseY);
        bu5.mouseMove(mouseX, mouseY);

    }

    @Override
    public void mouseClicked(MouseEvent e) {
        // TODO Auto-generated method stub
        mouseX=e.getX();
        mouseY=e.getY();
        if (bu1.mouseClick(mouseX, mouseY)) {

            GamePanel.nowMold = new Floor();
        }
        if (bu4.mouseClick(mouseX, mouseY)) {
            System.exit(0);
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {
        // TODO Auto-generated method stub

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
    public void draw(Graphics g) {
        // TODO Auto-generated method stub
        g.drawImage(bg1.getImage(), 0, 0,820,620,bg1.getImageObserver());
        g.drawImage(bg2.getImage(), 85, 250, bg2.getImageObserver());
        g.drawImage(bg3.getImage(), 0, -80, bg3.getImageObserver());
        g.drawImage(bg4.getImage(), 76, 40, bg4.getImageObserver());
        g.drawImage(bg5.getImage(), 0, 538, bg4.getImageObserver());
        g.drawImage(bg6.getImage(), 23, -5, bg4.getImageObserver());
        //g.drawImage(bg7.getImage(), 23, 130, bg4.getImageObserver());
        bu5.draw(g);
        //g.drawImage(bg8.getImage(), 410, 65, bg4.getImageObserver());
        bu1.draw(g);
        g.drawImage(bg9.getImage(), 410, 170, bg4.getImageObserver());
        g.drawImage(bg10.getImage(), 415, 255, bg4.getImageObserver());
        g.drawImage(bg11.getImage(), 420, 325, bg4.getImageObserver());
        //g.drawImage(bg12.getImage(), 570, 490, bg4.getImageObserver());
        bu2.draw(g);
        //g.drawImage(bg13.getImage(), 655, 529, bg4.getImageObserver());
        bu3.draw(g);
        //g.drawImage(bg14.getImage(), 724, 515, bg4.getImageObserver());
        bu4.draw(g);
    }

}