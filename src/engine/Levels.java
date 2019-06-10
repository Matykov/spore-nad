package engine;

import java.awt.*;

public class Levels {
    public static Level getTestLevel() {
        return new Level(new Player(new Point(200, 200), 100, 50, 25), 50);
    }

    public static Level getNetLevel() {
        return new Level();
    }
}
