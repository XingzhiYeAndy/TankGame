package demo;

import javax.swing.*;
import java.awt.*;

public class PicturePanel extends JPanel {
    Image image=null;
    public PicturePanel() {
        image=Toolkit.getDefaultToolkit().getImage(PicturePanel.class.getResource("/test.jpg"));
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        g.fillRect(0, 0, 1000, 750);
        g.drawOval(110,110,100,100);
        g.drawImage(image,40,40,40,40,this);
    }
}
