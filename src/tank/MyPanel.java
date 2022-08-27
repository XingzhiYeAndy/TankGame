package tank;

import org.w3c.dom.ls.LSOutput;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Vector;

public class MyPanel extends JPanel implements KeyListener,Runnable {
    MyTank myTank=null;
    Vector<EnemyTank> enemyTanks=new Vector<>();
    int enemyTankNum=3;
    public MyPanel() {

        myTank=new MyTank(100,100,0,5);
        for (int i = 0; i < enemyTankNum; i++) {
            enemyTanks.add(new EnemyTank(100+i*100,10,2,3));
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode()==KeyEvent.VK_W){
            myTank.moveUp();
        }
        else if (e.getKeyCode()==KeyEvent.VK_D){
            myTank.moveRight();
        }
        else if (e.getKeyCode()==KeyEvent.VK_S){
            myTank.moveDown();
        }
        else if (e.getKeyCode()==KeyEvent.VK_A){
            myTank.moveLeft();
        }else if(e.getKeyCode()==KeyEvent.VK_J){
            myTank.shotEnemyTank();

        }

    }

    @Override
    public void keyReleased(KeyEvent e) {

    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        // draw the table
        g.fillRect(0,0,1000,750);
        //draw my tank
        drawTank(myTank.getX(), myTank.getY(), g, myTank.getDirect(), 1);
        //draw my shot
        if (myTank.shot!=null&&myTank.shot.isLive==true){
            g.draw3DRect(myTank.shot.x,myTank.shot.y,1,1,false);
        }
        //draw enemy tanks
        for (int i = 0; i < enemyTanks.size(); i++) {
            EnemyTank tempTank=enemyTanks.get(i);
            drawTank(tempTank.getX(), tempTank.getY(),g, tempTank.getDirect(),0);
        }
    }

    /**
     *
     * @param x 坦克左上角的x坐标
     * @param y 坦克左上角的y坐标
     * @param g 画笔
     * @param direct 坦克的方向
     * @param type 坦克的类型
     */
    public void drawTank(int x,int y,Graphics g,int direct,int type){

        switch(type) {
            case 0: //我们的坦克
                g.setColor(Color.cyan);
                break;
            case 1:
                g.setColor(Color.yellow);
                break;
        }
        switch (direct){
            case 0:
                g.fill3DRect(x, y,10,60,false );//坦克左轮子
                g.fill3DRect(x+30, y,10,60,false );//坦克右轮子
                g.fill3DRect(x+10, y+10,20,40,false );//坦克盖子
                g.fillOval(x+9,y+20,20,20);//圆盖子
                g.drawLine(x+20,y+30,x+20,y);
                break;
            case 1:
                g.fill3DRect(x, y,60,10,false );//坦克左轮子
                g.fill3DRect(x, y+30,60,10,false );//坦克右轮子
                g.fill3DRect(x+10, y+10,40,20,false );//坦克盖子
                g.fillOval(x+20,y+10,20,20);//圆盖子
                g.drawLine(x+30,y+20,x+60,y+20);
                break;
            case 2:
                g.fill3DRect(x, y,10,60,false );//坦克左轮子
                g.fill3DRect(x+30, y,10,60,false );//坦克右轮子
                g.fill3DRect(x+10, y+10,20,40,false );//坦克盖子
                g.fillOval(x+9,y+20,20,20);//圆盖子
                g.drawLine(x+20,y+30,x+20,y+60);
                break;
            case 3:
                g.fill3DRect(x, y,60,10,false );//坦克左轮子
                g.fill3DRect(x, y+30,60,10,false );//坦克右轮子
                g.fill3DRect(x+10, y+10,40,20,false );//坦克盖子
                g.fillOval(x+20,y+10,20,20);//圆盖子
                g.drawLine(x+30,y+20,x,y+20);
                break;
            default:;


        }
    }

    @Override
    public void run() {
        while (true){
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            this.repaint();
        }


    }
}
