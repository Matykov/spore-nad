package engine;

import java.awt.*;
import java.io.Serializable;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Random;

public class Game implements Serializable
{
    protected Player Player;
    protected Level Level;
    private int ProgressBar;
    private ArrayList<Creature> Creatures;
    private ArrayList<Creature> Bots;

    private long tikck = 0;


    public Game(Level level)
    {
        Level = level;
        Player = level.getPlayer();
        ProgressBar = 0;
        Creatures = new ArrayList<>(level.getCreatures());
        Bots = new ArrayList<>(level.getBots());
        Creatures.addAll(Bots);
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

    private void moveBots()
    {
        Random random = new Random();

        for (Creature bot: Bots)
        {
            int changeAnglePossibility = random.nextInt(8);

            if (changeAnglePossibility == 0)
            {
                double angle = random.nextInt(17) * Math.PI / 8;
                bot.turn(angle);
            }

            bot.move(5);
            System.out.println(bot.getPosition());
        }

    }


    public void tryToFeedCreatures()
    {
        //TODO decrease complexity with sectors

        var meals = Level.getMeals();

        for (Creature creature: Creatures)
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
                            int nutrition = food.destroyPiece(piecePosition);
                            creature.putOnWeight(nutrition);

                            if (creature.IsPlayer)
                            {
                                ProgressBar += nutrition;
                            }
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
        if (tikck == 80000)
        {
            moveBots();
            tikck = 0;
        }
        tryToFeedCreatures();

        if (getPercentCompletion() >= 1)
        {
            System.out.println("Level completed");
        }

        tikck++;
    }

    public double getPercentCompletion()
    {
        return (double)ProgressBar / Level.getCompletedFattiness();
    }

    public Creature[] getBots() {
        return Bots.toArray(new Creature[Bots.size()]);
    }
}
