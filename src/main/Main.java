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
    Boolean paint = false;
    JComboBox box = new JComboBox();
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
    public String readFile(String filename){
        char c;
        String end = "";

        try {
        FileInputStream fis = new FileInputStream(filename);
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
        JFrame frame = new JFrame();
        JPanel panel = new JPanel();
        JComboBox comboBox = new JComboBox();
        JComboBox allFiles = new JComboBox();
        File Cfiles;

        Cfiles = File.listRoots()[0];
                for(File file : File.listRoots()){
            comboBox.addItem(file);

        }
        for (File file :  Cfiles.listFiles()){
            allFiles.addItem(file);
        }
        allFiles.addItemListener(e -> {


            System.out.println(e.getItem().toString());
            File tempFile = new File(e.getItem().toString());
            System.out.println(tempFile.isDirectory());
            if(!tempFile.isDirectory()){
                System.out.println(readFile(tempFile.getPath()));
            }
            System.out.println(tempFile.listFiles());
            File[] files = tempFile.listFiles();
            box.removeAllItems();

            for(File file : files){


            box.addItem(file);

            }
            panel.add(box);
        });


        panel.add(comboBox);
        panel.add(allFiles);

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
