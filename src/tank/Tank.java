package tank;

public class Tank {
    private int x; //tank x ray
    private int y; //tank y ray
    private int direct;
    private int speed=3;
    public Tank(int x, int y,int direct,int speed) {
        this.x = x;
        this.y = y;
        this.direct=direct;
        this.speed=speed;
    }

    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public int getX() {
        return x;
    }

    public int getDirect() {
        return direct;
    }

    public void setDirect(int direct) {
        this.direct = direct;
    }

    public void setX(int x) {
        this.x = x;
        System.out.println(this.x);
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
        System.out.println(this.x);
    }
    public void moveUp(){
        y-=speed;
        direct=0;
    }
    public void moveDown(){
        y+=speed;
        direct=2;
    }
    public void moveLeft(){
        x-=speed;
        direct=3;
    }
    public void moveRight(){
        x+=speed;
        direct=1;
    }
}
