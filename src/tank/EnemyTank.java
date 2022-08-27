package tank;

import java.util.Vector;

public class EnemyTank extends Tank implements Runnable {
    public static final int WAIT_SECOND = 15;
    int magicNum = 0;
    public static final int STEP_LENGTH = 80;
    boolean islive = true;
    Vector<Shot> shots = new Vector<>();

    public EnemyTank(int x, int y, int direct, int speed) {

        super(x, y, direct, speed);
    }

    @Override
    public void run() {

        while (islive) {

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

                        try {
                            Thread.sleep(WAIT_SECOND);
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

                        try {
                            Thread.sleep(WAIT_SECOND);
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
                        try {
                            Thread.sleep(WAIT_SECOND);
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


                        try {
                            Thread.sleep(WAIT_SECOND);
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
