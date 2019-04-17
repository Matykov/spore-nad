package logic;

import java.awt.*;

public class Levels
{
    public static Level getTestLevel()
    {
        Creature[] creatures = { new Player() };
        Point[] points = { new Point(200, 200) };

        return new Level((Player)creatures[0], creatures, points, 5);
    }
}
