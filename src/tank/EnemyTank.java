package tank;

import java.util.Vector;

public class EnemyTank extends Tank implements Runnable {

    int magicNum = 0;
    public static final int STEP_LENGTH = 80;

    Vector<Shot> shots = new Vector<>();

    public EnemyTank(int x, int y, int direct, int speed) {

        super(x, y, direct, speed/MyPanel.fps);
    }
    public void shotEnemyTank() {
        if (shots.size() >= 1) {
            return;
        }
        Shot shot = null;
        switch (getDirect()) {
            case 0:
                shot = new Shot(getX() + 20, getY(), 0);
                break;
            case 1:
                shot = new Shot(getX() + 60, getY() + 20, 1);
                break;
            case 2:
                shot = new Shot(getX() + 20, getY() + 60, 2);
                break;
            case 3:
                shot = new Shot(getX(), getY() + 20, 3);
                break;
        }
        shots.add(shot);
        new Thread(shot).start();
    }
    @Override
    public void run() {
        int waitSecond=1000/MyPanel.fps;

        while (isLive) {

            magicNum = (int) (Math.random() * STEP_LENGTH);

            switch (getDirect()) {
                case 0:
                    for (int i = 0; i < magicNum; i++) {
                        setDirect(0);
                        if (getY() > (getSpeed())) {
                            moveUp();
                        } else if (getY() > 0) {
                            moveUp(getY());
                        }
                        shotEnemyTank();
                        try {
                            Thread.sleep(waitSecond);
                        } catch (InterruptedException e) {
                            throw new RuntimeException(e);
                        }
                    }
                    setDirect(magicNum % 4);
                    break;
                case 1:
                    for (int i = 0; i < magicNum; i++) {
                        setDirect(1);
                        if (getX() < (MyPanel.panelWidth - getSpeed() - 60)) {
                            moveRight();
                        } else if (getX() < MyPanel.panelWidth) {
                            moveRight(MyPanel.panelWidth - getX() - 60);
                        }
                        shotEnemyTank();
                        try {
                            Thread.sleep(waitSecond);
                        } catch (InterruptedException e) {
                            throw new RuntimeException(e);
                        }
                    }
                    setDirect(magicNum % 4);
                    break;
                case 2:
                    for (int i = 0; i < magicNum; i++) {
                        setDirect(2);
                        if (getY() < (MyPanel.panelHeight - getSpeed() - 60)) {
                            moveDown();
                        } else if (getY() < MyPanel.panelHeight) {
                            moveDown(MyPanel.panelHeight - getY() - 60);
                        }
                        shotEnemyTank();
                        try {
                            Thread.sleep(waitSecond);
                        } catch (InterruptedException e) {
                            throw new RuntimeException(e);
                        }

                        setDirect(magicNum % 4);

                    }
                    break;
                case 3:
                    for (int i = 0; i < magicNum; i++) {
                        setDirect(3);
                        if (getX() > (getSpeed())) {
                            moveLeft();
                        } else if (getX() > 0) {
                            moveLeft(getX());
                        }

                        shotEnemyTank();
                        try {
                            Thread.sleep(waitSecond);
                        } catch (InterruptedException e) {
                            throw new RuntimeException(e);
                        }
                    }
                    setDirect(magicNum % 4);
                    break;
            }


        }


    }
}
