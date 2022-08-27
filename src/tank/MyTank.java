package tank;

public class MyTank extends Tank {
    Shot shot=null;

    public MyTank(int x, int y, int direct, int speed) {
        super(x, y, direct, speed);
    }
    public void shotEnemyTank(){
        if (shot!=null){
            shot.isLive=false;
        }
        switch (getDirect()){
            case 0:
                shot =new Shot(getX()+20,getY(),0);
                break;
            case 1:
                shot =new Shot(getX()+60,getY()+20,1);
                break;
            case 2:
                shot =new Shot(getX()+20,getY()+60,2);
                break;
            case 3:
                shot =new Shot(getX(),getY()+20,3);
                break;
        }
        new Thread(shot).start();
    }
}
