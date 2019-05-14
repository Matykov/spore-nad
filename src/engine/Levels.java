package engine;

import logic.NetPlayer;

import java.awt.*;

public class Levels
{
    public static Level getTestLevel()
    {
        Creature[] creatures = { new Player(new Point(200, 200), 100, 50, 25) };
        Point[] points = { new Point(200, 200), new Point(200, 100) };

        return new Level(creatures, points, 5, 19);
    }


    public static Level getTestNetLevel()
    {
        Creature[] creatures = {
                new NetPlayer(new Point(60, 60), 100, 50, 25, 0),
                new NetPlayer(new Point(120, 120), 100, 50, 25, 1),
                new NetPlayer(new Point(120, 60), 100, 50, 25, 2),
                new NetPlayer(new Point(60, 120), 100, 50, 25, 3)
        };
        Point[] points = { new Point(200, 200), new Point(200, 100) };

        return new Level(creatures, points, 5, 40);
    }

    public static Level getBotLevel()
    {
        Creature[] creatures = { new Player(new Point(200, 200), 100, 50, 25),
                    new Bot(new Point(100, 200), 100, 50, 15)};
        Point[] foodPoses = { new Point(200, 200), new Point(200, 100) };

        return new Level(creatures, foodPoses, 5, 19);
    }
}
