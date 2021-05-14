package TomAndJerry;

import Controller.Factory;
import GameObject.*;
import GameObject.Jerry;
import GameObject.Tom;
import java.util.Random;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class GameBoard extends JPanel implements ActionListener {
    // Declaring variables
    private Image tom, blackCat,jerry,misty, cheese, trap, mud,background,raspberry,title, flag;
    private Jerry jerryObj;
    private Tom tomObj;
    private Tom blackCatObj;
    private Tom mistyObj;
    private Graphics2D g2d;
    public static int chessCount = 91;
    public static boolean ingame;
    public static boolean freeze;
    private int freezeStart;
    public static boolean end = false;
    public static final int BLOCK_SIZE = 35;
    public static final int N_BLOCKS = 15;
    public static boolean victory = false;
    private boolean lose = false;
    private final int SCREEN_SIZE = N_BLOCKS * BLOCK_SIZE;
    private Timer timer;
    public static int score = 0;
    private GbPositionEvaluator g = new GbPositionEvaluator();
    public static int berry = 0;
    public static int lastberry = 0;
    public int elapsedTime = 0;
    public int seconds = 0;
    public int minutes = 0;


    // 0 means white block, 1 means left border, 2 means top border, 4 means right border
    // 8 means bottom border, 16 means cheese, 32 means mud, 64 means trap, 128 means raspberry

    // This is an updated map used after some elements are gone
    public static short updatedMap[] = {
            35, 26, 26, 6, 2, 2, 2, 2, 2, 6, 34, 10, 2, 10, 6,
            21, 1, 518, 4, 8, 8, 8, 8, 8, 12, 4, 16, 16, 516, 20,
            5, 9, 4, 16, 8, 16, 36, 16, 16, 8, 4, 24, 16, 12, 20,
            1, 0, 16, 4, 16, 16, 24, 8, 16, 4, 16, 4, 68, 8, 12,
            39, 4, 16, 4, 16, 0, 0, 4, 16, 4, 16, 4, 16, 8, 4,
            21, 4, 16, 12, 16, 20, 24, 4, 16, 4, 8, 4, 20, 20, 4,
            21, 20, 16, 16, 16, 16, 8, 8, 16, 16, 16, 4, 20, 4, 4,
            17, 4, 8, 8, 20, 4, 16, 20, 4, 16, 8, 12, 16, 16, 4,
            17, 16, 16, 8, 20, 256, 0, 256, 36, 16, 8, 24, 16, 16, 4,
            21, 4, 3, 16, 4, 256, 16, 256, 4, 16, 16, 20, 4, 20, 4,
            5, 4, 33, 16, 20, 4, 8, 524, 4, 16, 16, 4, 20, 12, 4,
            17, 4, 25, 8, 24, 8, 24, 8, 24, 8, 24, 20, 16, 16, 4,
            1, 16, 16, 24, 8, 24, 8, 24, 4, 4, 16, 4, 16, 16, 4,
            1, 4, 3, 0, 0, 0, 0, 4, 64, 4, 16, 4, 32, 16, 4,
            8, 12, 9, 8, 8, 8, 8, 12, 8, 8, 8, 28, 24, 8, 28
    };

    // Creating a new board
    public GameBoard(){
        gameInitialized();
    }

    // Initializing a new board
    public void gameInitialized(){
        loadImages();
        rescaleImage();
        initialObject();
        addKeyListener(new TAdapter(jerryObj));
        setFocusable(true);
    }

    // Creating new game objects
    private void initialObject(){
        Factory factory = Factory.ObjectBuilder();
        timer = new Timer(80, this);
        timer.start();
        ingame = false;
        jerryObj = factory.createJerry();
        tomObj = factory.createTom(140,175);
        tomObj.setDx(0);
        tomObj.setDy(1);
        blackCatObj = factory.createTom(350,210);
        blackCatObj.setDx(1);
        blackCatObj.setDy(0);
        mistyObj = factory.createTom(455,455);
        mistyObj.setDx(0);
        mistyObj.setDy(-1);
    }

    // Assigning values to game objects
    private void drawBoard(Graphics2D board){
        short i =0;
        for (int y = 140; y<SCREEN_SIZE+140; y+=BLOCK_SIZE){
            for(int x =35; x < SCREEN_SIZE+35; x+=BLOCK_SIZE){
                // Setting color of the board
                board.setColor(new Color(255,255,255));
                board.setStroke(new BasicStroke(5));
                // Drawing cheese
                if ((updatedMap[i]&16) != 0){
                    board.drawImage(cheese,x+5,y+5,this);
                }
                // Drawing left wall
                if ((updatedMap[i]&1)!= 0){
                    board.drawLine(x, y, x, y + BLOCK_SIZE - 1);
                }
                // Drawing up wall
                if ((updatedMap[i]&2) !=0){
                    board.drawLine(x, y ,x+BLOCK_SIZE-1,y);
                }
                // Drawing right wall
                if ((updatedMap[i]&4)!= 0){
                    board.drawLine(x + BLOCK_SIZE - 1, y, x + BLOCK_SIZE - 1,
                            y + BLOCK_SIZE - 1);
                }
                // Drawing bottom wall
                if ((updatedMap[i]&8)!= 0){
                    board.drawLine(x,y + BLOCK_SIZE - 1, x + BLOCK_SIZE - 1,
                            y + BLOCK_SIZE - 1);
                }
                // Drawing mud puddle
                if ((updatedMap[i]&32)!= 0){
                    board.drawImage(mud,x+5,y+5,this);
                }
                // Drawing trap
                if ((updatedMap[i]&64)!=0){
                    board.drawImage(trap,x+5,y+5,this);
                }
                // Drawing raspberry
                if((updatedMap[i]&128)!=0){
                    board.drawImage(raspberry, x+5, y+5, this);
                }
                i++;
            }
        }
    }

    // Loading images of all the game objects
    private void loadImages(){
        tom = new ImageIcon("src/Mockup_Elements/tom.png").getImage();
        blackCat = new ImageIcon("src/Mockup_Elements/black_cat.png").getImage();
        jerry = new ImageIcon("src/Mockup_Elements/jerry.png").getImage();
        misty = new ImageIcon("src/Mockup_Elements/misty.png").getImage();
        trap = new ImageIcon("src/Mockup_Elements/trap.png").getImage();
        mud = new ImageIcon("src/Mockup_Elements/mud.png").getImage();
        background = new ImageIcon("src/Mockup_Elements/background.png").getImage();
        cheese = new ImageIcon("src/Mockup_Elements/cheese.png").getImage();
        raspberry = new ImageIcon("src/Mockup_Elements/raspberry.png").getImage();
        title = new ImageIcon("src/Mockup_Elements/tom&jerry.png").getImage();
        flag = new ImageIcon("src/Mockup_Elements/flag.png").getImage();
    }

    // Rescaling/Resizing the game objects to match the screen size
    private void rescaleImage(){
        background = background.getScaledInstance(525,525,Image.SCALE_DEFAULT);
        cheese = cheese.getScaledInstance(25,25, Image.SCALE_DEFAULT);
        tom = tom.getScaledInstance(30,30,Image.SCALE_DEFAULT);
        jerry = jerry.getScaledInstance(25,25,Image.SCALE_DEFAULT);
        misty = misty.getScaledInstance(30,30,Image.SCALE_DEFAULT);
        blackCat = blackCat.getScaledInstance(30,30,Image.SCALE_DEFAULT);
        mud = mud.getScaledInstance(25,25,Image.SCALE_DEFAULT);
        trap = trap.getScaledInstance(25,25,Image.SCALE_DEFAULT);
        title = title.getScaledInstance(150,50, Image.SCALE_DEFAULT);
        raspberry = raspberry.getScaledInstance(25,25,Image.SCALE_DEFAULT);
        flag = flag.getScaledInstance(50,50,Image.SCALE_DEFAULT);

    }

    // Rendering characters in the game
    public void playGame(Graphics2D gameBoard){
        if (!freeze){
            jerryObj.accept(g);
        }
        tomObj.accept(g);
        blackCatObj.accept(g);
        mistyObj.accept(g);
        if (score <0){
            end = true;
            lose = true;
        }

    }
    // Setting the score and displaying on top left of the screen
    public void drawScore(Graphics2D g2d){
        String s;
        final Font font = new Font("Helvetica", Font.BOLD, 25);
        g2d.setFont(font);
        g2d.setColor(new Color(255, 0, 0));
        s = "Score: " + score;
        g2d.drawString(s, 50, 50);
    }

    public void drawTimer(Graphics2D g2d){
        String t;
        final Font font = new Font("Helvetica", Font.BOLD, 25);
        g2d.setFont(font);
        g2d.setColor(new Color(255, 0, 0));
        t = "Timer: " + String.format("%02d", minutes) + ":" + String.format("%02d", seconds);
        g2d.drawString(t, 400, 50);
    }

    public void randomBerry(){
        Random rand = new Random();
        int nextBerry = rand.nextInt(225);
        if (lastberry != 0){
            updatedMap[lastberry]-=128;
        }
        updatedMap[nextBerry]+=128;
        lastberry = nextBerry;
    }

    @Override
    // Frame to display the board, score, game
    public void paintComponent(Graphics g){
        //TODO:update every frame
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        g2d.setColor(new Color(0,0,0));
        g2d.fillRect(0,0,800,900);
        g2d.drawImage(background,35,140,this);
        g2d.drawImage(title, 230,20,this);
        g2d.drawImage(flag,35+525, 140,this);
        drawBoard(g2d);
        drawScore(g2d);
        drawTimer(g2d);

        if (ingame){
            playGame(g2d);
        }
        else if(jerryObj.getDx() == 1){
            jerryObj.move(true);
            jerryObj.setX(0);
            ingame = true;
        }
        if(berry ==20){
            randomBerry();
            berry = 0;
        }
        jerryObj.render(g2d, jerry, this);
        tomObj.render(g2d, tom, this);
        blackCatObj.render(g2d, blackCat, this);
        mistyObj.render(g2d, misty, this);
        boolean tomBool = tomObj.getX()== jerryObj.getX() && tomObj.getY() == jerryObj.getY();
        boolean blackBool = blackCatObj.getX()== jerryObj.getX() && blackCatObj.getY() == jerryObj.getY();
        boolean mistBool = mistyObj.getX()== jerryObj.getX() && mistyObj.getY() == jerryObj.getY();
        if (tomBool || blackBool ||mistBool){
            end = true;
            lose = true;
        }

        if(end){
            Font font = new Font("Helvetica", Font.BOLD, 60);
            String s = "";
            if (victory){
                s = "Victory!!!!!";
            } else if (lose){
                s = "Defeated!!!!!";
            }
            g.setColor(new Color(0,0,0));
            g.fillRect(100,300, 400, 150);
            g.setColor(new Color(255,0,0));
            g.setFont(font);
            g.drawString(s, 100,400);
            timer.stop();
        }
        Toolkit.getDefaultToolkit().sync();
        g2d.dispose();
    }

    // Repaints part of the screen where action is performed
    @Override
    public void actionPerformed(ActionEvent e) {
        elapsedTime += 80;
        minutes = (elapsedTime/60000) % 60;
        seconds = (elapsedTime/1000) % 60;
        if (freeze && freezeStart ==0){
            freezeStart = seconds;

        }
        if (freeze && (seconds >= freezeStart +3)){
            freeze = false;
            freezeStart =0;
        }
        repaint();

    }


    // Action Listener for Jerry
    class TAdapter extends KeyAdapter {
        private Jerry jerry;
        public TAdapter(Jerry jerry){
            this.jerry = jerry;
        }

        // Handles user key presses
        @Override
        public void keyPressed(KeyEvent e){
            int key = e.getKeyCode();
            // Move Jerry to the left by x position -1 if left arrow key is pressed
            if (key == KeyEvent.VK_LEFT) {
                jerry.setX(-1);
                jerry.setY(0);
            }
            // Move Jerry to the right by x position 1 if right arrow key is pressed
            else if (key == KeyEvent.VK_RIGHT) {
                jerry.setX(1);
                jerry.setY(0);
            }
            // Move Jerry up by y position -1 if up arrow key is pressed
            else if (key == KeyEvent.VK_UP) {
                jerry.setX(0);
                jerry.setY(-1);
            }
            // Move Jerry down by y position 1 if down arrow key is pressed
            else if (key == KeyEvent.VK_DOWN) {
                jerry.setX(0);
                jerry.setY(1);
            }
            // Pressing Esc will make Jerry disappear
            else if (key == KeyEvent.VK_ESCAPE) {
                GameBoard.ingame = false;
            }
            berry ++;
        }
    }
}
