package pacman;

//import java.awt.*;
//import javax.swing.*;
//import java.awt.event.*;
import java.awt.Color;
//import java.util.*;

public class Ghost {
    private int speed;
    private boolean state;
    private int vPos;
    private int hPos;
    private Color color;
    private String direction;
    
    public Ghost(int h, int v, int speed, Color color){
        this.speed = speed;
        direction = "right";
        state = false;
        this.color = color;
        vPos = v;
        hPos = h;
    }
    public boolean getState(){
        return state;
    }
    public void changeState(){
        if (state == true){
            state = false;
        }
        else {
            state = true;
        }
    }
    public void reverseDirection(){
        if ("up".equals(direction)){
            direction = "down";
        }
        if ("down".equals(direction)){
            direction = "up";
        }
        if ("left".equals(direction)){
            direction = "right";
        }
        if ("right".equals(direction)){
            direction = "left";
        }
    }
    public void setDirection(String direction){
        this.direction = direction;
    }
    public String getDirection(){
        return direction;
    }
    public void move(){
        if (direction.equals("up")){
            vPos = vPos - speed;
        }
        if (direction.equals("down")){
            vPos = vPos + speed;
        }
        if (direction.equals("left")){
            hPos = hPos - speed;
        }
        if (direction.equals("right")){
            hPos = hPos + speed;
        }
    }
    
    public int getSpeed(){
        return speed;
    }
    public void setSpeed(int ghostSpeed){
        this.speed = ghostSpeed;
    }
    public int getVPos(){
        return vPos;
    }
    public int getHPos(){
        return hPos;
    }
    public Color getColor(){
        return color;
    }
    public void setHPos(int hPos){
        this.hPos = hPos;
    }
    public void setVPos(int vPos){
        this.vPos = vPos;
    }
}
