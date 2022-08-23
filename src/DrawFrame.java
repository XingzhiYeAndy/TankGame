import javax.swing.*;
import java.awt.*;

public class DrawFrame extends JFrame{
    private MyPanel mp=null;
    public static void main(String[] args) {
        new DrawFrame();

    }

    public DrawFrame() throws HeadlessException {
        mp=new MyPanel();
        this.add(mp);
        this.setSize(900,800);
        this.setVisible(true);
    }
}
class MyPanel extends JPanel{
    @Override
    public void paint(Graphics g) {
        super.paint(g);
        g.drawOval(10,10,100,100);
    }
}