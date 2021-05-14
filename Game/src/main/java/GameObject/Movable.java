package GameObject;

public abstract class Movable implements GameObject{
    // Constructors for movement
    public abstract int[] getPosition();
    public abstract void move(boolean canMove);
    abstract public <T> Integer accept(gameObjectVisitor<T> g);
}
