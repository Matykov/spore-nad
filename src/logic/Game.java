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

//    public void observePlayer()
//    {
//
//    }

    public void observeCreatures()
    {
        for (var j = 0; j < SectorNet.size; j++)
        {
            for (var i = 0; i < SectorNet.size; i++)
            {
                var curSec = curSectors.sectors[j][i];

                for (Creature creature: curSec.getCreatures())
                {

                    creature.absPositon = new Point(creature.getSectorPosition().x + curSec.location.x,
                            creature.getSectorPosition().y + curSec.location.y);

                    //food eating
                    eatFood(curSec, creature);

                    //creature eating
                    eatCreatures(curSec, creature);

                    //move bot
                    if (creature instanceof Bot && tick == 8000)
                    {
                        moveBot(curSec, (Bot)creature);
                    }

                    //observe sector position
                    observeSectorPosition(curSec, creature);

                }
            }
        }
    }

    private void eatCreatures(Sector curSec, Creature creature)
    {
        for (Creature preyCreature: curSec.getCreatures())
        {
            if (creature.getFattiness() > preyCreature.getFattiness() &&
                    dist(creature.getSectorPosition(), preyCreature.getSectorPosition()) <=
                            creature.getFattiness() - preyCreature.getFattiness())
            {
                var nutrition = creature.eat(preyCreature);
                curSec.removeCreature(preyCreature);

                if (creature instanceof Player)
                {
                    progressBar += nutrition;
                }
            }
        }
    }

    private void eatFood(Sector curSec, Creature creature)
    {
        for (Food food : curSec.food)
        {
            for (Point piecePosition : food.getPieces().keySet())
            {
                if (dist(creature.getSectorPosition(), piecePosition) <= creature.getFattiness() - food.MaxSize)
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
    }

    private void moveBot(Sector curSec, Bot bot)
    {
        Random random = new Random();

        int changeAnglePossibility = random.nextInt(8);

        if (changeAnglePossibility == 0)
        {
            double angle = random.nextInt(17) * Math.PI / 8;
            bot.turn(angle);
        }

        bot.move(5);
        tick = 0;
    }

    private void observeSectorPosition(Sector curSec, Creature creature)
    {
        var creaturePosX = creature.getSectorPosition().x;
        var creaturePosY = creature.getSectorPosition().y;

        var isPlayer = curSec.player == creature;

        if (creaturePosX < 0)
        {
            if (isPlayer)
                curSectors.moveFocusLeft(player);
        }
        else if (creaturePosX > curSectors.sectorSize.x)
        {
            if (isPlayer)
                curSectors.moveFocusRight(player);
        }
        if (creaturePosY < 0)
        {
            if (isPlayer)
                curSectors.moveFocusUp(player);
        }
        else if (creaturePosY > curSectors.sectorSize.y)
        {
            if (isPlayer)
                curSectors.moveFocusDown(player);
        }
    }

    private double dist(Point p1, Point p2)
    {
        return Math.sqrt(Math.pow(p1.x - p2.x, 2) + Math.pow(p1.y - p2.y, 2));
    }

    public void update()
    {
        observeCreatures();

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

    public SectorNet getSectorNet()
    {
        return curSectors;
    }

}
