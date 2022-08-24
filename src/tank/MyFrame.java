package tank;

import javax.swing.*;
import java.awt.*;

public class MyFrame extends JFrame {
    MyPanel myPanel=null;
    public MyFrame() throws HeadlessException {
        myPanel=new MyPanel();
        this.add(myPanel);
        this.setSize(1000,750);
        this.addKeyListener(myPanel);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
    }

}
