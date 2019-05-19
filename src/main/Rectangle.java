package main;

import javax.swing.*;
import javax.tools.Tool;
import java.awt.*;
import java.awt.event.*;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class Rectangle extends JComponent {
    java.awt.Rectangle oval = new java.awt.Rectangle(10,10,20,20);
    boolean drawOval;
    JFrame popupMenu = new JFrame();
    JTextField jTextField = new JTextField();

    public Rectangle(){

    }

    @Override
    protected void paintComponent(Graphics g) {
       g.drawOval(oval.x,oval.y,oval.width,oval.height);
        work();



        super.paintComponent(g);

    }
    public void work(){
        popupMenu.setSize(200,100);
        popupMenu.setLocation(Toolkit.getDefaultToolkit().getScreenSize().width/2, Toolkit.getDefaultToolkit().getScreenSize().height/2);
        popupMenu.setLayout(new FlowLayout());
        popupMenu.add(jTextField);

        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                System.out.println("0");
                System.out.println(e.getPoint());
                System.out.println(oval.x +  " " + oval.y + " " + oval.width + " " + oval.height);
                java.awt.Rectangle newOval;
                newOval = oval;
                newOval.y = 0;
                newOval.x = 0;
                newOval.height = 30;
                newOval.width = 30;
                if(newOval.contains(e.getPoint())){
                    drawOval = true;
                    //popupMenu.setVisible(true);
                }
                super.mouseEntered(e);
            }
        });
    }
}
