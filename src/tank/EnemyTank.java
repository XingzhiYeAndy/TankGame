package tank;

import java.util.Vector;

public class EnemyTank extends Tank implements Runnable{
    public static final int WAIT_SECOND=50;
    int magicNum=0;
    public static final int STEP_LENGTH=80;
    boolean islive =true;
    Vector<Shot> shots=new Vector<>();
    public EnemyTank(int x, int y, int direct, int speed) {

        super(x, y, direct, speed);
    }

    @Override
    public void run() {

        while (islive){

            magicNum=(int)(Math.random()*STEP_LENGTH);

            switch (getDirect()){
                case 0:
                    for (int i = 0; i < magicNum; i++) {
                        moveUp();
                        try {
                            Thread.sleep(WAIT_SECOND);
                        } catch (InterruptedException e) {
                            throw new RuntimeException(e);
                        }
                    }
                    setDirect(magicNum%4);
                    break;
                case 1:
                    for (int i = 0; i < magicNum; i++) {
                        moveRight();
                        try {
                            Thread.sleep(WAIT_SECOND);
                        } catch (InterruptedException e) {
                            throw new RuntimeException(e);
                        }
                    }
                    setDirect(magicNum%4);
                    break;
                case 2:
                    for (int i = 0; i < magicNum; i++) {
                        moveDown();
                        try {
                            Thread.sleep(WAIT_SECOND);
                        } catch (InterruptedException e) {
                            throw new RuntimeException(e);
                        }
                    }
                    setDirect(magicNum%4);
                    break;
                case 3:
                    for (int i = 0; i < magicNum; i++) {
                        moveLeft();
                        try {
                            Thread.sleep(WAIT_SECOND);
                        } catch (InterruptedException e) {
                            throw new RuntimeException(e);
                        }
                    }
                    setDirect(magicNum%4);
                    break;
            }




        }


    }
}
