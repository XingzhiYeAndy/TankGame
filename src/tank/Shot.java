package tank;

public class Shot implements Runnable {
    public int x;
    public int y;
    public int direct;
    public int speed=500/MyPanel.fps;
    boolean isLive=true;

    public Shot(int x, int y, int direct) {
        this.x = x;
        this.y = y;
        this.direct = direct;

    }

    @Override
    public void run() {
        int sleeping =1000/MyPanel.fps;
        while (true){
            try {
                Thread.sleep(sleeping);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
            switch (direct){
                case 0:
                    y-=speed;
                    break;
                case 1:
                    x+=speed;
                    break;
                case 2:
                    y+=speed;
                    break;
                case 3:
                    x-=speed;
                    break;
            }
//            System.out.println(x+" "+y);
            if (!(x>=0&&x<=1000&&y>=0&&y<=750)||!isLive){

                break;
            }
        }
        isLive=false;

    }
}
