package sample.objects;

import javafx.scene.image.Image;
import sample.res.Images;

public class BladeWind {
    //冲击波坐标
    int x;
    int y;
    //冲击波图片大小
    int width;
    int height;

    public int getBladespeed() {
        return bladespeed;
    }

    //冲击波速度
    int bladespeed = 6;


    Image image = Images.bladewind;

    public Image getImage() {
        return image;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public void fireBladeWind(int x, int y){
        this.x = x;
        this.y = y;
    }
}
