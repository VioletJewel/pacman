/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pacman;

import javax.swing.*;
import java.awt.image.BufferedImage;
import java.awt.Graphics;
import java.awt.event.*;
import java.awt.Color;

/**
 *
 * @author mcclured
 */
public class DrawingPanel extends JPanel implements KeyListener{ 
    private PacManCharacter pacMan;
    private BufferedImage pacManImage;
    private Ghost[] ghosts = new Ghost[4];
    private Wall[] walls = new Wall[4];
    
    public DrawingPanel(){
        super();
        setBackground(Color.white.darker());
        addKeyListener(this);
        
        walls[0] = new Wall("horizontal",28,0,964);
        walls[1] = new Wall("vertical",0,28,964);
        walls[2] = new Wall("vertical",500,28,964);
        walls[3] = new Wall("horizontal",964,0,964);
        
        int pacSpeed = 4;
        pacMan = new PacManCharacter(2,62,pacSpeed);
        
        int ghostSpeed = 4;
        ghosts[0] = new Ghost(40,52,ghostSpeed,Color.orange);
        ghosts[1] = new Ghost(70,62,ghostSpeed,Color.red);
        ghosts[2] = new Ghost(100,72,ghostSpeed,Color.pink);
        ghosts[3] = new Ghost(130,82,ghostSpeed,Color.cyan);
        
        setFocusable(true);
        requestFocus();
    }
    
    public void run(){
          while(true){
              if (pacCollision() == false){
                  pacMan.move();
                  pacMan.changeState();
              }
              for (int i = 0; i <= 3; i ++){
                  if (ghostCollision(ghosts[i]) == false){
                      ghosts[i].move();
                  }
                  else{
                      int random = (int)(Math.random()*2);
                      String input = null;
                      String ghostDir = ghosts[i].getDirection();
                      if ("right".equals(ghostDir)){
                          if (random == 0){
                              input = "up";
                          }
                          else {
                              input = "down";
                          }
                      }
                      if ("left".equals(ghostDir)){
                          if (random == 0){
                              input = "up";
                          }
                          else {
                              input = "down";
                          }
                      }
                      if ("up".equals(ghostDir)){
                          if (random == 0){
                              input = "right";
                          }
                          else {
                              input = "left";
                          }
                      }
                      if ("down".equals(ghostDir)){
                          if (random == 0){
                              input = "left";
                          }
                          else{
                              input = "right";
                          }
                      }
                      ghosts[i].setDirection(input);
                      if (ghostCollision(ghosts[i]) == true){
                          ghosts[i].reverseDirection();
                      }
                  }
              }
              
              for (int i = 0;i <= 3;i++){
                      ghosts[i].changeState();
                      ghosts[i].setDirection(ghosts[i].getDirection());
                  }
            try {
                repaint();
                Thread.sleep(40);
            }
            catch (Exception e) {}

        }
    }
//    
//    
    public boolean pacCollision(){
        for (int i = 0; i < 4; i++){
            String wallOrientation = walls[i].getOrientation();
            int wallMin = walls[i].getMin();
            int wallLen = walls[i].getLen();
            int wallLine = walls[i].getLine();
            String pacDirection = pacMan.getDirection();
            int pacVPos = pacMan.getVPos();
            int pacHPos = pacMan.getHPos();
            int pacSpeed = pacMan.getSpeed();

            if ("up".equals(pacDirection)){
                if ("horizontal".equals(wallOrientation)){
                    if (pacHPos + 26 > wallMin - pacSpeed && pacHPos < wallMin + wallLen + pacSpeed && pacVPos - pacSpeed <= wallLine + 16 && pacVPos > wallLine + 16){
                        if (pacHPos + 26 + pacSpeed > wallMin && pacHPos + 26 < wallMin + 13){
                            pacMan.setHPos(wallMin - 26 - pacSpeed);
                            return false;
                        }
                        if (pacHPos  > wallMin + wallLen - 13 && pacHPos < wallMin + wallLen + pacSpeed){
                            pacMan.setHPos(wallMin + wallLen + pacSpeed);
                            return false;
                        }
                        pacMan.setState(false);
                        pacMan.setVPos(wallLine + 16 + pacSpeed);
                        return true;
                    }
                }
                else{
                    if (pacHPos + 26 > wallLine - pacSpeed && pacHPos < wallLine + 16 + pacSpeed && pacVPos - pacSpeed <= wallMin + wallLen && pacVPos > wallMin + wallLen){
                        if (pacHPos + 26 > wallLine - pacSpeed && pacHPos + 26 < wallLine + 13){
                            pacMan.setHPos(wallLine - 26 - pacSpeed);
                            return false;
                        }
                        if (pacHPos > wallLine + 3 && pacHPos < wallLine + 16 + pacSpeed){
                            pacMan.setHPos(wallLine + 16 + pacSpeed);
                            return false;
                        }
                        pacMan.setState(false);
                        pacMan.setVPos(wallMin + wallLen + pacSpeed);
                        return true;
                    }
                }
            }
          
          
            if ("down".equals(pacDirection)){
                if ("horizontal".equals(wallOrientation)){
                    if (pacHPos + 26 > wallMin - pacSpeed && pacHPos < wallMin + wallLen + pacSpeed && pacVPos + 26 + pacSpeed >= wallLine && pacVPos + 26 < wallLine){
                        if (pacHPos + 26 > wallMin - pacSpeed && pacHPos + 26 < wallMin + 13){
                            pacMan.setHPos(wallMin - 26 - pacSpeed);
                            return false;
                        }
                        if (pacHPos > wallMin + wallLen - 13 && pacHPos < wallMin + wallLen + pacSpeed){
                            pacMan.setHPos(wallMin + wallLen + pacSpeed);
                            return false;
                        }
                        pacMan.setState(false); 
                        pacMan.setVPos(wallLine - 30);
                        return true;
                    }
                }
                else{
                    if (pacHPos + 26 > wallLine - pacSpeed && pacHPos < wallLine + 16 + pacSpeed && pacVPos + pacSpeed >= wallMin - 26 && pacVPos + 26 < wallMin){
                        if (pacHPos + 26 > wallLine - pacSpeed && pacHPos + 26 < wallLine + 13){
                            pacMan.setHPos(wallLine - 26 - pacSpeed);
                            return false;
                        }
                        if (pacHPos > wallLine + 3 && pacHPos < wallLine + 16 + pacSpeed){
                            pacMan.setHPos(wallLine + 16 + pacSpeed);
                            return false;
                        }
                        pacMan.setState(false);
                        pacMan.setVPos(wallMin - 26 - pacSpeed);
                        return true;
                    }
                }
            }
            if ("left".equals(pacDirection)){
                if ("vertical".equals(wallOrientation)){
                    if (pacVPos + 26 > wallMin - pacSpeed && pacVPos < wallMin + wallLen + pacSpeed && pacHPos - pacSpeed <= wallLine + 16 && pacHPos > wallLine + 16){
                        if (pacVPos + 26 > wallMin - pacSpeed && pacVPos + 26 < wallMin + 13){
                            pacMan.setVPos(wallMin - 26 - pacSpeed);
                            return false;
                        }
                        if (pacVPos > wallMin + wallLen - 13 && pacVPos < wallMin + wallLen + pacSpeed){
                            pacMan.setVPos(wallMin + wallLen + pacSpeed);
                            return false;
                        }
                        pacMan.setState(false);
                        pacMan.setHPos(wallLine + 16 + pacSpeed);
                        return true;
                    }
                }
                else{
                    if (pacVPos + 26 > wallLine - pacSpeed && pacVPos < wallLine + 16 + pacSpeed && pacHPos - pacSpeed <= wallMin + wallLen && pacHPos > wallMin + wallLen){
                        if (pacVPos + 26 > wallLine - pacSpeed && pacVPos + 26 < wallLine + 13){
                            pacMan.setVPos(wallLine - 26 - pacSpeed);
                            return false;
                        }
                        if (pacVPos > wallLine + 3 && pacVPos < wallLine + 16 + pacSpeed){
                            pacMan.setVPos(wallLine + 16 + pacSpeed);
                            return false;
                        }
                        pacMan.setState(false);
                        pacMan.setHPos(wallMin + wallLen + pacSpeed);
                        return true;
                    }
                }
            }
            if ("right".equals(pacDirection)){
                if ("vertical".equals(wallOrientation)){
                    if(pacVPos + 26 > wallMin - pacSpeed && pacVPos < wallMin + wallLen + pacSpeed && pacHPos + 26 + pacSpeed >= wallLine && pacHPos + 26 < wallLine){
                        if (pacVPos + 26 > wallMin - pacSpeed && pacVPos + 26 < wallMin + 13){
                            pacMan.setVPos(wallMin - 26 - pacSpeed);
                        }
                        if (pacVPos > wallMin + wallLen - 13 && pacVPos < wallMin + wallLen + pacSpeed){
                            pacMan.setVPos(wallMin + wallLen + pacSpeed);
                            return false;
                        }
                        pacMan.setState(false);
                        pacMan.setHPos(wallLine - 26 - pacSpeed);
                        return true;
                    }
                }
                else {
                    if (pacVPos + 26 > wallLine - pacSpeed && pacVPos < wallLine + 16 + pacSpeed && pacHPos + 26 + pacSpeed > wallMin - pacSpeed && pacHPos + 26 < wallMin){
                        if (pacVPos + 26 > wallLine - pacSpeed && pacVPos + 26 < wallLine + 13){
                            pacMan.setVPos(wallLine - 26 - pacSpeed);
                            return false;
                        }
                        if (pacVPos > wallLine + 3 && pacVPos < wallLine + 16 + pacSpeed){
                            pacMan.setVPos(wallLine + 16 + pacSpeed);
                            return false;
                        }
                        pacMan.setState(false);
                        pacMan.setHPos(wallMin - 26 - pacSpeed);
                        return true;
                    }
                }
            }
        }
        
        return false;
    }
    
    public boolean ghostCollision(Ghost ghost){
        for (int i = 0; i < 4; i++){
            String wallOrientation = walls[i].getOrientation();
            int wallMin = walls[i].getMin();
            int wallLen = walls[i].getLen();
            int wallLine = walls[i].getLine();
//            for (int ii = 0; ii <= 3; ii++){
            String ghostDirection = ghost.getDirection();
            int ghostVPos = ghost.getVPos();
            int ghostHPos = ghost.getHPos();
            int ghostSpeed = ghost.getSpeed();

            if ("up".equals(ghostDirection)){
                if ("horizontal".equals(wallOrientation)){
                    if (ghostHPos + 26 > wallMin - ghostSpeed && ghostHPos < wallMin + wallLen + ghostSpeed && ghostVPos - ghostSpeed <= wallLine + 16 && ghostVPos > wallLine + 16){
                        ghost.setVPos(wallLine + 16 + ghostSpeed);
                        return true;
                    }
                }
                else{
                    if (ghostHPos + 26 > wallLine - ghostSpeed && ghostHPos < wallLine + 16 + ghostSpeed && ghostVPos - ghostSpeed <= wallMin + wallLen && ghostVPos > wallMin + wallLen){
                        ghost.setVPos(wallMin + wallLen + ghostSpeed);
                        return true;
                    }
                }
            }


            if ("down".equals(ghostDirection)){
                if ("horizontal".equals(wallOrientation)){
                    if (ghostHPos + 26 > wallMin - ghostSpeed && ghostHPos < wallMin + wallLen + ghostSpeed && ghostVPos + 26 + ghostSpeed >= wallLine && ghostVPos + 26 < wallLine){
                        ghost.setVPos(wallLine - 30);
                        return true;
                    }
                }
                else{
                    if (ghostHPos + 26 > wallLine - ghostSpeed && ghostHPos < wallLine + 16 + ghostSpeed && ghostVPos + ghostSpeed >= wallMin - 26 && ghostVPos + 26 < wallMin){
                        ghost.setVPos(wallMin - 26 - ghostSpeed);
                        return true;
                    }
                }
            }
            if ("left".equals(ghostDirection)){
                if ("vertical".equals(wallOrientation)){
                    if (ghostVPos + 26 > wallMin - ghostSpeed && ghostVPos < wallMin + wallLen + ghostSpeed && ghostHPos - ghostSpeed <= wallLine + 16 && ghostHPos > wallLine + 16){
                        ghost.setHPos(wallLine + 16 + ghostSpeed);
                        return true;
                    }
                }
                else{
                    if (ghostVPos + 26 > wallLine - ghostSpeed && ghostVPos < wallLine + 16 + ghostSpeed && ghostHPos - ghostSpeed <= wallMin + wallLen && ghostHPos > wallMin + wallLen){
                        pacMan.setHPos(wallMin + wallLen + ghostSpeed);
                        return true;
                    }
                }
            }
            if ("right".equals(ghostDirection)){
                if ("vertical".equals(wallOrientation)){
                    if(ghostVPos + 26 > wallMin - ghostSpeed && ghostVPos < wallMin + wallLen + ghostSpeed && ghostHPos + 26 + ghostSpeed >= wallLine && ghostHPos + 26 < wallLine){
                        ghost.setHPos(wallLine - 26 - ghostSpeed);
                        return true;
                    }
                }
                else {
                    if (ghostVPos + 26 > wallLine - ghostSpeed && ghostVPos < wallLine + 16 + ghostSpeed && ghostHPos + 26 + ghostSpeed > wallMin - ghostSpeed && ghostHPos + 26 < wallMin){
                        ghost.setHPos(wallMin - 26 - ghostSpeed);
                        return true;
                    }
                }
            }
        }
        return false;
    }
    
    public BufferedImage drawPacMan(Graphics g,PacManCharacter pacMan){
        BufferedImage img = new BufferedImage(26,26,BufferedImage.TYPE_INT_RGB);
        Graphics pacImage = img.getGraphics();
        pacImage.setColor(getBackground());
        pacImage.fillRect(0,0,26,26);
        
        pacImage.setColor(pacMan.getColor());
        String pacDirection = pacMan.getDirection();
        boolean pacOpen = pacMan.getState();
        
        if ("left".equals(pacDirection)){
            if (pacOpen == false){
                pacImage.fillRect(6,0,10,2);  //first (top)
                pacImage.fillRect(2,2,18,2);  //second
                pacImage.fillRect(0,4,22,4);  //third (two box height)
                pacImage.fillRect(4,8,10,2);  //fourth left (small)
                pacImage.fillRect(10,10,4,2);  //fifth left (small)
                pacImage.fillRect(10,14,4,2);  //sixth left (small)
                pacImage.fillRect(4,16,10,2);  //seventh left (small)
                pacImage.fillRect(0,18,22,4);  //eighth (two box height)
                pacImage.fillRect(14,8,10,10);  //big squre (right)
                pacImage.fillRect(2,22,18,2);  //ninth
                pacImage.fillRect(6,24,10,2);  //tenth (bottom)
            }
            else {
                pacImage.fillRect(4,0,10,4); //first (top)
                pacImage.fillRect(6,4,8,2); //second left
                pacImage.fillRect(8,6,6,2); //third left
                pacImage.fillRect(10,8,4,2); //fourth left
                pacImage.fillRect(12,10,2,2); //fifth left (just above divide)
                pacImage.fillRect(12,14,2,2); //sixth left (just below divide)
                pacImage.fillRect(10,16,4,2); //seventh left
                pacImage.fillRect(8,18,6,2); //eighth left
                pacImage.fillRect(6,20,8,2); //ninth left
                pacImage.fillRect(4,22,10,4); //tenth (bottom)
                pacImage.fillRect(14,2,4,22); //left on right panel
                pacImage.fillRect(18,4,2,18); //middle on right panel
                pacImage.fillRect(20,8,2,10); //right on right panel
            }
        }
        if ("right".equals(pacDirection)){
            if (pacOpen == false){
                pacImage.fillRect(10,0,10,2);  //first (top)
                pacImage.fillRect(6,2,18,2);  //second
                pacImage.fillRect(4,4,22,4);  //third (two box height)
                pacImage.fillRect(12,8,10,2);  //fourth
                pacImage.fillRect(12,10,4,2);  //fifth
                pacImage.fillRect(12,14,4,2);  //sixth
                pacImage.fillRect(12,16,10,2);  //seventh
                pacImage.fillRect(4,18,22,4);  //eighth  (two height box)
                pacImage.fillRect(6,22,18,2);  //ninth 
                pacImage.fillRect(10,24,10,2);  //tenth
                pacImage.fillRect(2,8,10,10);  //big box left
            }
            else{
                pacImage.fillRect(12,0,10,4);
                pacImage.fillRect(12,4,8,2);
                pacImage.fillRect(12,6,6,2);
                pacImage.fillRect(12,8,4,2);
                pacImage.fillRect(12,10,2,2);
                pacImage.fillRect(12,14,2,2);
                pacImage.fillRect(12,16,4,2);
                pacImage.fillRect(12,18,6,2);
                pacImage.fillRect(12,20,8,2);
                pacImage.fillRect(12,22,10,4);
                pacImage.fillRect(8,2,4,22);  //right on left pannel
                pacImage.fillRect(6,4,2,18);  //middle on left pannel
                pacImage.fillRect(4,8,2,10);  //left on left panel

            }
        }
        //-----------------------------------------------------------------------------------------------------------
        if ("down".equals(pacDirection)){
            if (pacOpen == false){
                pacImage.fillRect(0,10,2,10);  //first (top)
                pacImage.fillRect(2,6,2,18);  //second
                pacImage.fillRect(4,4,4,22);  //third (two box height)
                pacImage.fillRect(8,12,2,10);  //fourth left (small)
                pacImage.fillRect(10,12,2,4);  //fifth left (small)
                pacImage.fillRect(14,12,2,4);  //sixth left (small)
                pacImage.fillRect(16,12,2,10);  //seventh left (small)
                pacImage.fillRect(18,4,4,22);  //eighth (two box height)
                pacImage.fillRect(8,2,10,10);  //big squre (right)
                pacImage.fillRect(22,6,2,18);  //ninth
                pacImage.fillRect(24,10,2,10);  //tenth (bottom)
            }
            else {
                pacImage.fillRect(0,12,4,10); //first (top)
                pacImage.fillRect(4,12,2,8); //second left
                pacImage.fillRect(6,12,2,6); //third left
                pacImage.fillRect(8,12,2,4); //fourth left
                pacImage.fillRect(10,12,2,2); //fifth left (just above divide)
                pacImage.fillRect(14,12,2,2); //sixth left (just below divide)
                pacImage.fillRect(16,12,2,4); //seventh left
                pacImage.fillRect(18,12,2,6); //eighth left
                pacImage.fillRect(20,12,2,8); //ninth left
                pacImage.fillRect(22,12,4,10); //tenth (bottom)
                pacImage.fillRect(2,8,22,4); //left on right panel
                pacImage.fillRect(4,6,18,2); //middle on right panel
                pacImage.fillRect(8,4,10,2); //right on right panel
            }
        }
        if ("up".equals(pacDirection)){
            if (pacOpen == false){
                pacImage.fillRect(0,6,2,10);  //first (top)
                pacImage.fillRect(2,2,2,18);  //second
                pacImage.fillRect(4,0,4,22);  //third (two box height)
                pacImage.fillRect(8,4,2,10);  //fourth
                pacImage.fillRect(10,10,2,4);  //fifth
                pacImage.fillRect(14,10,2,4);  //sixth
                pacImage.fillRect(16,4,2,10);  //seventh
                pacImage.fillRect(18,0,4,22);  //eighth  (two height box)
                pacImage.fillRect(22,2,2,18);  //ninth 
                pacImage.fillRect(24,6,2,10);  //tenth
                pacImage.fillRect(8,14,10,10);  //big box left
            }
            else{
                pacImage.fillRect(0,4,4,10);
                pacImage.fillRect(4,6,2,8);
                pacImage.fillRect(6,8,2,6);
                pacImage.fillRect(8,10,2,4);
                pacImage.fillRect(10,12,2,2);
                pacImage.fillRect(14,12,2,2);
                pacImage.fillRect(16,10,2,4);
                pacImage.fillRect(18,8,2,6);
                pacImage.fillRect(20,6,2,8);
                pacImage.fillRect(22,4,4,10);
                pacImage.fillRect(2,14,22,4);  //right on left pannel
                pacImage.fillRect(4,18,18,2);  //middle on left pannel
                pacImage.fillRect(8,20,10,2);  //left on left panel

            }
        }
        return img;
    }
    public BufferedImage drawGhost(Graphics g,Ghost ghost){
        BufferedImage img = new BufferedImage(28,28,BufferedImage.TYPE_INT_RGB);
        Graphics ghostImage = img.getGraphics();
        
        ghostImage.setColor(getBackground());
        ghostImage.fillRect(0,0,28,28);
        
        ghostImage.setColor(ghost.getColor());
        
        ghostImage.fillRect(2,6,24,18);  //main
        ghostImage.fillRect(0,12,2,12);  //left panel
        ghostImage.fillRect(26,12,2,12);  //right panel
        ghostImage.fillRect(4,4,20,2);  //bottom top-panel
        ghostImage.fillRect(6,2,16,2);  //middle top-panel
        ghostImage.fillRect(10,0,8,2);  //top top-panel
        
        boolean ghostState = ghost.getState();
        
        if (ghostState == true){
            ghostImage.fillRect(0,24,4,2); //top outer left
            ghostImage.fillRect(0,26,2,2);  //bottom outer left
            ghostImage.fillRect(6,24,6,2);  //top inner left
            ghostImage.fillRect(8,26,4,2);  //bottom inner left
            ghostImage.fillRect(16,24,6,2);  //top inner right
            ghostImage.fillRect(16,26,4,2);  //bottom inner right
            ghostImage.fillRect(24,24,4,2);  //top outer right
            ghostImage.fillRect(26,26,2,2);  //bottom outer right
        }
        else {
            ghostImage.fillRect(0,24,8,2); //top left
            ghostImage.fillRect(2,26,4,2);  //bottom left
            ghostImage.fillRect(10,24,8,2);  //top middle
            ghostImage.fillRect(12,26,4,2);  //bottom middle
            ghostImage.fillRect(20,24,8,2);  //top right
            ghostImage.fillRect(22,26,4,2);  //bottom right
        }
        
        String ghostDirection = ghost.getDirection();
        
        ghostImage.setColor(Color.white);
        
        if ("up".equals(ghostDirection)){
            ghostImage.fillRect(4,4,2,6);
            ghostImage.fillRect(6,2,4,10);
            ghostImage.fillRect(10,4,2,6);
            ghostImage.fillRect(16,4,2,6);
            ghostImage.fillRect(18,2,4,10);
            ghostImage.fillRect(22,4,2,6);
            
            ghostImage.setColor(Color.black);
            ghostImage.fillRect(6,2,4,4);
            ghostImage.fillRect(18,2,4,4);
        }
        if ("down".equals(ghostDirection)){
            ghostImage.fillRect(4,10,2,6);
            ghostImage.fillRect(6,8,4,10);
            ghostImage.fillRect(10,10,2,6);
            ghostImage.fillRect(16,10,2,6);
            ghostImage.fillRect(18,8,4,10);
            ghostImage.fillRect(22,10,2,6);
            
            ghostImage.setColor(Color.black);
            ghostImage.fillRect(6,14,4,4);
            ghostImage.fillRect(18,14,4,4);
        }
        if ("left".equals(ghostDirection)){
            ghostImage.fillRect(2,8,2,6);
            ghostImage.fillRect(4,6,4,10);
            ghostImage.fillRect(8,8,2,6);
            ghostImage.fillRect(14,8,2,6);
            ghostImage.fillRect(16,6,4,10);
            ghostImage.fillRect(20,8,2,6);
            
            ghostImage.setColor(Color.black);
            ghostImage.fillRect(2,10,4,4);
            ghostImage.fillRect(14,10,4,4);
        }
        if ("right".equals(ghostDirection)){
            ghostImage.fillRect(6,8,2,6);
            ghostImage.fillRect(8,6,4,10);
            ghostImage.fillRect(12,8,2,6);
            ghostImage.fillRect(18,8,2,6);
            ghostImage.fillRect(20,6,4,10);
            ghostImage.fillRect(24,8,2,6);
            
            ghostImage.setColor(Color.black);
            ghostImage.fillRect(10,10,4,4);
            ghostImage.fillRect(22,10,4,4);
        }
        
        return img;
    }
    
    public BufferedImage drawWall(Graphics g, Wall wall){
        
        BufferedImage img;
        if ("vertical".equals(wall.getOrientation())){
            img = new BufferedImage(16,wall.getLen(),BufferedImage.TYPE_INT_RGB);
        }
        else{
            img = new BufferedImage(wall.getLen(),16,BufferedImage.TYPE_INT_RGB);
        }
        Graphics wallImage = img.getGraphics();
        
        wallImage.setColor(getBackground());
        
        if ("vertical".equals(wall.getOrientation())){
            wallImage.fillRect(0,0,16,wall.getLen());
        }
        else{
            wallImage.fillRect(0,0,wall.getLen(),16);
        }
        
        
        wallImage.setColor(Color.blue);
        if ("vertical".equals(wall.getOrientation())){
            wallImage.fillRect(4,0,8,2);
            wallImage.fillRect(2,2,2,2);
            wallImage.fillRect(12,2,2,2);
            wallImage.fillRect(0,4,2,wall.getLen()-8);
            wallImage.fillRect(14,4,2,wall.getLen()-8);
            wallImage.fillRect(2,wall.getLen()-4,2,2);
            wallImage.fillRect(12,wall.getLen()-4,2,2);
            wallImage.fillRect(4,wall.getLen()-2,8,2);
        }
        else{
            wallImage.fillRect(0,4,2,8);
            wallImage.fillRect(2,2,2,2);
            wallImage.fillRect(2,12,2,2);
            wallImage.fillRect(4,0,wall.getLen()-8,2);
            wallImage.fillRect(4,14,wall.getLen()-8,2);
            wallImage.fillRect(wall.getLen()-4,2,2,2);
            wallImage.fillRect(wall.getLen()-4,12,2,2);
            wallImage.fillRect(wall.getLen()-2,4,2,8);            
        }
        return img;
    }
    
    
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        
        for (int i = 0; i < 4; i++){
            BufferedImage testWallImage = drawWall(g,walls[i]);
            if ("vertical".equals(walls[i].getOrientation())){
                g.drawImage(testWallImage,walls[i].getLine(),walls[i].getMin(),Color.blue,this);
            }
            else{
                g.drawImage(testWallImage,walls[i].getMin(),walls[i].getLine(),Color.blue,this);
            }
        }
        
            BufferedImage pacManImage = drawPacMan(g,pacMan);
            g.drawImage(pacManImage,pacMan.getHPos(),pacMan.getVPos(),Color.white,this);
        
        BufferedImage[] images = new BufferedImage[4];
        
        for (int count = 0;count <= 3;count++){
            images[count] = drawGhost(g,ghosts[count]);
            g.drawImage(images[count],ghosts[count].getHPos(),ghosts[count].getVPos(),Color.white,this);
        }
    }
    public void keyPressed (KeyEvent e) {
        
        if (e.getKeyCode()==(KeyEvent.VK_UP)){
            pacMan.setDirection("up");
        }
        
        if (e.getKeyCode()==(KeyEvent.VK_DOWN)){
            pacMan.setDirection("down");
        }
        
        if (e.getKeyCode()==(KeyEvent.VK_LEFT) ){
            pacMan.setDirection("left");
        }
        if (e.getKeyCode()==(KeyEvent.VK_RIGHT)){
            pacMan.setDirection("right");
        }
        //--------------------------------------------------------------------------------------------------
        if (e.getKeyCode()==(KeyEvent.VK_W)){
            for (int count = 0; count <= 3; count++){
                ghosts[count].setDirection("up");
            }
        }
        
        if (e.getKeyCode()==(KeyEvent.VK_S)){
            for (int count = 0; count <= 3; count++){
                ghosts[count].setDirection("down");
            }
        }
        
        if (e.getKeyCode()==(KeyEvent.VK_A) ){
            for (int count = 0; count <= 3; count++){
                ghosts[count].setDirection("left");
            }
        }
        if (e.getKeyCode()==(KeyEvent.VK_D)){
            for (int count = 0; count <= 3; count++){
                ghosts[count].setDirection("right");
            }
        }
//        
//        if (e.getKeyCode()==(KeyEvent.VK_SPACE)){
//            String rest = "rest";
//            while (rest.equals("rest")){
//                if (e.getKeyCode()!=(KeyEvent.VK_SPACE)){
//                    rest = "stop";
//                }
//            }
//            rest = "rest";
//            while (rest.equals("rest")){
//                if (e.getKeyCode() == (KeyEvent.VK_SPACE)){
//                    rest = "stop";
//                }
//            }
//        }
    }
    public void keyTyped (KeyEvent e){}
    public void keyReleased (KeyEvent e) {}
}
