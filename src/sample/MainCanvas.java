package sample;

import javafx.event.EventHandler;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import sample.objects.BladeWind;
import sample.objects.Hero;
import sample.res.Images;

import java.util.ArrayList;

public class MainCanvas extends Canvas {
    public static final int WIDTH = 800;
    public static final int HEIGHT = 600;
    public GraphicsContext graphicsContext = this.getGraphicsContext2D();

    //Hero对象
    Hero hero = new Hero();
    //冲击波对象
    BladeWind bladeWind = new BladeWind();
    //设置发射冲击波开关
    boolean STATE = false;
    //设置冲击波的ArrayList
    ArrayList<BladeWind> bwal = new ArrayList<>();

    public MainCanvas() throws InterruptedException {
        this.setWidth(WIDTH);
        this.setHeight(HEIGHT);
        this.setFocusTraversable(true);
        setup();
        paintAll();


        new Thread(() -> {
            System.out.println("线程开始");
            this.addEventHandler(KeyEvent.KEY_PRESSED, new EventHandler<KeyEvent>() {
                @Override
                public void handle(KeyEvent event) {
                    onKeyPressed(event);
                }
            });
            this.addEventHandler(KeyEvent.KEY_RELEASED, new EventHandler<KeyEvent>() {
                @Override
                public void handle(KeyEvent event) {
                    if (event.getCode() == KeyCode.X) {
                        hero.stopAttack();
                        STATE = false;
                    } else if (event.getCode() == KeyCode.Z) {
                        hero.stopAttack();
                    } else if (event.getCode() == KeyCode.UP) {
                        hero.stopAttack();
                    }
                }
            });
        }).start();

        //此线程用来不断刷新画面
        new Thread(() -> {
            while (true) {
                //汇出场景和英雄
                paintAll();
                //汇出发射的冲击波
                paintBladeSelectly();
                try {
                    Thread.sleep(50);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();

    }

    private void paintBladeSelectly() {
        if (STATE) {
            //每次按下按键 就在ArrayList里添加一个新的冲击波
            bwal.add(new BladeWind((int) (hero.getX() + hero.getImage().getWidth()), hero.getY(), true));
        }
        //遍历所有冲击波，如果正在发射状态，就画出来
        for (BladeWind bw : bwal
        ) {
            if (bw.isFired() && bw.getX() <= 800) {
                paintBladeWind(bw.getX(), bw.getY(), bw);
                bw.setX(bw.getX() + bw.getBladespeed());
            } else {
                bw.setFired(false);
            }
        }
    }

    //此方法用来设置hero初始值
    void setup() {
        hero.setX(200);
        hero.setY(450);
    }

    private void onKeyPressed(KeyEvent event) {
        if (event.getCode() == KeyCode.LEFT) {
            hero.setX(hero.getX() - hero.getSpeed());
        } else if (event.getCode() == KeyCode.RIGHT) {
            hero.setX(hero.getX() + hero.getSpeed());
        } else if (event.getCode() == KeyCode.UP) {
            hero.setY(hero.getY() - hero.getJumpSpeed());
            hero.jump();
        } else if (event.getCode() == KeyCode.DOWN) {
            hero.setY(hero.getY() + hero.getSpeed());
        } else if (event.getCode() == KeyCode.X) {
            hero.HeavyAttack();
            STATE = true;
        } else if (event.getCode() == KeyCode.Z) {
            hero.attack();
        }
    }


    public void paintAll() {
        paint();
        paintHero(hero.getX(), hero.getY());
    }

    public void paint() {
        graphicsContext.drawImage(Images.background, 0, 0);
    }

    public void paintHero(int x, int y) {
        graphicsContext.drawImage(hero.getImage(), x, y);
    }

    public void paintBladeWind(int x, int y, BladeWind bladeWind) {
        graphicsContext.drawImage(bladeWind.getImage(), x, y);
    }


}
