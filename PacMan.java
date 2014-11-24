package pacman;

import java.awt.*;
import javax.swing.*;
//import java.awt.event.*;
//import java.awt.Color;
//import java.util.*;

public class PacMan extends JFrame {
    
    public PacMan(){
        JFrame frame = new JFrame();
        frame.setBounds(0,0,1280,996);
        frame.setLayout(new GridLayout(1,1,3,3));
        frame.setLocationRelativeTo(null);
        
        Container contentPane = frame.getContentPane();
        DrawingPanel dp = new DrawingPanel();
        contentPane.add(dp);
        
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        frame.setTitle("Pacman");
        frame.setUndecorated(true);
        frame.setVisible(true);
        dp.run();
    }
    public static void main(String[] args) {
        PacMan main = new PacMan();
	}
}
