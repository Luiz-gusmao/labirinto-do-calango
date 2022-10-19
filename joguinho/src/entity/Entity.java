package entity;
import java.awt.image.BufferedImage;
import java.awt.Rectangle;
public class Entity {
   


    public BufferedImage[]up= new BufferedImage[10] ;
    public BufferedImage[]down=new BufferedImage[10];
    public BufferedImage[]left=new BufferedImage[10];
    public BufferedImage[]right=new BufferedImage[10];
    public BufferedImage[]idleUp= new BufferedImage[3] ;
    public BufferedImage[]idleDown=new BufferedImage[3];
    public BufferedImage[]idleLeft=new BufferedImage[3];
    public BufferedImage[]idleRight=new BufferedImage[3];



    public int worldx,worldy ;
    public int speed;
    public String direction ;
    public String lastDirectionState;
    public int spriteCounter=0;
    public int spriteNum=0;
    public int idlespriteCounter=0;
    public int idleSpriteNum=0;
    public Rectangle solidArea;
    public boolean collisionOn;
}
