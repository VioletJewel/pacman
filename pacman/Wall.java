package pacman;

//import java.awt.*;
//import javax.swing.*;
//import java.awt.event.*;
import java.awt.Color;
//import java.util.*;

public class Wall {
    private String orientation;
    private int horizontalLine, verticalLine;
    private int verticalMin,verticalLen;
    private int horizontalMin,horizontalLen;
    private Color color;
    
    public Wall(String orientation, int line, int min, int len){
        this.orientation = orientation;
        if ("vertical".equals(this.orientation)){
            horizontalLine = line;
            verticalMin = min;
            verticalLen = len;
        }
        else{
            verticalLine = line;
            horizontalMin = min;
            horizontalLen = len;
        }
        color = Color.blue;
    }
    public Color getColor(){
        return color;
    }
    public String getOrientation(){
        return orientation;
    }
    public int getMin(){
        if ("vertical".equals(orientation)){
            return verticalMin;
        }
        else{
            return horizontalMin;
        }
    }
    public int getLen(){
        if ("vertical".equals(orientation)){
            return verticalLen;
        }
        else{
            return horizontalLen;
        }
    }
    public int getLine(){
        if ("vertical".equals(orientation)){
            return horizontalLine;
        }
        else{
            return verticalLine;
        }
    }
}
