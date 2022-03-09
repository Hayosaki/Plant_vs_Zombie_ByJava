package game;

import java.awt.geom.Point2D;

public class Vector2D extends Point2D.Double{

    /**
     *
     */
    private static final long serialVersionUID = 1L;
    public Vector2D() {
        super();
        // TODO Auto-generated constructor stub
    }

    public Vector2D(double x, double y) {
        super();
        this.x = x;
        this.y = y;
    }

    //矢量的减法
    public Vector2D minusVec(Vector2D vec2d) {
        return new Vector2D(x-vec2d.x, y-vec2d.y);
    }

    //矢量的加法
    public Vector2D addVec(Vector2D vec2d) {
        return new Vector2D(x+vec2d.x, y+vec2d.y);
    }

    //求矢量模
    public double getNorm() {
        return Math.sqrt(x*x+y*y);
    }

    //矢量的点乘
    public double multiplyDot(Vector2D vec2d) {
        return x*vec2d.x+y*vec2d.y;
    }

    //矢量的叉乘
    public double multiplyCross(Vector2D vec2d) {
        return this.x*vec2d.y-this.y*vec2d.x;
    }

    //求矢量的单位向量
    public void normalize() {
        double  nor=getNorm();
        x=x/nor;
        y=y/nor;
    }
    @Override
    public String toString() {
        return "Vector2D [x=" + x + ", y=" + y + "]";
    }

}
