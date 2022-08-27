package tank;

public class Bomb {
    int x,y;
    int life =MyPanel.fps/6;//生命周期
    boolean isLive=true;

    public Bomb(int x, int y) {
        this.x = x;
        this.y = y;
    }
    public void lifeDown(){
        if (life>0){
            life--;
        }else {
            isLive=false;
        }
    }
}
