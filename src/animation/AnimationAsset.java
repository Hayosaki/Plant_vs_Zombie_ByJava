package animation;

import java.awt.Color;
import java.util.ArrayList;

import javax.swing.ImageIcon;

public class AnimationAsset {
    public ArrayList<ImageIcon> img = new ArrayList<ImageIcon>();
    public int frameAmount;


    /*
     * 创建一个关键帧动画
     *
     * @param path图片所在文件夹
     * @param name图片文件的名字
     * @param amount关键帧总数
     */
    public AnimationAsset(String path, String name, int amount) {
        super();
        this.frameAmount = amount;
        for (int i = 0; i < amount; i++) {
            img.add(new ImageIcon(path + "\\" + name +"_"+ i + ".png"));
        }
    }
    /*
     * 创建一个关键帧动画,要求命名规则为Frame0.png,Frame1.png.......
     * @param path图片所在文件夹
     * @param amount关键帧总数
     */
    public AnimationAsset(String path, int amount) {
        super();
        this.frameAmount = amount;
        for (int i = 0; i < amount; i++) {
            img.add(new ImageIcon(path + "\\" + "Frame"+ i + ".png"));
        }
    }
}