package main;

import com.sun.org.apache.xml.internal.security.utils.resolver.implementations.ResolverLocalFilesystem;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.nio.file.FileSystem;
import java.nio.file.FileSystems;
import java.nio.file.FileVisitor;
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
    String home = System.getProperty("user.home");
    int maxCharsint;
    String sep = System.getProperty("file.separator");

    String filename;
    JTextArea name = new JTextArea("Filename: ");
    JTextArea types = new JTextArea("File type: ");
    JTextArea chars = new JTextArea("Max chars: ");
    JButton button = new JButton("Find files");
    JButton button1 = new JButton("Left");
    JButton button2 = new JButton("Right");
    JTextField maxChars = new JTextField("2");
    JTextField textField = new JTextField();
    JTextField type = new JTextField(".txt");
    JButton jButton = new JButton("Read");

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

        button.addActionListener(e -> {
            try {
                maxCharsint = Integer.parseInt( maxChars.getText());

            } catch (NumberFormatException ex) {
                JFrame popFrame = new JFrame();
                JPanel popPanel = new JPanel();
                    popFrame.setSize(260,150);
                popFrame.setLocation(Toolkit.getDefaultToolkit().getScreenSize().width/2 - 130,Toolkit.getDefaultToolkit().getScreenSize().height/2-75);
                JTextField popField = new JTextField();
                JButton popButton = new JButton("OK");
                popPanel.setLayout(new BoxLayout(popPanel, BoxLayout.PAGE_AXIS));
                popField.setText("The maximum char has to be a number!");
                popField.setEditable(false);
                popPanel.add(popField);
                popPanel.add(popButton);
                popFrame.add(popPanel);
                popFrame.setVisible(true);
                popButton.addActionListener(e12 -> {
                    popFrame.setVisible(false);


                });
            }


        String randomPath = "";
        int i1 = 0;
        int i2 =0;
        for(int i = 0; i < maxCharsint; i++) {

            i1 = 0;
            for (char c3 = 'a'; i1 < 26; c3++) {
                randomPath += c3;
                File file = new File(home + sep + randomPath + type.getText());

                if (file.exists()) {
                    System.out.println("Found: " + randomPath);


                }
                System.out.println(randomPath.charAt(randomPath.length()-1) + " : " + c3);
                randomPath.replace(randomPath.charAt(randomPath.length()-1),' ');
                i1++;
            }
            System.out.println(randomPath);

        }});


            JPanel panel = new JPanel();
            JFrame frame = new JFrame();
            filename = "";
            maxChars.setPreferredSize(new Dimension(250,70));
            textField.setPreferredSize(new Dimension(250, 70));
            type.setPreferredSize(new Dimension(250,70));
            panel.setLayout(new FlowLayout());
            name.setEditable(false);
            types.setEditable(false);
            chars.setEditable(false);
            panel.add(name);
            panel.add(textField);
            panel.add(types);
            panel.add(type);
            panel.add(chars);
            panel.add(maxChars);
            panel.add(button);
            panel.add(jButton);
            ArrayList<Character> al = new ArrayList<>();
            int i =0;
            for(char c = 'a'; i < 26 ; c++){
                al.add(c);
                System.out.println(al.get(i));
                i++;
            }
            jButton.addActionListener(e -> {
                filename = textField.getText();
                String fileType = type.getText();



                System.out.println(fileType);
                try {
                    String home = System.getProperty("user.home");
                    String sep = System.getProperty("file.separator");

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
                } catch (NullPointerException e1) {
                    System.err.println(e1);

                }
            });



        frame.setLayout(new FlowLayout());
        frame.add(panel);

        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);


        frame.setSize(Toolkit.getDefaultToolkit().getScreenSize());



    }
    public double random(int min,int max){
        return Math.random()*(max - min) +min;
    }
}
