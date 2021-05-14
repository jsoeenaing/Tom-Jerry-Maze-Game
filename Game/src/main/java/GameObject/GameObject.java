package GameObject;

import TomAndJerry.GameBoard;

import java.awt.*;

public interface GameObject {
    // render method is public abstract by default
    public void render(Graphics2D board, Image image, GameBoard gb);
}
