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

public class MainCanvas extends Canvas {
    public static final int WIDTH =800;
    public static final int HEIGHT = 600;
    public GraphicsContext graphicsContext = this.getGraphicsContext2D();

    //Hero对象
    Hero hero = new Hero();
    //冲击波对象
    BladeWind bladeWind;

    //此方法用来设置hero初始值
    void setup(){
        hero.setX(200);
        hero.setY(450);
    }



    public MainCanvas() throws InterruptedException {
        this.setWidth(WIDTH);
        this.setHeight(HEIGHT);
        this.setFocusTraversable(true);
        setup();
        paintAll();


        new Thread(()-> {
            System.out.println("线程开始");
            this.addEventHandler(KeyEvent.KEY_PRESSED,new EventHandler<KeyEvent>() {
                @Override
                public void handle(KeyEvent event) {
                    onKeyPressed(event);
                }
            });
            this.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent event) {
                }
            });
            this.addEventHandler(KeyEvent.KEY_RELEASED, new EventHandler<KeyEvent>() {
                @Override
                public void handle(KeyEvent event) {
                    if (event.getCode() == KeyCode.Z){
                        hero.stopAttack();
                    }else if (event.getCode() == KeyCode.X){
                        hero.stopAttack();
                    }else if (event.getCode() == KeyCode.UP){
                        hero.stopAttack();
                    }
                }
            });
        }).start();

        //此线程用来不断刷新画面
        new Thread(()->{
            while (true) {
                paintAll();
                try {
                    Thread.sleep(50);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();

    }

    private void onKeyPressed(KeyEvent event) {
        if(event.getCode() == KeyCode.LEFT){
            hero.setX(hero.getX() - hero.getSpeed());
        }else if(event.getCode() == KeyCode.RIGHT){
            hero.setX(hero.getX() + hero.getSpeed());
        }else if(event.getCode() == KeyCode.UP){
            hero.setY(hero.getY()- hero.getJumpSpeed());
            hero.jump();
        }else if(event.getCode() == KeyCode.DOWN){
            hero.setY(hero.getY()+hero.getSpeed());
        }else if (event.getCode() == KeyCode.Z){
            hero.attack();
        }else if (event.getCode() == KeyCode.X){
            hero.HeavyAttack();


            bladeWind = new BladeWind();
            bladeWind.setX((int) (hero.getX()+hero.getImage().getWidth()));
            bladeWind.setY(hero.getY());

            //单开的唯一的绘制冲击波的线程
            BladeWindThread bladeWindThread = new BladeWindThread(bladeWind);
            bladeWindThread.start();

        }
    }

    public void paintAll(){
        paint();
        paintHero(hero.getX(),hero.getY());
    }

    public void paint(){
        graphicsContext.drawImage(Images.background,0,0);
    }

    public void paintHero(int x,int y){
        graphicsContext.drawImage(hero.getImage(),x,y);
    }

    public void paintBladeWind(int x,int y, BladeWind bladeWind){
        graphicsContext.drawImage(bladeWind.getImage(),x,y);
    }

    class BladeWindThread extends Thread{

        BladeWind bladeWind;

        public BladeWindThread(BladeWind bladeWind){
                this.bladeWind = bladeWind;
        }

        @Override
        public void run() {
            System.out.println("KAISHI");
            while (bladeWind.getX()<=800) {
                paintBladeWind(bladeWind.getX(),bladeWind.getY(),bladeWind);
                bladeWind.setX(bladeWind.getX()+bladeWind.getBladespeed());
                try {
                    Thread.currentThread().sleep(10);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

}
