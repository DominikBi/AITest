package main;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class Rectangle extends JComponent {
    JTextField textField = new JTextField();
    int xChar = 55;
    boolean drawn = false;
    String text = "";


    @Override
    protected void paintComponent(Graphics g) {
        setFocusable(true);
        g.drawRect(30, 30, 580, 300);
        g.drawRect(50, 50, 500, 50);
        KeyListener keyListener = new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                System.out.println(e.getKeyChar());
                if (e.getKeyChar() == 'a') {
                    try {
                        for (int i = 0; i < System.in.available(); i++) {
                            text += System.in.read();
                        }

                    } catch (IOException e1) {
                        e1.printStackTrace();
                    }

                }
                super.keyTyped(e);
            }


        };
        addKeyListener(keyListener);
        if(drawn){
            g.drawString(text,xChar,75);
            drawn = false;
        }
        work(text);
        super.paintComponent(g);

    }
    public void work(String text){
        char[] textAsCharArray = text.toCharArray();
        Map<String, Integer> operatorPoints = new HashMap<>();
        boolean divide;
        boolean plus;
        boolean minus;
        boolean multiply;
        for(int i = 0; i < textAsCharArray.length; i++){
            if(textAsCharArray[i] == '+'){
                plus = true;
                operatorPoints.put("plus",i);
            }
            else if(textAsCharArray[i] == '-'){
                minus = true;
                operatorPoints.put("minus",i);
            }
            else if(textAsCharArray[i] == '/'){
                divide = true;
                operatorPoints.put("divide",i);
            }
            else if(textAsCharArray[i] == '*'){
                multiply = true;
                operatorPoints.put("multiply",i);

            }


        }


    }
}
