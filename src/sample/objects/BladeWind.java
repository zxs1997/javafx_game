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

    boolean isFired = false;
    //冲击波速度
    int bladespeed = 18;
    Image image = Images.bladewind;

    public BladeWind(int x, int y, boolean isFired) {
        this.x = x;
        this.y = y;
        this.isFired = isFired;
    }

    public BladeWind() {
        this.isFired = isFired;
    }

    public boolean isFired() {
        return isFired;
    }

    public void setFired(boolean fired) {
        isFired = fired;
    }

    public int getBladespeed() {
        return bladespeed;
    }

    public Image getImage() {
        return image;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public void fireBladeWind(int x, int y) {
        this.x = x;
        this.y = y;
    }
}
