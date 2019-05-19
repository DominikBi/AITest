package main;

import com.sun.org.apache.xml.internal.security.utils.resolver.implementations.ResolverLocalFilesystem;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.nio.file.FileSystem;
import java.util.ArrayList;
import java.util.logging.FileHandler;

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

    String filename;

    JButton button1 = new JButton("Left");
    JButton button2 = new JButton("Right");
    JTextField textField = new JTextField();
    JTextField type = new JTextField();
    JButton jButton = new JButton("Read");
    private Object ResolverLocalFilesystem;

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
    public String readFile(String filename, String type){
        char c;
        String end = "";
        String home = System.getProperty("user.home");

        String sep = System.getProperty("file.separator");
        try {
        FileInputStream fis = new FileInputStream(home +sep+filename + type);
        while (fis.available() > 0){
            c = (char) fis.read();
            end += c;
        }
    } catch (FileNotFoundException e) {
        System.err.println(e);
        e.printStackTrace();
    } catch (IOException e) {
        System.err.println(e);
        e.printStackTrace();
            }
        return  end;

        }
        public void start() {
            JPanel panel = new JPanel();
            JFrame frame = new JFrame();
            filename = "";
            textField.setPreferredSize(new Dimension(250, 70));
            type.setPreferredSize(new Dimension(250,70));
            panel.setLayout(new FlowLayout());
            panel.add(textField);
            panel.add(type);
            panel.add(jButton);
            ArrayList<Character> al = new ArrayList<Character>();
            int i =0;
            for(char c = 'a'; i < 26 ; c++){
                al.add(c);
                System.out.println(al.get(i));
                i++;
            }
            i=0;
            jButton.addActionListener(e -> {
                filename = textField.getText();
                String fileType = type.getText();
                System.out.println(fileType);
                try {
                    String home = System.getProperty("user.home");
                    String sep = System.getProperty("file.separator");
                    System.out.println(home + sep+ filename + ".txt");
                    File file = new File(home + sep+ filename + fileType);

                    if(file.exists()) {
                        JFrame jFrame = new JFrame();
                        JPanel panel1 = new JPanel();
                        JTextArea jTextArea = new JTextArea();
                        System.out.println(readFile(filename, fileType));
                        jTextArea.append(readFile(filename, fileType));
                        jTextArea.setPreferredSize(new Dimension(300,300));
                        jTextArea.setLocation(Toolkit.getDefaultToolkit().getScreenSize().width/2, 300);
                        jTextArea.setEditable(false);
                        panel1.setLayout(new FlowLayout());
                        panel1.add(jTextArea);

                        jFrame.add(panel1);
                    }
                } catch (NullPointerException e1){
                    System.err.println(e1);
                }

            });



        frame.setLayout(new FlowLayout());
        frame.add(panel);

        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);


        GraphicsDevice gp = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice();
        gp.setFullScreenWindow(frame);



    }
    public double random(int min,int max){
        return Math.random()*max +(max - min - 1);
    }
}
