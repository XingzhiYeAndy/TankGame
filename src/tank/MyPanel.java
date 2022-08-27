package tank;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Vector;

public class MyPanel extends JPanel implements KeyListener, Runnable {
    public static int fps=120;
    MyTank myTank = null;
    Vector<EnemyTank> enemyTanks = new Vector<>();
    int enemyTankNum = 3;
    Vector<Bomb> bombs = new Vector<>();
    Image image1 = null;
    Image image2 = null;
    Image image3 = null;
    public static int panelWidth = 600;
    public static int panelHeight = 600;

    @Override
    public int getWidth() {
        return panelWidth;
    }

    @Override
    public int getHeight() {
        return panelHeight;
    }

    public MyPanel() {
        //设置画布
        // 新建我的坦克
        myTank = new MyTank(100, 100, 0, 6);
        // 循环加入敌人坦克
        for (int i = 0; i < enemyTankNum; i++) {
            EnemyTank enemyTank = new EnemyTank(100 + i * 100, 10, 2, 1);
            new Thread(enemyTank).start();
            Shot shot = new Shot(enemyTank.getX() + 20, enemyTank.getY() + 60, enemyTank.getDirect());
            enemyTank.shots.add(shot);
            new Thread(shot).start();
            enemyTanks.add(enemyTank);
        }
        image1 = Toolkit.getDefaultToolkit().getImage(MyPanel.class.getResource("/bomb_1.gif"));
        image2 = Toolkit.getDefaultToolkit().getImage(MyPanel.class.getResource("/bomb_2.gif"));
        image3 = Toolkit.getDefaultToolkit().getImage(MyPanel.class.getResource("/bomb_3.gif"));
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        switch (e.getKeyCode()) {
            case KeyEvent.VK_W:
                myTank.setDirect(0);
                if (myTank.getY() > (myTank.getSpeed())) {
                    myTank.moveUp();
                } else if (myTank.getY() > 0) {
                    myTank.moveUp(myTank.getY());
                }
                break;
            case KeyEvent.VK_D:
                myTank.setDirect(1);
                if (myTank.getX() < (panelWidth - myTank.getSpeed() - 60)) {
                    myTank.moveRight();
                } else if (myTank.getX() < panelWidth) {
                    myTank.moveRight(panelWidth - myTank.getX() - 60);
                }
                break;
            case KeyEvent.VK_S:

                myTank.setDirect(2);
                if (myTank.getY() < (panelHeight - myTank.getSpeed() - 60)) {
                    myTank.moveDown();
                } else if (myTank.getY() < panelHeight) {
                    myTank.moveDown(panelHeight - myTank.getY() - 60);
                }
                break;

            case KeyEvent.VK_A:
                myTank.setDirect(3);
                if (myTank.getX() > (myTank.getSpeed())) {
                    myTank.moveLeft();
                } else if (myTank.getX() > 0) {
                    myTank.moveLeft(myTank.getX());
                }
                break;
            case KeyEvent.VK_J:
                myTank.shotEnemyTank();
                break;

        }


    }

    @Override
    public void keyReleased(KeyEvent e) {

    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        // draw the table
        g.fillRect(0, 0, panelWidth, panelHeight);
        //draw my tank
        drawTank(myTank.getX(), myTank.getY(), g, myTank.getDirect(), 1);
        //画子弹
        for (int i = 0; i < myTank.shots.size(); i++) {
            if (myTank.shots.get(i).isLive){
                g.draw3DRect(myTank.shots.get(i).x, myTank.shots.get(i).y, 1, 1, false);
            }else{
                myTank.shots.remove(i);
                i--;
            }
        }

        //draw enemy tanks and shots
        for (int i = 0; i < enemyTanks.size(); i++) {

            EnemyTank tempTank = enemyTanks.get(i);
            if (tempTank.islive) {
                drawTank(tempTank.getX(), tempTank.getY(), g, tempTank.getDirect(), 0);
                for (int j = 0; j < tempTank.shots.size(); j++) {
                    Shot shot = tempTank.shots.elementAt(j);
                    if (shot.isLive == true) {
                        g.draw3DRect(shot.x, shot.y, 1, 1, false);
                    } else {
                        tempTank.shots.remove(shot);
                        j--;
                    }
                }
            } else {
                enemyTanks.remove(i);
                i--;
            }


        }
        // 画炸弹
        for (int i = 0; i < bombs.size(); i++) {
            Bomb bomb = bombs.get(i);
            if (bomb.isLive) {
                if (bomb.life > 6) {
                    g.drawImage(image1, bomb.x, bomb.y, 60, 60, this);
                } else if (bomb.life > 3) {
                    g.drawImage(image2, bomb.x, bomb.y, 60, 60, this);
                } else if (bomb.life > 0) {
                    g.drawImage(image3, bomb.x, bomb.y, 60, 60, this);
                }
                bomb.lifeDown();
                if (bomb.life == 0) {
                    bomb.isLive = false;
                }
            } else {
                bombs.remove(i);
                i--;
            }


        }


    }

    /**
     * @param x      坦克左上角的x坐标
     * @param y      坦克左上角的y坐标
     * @param g      画笔
     * @param direct 坦克的方向
     * @param type   坦克的类型
     */
    public void drawTank(int x, int y, Graphics g, int direct, int type) {

        switch (type) {
            case 0: //我们的坦克
                g.setColor(Color.cyan);
                break;
            case 1:
                g.setColor(Color.yellow);
                break;
        }
        switch (direct) {
            case 0:
                g.fill3DRect(x, y, 10, 60, false);//坦克左轮子
                g.fill3DRect(x + 30, y, 10, 60, false);//坦克右轮子
                g.fill3DRect(x + 10, y + 10, 20, 40, false);//坦克盖子
                g.fillOval(x + 9, y + 20, 20, 20);//圆盖子
                g.drawLine(x + 20, y + 30, x + 20, y);
                break;
            case 1:
                g.fill3DRect(x, y, 60, 10, false);//坦克左轮子
                g.fill3DRect(x, y + 30, 60, 10, false);//坦克右轮子
                g.fill3DRect(x + 10, y + 10, 40, 20, false);//坦克盖子
                g.fillOval(x + 20, y + 10, 20, 20);//圆盖子
                g.drawLine(x + 30, y + 20, x + 60, y + 20);
                break;
            case 2:
                g.fill3DRect(x, y, 10, 60, false);//坦克左轮子
                g.fill3DRect(x + 30, y, 10, 60, false);//坦克右轮子
                g.fill3DRect(x + 10, y + 10, 20, 40, false);//坦克盖子
                g.fillOval(x + 9, y + 20, 20, 20);//圆盖子
                g.drawLine(x + 20, y + 30, x + 20, y + 60);
                break;
            case 3:
                g.fill3DRect(x, y, 60, 10, false);//坦克左轮子
                g.fill3DRect(x, y + 30, 60, 10, false);//坦克右轮子
                g.fill3DRect(x + 10, y + 10, 40, 20, false);//坦克盖子
                g.fillOval(x + 20, y + 10, 20, 20);//圆盖子
                g.drawLine(x + 30, y + 20, x, y + 20);
                break;
            default:
                ;


        }
    }

    @Override
    public void run() {
        int waiting=1000/fps;
        while (true) {
            try {
                Thread.sleep(waiting);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            // 判断是否击中敌人坦克
            for (int i = 0; i < myTank.shots.size(); i++) {
                if (myTank.shots.get(i) != null && myTank.shots.get(i).isLive) {
                    for (EnemyTank enemyTank : enemyTanks) {
                        hitTank(myTank.shots.get(i), enemyTank);
                    }

                }
            }

            this.repaint();
        }


    }

    public void hitTank(Shot shot, EnemyTank enemyTank) {
        switch (enemyTank.getDirect()) {
            case 0:
            case 2:
                if (shot.x > enemyTank.getX() && shot.x < (enemyTank.getX() + 40) && shot.y > enemyTank.getY()
                        && shot.y < (enemyTank.getY() + 60)) {
                    shot.isLive = false;
                    enemyTank.islive = false;
                    System.out.println("BOOM! ");
                    Bomb bomb = new Bomb(enemyTank.getX(), enemyTank.getY());
                    bombs.add(bomb);
                }
                break;
            case 1:
            case 3:
                if (shot.x > enemyTank.getX() && shot.x < (enemyTank.getX() + 60) && shot.y > enemyTank.getY() && shot.y < (enemyTank.getY() + 40)) {
                    shot.isLive = false;
                    enemyTank.islive = false;
                    System.out.println("BOOM! ");
                    Bomb bomb = new Bomb(enemyTank.getX(), enemyTank.getY());
                    bombs.add(bomb);
                }
                break;
        }

    }
}
