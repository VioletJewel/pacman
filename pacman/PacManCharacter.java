package pacman;

//import java.awt.*;
//import javax.swing.*;
//import java.awt.event.*;
import java.awt.Color;
//import java.util.*;

public class PacManCharacter {
    private String direction;
    private int speed;
    private Color color;
    private int vPos;
    private int hPos;
    private boolean open;
    
    
    public PacManCharacter(int hPos,int vPos,int speed){
        this.hPos = hPos;
        this.vPos = vPos;
        this.speed = speed;
        open = true;
        direction = "down";
        color = Color.YELLOW;
    }
    
    public void setState(boolean pacOpen){
        this.open = pacOpen;
    }
    public boolean getState(){
        return open;
    }
    public void changeState(){
        if (open == true){
            open = false;
        }
        else {
            open = true;
        }
    }
    public void setDirection(String direction){
        this.direction = direction;
    }
    public String getDirection(){
        return direction;
    }
    public int getSpeed(){
        return speed;
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
    public void testMove(){
        if (direction.equals("up")){
            if (vPos == vPos - speed){
                
            }
        }
        if (direction.equals("down")){
            if (vPos == vPos + speed){
                
            }
        }
        if (direction.equals("left")){
            if (hPos == hPos - speed){
                
            }
        }
        if (direction.equals("right")){
            if (hPos == hPos + speed){
                
            }
        }
    }
    public Color getColor(){
        return color;
    }
    public void setColor(Color color){
        this.color = color;
    }
    public int getVPos(){
        return vPos;
    }
    public int getHPos(){
        return hPos;
    }
    public void setVPos(int vPos){
        this.vPos = vPos;
    }
    public void setHPos(int hPos){
        this.hPos = hPos;
    }
}
