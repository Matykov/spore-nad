package logic;

import java.awt.*;

public class Levels
{
    public static Level getTestLevel()
    {
        Creature[] creatures = { new Player(new Point(60, 60), 100, 50, 25) };
        Point[] points = { new Point(200, 200), new Point(200, 100) };

        return new Level((Player)creatures[0], creatures, points, 5);
    }
}
