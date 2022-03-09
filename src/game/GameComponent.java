package game;

import java.awt.Graphics;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;



public abstract class GameComponent implements MouseListener,MouseMotionListener{
    public abstract void draw(Graphics g);

}
