package main;

import javax.swing.*;
import java.awt.*;

public class Main {
    private double clickedLeft;
    private double clickedRight;
    private int clicked;
    private double probabilityLeft;
    private double probabilityRight;
    private int streakLeft;
    private int streakRight;
    int i = 1;
    int pastI = 0;
    int newPastI;
    Rectangle r = new Rectangle();
    JButton button1 = new JButton("Left");
    JButton button2 = new JButton("Right");
    JTextField textField = new JTextField();

    public static void main(String[] args){
       Main main = new Main();
       main.start();


    }
    public void add(){

        button1.addActionListener(e -> {
            clicked++;
            clickedLeft++;

            probabilityLeft = clickedLeft/clicked;
            probabilityRight = (clickedRight)/clicked;

            streakLeft++;
            streakRight = 0;
            double rand = random(0,1);

            if(rand < probabilityRight){

                textField.setText("Right");
                System.out.println("RIGHT");
            }else if(rand  < probabilityLeft){
                textField.setText("Left");
                System.out.println("LEFT");
            }
            else{
                textField.setText("Dont know");
            }
            System.out.println("Left: " + probabilityLeft + " Right: " + probabilityRight + " " + random(0,1));

        });

        button2.addActionListener(e -> {
            probabilityLeft = clickedLeft/clicked;
            probabilityRight = (clickedRight)/clicked;
            clicked++;
            clickedRight++;
            streakRight++;
            streakLeft = 0;
            double rand = random(0,1);

            if(rand < probabilityRight){
                textField.setText("Right");
                System.out.println("RIGHT");
            }else if(rand < probabilityLeft){
                textField.setText("Left");
                System.out.println("LEFT");
            }
            else{
                textField.setText("Dont know");
            }
            System.out.println("Left: " + probabilityLeft + " Right: " + probabilityRight);
        });

    }
    public void start(){
     JPanel panel = new JPanel();
        JFrame frame = new JFrame();
        r.setPreferredSize(Toolkit.getDefaultToolkit().getScreenSize());
        panel.add(r);
        textField.setPreferredSize(new Dimension(100,100));
        panel.add(textField);




        panel.setLayout(new FlowLayout());
        frame.add(panel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(Toolkit.getDefaultToolkit().getScreenSize());
        frame.setVisible(true);
    }
    public double random(int min,int max){
        return Math.random()*max +(max - min - 1);
    }
}
