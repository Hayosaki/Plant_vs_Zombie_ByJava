package plants;

import java.awt.Graphics;

public abstract class Bullet {
    public int atk;
    public int buff=0;
    public double speed=600;
    public double X;
    public double Y;
    public abstract void draw(Graphics g);
}