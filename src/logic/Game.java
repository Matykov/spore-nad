package logic;

import java.awt.*;
import java.io.Serializable;

public class Game implements Serializable
{
    protected Player Player;
    protected Level Level;
    private int ProgressBar;

    public Game(Level level)
    {
        Level = level;
        Player = level.getPlayer();
    }

    protected Game(){

    }

    public Player getPlayer()
    {
        return Player;
    }

    public Level getLevel()
    {
        return Level;
    }

    public int getProgressBar()
    {
        return ProgressBar;
    }

    public void tryToFeedCreatures()
    {
        //TODO decrease complexity with sectors

        var creatures = Level.getCreatures();
        var meals = Level.getMeals();

        for (Creature creature: creatures)
        {
            var creaturePos = creature.getPosition();

            for (Food food: meals)
            {
//                if (creaturePos.distance(food.getPosition()) <= food.PiecesRarity + creature.getFattiness())
//                {
                    for (Point piecePosition: food.getPieces().keySet())
                    {
                        if (dist(creaturePos, piecePosition) <= creature.getFattiness() - food.MaxSize)
                        {
                            creature.putOnWeight(food.destroyPiece(piecePosition));
                            return;
                        }
                    }
//                }
            }
        }
    }

    private double dist(Point p1, Point p2)
    {
        return Math.sqrt(Math.pow(p1.x - p2.x, 2) + Math.pow(p1.y - p2.y, 2));
    }


    public void update()
    {
        tryToFeedCreatures();
    }
}
