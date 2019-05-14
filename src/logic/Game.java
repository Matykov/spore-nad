package logic;

import engine.*;

import java.awt.*;
import java.io.Serializable;
import java.util.Random;

public class Game implements Serializable
{
    protected Player player;
    protected Level level;
    protected int progressBar;
    protected SectorNet curSectors;

    private long tick = 0;
    private boolean isLevelCompleted = false;


    public Game(Level level)
    {
        this.level = level;
        player = level.getPlayer();
        progressBar = 0;

        curSectors = new SectorNet(level);
    }

    protected Game(){

    }

    public Player getPlayer()
    {
        return player;
    }

    public Level getLevel() {
        return level;
    }

    public void observePlayer()
    {
        var playerPosX = player.getPosition().x + player.getFattiness();
        var playerPosY = player.getPosition().y + player.getFattiness();

        if (playerPosX < 0)
            curSectors.moveFocusLeft(player);
        else if (playerPosX > curSectors.sectorSize.x)
            curSectors.moveFocusRight(player);
        if (playerPosY < 0)
            curSectors.moveFocusUp(player);
        else if (playerPosY > curSectors.sectorSize.y)
            curSectors.moveFocusDown(player);
    }

    public void tryToFeedCreatures()
    {
        Random random = new Random();

        for (var j = 0; j < 3; j++)
        {
            for (var i = 0; i < 3; i++)
            {
                var curSec = curSectors.sectors[j][i];

                for (Creature creature: curSec.getCreatures())
                {

                    var creaturePos = creature.getPosition();
                    //food eating
                    for (Food food : curSec.food)
                    {
                        for (Point piecePosition : food.getPieces().keySet())
                        {
                            if (dist(creaturePos, piecePosition) <= creature.getFattiness() - food.MaxSize)
                            {
                                int nutrition = food.destroyPiece(piecePosition);
                                creature.putOnWeight(nutrition);

                                curSec.removeFood();

                                if (creature instanceof Player)
                                {
                                    progressBar += nutrition;
                                }
                            }
                        }
                    }

                    //creature eating
                    for (Creature preyCreature: curSec.getCreatures())
                    {
                        if (creature.getFattiness() > preyCreature.getFattiness() &&
                                dist(creaturePos, preyCreature.getPosition()) <= creature.getFattiness() - preyCreature.getFattiness())
                        {
                            var nutrition = creature.eat(preyCreature);
                            curSec.removeCreature(preyCreature);

                            if (creature instanceof Player)
                            {
                                progressBar += nutrition;
                            }
                        }
                    }

                    if (creature instanceof Bot && tick == 8000)
                    {
                        var bot = (Bot)creature;
                        int changeAnglePossibility = random.nextInt(8);

                        if (changeAnglePossibility == 0)
                        {
                            double angle = random.nextInt(17) * Math.PI / 8;
                            bot.turn(angle);
                        }

                        bot.move(5);
                        tick = 0;
                    }
                }
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
        observePlayer();

        if (getPercentCompletion() >= 1)
        {
            System.out.println("level completed");
            isLevelCompleted = true;
        }

        tick++;
    }

    public double getPercentCompletion()
    {
        return (double) progressBar / level.getCompletedFattiness();
    }

    public boolean isLevelCompleted()
    {
        return isLevelCompleted;
    }

}
