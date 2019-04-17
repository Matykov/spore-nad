package logic;

import java.awt.*;

public class Game
{
    private Player Player;
    private Level Level;
    private int ProgressBar;

    public Game(Level level)
    {
        Level = level;
        Player = level.getPlayer();
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
                if (creaturePos.distance(food.getPosition()) <= food.PiecesRarity + creature.getFattiness())
                {
                    for (Point piecePosition: food.getPieces().keySet())
                    {
                        if (creaturePos.distance(piecePosition) < creature.getFattiness())
                        {
                            food.destroyPiece(piecePosition);
                            System.out.println("destroyed " + piecePosition.toString());
                            return;
                        }
                    }
                }
            }
        }
    }

    public void update()
    {
        tryToFeedCreatures();
    }
}
