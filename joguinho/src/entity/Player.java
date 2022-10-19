package entity;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import main.KeyHandler;
import main.GamePanel;
import java.io.IOException;
import javax.imageio.ImageIO;


public class Player extends Entity {
    GamePanel gp;
    KeyHandler keyH;

    public final int screenY;
    public final int screenX;


    
    public Player (GamePanel gp, KeyHandler keyH){
        this.gp= gp;
        this.keyH= keyH;
        setDefautValues();
        setPlayertImage();
         
        screenX=gp.screenWidth/2 -(gp.tileSize/2);
        screenY=gp.screenHeight/2 -(gp.tileSize/2);

        solidArea = new Rectangle(7*gp.scale,12*gp.scale,11*gp.scale,11*gp.scale);

    }

    public void setDefautValues(){

        worldx=100;
        worldy=100;
        speed=4;
        direction="down";
        keyH.directionState="down";
        keyH.idleState=true;
    }

    public void setPlayertImage(){
        
        try { 


            // sprites UP Animation
            up[0]=ImageIO.read(getClass().getResourceAsStream("/Chibi/Andando/N-1.png"));
            up[1]=ImageIO.read(getClass().getResourceAsStream("/Chibi/Andando/N-2.png"));
            up[2]=ImageIO.read(getClass().getResourceAsStream("/Chibi/Andando/N-3.png"));
            up[3]=ImageIO.read(getClass().getResourceAsStream("/Chibi/Andando/N-4.png"));
            
            //sprites Down Animation
            down[0]=ImageIO.read(getClass().getResourceAsStream("/Chibi/Andando/S-1.png"));
            down[1]=ImageIO.read(getClass().getResourceAsStream("/Chibi/Andando/S-2.png"));
            down[2]=ImageIO.read(getClass().getResourceAsStream("/Chibi/Andando/S-3.png"));
            down[3]=ImageIO.read(getClass().getResourceAsStream("/Chibi/Andando/S-4.png"));
           

            //sprites Left animation

            left[0]=ImageIO.read(getClass().getResourceAsStream("/Chibi/Andando/O-1.png"));
            left[1]=ImageIO.read(getClass().getResourceAsStream("/Chibi/Andando/O-2.png"));
            left[2]=ImageIO.read(getClass().getResourceAsStream("/Chibi/Andando/O-3.png"));
            left[3]=ImageIO.read(getClass().getResourceAsStream("/Chibi/Andando/O-4.png"));
           
            //sprites Right animation
            right[0]=ImageIO.read(getClass().getResourceAsStream("/Chibi/Andando/L-1.png"));
            right[1]=ImageIO.read(getClass().getResourceAsStream("/Chibi/Andando/L-2.png"));
            right[2]=ImageIO.read(getClass().getResourceAsStream("/Chibi/Andando/L-3.png"));
            right[3]=ImageIO.read(getClass().getResourceAsStream("/Chibi/Andando/L-4.png"));
           

            //idle up
            idleUp[0]=ImageIO.read(getClass().getResourceAsStream("/Chibi/Respirando/N-1.png"));
            idleUp[1]=ImageIO.read(getClass().getResourceAsStream("/Chibi/Respirando/N-2.png"));
            

            //idle left
            idleLeft[0]=ImageIO.read(getClass().getResourceAsStream("/Chibi/Respirando/O-1.png"));
            idleLeft[1]=ImageIO.read(getClass().getResourceAsStream("/Chibi/Respirando/O-2.png"));
           
            //idle down
            idleDown[0]=ImageIO.read(getClass().getResourceAsStream("/Chibi/Respirando/S-1.png"));
            idleDown[1]=ImageIO.read(getClass().getResourceAsStream("/Chibi/Respirando/S-2.png"));
            


            //idle right
            idleRight[0]=ImageIO.read(getClass().getResourceAsStream("/Chibi/Respirando/L-1.png"));
            idleRight[1]=ImageIO.read(getClass().getResourceAsStream("/Chibi/Respirando/L-2.png"));
            




        } catch (IOException e) {

            e.printStackTrace();
            
        

    }
}


    public void animationState(){
        spriteCounter++;
        idlespriteCounter++;


        if(spriteCounter>8){
            
            spriteNum++;
           
            spriteCounter=0;

            if(spriteNum==4)
            spriteNum=0;
        }

        if(idlespriteCounter>10){
            idleSpriteNum++;
            idlespriteCounter=0;

            if(idleSpriteNum==2)
            idleSpriteNum=0;
        }

    }


    public void update(){

        
        if(keyH.upPress==true && keyH.downPress !=true){
            direction="up";
            keyH.directionState="up";
            
        }

        if(keyH.downPress==true && keyH.upPress !=true){
            direction="down";
            keyH.directionState="down";
           
        }

        if(keyH.leftPress==true && keyH.rightPress !=true){
            direction="left";
            keyH.directionState="left";
            
        }
        
        
        if(keyH.rightPress==true && keyH.leftPress !=true){
            direction="right";
            keyH.directionState="right";
            
        }

        if(keyH.rightPress==false && keyH.leftPress==false && keyH.upPress==false && keyH.downPress==false){

            direction="idle";

        }

        if(keyH.rightPress==true && keyH.leftPress==true){

            direction="idle";

        }

        if(keyH.upPress==true && keyH.downPress==true){

            direction="idle";

        }


        collisionOn=false;
        gp.cCheck.checkTile(this);


        if(collisionOn==false){

            switch(direction){

                case "up":
                worldy-=speed;
                break;
                case "down":
                worldy+=speed;
                break;
                case "left":
                worldx-=speed;
                break;
                case "right":
                worldx+=speed;
                break;
            }
        }




        
            //adds to animation sprite
        animationState();
        
        


    }
    public void draw(Graphics2D g2){

        
        BufferedImage image=null;

        switch(direction){

        case "up":

        image=up[spriteNum];

        break;

        case "down":

        image=down[spriteNum];

        break;

        case "left":

        image=left[spriteNum];

        
        break;

        case "right":


        image=right[spriteNum];

        break;

        case "idle":

        if(keyH.directionState=="right")
        image=idleRight[idleSpriteNum];

        
        if(keyH.directionState=="left")
        image=idleLeft[idleSpriteNum];


        if(keyH.directionState=="up")
        image=idleUp[idleSpriteNum];

        
        if(keyH.directionState=="down")
        image=idleDown[idleSpriteNum];

        break;

    }


    g2.drawImage(image, screenX, screenY,gp.tileSize,gp.tileSize,null);
    

    }
}


