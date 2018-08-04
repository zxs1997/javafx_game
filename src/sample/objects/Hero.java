package sample.objects;

import javafx.scene.Parent;
import javafx.scene.image.Image;
import sample.res.Images;


public class Hero {
    private Image image;//Hero图像
    private int x;//Hero坐标

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

    private int y;
    private int speed = 8;
    private int jumpSpeed = 20;

    public Hero() {
        image = Images.hero;
    }

    public int getJumpSpeed() {
        return jumpSpeed;
    }

    public int getSpeed() {
        return speed;
    }

    public Image getImage() {
        return image;
    }

    public void attack() {
        image = Images.heroAttack;
    }

    public void stopAttack() {
        image = Images.hero;
    }

    public void HeavyAttack() {
        image = Images.heavyAttack;
    }

    public void jump() {
        image = Images.jump;
    }
}
