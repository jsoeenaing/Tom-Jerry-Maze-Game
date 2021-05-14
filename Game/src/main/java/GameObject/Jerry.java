package GameObject;

import TomAndJerry.GameBoard;

import java.awt.*;
import java.util.Random;

public class Jerry extends Movable{
    // Instantiating array for position of Jerry
    private int[] pos;
    public static Jerry jerry = null;
    private int dx =0;
    private int dy=0;
    public Jerry(int x, int y){
        pos = new int[2];
        pos[0] = x;
        pos[1] = y;
    }
    @Override
    // Renders the position of Jerry on game board
    public void render(Graphics2D board, Image jerryImage, GameBoard gb) {
        board.drawImage(jerryImage, pos[0],pos[1],gb);
    }

    @Override
    // Returns the position of Jerry
    public int[] getPosition() {
        return pos;
    }

    // Returns the position-x of Jerry
    public int getX(){
        return pos[0];
    }

    // Returns position-y of Jerry
    public int getY(){
        return pos[1];
    }

    // Setting position-x of Jerry
    public void setX(int dx){
        this.dx = dx;
    }

    // Setting position-y of Jerry
    public void setY(int dy){
        this.dy = dy;
    }

    // Returns the set position-x of Jerry
    public int getDx(){
        return this.dx;
    }

    // Returns the set position-y of Jerry
    public int getDy(){
        return this.dy;
    }
    @Override

    // Moves Jerry by a factor of 5 if path is available
    public void move(boolean canMove) {
        if (canMove){
            pos[0]+=dx * 35;
            pos[1]+=dy * 35;
        }
    }

    @Override
    // Allows Jerry to visit game objects
    public <T> Integer accept(gameObjectVisitor<T> g){
        return g.visit(this);
    }
}
