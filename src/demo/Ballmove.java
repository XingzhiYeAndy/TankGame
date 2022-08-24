package demo;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Ballmove extends JFrame {
    ballPanel bp=null;
    public static void main(String[] args) {
        new Ballmove();
    }

    public Ballmove() throws HeadlessException {
        bp=new ballPanel();
        this.add(bp);
        this.addKeyListener(bp);
        this.setSize(1000,1000);
        this.setDefaultCloseOperation(3);
        this.setVisible(true);
    }
}
class ballPanel extends JPanel implements KeyListener {
    int x=10;
    int y=10;
    @Override
    public void paint(Graphics g) {
        super.paint(g);
        g.drawOval(x,y,10,10);

    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode()==KeyEvent.VK_DOWN){
            y--;

        }
        this.repaint();
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}