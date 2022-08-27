package demo;

import tank.MyPanel;

import javax.swing.*;
import java.awt.*;

public class PictureFrame extends JFrame {
    PicturePanel myPanel=null;
    public static void main(String[] args) {
        new PictureFrame();
    }

    public PictureFrame() throws HeadlessException {
        myPanel=new PicturePanel();
        this.add(myPanel);
        this.setSize(1000,750);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
    }
}
