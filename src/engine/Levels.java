package engine;

import java.awt.*;

public class Levels
{
    public static Level getTestLevel()
    {
        return new Level(new Player(new Point(200, 200), 100, 50, 25), 50);
    }
    public static  Level getNetLevel()
    {
        return new Level();
    }


//    public static Level getTestNetLevel()
//    {
//        Creature[] creatures = {
//                new NetPlayer(new Point(60, 60), 100, 50, 25, 0),
//                new NetPlayer(new Point(120, 120), 100, 50, 25, 1),
//                new NetPlayer(new Point(120, 60), 100, 50, 25, 2),
//                new NetPlayer(new Point(60, 120), 100, 50, 25, 3)
//        };
//        Point[] points = { new Point(200, 200), new Point(200, 100) };
//
//        return new Level(creatures, points, 5, 40);
//    }
}
