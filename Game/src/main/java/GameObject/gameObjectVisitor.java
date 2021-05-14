package GameObject;

public interface gameObjectVisitor<T> {
    // Declaring methods
    Integer visit(Tom tom);
    Integer visit(Jerry jerry);
}
