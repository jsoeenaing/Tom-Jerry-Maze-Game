package GameObject;

import TomAndJerry.GameBoard;

import java.awt.*;
import java.util.Random;

public class Tom extends Movable{
    private int[] pos;
    private int dy;
    private int dx;
    public Tom(int x, int y){
        pos = new int[2];
        pos[0] = x;
        pos[1] = y;
    }

    @Override
    // Renders the position of Tom on game board
    public void render(Graphics2D board, Image tomImage, GameBoard gb) {
        board.drawImage(tomImage, pos[0],pos[1],gb);
    }

    @Override
    // Returns the position of Tom
    public int[] getPosition() {
        return pos;
    }

    // Returns the position-x of Tom
    public int getX(){
        return pos[0];
    }

    // Returns the position-y of Tom
    public int getY(){
        return pos[1];
    }

    public int getDx(){return dx;}

    public int getDy(){return dy;}

    public void setDx(int dx){this.dx = dx;}

    public void setDy(int dy){this.dy = dy;}

    @Override
    public void move(boolean canMove) {
        if (canMove){
            pos[0]+=dx * 35;
            pos[1]+=dy * 35;
        }

    }

    @Override
    // Allows Tom to visit game objects
    public <T> Integer accept(gameObjectVisitor<T> g){
        return g.visit(this);
    }
}
