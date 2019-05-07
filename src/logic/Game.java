package logic;

import engine.*;

import java.awt.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Random;

public class Game implements Serializable
{
    protected Player player;
    protected Level level;
    protected int progressBar;
    protected ArrayList<Creature> creatures;
    protected ArrayList<Creature> bots;

    private long tikck = 0;
    private boolean isLevelCompleted = false;


    public Game(Level level)
    {
        this.level = level;
        player = level.getPlayer();
        progressBar = 0;
        creatures = new ArrayList<>(level.getCreatures());
        bots = new ArrayList<>(level.getBots());
        creatures.addAll(bots);
    }

    protected Game(){

    }

    public Player getPlayer()
    {
        return player;
    }

    public Level getLevel()
    {
        return level;
    }

    public int getProgressBar()
    {
        return progressBar;
    }

    private void moveBots()
    {
        Random random = new Random();

        for (Creature bot: bots)
        {
            int changeAnglePossibility = random.nextInt(8);

            if (changeAnglePossibility == 0)
            {
                double angle = random.nextInt(17) * Math.PI / 8;
                bot.turn(angle);
            }

            bot.move(5);
        }

    }


    public void tryToFeedCreatures()
    {
        //TODO decrease complexity with sectors

        var meals = level.getMeals();

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
                            int nutrition = food.destroyPiece(piecePosition);
                            creature.putOnWeight(nutrition);

                            if (creature.IsPlayer)
                            {
                                progressBar += nutrition;
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
            System.out.println("level completed");
            isLevelCompleted = true;
        }

        tikck++;
    }

    public double getPercentCompletion()
    {
        return (double) progressBar / level.getCompletedFattiness();
    }

    public Creature[] getBots() {
        return bots.toArray(new Creature[bots.size()]);
    }

    public boolean isLevelCompleted()
    {
        return isLevelCompleted;
    }

}
