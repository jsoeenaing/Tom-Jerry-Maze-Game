package Controller;

import GameObject.Jerry;
import GameObject.Tom;

// Base factory class
public class Factory {
    public static Factory factory = null;

    // Use ObjectBuilder method to get object of type Factory
    public static Factory ObjectBuilder(){
        if (factory == null) {
            factory = new Factory();
        }
        return factory;
    }

    // Use createJerry method to get object of type Jerry
    public Jerry createJerry(){
        if (Jerry.jerry==null) {
            Jerry.jerry = new Jerry(0, 630);
        }
        return Jerry.jerry;
    }

    // Use createTom method to get object of type Tom
    public Tom createTom(int x, int y){
        return new Tom(x, y);
    }
}
