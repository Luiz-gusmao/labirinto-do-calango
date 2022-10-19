package main;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import javax.swing.JPanel;

import entity.Player;
import tile.TileManager;


public class GamePanel extends JPanel implements Runnable{

    //screen sttings

    public final int originalTilsize = 23; //  JxJ tile size
    public final int scale = 4;
    public final int tileSize = originalTilsize * scale;
    public final int maxScreenCol = 16;
    public final int maxScreenRow = 10;
    public final int screenWidth = tileSize * maxScreenCol;
    public final int screenHeight = tileSize * maxScreenRow;


    public final int maxWorldCol=50;
    public final int maxWorldRow=50;
    public final int maxWorldWidth=tileSize * maxWorldCol;
    public final int maxWorldHeight=tileSize * maxWorldCol;


    int fps=60;


    //sets player anchors
    TileManager tileM = new TileManager(this);
    KeyHandler keyH = new KeyHandler();
    Thread gameThread;
    public CollisionChecker cCheck= new CollisionChecker(this);
    public Player player = new Player(this,keyH);



    public GamePanel(){
        //set screen
        this.setPreferredSize(new Dimension (screenWidth,screenHeight));
        this.setBackground(Color.black);
        this.setDoubleBuffered(true);
        this.addKeyListener(keyH);
        this.setFocusable(true);
    }
        public void startGameThread(){
            gameThread = new Thread(this);
            gameThread.start();

        }
    @Override
    public void run() {
        double drawInterval= 1000000000/fps;
        double delta=0;
        long lastTime= System.nanoTime();
        long currentTime;

        while (gameThread != null){

            currentTime=System.nanoTime();
            delta+=(currentTime-lastTime)/drawInterval;
            lastTime=currentTime;

            if(delta >= 1 ){
                //update program
                update();
                //Image buffer
                repaint();
                delta--;
            }

        }

        
    }

    public void update(){

        player.update();

    }

    public void paintComponent(Graphics g){

        super.paintComponent(g);
        Graphics2D g2 =(Graphics2D) g;
        tileM.draw(g2);
        player.draw(g2);
        
        g2.dispose();
    }

}