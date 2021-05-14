package GameObject;

import TomAndJerry.GameBoard;

import java.util.Random;

public class GbPositionEvaluator implements gameObjectVisitor<GameObject>{
    // If Tom encounters a game object, nothing happens
    @Override
    public Integer visit(Tom tom) {
        int index;
        short element;

        // Setting available directions for Jerry to move
        // Jerry gets stopped when encountering the walls
        index = ((tom.getX() - 35) / GameBoard.BLOCK_SIZE) +
                (GameBoard.N_BLOCKS * (((tom.getY()-140) / GameBoard.BLOCK_SIZE)));
        element = GameBoard.updatedMap[index];
        //limit cat movement
        boolean left1 = tom.getDx() == -1;
        boolean left2 = (element & 1) != 0 || (GameBoard.updatedMap[index - 1] & 4) != 0;
        boolean left = left1 && left2;

        boolean bottom1 = tom.getDy() == 1;
        boolean bottom2 = (element & 8) != 0 || (GameBoard.updatedMap[index + 15] & 2) != 0;
        boolean bottom = bottom1 && bottom2;

        boolean top1 = tom.getDy() == -1;
        boolean top2 = (element & 2) != 0 || (GameBoard.updatedMap[index - 15] & 8) != 0;
        boolean top = top1 && top2;

        boolean right1 = tom.getDx() == 1;
        boolean right2 = (element & 4) != 0 || (GameBoard.updatedMap[index + 1] & 1) != 0;
        boolean right =  right1 && right2;

        if(left||right||top||bottom){
            randomDirect(tom);
        }
        else {
            tom.move(true);
        }
        return null;
    }

    // Jerry encountering game objects function
    @Override
    public Integer visit(Jerry jerry) {
        // Initializing index and element to 0
        int index;
        short element;

        // Setting available directions for Jerry to move
        // Jerry gets stopped when encountering the walls
        index = ((jerry.getX() - 35) / GameBoard.BLOCK_SIZE) +
                (GameBoard.N_BLOCKS * (((jerry.getY()-140) / GameBoard.BLOCK_SIZE)));
        element = GameBoard.updatedMap[index];


        boolean left1 = jerry.getDx() == -1;
        boolean left2 = (element & 1) != 0 || (GameBoard.updatedMap[index - 1] & 4) != 0;
        boolean left = left1 && left2;

        boolean bottom1 = jerry.getDy() == 1;
        boolean bottom2 = (element & 8) != 0 || (GameBoard.updatedMap[index + 15] & 2) != 0;
        boolean bottom = bottom1 && bottom2;

        boolean top1 = jerry.getDy() == -1;
        boolean top2 = (element & 2) != 0 || (GameBoard.updatedMap[index - 15] & 8) != 0;
        boolean top = top1 && top2;

        boolean right1 = jerry.getDx() == 1;
        boolean right2 = (element & 4) != 0 || (GameBoard.updatedMap[index + 1] & 1) != 0;
        boolean right =  right1 && right2;

        jerry.move(!left && !right && !bottom && !top);

        // If Jerry is at the position of cheese, the cheese will disappear
        // Score is updated by +1g
        if((element & 16) !=0){
            GameBoard.updatedMap[index] = (short)(element & 15);
            GameBoard.score++;
            GameBoard.chessCount--;
            if (GameBoard.chessCount == 0){
                GameBoard.updatedMap[14] -=4;
            }
        }
        if((element & 32)!= 0){
            GameBoard.updatedMap[index] = (short)(element & 31);
            GameBoard.score-=6;
        }

        if((element & 64)!=0){
            GameBoard.freeze =true;
        }
        if((element & 128)!=0){
            GameBoard.updatedMap[index] = (short)(element & 127);
            GameBoard.score+=5;
        }
        if(index == 14 && GameBoard.chessCount ==0){
            GameBoard.end = true;
            GameBoard.victory = true;
        }

        return null;

    }
    private void randomDirect(Tom t){
        Random random = new Random();
        int direct = random.nextInt(2);
        if(direct == 0){
            //setting dx
            int vertical = random.nextInt(2);
            if(vertical==0){
                t.setDx(1);
                t.setDy(0);
            } else {
                t.setDx(-1);
                t.setDy(0);
            }
        }
        else {
            //setting dy
            int horiztal = random.nextInt(2);
            if(horiztal == 0){
                t.setDy(1);
                t.setDx(0);
            }
            else {
                t.setDy(-1);
                t.setDx(0);
            }
        }
    }
}
